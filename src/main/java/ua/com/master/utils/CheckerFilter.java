package ua.com.master.utils;


import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Created by IntelliJ IDEA.
 * User: ""
 * Date: 19.05.2007
 * Time: 11:55:03
 * To change this template use File | Settings | File Templates.
 */
public class CheckerFilter implements Filter
{
    private static final Logger log = Logger.getLogger(CheckerFilter.class);

    public void init(FilterConfig filterConfig) throws ServletException
    {
        log.info("CheckerFilter is initialized");
    }


    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse res = (HttpServletResponse)servletResponse;
        HttpSession session = req.getSession();
        ServletContext servletContext=session.getServletContext();

        boolean forward = false;
        boolean management = false;
        boolean archiving=false;


        if (session != null)
        {

                forward = false;

        }
        else
        {
            forward = false;
        }

        if (req.getRequestURI().indexOf("login.xhtml")>=0) forward = false;
        else  if (req.getRequestURI().indexOf("management/initTestData.xhtml")>=0)
        {
            forward = false;
            management = true;
            archiving=false;
        }
        else  if (req.getRequestURI().indexOf("archiving/archivingData.xhtml") >= 0)
        {
            forward = false;
            management = false;
            archiving=true;
        }

        if (forward)
        {
          res.sendRedirect("/master/index.xhtml");
        }
        else
        {
            if (!management && !archiving && req.getRequestURI().indexOf("login.xhtml")<0)
            {
                HibernateAuditInterceptor.setUser("admin");

            }
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    public void destroy()
    {

    }
}
