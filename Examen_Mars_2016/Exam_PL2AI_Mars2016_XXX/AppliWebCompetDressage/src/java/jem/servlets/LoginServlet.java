package jem.servlets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.login.FailedLoginException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jem.model.GestionnaireEpreuve;
import jem.model.Juge;

/**
 * connection d'un juge au gestionnaire de compétition. Le mot de passe est
 * vérifié. Si il est correct la servlet redirige la requête vers la page de
 * saisie d'une note, sinon il est redirigé sur la page de login. La connexion
 * n'est possible que si le juge n'est pas déjà connecté.
 *
 * @author Philippe Genoud - UGA Université Grenoble Alpes - Lab. LIG Steamer
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String forwardUrl = "/WEB-INF/saisieNote.jsp";
        ServletContext context = getServletContext();
        GestionnaireEpreuve gestionnaire = (GestionnaireEpreuve) context.getAttribute("gestionnaire");

        int noJuge = Integer.parseInt(request.getParameter("noJuge"));

        // on enregistre le juge dans la session il manque la verification du mot de passe
        // et la vérification que le juge n'est pas déjà connecté.
        Juge juge = gestionnaire.getJuge(noJuge);
        HttpSession session = request.getSession();
        session.setAttribute("juge", juge);

        // redirection sur la page de saisie des notes
        request.getRequestDispatcher(forwardUrl).forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
