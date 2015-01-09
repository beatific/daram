package org.beatific.daram.element;

import org.beatific.daram.context.Context;
import org.beatific.daram.xml.Element;
import org.w3c.dom.Node;

public class Attribute extends Element {

	private String var;
	private String name;
	
	public Attribute(Node node, Element parentElement) {
		super(parentElement);
		this.var = getNodeAttribute(node, "var");
		this.name = getNodeAttribute(node, "name");
	}

	@Override
	public void process(final Context context) {
		context.setAttribute(var, name);
		super.process(context);
	}
}
