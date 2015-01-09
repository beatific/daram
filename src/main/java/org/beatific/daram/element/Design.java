package org.beatific.daram.element;

import org.beatific.daram.context.Context;
import org.beatific.daram.xml.Element;
import org.w3c.dom.Node;

public class Design extends Element {

	private String name;
	
	public Design(Node node, Element parentElement) {
		super(parentElement);
		this.name = getNodeAttribute(node, "name");
	}
	
	@Override
	public void process(final Context context) {
		context.setName(getValue(context, name));
		super.process(context);
	}

}
