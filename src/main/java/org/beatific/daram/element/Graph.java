package org.beatific.daram.element;

import org.beatific.daram.context.Context;
import org.beatific.daram.xml.Element;
import org.w3c.dom.Node;

public class Graph extends Element {
	
	private String xValue;
	private String yValue;
	private String name;
	
	public Graph(Node node, Element parentElement) {
		super(parentElement);
		this.xValue = getNodeAttribute(node, "x-value");
		this.yValue = getNodeAttribute(node, "y-value");
		this.name = getNodeAttribute(node, "name");
	}
	
	@Override
	public void process(final Context context) {
		context.addGraph(getValue(context, xValue), getValue(context, yValue), getValue(context, name));
		super.process(context);
	}
}
