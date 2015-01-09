package com.adahas.tools.jmxeval;

import java.util.HashMap;
import java.util.Map;

import javax.management.MBeanServerConnection;

import com.adahas.tools.jmxeval.exception.EvalException;
import com.adahas.tools.jmxeval.response.Response;
import com.adahas.tools.jmxeval.response.Status;

/**
 * Execution context
 */
public class Context {

  /**
   * Filename config name
   */
  public static final String CONFIG_FILENAME = "filename";
  
  /**
   * Validation config name 
   */
  public static final String CONFIG_VALIDATE = "validate";
  
  /**
   * Schema config name
   */
  public static final String CONFIG_SCHEMA = "schema";
  
  /**
   * JMX server connection
   */
  private transient MBeanServerConnection connection;

  /**
   * Dynamic variables collection used while execution
   */
  private final transient Map<String, Object> variables = new HashMap<String, Object>();
  
  /**
   * Configuration values
   */
  private transient final Map<String, String> config;
  
  /**
   * Response
   */
  private transient final Response response = new Response();
  
  /**
   * Constructs the context with given configuration
   * 
   * @param configFile Configuration elements map
   */
  public Context(final Map<String, String> config) {
    this.config = config;
  }
  
  /**
   * Get a config value
   * 
   * @param key Config key
   * @return Config value
   */
  public String getConfigValue(final String key) {
    return config.get(key);
  }
  
  /**
   * Get the response instance
   * 
   * @return the response
   */
  public Response getResponse() {
    return response;
  }

  /**
   * Get the MBeanServerConnection instance
   * 
   * @return the connection
   */
  public MBeanServerConnection getConnection() {
    return connection;
  }

  /**
   * Set the MBeanServerConnection instance
   * 
   * @param connection the connection to set
   */
  public void setConnection(final MBeanServerConnection connection) {
    this.connection = connection;
  }

  /**
   * Sets a variable in the global variables collection
   * 
   * @param name Name of the variable
   * @param value Value of the variable
   * @throws EvalException When the variable is already defined or is having a reserved name as its variable name
   */
  public void setVar(final String name, final Object value) throws EvalException {
    if (variables.containsKey(name)) {
      throw new EvalException(Status.UNKNOWN, "Variable already set: " + name);
    } else {
      variables.put(name, value);
    }
  }

  /**
   * Get a variable from the global variables collection
   * 
   * @param name Name of the variable with optional : separated default value.
   * @return Value of the variable
   * @throws EvalException When the variable is not set and no default is provided
   */
  public Object getVar(final String name) throws EvalException {
    String key = name;
    String defaultValue = null;
    int seperator = name.indexOf(":");
    if (seperator >= 0)
    {
        key = name.substring(0, seperator);
        defaultValue = name.substring(seperator+1);
    }
    if (variables.containsKey(key)) {
      return variables.get(key);
    } else if (defaultValue != null) {
      return defaultValue;
    } else {
      throw new EvalException(Status.UNKNOWN, "Variable not set: " + name);
    }
  }
  
  public void setConfigValidate(String value) {
    config.put(CONFIG_VALIDATE, value);
  }
  
  public void setConfigSchema(String value) {
    config.put(CONFIG_SCHEMA, value);
  }
  
  public void setDefine(String name_value) throws EvalException
  {
    String[] s = name_value.split("=", 2);
    if (s.length != 2)
    {
      throw new EvalException(Status.UNKNOWN, "arg to --set ("+name_value+") must be in \"name=value\" format!");
    }
    setVar(s[0], s[1]);
  }
  
  public void setConfigFilename(String value) {
    config.put(CONFIG_FILENAME, value);
  }
}
