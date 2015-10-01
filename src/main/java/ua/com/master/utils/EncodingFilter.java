package ua.com.master.utils;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;


/**
 * Created by Oxana on 01.10.2015.
 */
@WebFilter("/*")
public class EncodingFilter implements Filter {
    protected String encoding = null;
    protected FilterConfig filterConfig = null;
    protected boolean ignore = true;

    private static final Logger log = Logger.getLogger(EncodingFilter.class);

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
            // Conditionally select and set the character encoding to be used
            if (ignore || (request.getCharacterEncoding() == null)) {
                String encoding = selectEncoding(request);
                if (encoding != null)
                    request.setCharacterEncoding(encoding);
            }

            response.setContentType("text/html; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            // Pass control on to the next filter
            chain.doFilter(request, response);
        }
    protected String selectEncoding(ServletRequest request) {
        return (this.encoding);
    }
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("Initializing filter...");
        log.debug("Obtaining SessionFactory from static HibernateUtil singleton");
        this.filterConfig = filterConfig;
        this.encoding = filterConfig.getInitParameter("encoding");
        String value = filterConfig.getInitParameter("ignore");
        if (value == null)
            this.ignore = true;
        else if (value.equalsIgnoreCase("true"))
            this.ignore = true;
        else if (value.equalsIgnoreCase("yes"))
            this.ignore = true;
        else
            this.ignore = false;
    }

    public void destroy() {
        this.encoding = null;
        this.filterConfig = null;
    }
}
