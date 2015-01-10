package org.beatific.daram.reader;

import java.util.Map;

import org.beatific.daram.context.Context;
import org.beatific.daram.context.impl.DaramContext;
import org.beatific.daram.process.Processor;
import org.beatific.daram.xml.ElementBuilder;
import org.springframework.scheduling.annotation.Scheduled;

public class JmxReader {

	private String configFile;

	public void setConfigFile(String configFile) {
		this.configFile = configFile;
	}

	@Scheduled(cron = "* */1 * * * *")
	public void read() {

		Processor processor = Processor.getInstance();
		processor.setConfigFile(configFile);
		processor.process();
		
		System.out.println(processor.getResult());
	}

	protected Context getContextInstance(final Map<String, String> config) {
		return new DaramContext(config);
	}

	protected ElementBuilder getElementBuilderInstance() {
		return new ElementBuilder();
	}
}
