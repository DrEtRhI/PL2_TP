package demo.servlets;

import demo.services.LoginManager;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccountCreationServlet extends HttpServlet {

    private ServletContext context;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.context = config.getServletContext();
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String targetId = request.getParameter("id");
        String passwd = request.getParameter("passwd");
        String passwdConfirm = request.getParameter("passwdConfirm");
        boolean badParameters = false;
        if (! passwdConfirm.equals(passwd)) {
            request.setAttribute("badPasswd", "passwords must match");
            badParameters = true;
        }
        if (! LoginManager.validateUserId(targetId)) {
            request.setAttribute("badUserId", "User id allready in use");
            badParameters = true;
        }

        if (badParameters) {
            context.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        if ((targetId != null) && LoginManager.createAccount(targetId, passwd)) {
            request.setAttribute("targetId", targetId);
            context.getRequestDispatcher("/success.jsp").forward(request, response);
        } else {
            context.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }
}


