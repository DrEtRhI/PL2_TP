package im2ag.m2pcci.selectdemo.servlets;

import im2ag.m2pcci.selectdemo.dao.PaysDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Cette Servlet retourne sous forme d'un liste d'options HTML la liste de tous
 * les pays contenus dans la table PAYS ou si la requête a un paramètre
 * avecSportif=true la liste des pays pour lesquels il existe au moins un
 * sportif ou si la requête a comme parametre avecSportif=false la liste des
 * pays pour lesquels il n'y a aucun sportif.
 *
 * @author Philippe Genoud - UGA Université Grenoble Alpes - Lab. LIG Steamer
 */
@WebServlet(name = "ListePaysServlet", urlPatterns = {"/listeDesPays"})
public class ListePaysServlet extends HttpServlet {

    @Resource(name = "jdbc/UFRIMA")
    private DataSource dataSource;

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
        String avecSportif = request.getParameter("avecSportif");
        String listeOptions;
        try {
            if ("true".equals(avecSportif)) {
                    listeOptions = PaysDAO.listePaysSportif(dataSource, true);
            } else if ("false".equals(avecSportif)) {
                    listeOptions = PaysDAO.listePaysSportif(dataSource, false);
            } else {
                    listeOptions = PaysDAO.listePays(dataSource);
            }
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println(listeOptions);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListePaysServlet.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServletException(ex.getMessage(), ex);
        }
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
