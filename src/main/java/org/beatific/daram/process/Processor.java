package org.beatific.daram.process;

import java.util.HashMap;
import java.util.Map;

import org.beatific.daram.constant.Constants;
import org.beatific.daram.constraint.Assert;
import org.beatific.daram.context.Context;
import org.beatific.daram.context.impl.DaramContext;
import org.beatific.daram.element.Daram;
import org.beatific.daram.result.Result;
import org.beatific.daram.xml.Transformer;

public class Processor {
	
	private String configFile = null;
	private Context context = null;
	private Transformer transformer = null;
	private static final Processor processor = new Processor();
	
	public static Processor getInstance() {
		return processor;
	}
	public void setConfigFile(String configFile) {
		this.configFile = configFile;
	}
	
	public void process() {
		Context context = getContextInstance();
		context.clear();
		Transformer transformer = getTransformerInstance(context);
		Daram daram = (Daram)transformer.transform(context);
		daram.process(context);
	}
	
	protected Transformer getTransformerInstance(Context context) {
		
		Assert.notNull(context);
		
		if(this.transformer == null)this.transformer = new Transformer();
		return this.transformer;
	}
	
	protected Context getContextInstance() {
		
		Assert.notNull(configFile);;
		
		Map<String, String> options = new HashMap<String, String>();
		options.put(Constants.CONFIG_FILENAME, configFile);
		
		if(this.context == null)this.context = new DaramContext(options);
		return this.context;
	}
	
	public Result getResult() {
		return this.context.getResult();
	}
}
