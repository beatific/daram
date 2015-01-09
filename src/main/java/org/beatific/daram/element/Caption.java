package org.beatific.daram.element;

import org.beatific.daram.context.Context;
import org.beatific.daram.xml.Element;
import org.w3c.dom.Node;

public class Caption extends Element {

    private String format;
	
	public Caption(Node node, Element parentElement) {
		super(parentElement);
		this.format = getNodeAttribute(node, "format");
	}
	
	@Override
	public void process(final Context context) {
		context.setCaption(getValue(context, format));
		super.process(context);
	}
}
