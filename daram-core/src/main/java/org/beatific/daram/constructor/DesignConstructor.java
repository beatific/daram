package org.beatific.daram.constructor;

import org.beatific.daram.design.Design;
import org.beatific.daram.design.DesignHolder;
import org.beatific.daram.design.Graph;
import org.beatific.ddirori.bean.BeanDefinition;
import org.beatific.ddirori.bean.Constructor;
import org.beatific.ddirori.bean.annotation.Action;
import org.beatific.ddirori.type.TagType;

@Action(tag="design", type=TagType.ATTRIBUTE)
public class DesignConstructor implements Constructor<Design>{

	@Override
	public Design create(BeanDefinition definition) {
		
		Design design = new Design();
		design.setName((String)definition.attributes().get("name"));
		design.setxTag((String)definition.attributes().get("x-tag"));
		design.setyTag((String)definition.attributes().get("y-tag"));
		
		for(BeanDefinition child : definition.children()) {
			if("caption".equals(child.getTagName())) {
				design.setCaptionExpression((String)child.attributes().get("format"));
			} else if("graph".equals(child.getTagName())) {
				design.addGraph(newGraph((String)child.attributes().get("name"), (String)child.attributes().get("y-value")));
			}
		}

		DesignHolder.hold(design);
		
		return design;
	}

	private Graph newGraph(String graphName, String yExpression) {
		Graph graph = new Graph();
		graph.setName(graphName);
		graph.setyExpression(yExpression);
		return graph;
	}
}
