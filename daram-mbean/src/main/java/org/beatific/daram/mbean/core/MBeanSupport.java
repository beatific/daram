package org.beatific.daram.mbean.core;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.management.Descriptor;
import javax.management.IntrospectionException;
import javax.management.JMException;
import javax.management.modelmbean.DescriptorSupport;
import javax.management.modelmbean.InvalidTargetObjectTypeException;
import javax.management.modelmbean.ModelMBean;
import javax.management.modelmbean.ModelMBeanAttributeInfo;
import javax.management.modelmbean.ModelMBeanInfo;
import javax.management.modelmbean.ModelMBeanInfoSupport;
import javax.management.modelmbean.ModelMBeanOperationInfo;
import javax.management.modelmbean.RequiredModelMBean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MBeanSupport {

	private static Log logger = LogFactory.getLog(MBeanSupport.class);

	public static ModelMBean makeModelMBean(Object resource)
			throws JMException, InvalidTargetObjectTypeException {

		final Method[] methods = resource.getClass().getMethods();

		final List<Method> operations = new ArrayList<Method>();
		final List<Method> getters = new ArrayList<Method>();
		final Map<String, Method> setters = new LinkedHashMap<String, Method>();

		for (Method method : methods) {

			if (method.getDeclaringClass().equals(Object.class))
				continue;

			if (method.getName().startsWith("get")
					&& !method.getName().equals("get")
					&& !method.getName().equals("getClass")
					&& method.getParameterTypes().length == 0
					&& method.getReturnType() != void.class) {
				getters.add(method);
			}
			if (method.getName().startsWith("set")
					&& !method.getName().equals("set")
					&& method.getParameterTypes().length == 1
					&& method.getReturnType().equals(void.class)) {
				setters.put(method.getName(), method);
			}

			operations.add(method);
		}

		final List<ModelMBeanAttributeInfo> attrinfo = new ArrayList<ModelMBeanAttributeInfo>();

		for (Method getter : getters) {

			final String attrName = getter.getName().substring(3);
			final String setterMethod = "set" + attrName;

			Method setter = setters.remove(setterMethod);

			if (setter != null) {
				if (!getter.getReturnType().equals(
						setter.getParameterTypes()[0])) {
					logger.error("Warning: setter "
							+ setter.getName()
							+ " doesn't have the expected type: setter ignored.");
					setter = null;
				}
			}

			attrinfo.add(makeAttribute(getter, setter));
		}

		for (Method setter : setters.values()) {
			logger.error("Warning: setter " + setter.getName()
					+ " has no corresponding getter!");
			attrinfo.add(makeAttribute(null, setter));
		}

		final ModelMBeanAttributeInfo[] attrs = attrinfo
				.toArray(new ModelMBeanAttributeInfo[attrinfo.size()]);

		final int opcount = operations.size();
		final ModelMBeanOperationInfo[] ops = new ModelMBeanOperationInfo[opcount];
		for (int i = 0; i < opcount; i++) {
			final Method m = operations.get(i);
			ops[i] = new ModelMBeanOperationInfo(m.getName(), m);
		}

		ModelMBeanInfo mmbi = new ModelMBeanInfoSupport(resource.getClass()
				.getName(), resource.getClass().getName(), attrs, null, ops,
				null);
		ModelMBean mmb = new RequiredModelMBean(mmbi);
		mmb.setManagedResource(resource, "ObjectReference");
		return mmb;
	}

	private static ModelMBeanAttributeInfo makeAttribute(Method getter,
			Method setter) throws IntrospectionException {
		final String attrName;
		if (getter != null)
			attrName = getter.getName().substring(3);
		else
			attrName = setter.getName().substring(3);

		final List<String> descriptors = new ArrayList<String>();
		descriptors.add("name=" + attrName);
		descriptors.add("descriptorType=attribute");
		if (getter != null) {
			descriptors.add("getMethod=" + getter.getName());
		}
		if (setter != null) {
			descriptors.add("setMethod=" + setter.getName());
		}

		final Descriptor attrD = new DescriptorSupport(
				descriptors.toArray(new String[descriptors.size()]));

		return new ModelMBeanAttributeInfo(attrName, attrName, getter, setter,
				attrD);
	}
}
