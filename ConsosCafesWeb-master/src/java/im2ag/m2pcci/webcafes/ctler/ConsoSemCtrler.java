/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package im2ag.m2pcci.webcafes.ctler;

import im2ag.m2pcci.webcafes.model.ListeConsos;
import java.io.IOException;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import m2pcci.im2ag.webcafes.model.dao.ConsosDAO;

/**
 * Contrôleeur qui effectue l'aiguillage entre les différents servlet en charge
 * de l'affichage du tableau des consommations de la semaine.
 *
 * @author Philippe GENOUD - Université Grenoble Alpes - Lab LIG-Steamer
 */
@WebServlet(name = "ConsoSemCtrler", urlPatterns = {"/consosSem"})
public class ConsoSemCtrler extends HttpServlet {

    /**
     * DataSource pour la base de données des consommations de café, elle permet
     * d'obtenir des connection JDBC.
     *
     * Cette ressource est gérée par le container (Tomcat) et est injectée dans
     * l'application grâce l'annation @Resource
     */
    @Resource(name = "jdbc/CAFES")
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
        // récuperation du paramètre numSem,numéro de la semaine.
        // selon le client (et son support de HTML5) le format du paramètre de
        // de la requête peut être différent. On a ici du code qui permet
        // de prendre en compte la spécificité du client (on considère le cas
        // particulier de Chrome). 
        // bien sûr dans la "vraie vie", le traitement devrait être fait dans la
        // page HTML, avec un script JavaScript, pour envoyer un numéro de semaine
        // normalisé quel que soit le support de HTML5. Le faire ici à uniquement
        // un but pédagogique, l'objectif étant de vous montrer la manipulation
        // des en-têtes (headers) de requêtes HTTP.
        int noSemaine;
        String numSem = request.getParameter("numSem");

        String userAgent = request.getHeader("User-Agent");
        if (userAgent.toLowerCase().contains("chrome")) {
            // le  paramètre numSem de la requête est de la forme
            // <année>-W<numero de semaine>, par exemple pour la
            // 7ème semaine de 2015 on aura numSem=2015-W07
            // on ne considère donc que les deux derniers caractères
            numSem = numSem.substring(numSem.length() - 2);
        }
        noSemaine = Integer.parseInt(numSem);
        
        String format = request.getParameter("format").toUpperCase();
        
        // construction du modèle
        try {
            ListeConsos lesConsosDesProg = ConsosDAO.getConsoParSemaine(dataSource, noSemaine);
            request.setAttribute("listeConsos", lesConsosDesProg);
        } catch (SQLException ex) {
            throw new ServletException(ex.getMessage(), ex);
        }

        // redirection vers les vues
        switch (format) {
            case "PDF":
                request.getRequestDispatcher("/consosSemPDF").forward(request, response);
                break;
            default:
                request.getRequestDispatcher("/WEB-INF/consosParSemaine.jsp").forward(request, response);
                break;
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
