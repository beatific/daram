package com.adahas.tools.jmxeval.response;

import java.util.Date;

/**
 * Result of a single eval check
 */
public class GraphResult {

	/**
	 * Name of the eval
	 */
	private final transient String name;

	/**
	 * Eval result message
	 */
	private final transient String message;

	private final transient String graph;
	
	private final transient Date date;

	/**
	 * Constructs an eval result
	 * 
	 * @param name
	 *            Name of the eval
	 * @param status
	 *            Eval status
	 * @param message
	 *            Eval result message
	 */
	public GraphResult(final String name, 
			final String message, final String graph, final Date date) {
		this.name = name;
		this.message = message;
		this.graph = graph;
		this.date = date;
	}

	/**
	 * Get the name of the eval
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * Get the result message of the eval
	 * 
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Get the result graph value
	 * 
	 * @return the graph value
	 */
	public String getGraph() {
		return graph;
	}
	
	/**
	 * Get the result date
	 * 
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Convert the result to its string representation
	 */
	@Override
	public String toString() {
		return "GraphResult [name=" + name
				+ ", message=" + message + ", graph=" + graph + ", date="
				+ date + "]";
	}

	
	

}
