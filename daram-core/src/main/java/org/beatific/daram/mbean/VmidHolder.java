package org.beatific.daram.mbean;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class VmidHolder implements Iterator<Entry<String, String>>, Iterable<Entry<String, String>> {

	private HashMap<String, String> holder = new HashMap<String, String>();
	
	public synchronized void hold(String key, String value) {
		System.out.println("holder key[" + key + "] value[" + value + "]");
		holder.put(key, value);
	}
	
	@Override
	public synchronized boolean hasNext() {
		return holder.entrySet().iterator().hasNext();
	}

	@Override
	public synchronized Entry<String, String> next() {
        return holder.entrySet().iterator().next();
	}

	@Override
	public synchronized void remove() {
		holder.entrySet().iterator().remove();
	}

	@Override
	public Iterator<Entry<String, String>> iterator() {
		return this;
	}
}
