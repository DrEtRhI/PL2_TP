package im2ag.m2pcci.selectdemo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Méthodes utilitaires pour les DAO (Data Access Objects)
 * @author Philippe Genoud - UGA Université Grenoble Alpes - Lab LIG STeamer
 */
public class DBUtilDAO {

    /**
     * Transforme un ResultSet en un liste d'options HTML.
     *
     * @param rs le ResultSet à transformer
     * @param topOption l'option qui sera affichée en premier dans la liste
     * (rien n'est affiché si topOption est null).
     * @return le code HTML correspondant à la liste d'options. Par exemple si
     * le ResultSet est le suivant:<br>
     * -----------------------<br>
     * | DE  |  Allemagne    |<br>
     * | FR  |  France       |<br>
     * | US  |  Etats-Unis   |<br>
     * -----------------------<br>
     * <br>
     * que topOption vaut "--Sélectionnez un pays"<br>
     * <br>
     * Le résultat sera:<br>
     * <br>
     * &lt;option value="">--Sélectionnez un pays&lt;/option&gt;
     * &lt;option value="DE">Allemagne&lt;/option&gt;
     * &lt;option value="FR">France&lt;/option&gt;
     * ...
     * &lt;option value="US">Etats-unis&lt;/option&gt;
     * 
     * @throws SQLException si pb avec la BD
     */
    public static String resultSet2OptionList(ResultSet rs, String topOption) throws SQLException {
        // on utilise unStringBuilder plus efficace que des objets String pour
        // la concaténation de chaînes de caractères
        StringBuilder sb = new StringBuilder();
        sb.append("<option value=\"\">--Selectionnez ").append(topOption).append("</option>");
        while (rs.next()) {
            sb.append("<option value=\"");
            sb.append(rs.getString(1));
            // plutôt que d'envoyer des message append dans des instructions
            // consécutives on peut les enchaîner comme ci-dessous.
            sb.append("\">").append(rs.getString(2)).append("</option>");
        }
        return sb.toString();
    }

}
