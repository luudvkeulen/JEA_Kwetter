package filters;

import controllers.LoginController;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter {

    public LoginFilter() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc) throws IOException, ServletException {
        LoginController loginController = (LoginController) ((HttpServletRequest) request).getSession().getAttribute("loginController");

        if (loginController == null || !loginController.isLoggedIn()) {
            String contextPath = ((HttpServletRequest) request).getContextPath();
            ((HttpServletResponse) response).sendRedirect(contextPath + "/login.xhtml");
            return;
        }

        fc.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

}
