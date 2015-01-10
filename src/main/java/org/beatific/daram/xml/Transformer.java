package org.beatific.daram.xml;

import org.beatific.daram.context.Context;

public class Transformer {

	private Element element = null;

	public Element transform(Context context) {
		if (this.element == null)
			this.element = new ElementBuilder().build(context);
		return this.element;
	}
}
