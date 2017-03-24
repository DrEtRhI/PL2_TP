package ajax.demo.servlets;

import ajax.demo.services.LoginManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ValidationServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        // Extract the data of the input form field whose name is "id"
        String targetId = request.getParameter("id");

        //  Send back either "<valid>true</valid>" or "<valid>false</valid>"
        //  XML message depending on the validity of the data that was entered.
        //  Note that the content type is "text/xml".
        //
        String rep;
        if ((targetId != null) && LoginManager.validateUserId(targetId)) {
           rep = "{ \"valid\":true}";
        } else {
            rep = "{ \"valid\":false}";
        }
        response.setContentType("application/json");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write(rep);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String targetId = request.getParameter("id");
        String passwd = request.getParameter("passwd");
        if ((targetId != null) && LoginManager.createAccount(targetId, passwd)) {
            request.setAttribute("targetId", targetId);
            getServletContext().getRequestDispatcher("/success.jsp").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

}
