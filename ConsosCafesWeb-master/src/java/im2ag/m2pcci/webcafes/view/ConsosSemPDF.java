package im2ag.m2pcci.webcafes.view;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import im2ag.m2pcci.webcafes.model.ListeConsos;
import im2ag.m2pcci.webcafes.model.Programmeur;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cette servlet affiche pour une semaine donnée la liste des consommations de
 * café d'un programmeur en format PDF.
 *
 * @author Philippe Genoud - LIG-Steamer - UJF
 */
@WebServlet(name = "ConsosSemPDF", urlPatterns = {"/consosSemPDF"})
public class ConsosSemPDF extends HttpServlet {


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

       // récupératon du modèle comme attribut de la requête
        ListeConsos lesConsos = (ListeConsos) request.getAttribute("listeConsos");
        
        // génération de la réponse PDF
        response.setContentType("application/pdf");
        response.addHeader("Content-disposition", "filename=consosSem" + lesConsos.getNoSem() + ".pdf");
        try (OutputStream out = response.getOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();

            Paragraph titre = new Paragraph("Consommations Semaine "
                    + lesConsos.getNoSem(),
                    FontFactory.getFont(FontFactory.TIMES, 18, Font.BOLDITALIC, BaseColor.BLUE));
            titre.setAlignment(Element.ALIGN_CENTER);
            titre.setSpacingAfter(30f);

            document.add(titre);

            Font font = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLDITALIC, BaseColor.BLACK);
            PdfPTable table = new PdfPTable(2);

            table.addCell(new Paragraph("Programmeur", font));
            table.addCell(new Paragraph("Nombre de Tasses", font));

            int nbTotalTasses = 0;

            for (Programmeur p : lesConsos) {
                table.addCell(" " + p.getPrenom() + " " + p.getNom() + " ");
                table.addCell(" " + p.getNbTasses() + " ");
                nbTotalTasses += p.getNbTasses();
            }
            document.add(table);
            Paragraph paraTotal = new Paragraph("Nombre total de tasses: " + nbTotalTasses);
            paraTotal.setSpacingBefore(20f);
            document.add(paraTotal);
            document.close();
        } catch (DocumentException ex) {
            Logger.getLogger(ConsosSemPDF.class.getName()).log(Level.SEVERE, null, ex);
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
