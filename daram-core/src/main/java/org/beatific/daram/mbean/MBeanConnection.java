package org.beatific.daram.mbean;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.rmi.ssl.SslRMIClientSocketFactory;

import org.beatific.daram.constructor.GetConnectionException;

public class MBeanConnection {
	
	private String url;
	private String username;
	private String password;
	private String ssl;
	
	private MBeanServerConnection connection;
	private List<MBean> mbeans;
	
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
	
	private MBeanServerConnection getConnection() {
		if(this.connection == null) this.connection = connect();
		return this.connection;
	}
	
	private MBeanServerConnection connect() {
		JMXConnector jmxConnector = null;
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
			if (jmxConnector != null) try { jmxConnector.close(); } catch (IOException e) {}
		}
		
	}
	
	public Map<String, Object> reload() {
		
		Map<String, Object> beans = new HashMap<String, Object>();
		if(getConnection() == null) return null;
		for(MBean mbean : mbeans)  {
			beans.putAll(mbean.loadMBean(getConnection()));
		}
		return beans;
	}
}
