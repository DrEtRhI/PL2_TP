package jem.servlets;

import java.io.IOException;
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
 * se charge de déterminer la page qui sera affichée sur le poste client en
 * fonction de la progression de la compétition. 3 cas de figure sont possibles:
 * 
* - L'épreuve est terminée, la servlet redirige la requête vers la page
 * finEpreuve.jsp qui affiche le classement final.
 * 
* - Le cavalier en piste est celui pour lequel ce juge vient de mettre une
 * note, mais son résultat est incomplet : il manque au moins l’une des notes
 * d’un autre juge. La requête est redirigée vers la page attenteNotes.jsp.
 * Celle-ci affiche les notes déjà attribuées à ce cavalier et contient un code
 * JavaScript qui au bout de 5 secondes renvoie une requête à cette Servlet.
 *
 * - Le cavalier en piste est un nouveau cavalier pour lequel ce juge n’a pas
 * encore attribué de note, la servlet redirige la requête vers la page
 * saisieNote.jsp quipermettra de saisir la note du juge pour ce cavalier.
 *
 * @author Philippe Genoud - UGA Université Grenoble Alpes - Lab. LIG Steamer
 */
@WebServlet(name = "TesterAttente", urlPatterns = {"/afficherProgression"})
public class ProgressionServlet extends HttpServlet {

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
        // recupération des objets attributs de contexte et attributs de session
        ServletContext context = getServletContext();
        GestionnaireEpreuve gestionnaire = (GestionnaireEpreuve) context.getAttribute("gestionnaire");
        HttpSession session = request.getSession();
        Juge juge = (Juge) session.getAttribute("juge");
        String forwardUrl;
        if (gestionnaire.epreuveTerminee()) {
            forwardUrl = "/WEB-INF/finEpreuve.jsp";
        } else if (gestionnaire.getResultatCavalierEnPiste().getNote(juge.getNumero()) == -1) {
            // Le cavalier en piste est un nouveau cavalier pour lequel ce juge n’a pas encore attribué de note
            forwardUrl = "/WEB-INF/saisieNote.jsp";
        } else {
            // Le cavalier en piste est celui pour lequel ce juge vient de mettre une note, mais il est incomplet 
            forwardUrl = "/WEB-INF/attenteNotes.jsp";
        }
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
