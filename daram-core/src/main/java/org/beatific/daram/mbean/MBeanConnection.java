package org.beatific.daram.mbean;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.rmi.ssl.SslRMIClientSocketFactory;

public class MBeanConnection {
	
	private String url;
	private String username;
	private String password;
	private String ssl;
	private String id;
	
	private MBeanServerConnection connection;
	private List<MBean> mbeans;
	private JMXConnector jmxConnector;
	private VmidHolder holder;
	
	public MBeanConnection(VmidHolder holder) {
	    	this.holder = holder;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSsl(String ssl) {
		this.ssl = ssl;
	}

	public void setConnection(MBeanServerConnection connection) {
		this.connection = connection;
	}

	public void setMbeans(List<MBean> mbeans) {
		this.mbeans = mbeans;
	}
	
	public Object getAttribute(ObjectName name, String attribute) throws AttributeNotFoundException, InstanceNotFoundException, MBeanException, ReflectionException, IOException {
		try {
			return getConnection().getAttribute(name, attribute);
		} catch (IOException e) {
			disconnect();
			throw e;
		} 
	}
	
	private MBeanServerConnection getConnection() {
		if(this.connection == null) this.connection = connect();
		return this.connection;
	}
	
	private MBeanServerConnection connect() {
		
		try {
			final JMXServiceURL jmxServiceURL = new JMXServiceURL(url);
			final Map<String, Object> jmxEnv = new HashMap<String, Object>();

			if (username != null && password != null) {
				jmxEnv.put(JMXConnector.CREDENTIALS, new String[] {username, password});
			}

			if (Boolean.parseBoolean(ssl)) {
				SslRMIClientSocketFactory csf = new SslRMIClientSocketFactory();
				jmxEnv.put("com.sun.jndi.rmi.factory.socket", csf);
			}

			jmxConnector = JMXConnectorFactory.connect(jmxServiceURL, jmxEnv);

			return jmxConnector.getMBeanServerConnection();

		} catch (Exception e) {
			return null;
		} finally {
			
			System.out.println("jmxConnector" + jmxConnector);
			if(jmxConnector != null) {
				
				try {
					Object id = this.getAttribute(new ObjectName("java.lang:type=Runtime"), "Name");
					
					System.out.println("id[" + id + "]");
					
					if(id instanceof String)this.holder.hold(this.id, ((String) id).substring(0, ((String) id).indexOf("@")));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public void disconnect() {

		if (jmxConnector != null) try { jmxConnector.close(); } catch (IOException e) {}
		connection = null;

	}
	
	public Map<String, Object> reload() {
		
		Map<String, Object> beans = new HashMap<String, Object>();
		if(getConnection() == null) return null;
		for(MBean mbean : mbeans)  {
			beans.putAll(mbean.loadMBean(this));
		}
		return beans;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
}
