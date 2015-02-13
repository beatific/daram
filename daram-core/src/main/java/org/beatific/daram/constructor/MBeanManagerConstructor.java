package org.beatific.daram.constructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.beatific.daram.mbean.MBean;
import org.beatific.daram.mbean.MBeanConnection;
import org.beatific.daram.mbean.MBeanManager;
import org.beatific.ddirori.bean.BeanDefinition;
import org.beatific.ddirori.bean.Constructor;
import org.beatific.ddirori.bean.annotation.Action;
import org.beatific.ddirori.type.TagType;

@Action(tag="connection", type=TagType.ATTRIBUTE)
public class MBeanManagerConstructor implements Constructor<MBeanManager>{

	@Override
	public MBeanManager create(BeanDefinition definition) {
		
		String basePackage = (String)definition.attributes().get("basePackage");
		
		MBeanManager.setBasePacket(basePackage);
		String id = (String)definition.attributes().get("id");
		String url = (String)definition.attributes().get("url");
		String username = (String)definition.attributes().get("username");
		String password = (String)definition.attributes().get("password");
		String ssl = definition.attributes().get("ssl") == null ? Boolean.FALSE.toString() : (String)definition.attributes().get("ssl");
		
		MBeanConnection connection = new MBeanConnection();
		connection.setUrl(url);
		connection.setUsername(username);
		connection.setPassword(password);
		connection.setSsl(ssl);
		connection.setId(id);
		
		List<MBean> mbeans = new ArrayList<MBean>();
		
		for(BeanDefinition child : definition.children()) {
			if("mbean".equals(child.getTagName()))
				mbeans.add(getMBean(child));
		}
		
		connection.setMbeans(mbeans);
		MBeanManager.addConnection(connection);
		
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

}
