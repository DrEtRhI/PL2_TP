/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jem.servlets;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jem.model.GestionnaireEpreuve;
import jem.model.Resultat;

/**
 * Cette servlet permet de récupérer le résultat détaillé d'un cavalier.
 * Elle est invoquée en donnant en paramètre de la requête le rang (la position)
 * dans le classement du cavalier pourlequel on veut les notes détaillées.
 * Le resultat de ce cavalier est enregistré comme attribut de la requête qui
 * est redirigée vers la page detailResultat.jsp qui se charge de produire
 * le code HTML affichant ce résultat.
 * 
 * @author Philippe Genoud - UGA Université Grenoble Alpes - Lab. LIG Steamer
 */
@WebServlet(name = "DetailResultat", urlPatterns = {"/detailResultat"})
public class DetailResultat extends HttpServlet {

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
        int rang = Integer.parseInt(request.getParameter("rang"));
        ServletContext context = getServletContext();
        GestionnaireEpreuve ge = (GestionnaireEpreuve) context.getAttribute("gestionnaire");
        Resultat resultat = ge.getClassement()[rang -1];
        request.setAttribute("resultat",resultat);
        request.getRequestDispatcher("/WEB-INF/detailResultat.jsp").forward(request, response);
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
