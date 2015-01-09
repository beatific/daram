package org.beatific.daram.process;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.scheduling.annotation.Scheduled;

import com.adahas.tools.jmxeval.Context;
import com.adahas.tools.jmxeval.exception.ConfigurationException;
import com.adahas.tools.jmxeval.exception.EvalException;
import com.adahas.tools.jmxeval.model.ElementBuilder;
import com.adahas.tools.jmxeval.model.impl.JMXEval;
import com.adahas.tools.jmxeval.response.GraphResult;
import com.adahas.tools.jmxeval.response.Status;

public class JmxReader {

	private String configFile = "C:/Users/08151/git/daram/src/main/resources/jmxeval-threadcount.xml";
	private String validate = "false";
	private String schema = "1.2";
	
	public void setConfigFile(String configFile) {
		this.configFile = configFile;
	}
	
	public void setValidate(String validate) {
		this.validate = validate;
	}
	
	public void setSchema(String schema) {
		this.schema = schema;
	}
	
	@Scheduled(cron="* */1 * * * *")
	public void read() {
		Map<String, String> options = new HashMap<String,String>();
		
		final Context context = getContextInstance(options);
		context.setConfigFilename(configFile);
		context.setConfigSchema(schema);
		context.setConfigValidate(validate);
		
		try {
			// capture start time
		      // build the config element
		      final ElementBuilder elementBuilder = getElementBuilderInstance();
		      final JMXEval jmxEval = (JMXEval) elementBuilder.build(context);
		    
		      // process the evals
		      jmxEval.process(context);

		      List<GraphResult> results = context.getResponse().getGraphReslts();
		      
		      for(GraphResult result : results) {
		    	  System.out.println(result);
		      }
		      
		      
		    } catch (ConfigurationException e) {
		      
		      // print exception
		    	System.out.println(e.getMessage());
		      if (e.getCause() != null) {
		    	  System.out.println("Error details:");
		        e.printStackTrace();
		      }
		      
		      // indicate error by returning a non-zero value
		      System.err.println("Status : " + Status.UNKNOWN.getValue());
		      
		    } catch (EvalException e) {
		      
		      // print exception
		    	System.out.println(e.getMessage());
		      if (e.getCause() != null) {
		    	  System.out.println("Error details:");
		        e.printStackTrace();
		      }
		      
		      // indicate error by returning a non-zero value
		      System.err.println("Status : " + e.getStatus().getValue());
		    } 
	}
	
	
	protected Context getContextInstance(final Map<String, String> config) {
	     return new Context(config);
	}
	
	protected ElementBuilder getElementBuilderInstance() throws ConfigurationException {
	    return new ElementBuilder();
	  }
}
