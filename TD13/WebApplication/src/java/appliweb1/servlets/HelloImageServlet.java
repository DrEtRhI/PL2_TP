/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appliweb1.servlets;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author thierrye
 */
@WebServlet(name = "HelloImageServlet", urlPatterns = {"/HelloImageServlet"})
public class HelloImageServlet extends HttpServlet {

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
        response.setContentType("image/png");
        String msg = request.getParameter("nom");
        int nbRepetition = Integer.parseInt(request.getParameter("nb"));
        ImageIO.write(createImage(400, msg, nbRepetition, Color.blue, Color.yellow), "png", response.getOutputStream());
    }
    
    /**
 * Crée une image en mémoire où le message à afficher est déssiné n fois en
 * pivotant par rapport au centre de l'image (qui est carrée)
 *
 * @param size taille de l'image
 * @param msg la message à afficher
 * @param nbRepetition le nombre de répétitions du message
 * @param backgroundColor couleur du fond de l'image
 * @param textColor couleur du texte
 * @return l'image
 */
BufferedImage createImage(int size, String msg, int nbRepetition,
        Color backgroundColor, Color textColor) {
    BufferedImage buffer = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
    Graphics2D g = buffer.createGraphics();
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
    g.setFont(new Font("Monospaced", Font.BOLD, 24));
    g.setColor(backgroundColor);   // la couleur du fond
    g.fillRect(0, 0, size, size);
    g.setColor(textColor); // la couleur du texte
    g.drawString(msg, size / 2 + 20, size / 2);
    for (int i = 0; i < nbRepetition; i++) {
        Graphics2D g1 = (Graphics2D) g.create();
        double angle = (i * 2 * Math.PI) / nbRepetition;
        g1.rotate(angle, size / 2, size / 2);
        g1.translate(size / 2 + 20, size / 2);
        g1.drawString(msg, 0, 0);
    }
    return buffer;
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
