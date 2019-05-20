package com.iflytek.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/*")
public class LoginFilter implements Filter {

    /**
     * Default constructor.
     */
    public LoginFilter() {
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
        // 在请求之前执行某些操作
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;
        System.out.println("begin:我执行了"+req.getRequestURI()+"请求...");
        String requestUri = req.getRequestURI();
        String user = (String)req.getSession().getAttribute("user");
        if (!(requestUri.indexOf("/LoginServlet")>-1 || requestUri.indexOf("/VerifyCodeServlet")>-1
                || requestUri.endsWith(".css") || requestUri.endsWith(".js")
                || requestUri.endsWith(".jpg") || requestUri.endsWith(".png")
                || requestUri.endsWith(".gif") || requestUri.endsWith(".ico"))) {
            // 判断是否已经登录过 ...  如果登录了 直接执行相关操作   否则跳转到登录页面

            if (user == null) {
                req.setAttribute("error", "请登录后再试...");
                req.getRequestDispatcher("/LoginServlet").forward(request, response);
                return ;
            }

            if(requestUri.indexOf(".jsp")>-1 || requestUri.indexOf(".css")>-1) {
                req.setAttribute("error", "对不起，您无权访问系统资源，请联系管理员...");
                req.getRequestDispatcher("/LoginServlet").forward(request, response);
                return ;
            }
        }

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 如果你登录过  那么就直接跳转到 main.jsp 这样的主界面
        if (requestUri.indexOf("/LoginServlet")>-1 && user != null) {
            req.getRequestDispatcher("jsp/main.jsp").forward(request, response);
            return ;
        }

        try {
            chain.doFilter(request, response); // 执行相应的请求
        } catch(ServletException e) {
            req.setAttribute("error", "系统错误，请联系系统管理员");
            req.setAttribute("errorMessage", e.getMessage());
            req.setAttribute("errorDetail", e.getStackTrace()[3].toString());
            req.getRequestDispatcher("jsp/error.jsp").forward(req, res);
        }
        // 请求之后执行某些操作
        System.out.println("end:我执行了"+req.getRequestURI()+"请求...");
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

}
