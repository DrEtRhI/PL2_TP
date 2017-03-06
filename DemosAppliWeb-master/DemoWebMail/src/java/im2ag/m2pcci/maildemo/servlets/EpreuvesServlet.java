package im2ag.m2pcci.maildemo.servlets;

import im2ag.m2pcci.maildemo.dao.EpreuveDAO;
import im2ag.m2pcci.maildemo.model.Epreuve;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cette servlet permet de répondre à différentes requêtes concernant les
 * épreuves sportives. Ces requêtes sont définies par le paramètre "action" de
 * la requête HTTP. 
 * 
 * Si le paramètre "action" vaut "liste" la servlet renvoie le code HTML correspondant
 * aux options d'une liste de sélection avec comme valeur l'identifiant de l'épreuve
 * (un entier) et comme texte le nom de l'épreuve.
 * 
 * Si la paramètre "action" vaut "date", la servlet renvoie le code HTML correspondant
 * à la date et l'horaire de l'épreuve définie par le second paramètre de la requête
 * HTTP, "noEpreuve".
 * 
 *
 * @author Philippe Genoud - UGA Université Grenoble Alpes - Lab. LIG Steamer
 */
@WebServlet(name = "EpreuvesServlet", urlPatterns = {"/epreuve"})
public class EpreuvesServlet extends HttpServlet {

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
        String action = request.getParameter("action");
        StringBuilder sb = new StringBuilder();
        switch (action) {
            case "list":
                sb.append("<option value=\"\" disabled selected>--sélectionnez une épreuve");
                int idEp = 0;
                for (Epreuve ep : EpreuveDAO.getEpreuves()) {
                    sb.append("<option value=\"").append(idEp).append("\">").append(ep.getNom()).append("</option>");
                    idEp++;
                }
                break;
            case "date":
                int noEp = Integer.parseInt(request.getParameter("noEpreuve"));
                Epreuve ep = EpreuveDAO.getEpreuve(noEp);
                sb.append(ep.getFormatedDate());
                break;
            default:
                throw new ServletException("action inconnue " + action);
        }
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println(sb.toString());
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
