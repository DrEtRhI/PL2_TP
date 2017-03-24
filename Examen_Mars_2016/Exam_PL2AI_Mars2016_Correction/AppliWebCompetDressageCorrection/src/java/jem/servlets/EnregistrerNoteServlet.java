package jem.servlets;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jem.model.Cavalier;
import jem.model.GestionnaireEpreuve;
import jem.model.Juge;

/**
 * Enregistre une note d'un juge.
 *
 * @author Philippe Genoud - UGA Université Grenoble Alpes - Lab. LIG Steamer
 */
@WebServlet(name = "NotesServlet", urlPatterns = {"/enregistrerNote"})
public class EnregistrerNoteServlet extends HttpServlet {

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
        Cavalier cavalierEnCours = gestionnaire.getCavalierEnPiste();
        // récupération des paramètres de la requête
        double note = Double.parseDouble(request.getParameter("note"));
        // traitement de la requête
        // on rajoute la note de ce juge au résultat du cavalier en piste
        gestionnaire.enregistrerNote(juge.getNumero(), note);
        // si le résultat est complet, il a été intégré au classement,
        // on passe au cavalier suivant.
        if (gestionnaire.getResultatCavalierEnPiste().estComplet()) {
            gestionnaire.cavalierSuivant();
        }
        // redirection vers servlet qui en fonction de la progression de
        // la compétition se chargera d'afficher la bonne page pour
        // ce juge
        request.getRequestDispatcher("/afficherProgression").forward(request, response);
    }

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

}
