package org.beatific.daram.process;

import java.util.HashMap;
import java.util.Map;

import org.beatific.daram.constant.Constants;
import org.beatific.daram.context.Context;
import org.beatific.daram.context.impl.DaramContext;
import org.beatific.daram.element.Daram;
import org.beatific.daram.xml.ElementBuilder;
import org.springframework.scheduling.annotation.Scheduled;

public class JmxReader {

	private String configFile = "C:/Users/08151/git/daram/src/main/resources/jmxeval-threadcount.xml";
	private String validate = "false";
	private String schema = "1.2";

	public void setConfigFile(String configFile) {
		this.configFile = configFile;
	}

	@Scheduled(cron = "* */1 * * * *")
	public void read() {
		Map<String, String> options = new HashMap<String, String>();

		options.put(Constants.CONFIG_FILENAME, configFile);
		final Context context = getContextInstance(options);

		try {
			final ElementBuilder elementBuilder = getElementBuilderInstance();
			final Daram daram = (Daram) elementBuilder.build(context);

			daram.process(context);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	protected Context getContextInstance(final Map<String, String> config) {
		return new DaramContext(config);
	}

	protected ElementBuilder getElementBuilderInstance() {
		return new ElementBuilder();
	}
}
