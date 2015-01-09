package org.beatific.daram.element;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.rmi.ssl.SslRMIClientSocketFactory;

import org.beatific.daram.context.Context;
import org.beatific.daram.xml.Element;
import org.w3c.dom.Node;

public class Connection extends Element {

	private String url;
	private String username;
	private String password;
	private String ssl;

	public Connection(final Node node, final Element parentElement) {
		super(parentElement);

		url = getNodeAttribute(node, "url");
		username = getNodeAttribute(node, "username");
		password = getNodeAttribute(node, "password");
		ssl = getNodeAttribute(node, "ssl", Boolean.FALSE.toString());
	}

	@Override
	public void process(final Context context) {
		JMXConnector jmxConnector = null;
		try {
			final JMXServiceURL jmxServiceURL = new JMXServiceURL(getValue(context, url));
			final Map<String, Object> jmxEnv = new HashMap<String, Object>();

			if (username != null && password != null) {
				jmxEnv.put(JMXConnector.CREDENTIALS, new String[] { getValue(context, username), getValue(context, password) });
			}

			if (Boolean.parseBoolean(getValue(context, ssl))) {
				SslRMIClientSocketFactory csf = new SslRMIClientSocketFactory();
				jmxEnv.put("com.sun.jndi.rmi.factory.socket", csf);
			}

			jmxConnector = JMXConnectorFactory.connect(jmxServiceURL, jmxEnv);

			context.setConnection(jmxConnector.getMBeanServerConnection());

		} catch (Exception e) {}

		try {
			super.process(context);

		} finally {
			if (jmxConnector != null) try { jmxConnector.close(); } catch (IOException e) {}
		}
	}

}
