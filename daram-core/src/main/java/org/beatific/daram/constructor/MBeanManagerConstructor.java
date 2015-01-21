package org.beatific.daram.constructor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.rmi.ssl.SslRMIClientSocketFactory;

import org.beatific.daram.mbean.MBean;
import org.beatific.daram.mbean.MBeanManager;
import org.beatific.ddirori.bean.BeanDefinition;
import org.beatific.ddirori.bean.Constructor;
import org.beatific.ddirori.bean.annotation.Action;
import org.beatific.ddirori.type.TagType;

@Action(tag="connection", type=TagType.BEAN)
public class MBeanManagerConstructor implements Constructor<MBeanManager>{

	@Override
	public MBeanManager create(BeanDefinition definition) {
		
		String basePackage = (String)definition.parent().attributes().get("basePackage");
		
		MBeanManager manager = new MBeanManager(basePackage);
		String url = (String)definition.attributes().get("url");
		String username = (String)definition.attributes().get("username");
		String password = (String)definition.attributes().get("password");
		String ssl = definition.attributes().get("ssl") == null ? Boolean.FALSE.toString() : (String)definition.attributes().get("ssl");
		
		manager.setConnection(getConnection(url, username, password, ssl));
		
		List<MBean> mbeans = new ArrayList<MBean>();
		
		for(BeanDefinition child : definition.children()) {
			if("mbean".equals(child.getTagName()))
				mbeans.add(getMBean(child));
			
		}
		
		manager.setMbeans(mbeans);
		
		
		return null;
	}
	
	private MBean getMBean(BeanDefinition definition) {
		MBean mbean = new MBean();

		
		Map<String, Object> map = definition.attributes();
		mbean.setObjectName((String)map.get("objectName"));
		
		for(BeanDefinition child : definition.children()) {
			if("attribute".equals(child.getTagName())) {
				Map<String, Object> childAttributes = child.attributes();
				mbean.setAttribute((String)childAttributes.get("var"), (String)childAttributes.get("name"));
			}
		}
		return mbean;
	}

	private MBeanServerConnection getConnection(String url, String username, String password, String ssl) {
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
			throw new GetConnectionException(e);
		} finally {
			if (jmxConnector != null) try { jmxConnector.close(); } catch (IOException e) {}
		}
		
	}
}
