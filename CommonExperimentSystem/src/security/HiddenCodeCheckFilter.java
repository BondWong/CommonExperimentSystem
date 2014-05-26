package security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class HiddenCodeCheckFilter
 */
public class HiddenCodeCheckFilter implements Filter {

    /**
     * Default constructor. 
     */
    public HiddenCodeCheckFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		System.out.println("hiddenCodeCheckFilter");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		String hiddenCode = request.getParameter("hiddenCode");
		HttpSession session = httpRequest.getSession();
		synchronized(session){
			String hd = (String) session.getAttribute("hiddenCode");
			if(hd.equals(hiddenCode))
				chain.doFilter(request, response);
			else{
			//dispatcher
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
