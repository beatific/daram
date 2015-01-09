package org.beatific.daram.xml;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import org.beatific.daram.context.Context;
import org.beatific.daram.pattern.REMatchers;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class Element {

	private transient final Element parentElement;
	private transient final List<Element> childElements = new ArrayList<Element>();

	protected Element(final Element parentElement) {
		this.parentElement = parentElement;
	}

	public void process(final Context context) {
		for (Element element : childElements) {
			element.process(context);
		}
	}

	protected Element getParentElement() {
		return parentElement;
	}

	protected List<Element> getChildElements() {
		return childElements;
	}

	protected void addChildElement(final Element childElement) {
		this.childElements.add(childElement);
	}

    protected String getValues(final Context context, final String source) {
		
		final Matcher matcher = REMatchers.innerBracesMatcher(source);
		final StringBuffer buffer = new StringBuffer();
		
	    while (matcher.find()) {
	      String attribute = matcher.group(1);
	      Matcher methodMatcher = REMatchers.methodMatcher(attribute);
	      String method;
	      String[] args;
	      
	      Object result = null;
	      
	      if (methodMatcher.find()) {
	    	  attribute = methodMatcher.group(1);
	    	  method = methodMatcher.group(2);
	    	  args = methodMatcher.group(3).split(",");
	    	  
	    	  Object object = context.getAttribute(attribute);
	    	  result = invoke(object, method, TransferObjectType(args));
	      } else {
	          result = context.getAttribute(attribute);
	      }
	      
	      matcher.appendReplacement(buffer, String.valueOf(result));
	    }
	    matcher.appendTail(buffer);
	    
	    return buffer.toString();
	}
	
	protected Object invoke(Object object, String methodName, Object... args) {
		Class<?> clazz = object.getClass();
		Method method = null;
		Object result = null;
		
		try {
			method = clazz.getMethod(methodName, getObjectTypeList(args));
			result = method.invoke(object, args);
		} catch (Exception e) {}
		
		return result;
	}
	
	private Class<?>[] getObjectTypeList(Object[] args) {
		Class<?>[] classes = new Class<?>[args.length];
		int index = 0;
		for(Object arg : args) {
			classes[index++] = arg.getClass();
		}
		return classes;
	}
	
	private Object[] TransferObjectType(String[] args) {
		Object[] objects = new Object[args.length];
		int index = 0;
		
		for(String arg : args) {
			
			arg = arg.trim();
			
			Matcher numericMatcher = REMatchers.numericMatcher(arg);
			Matcher decimalMatcher = REMatchers.decimalMatcher(arg);
			Matcher letterMatcher = REMatchers.letterMatcher(arg);
			
			if(numericMatcher.find()) {
				objects[index++] = Long.parseLong(arg);
				continue;
			} else if(decimalMatcher.find()) {
				objects[index++] = Double.parseDouble(arg);
				continue;
			} else if(letterMatcher.find()) {
				objects[index++] = letterMatcher.group(1);
				continue;
			} else {
				objects[index++] = arg;
				continue;
			}
		}
		return objects;
	}

	protected String replaceWithVars(final String source) {
		final Matcher matcher = REMatchers.innerBracesMatcher(source);

		final StringBuffer buffer = new StringBuffer();
		while (matcher.find()) {
			final String varName = matcher.group(1);
			final Object result = System.getProperty(varName);
			if (result != null) {
				matcher.appendReplacement(buffer, String.valueOf(result));
			}
		}
		matcher.appendTail(buffer);

		return buffer.toString();
	}

	protected String getNodeAttribute(final Node node, final String attribute,
			final String defaultValue) {
		final NamedNodeMap serverNodeAttributes = node.getAttributes();
		final Node attributeNode = serverNodeAttributes.getNamedItem(attribute);

		String returnValue;

		if (attributeNode == null) {
			returnValue = defaultValue;
		} else {
			returnValue = attributeNode.getNodeValue();
		}

		if (returnValue != null) {
			returnValue = replaceWithVars(returnValue);
		}

		return returnValue;
	}

	protected String getNodeAttribute(final Node node, final String attribute) {
		return getNodeAttribute(node, attribute, null);
	}
}
