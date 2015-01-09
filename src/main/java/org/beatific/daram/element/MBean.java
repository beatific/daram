package org.beatific.daram.element;

import org.beatific.daram.context.Context;
import org.beatific.daram.xml.Element;
import org.w3c.dom.Node;

public class MBean extends Element {

	private String objectName;

	public MBean(Node node, Element parentElement) {
		super(parentElement);
		this.objectName = getNodeAttribute(node, "objectName");
	}

	@Override
	public void process(final Context context) {

		context.setObjectName(objectName);
		super.process(context);
	}

}
