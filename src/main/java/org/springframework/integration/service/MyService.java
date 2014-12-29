package org.springframework.integration.service;

import javax.management.openmbean.CompositeDataSupport;

public class MyService {

	public void service(CompositeDataSupport payload) {
		try {
			System.out.println("init:" + (Long)payload.get("init")/(1024.f)  + "; max:" + (Long)payload.get("max")/(1024.f) +  "; used: " + (Long)payload.get("used")/(1024.f) + "; committed: " + (Long)payload.get("committed")/(1024.f));
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
