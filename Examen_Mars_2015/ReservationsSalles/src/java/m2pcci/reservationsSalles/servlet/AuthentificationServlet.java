/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m2pcci.reservationsSalles.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import m2pcci.reservationsSalles.dao.IdentifiantInconnuExcpetion;
import m2pcci.reservationsSalles.dao.UtilisateursDAO;
import m2pcci.reservationsSalles.dao.MotPasseIncorrectException;
import m2pcci.reservationsSalles.model.Utilisateur;

/**
 *
 * @author Philippe Genoud - UJF - LIG STeamer
 */
@WebServlet(name = "AuthentificationServlet", urlPatterns = {"/authentification"})
public class AuthentificationServlet extends HttpServlet {

    /**
     * pour récupérer la data source gérée par le serveur tomcat
     */
    @Resource(name = "jdbc/im2ag")
    private DataSource ds;

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
        String redirectURL = "";  // l'url de redirection
        String login = request.getParameter("login");        //recupération des paramètres de connexion
        String motDePasse = request.getParameter("password");

        HttpSession session = request.getSession();

        // authentification de l'utilisateur 
        try {
            Utilisateur utilisateur = UtilisateursDAO.authentifier(ds, login, motDePasse);
            if (utilisateur == null) {
                throw new IdentifiantInconnuExcpetion();
            }
            redirectURL = "/WEB-INF/reservations.jsp";
            session.setAttribute("utilisateur", utilisateur);
        } // en fonction du succès ou de l'echec de l'authentification redirection
        // vers la page définie par redirectURL (qui peut être soit l'URL
        // reservations.jsp ou l'URL  de la page d'acceuil (index.jsp))
        catch (MotPasseIncorrectException ex) {
            redirectURL = "index.jsp";
            request.setAttribute("messageErreur", "Mot de passe incorrect");

        } catch (IdentifiantInconnuExcpetion ex) {
            redirectURL = "index.jsp";
            request.setAttribute("messageErreur", "Identifiant inconnu");
        } catch (SQLException ex) {
            redirectURL = "index.jsp";
            request.setAttribute("exceptionBD", ex.getMessage());
            request.setAttribute("messageErreur", "Erreur en relation avec la base de données");
        } finally {
            request.getRequestDispatcher(redirectURL).forward(request, response);
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
        
        request.getRequestDispatcher("index.jsp").forward(request, response);
        
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
