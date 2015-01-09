package org.beatific.daram.xml;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.beatific.daram.constant.Constants;
import org.beatific.daram.context.Context;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ElementBuilder {

	private Properties mapping = new Properties();

	public ElementBuilder() {
		try {
			mapping.load(ElementBuilder.class
					.getResourceAsStream("/mapping.properties"));
		} catch (IOException e) {
			throw new RuntimeException("Not Exists File(mapping.properties)");
		}
	}

	public Element build(final Context context) {

		final String xmlFileName = context
				.getConfigValue(Constants.CONFIG_FILENAME);
		final File xmlFile = new File(xmlFileName);

		if (!xmlFile.exists() || !xmlFile.canRead()) {
			throw new RuntimeException(
					"Error: Can not read configuration file: "
							+ xmlFile.getAbsolutePath());
		}

		final DocumentBuilderFactory builderFactory = DocumentBuilderFactory
				.newInstance();
		builderFactory.setNamespaceAware(true);
		builderFactory.setValidating(false);
		builderFactory.setAttribute(
				"http://java.sun.com/xml/jaxp/properties/schemaLanguage",
				"http://www.w3.org/2001/XMLSchema");

		try {
			final DocumentBuilder documentBuilder = builderFactory
					.newDocumentBuilder();
			final Document document = documentBuilder.parse(xmlFile);
			return build(document.getDocumentElement(), null);

		} catch (Exception e) {
			throw new RuntimeException("XML Parse error");
		}
	}

	protected Element build(final Node node, final Element parentElement) {
		final String className = (String) mapping.get(node.getNodeName());

		if (className == null) {
			throw new RuntimeException("Can not find mapping for element: "
					+ node.getNodeName());
		}

		final Element element = createElementInstance(className, node,
				parentElement);

		// build child elements
		if (element != null) {
			final NodeList childElementsList = node.getChildNodes();
			for (int i = 0; i < childElementsList.getLength(); i++) {
				final Node childNode = childElementsList.item(i);

				if (childNode.getNodeType() == Node.ELEMENT_NODE) {
					final Element childElement = build(childNode, element);
					element.addChildElement(childElement);
				}
			}
		}

		return element;
	}

	protected Element createElementInstance(final String className,
			final Node node, final Element parentElement) {

		try {
			final Constructor<?> constructor = Class.forName(className).getConstructor(Node.class, Element.class);
			return (Element) constructor.newInstance(node, parentElement);

		} catch (Exception e) {
			throw new RuntimeException("Element definition error");
		}
	}
}
