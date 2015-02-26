package org.beatific.daram.mbean;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import org.beatific.ddirori.bean.BeanContainer;

public class MBeanContainer extends BeanContainer {

	Map<String, Object> registry = new HashMap<String, Object>();
	
	@Override
	protected void registerBean(String beanName, Object bean) {
		registry.put(beanName, bean);
		
	}

	@Override
	protected void registerTemp(String tempName, Object temp) {
		
	}

	@Override
	protected Object getTemp(String tempName) {
		return null;
	}

	@Override
	protected void clearBean() {
		registry.clear();
		
	}

	@Override
	protected void clearTemp() {
		
	}

	@Override
	public Object getBean(String beanName) {
		return registry.get(beanName);
	}

	@Override
	protected Object findBean(Class<?> clazz) {
		return null;
	}

	@Override
	public Map<String, Object> getBeanWithAnnotation(
			Class<? extends Annotation> annotationClass) {
		return null;
	}

}
