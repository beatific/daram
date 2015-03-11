package org.beatific.daram.mbean.stuck;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.beatific.daram.mbean.core.MBeanContainer;

public class StuckFilter implements Filter {
	
	private MBeanContainer container = null;
	private static final String STUCK_OBJECT_NAME = "org.beatific.daram:type=ThreadStuck"; 
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.container = MBeanContainer.getMBeanContainer();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		StuckMonitor monitor = (StuckMonitor)container.getMBean(STUCK_OBJECT_NAME);
		monitor.register(Thread.currentThread());
		chain.doFilter(request, response);
		
	}

	@Override
	public void destroy() {
		
	}

}
