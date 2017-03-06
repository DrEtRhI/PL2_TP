/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package im2ag.m2pcci.demoweb.ctrler.dao;

import im2ag.m2pcci.demoweb.model.Spectacle;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author Philippe GENOUD - Université Grenoble Alpes - Lab LIG-Steamer
 */
public class DateDAO {

    private static final String INSERT_DATE_SQL = "INSERT INTO spectacle (nom, date_spectacle) VALUES (?, ?)";
    private static final String ALL_DATES_SQL = "SELECT *  FROM spectacle ORDER BY date_spectacle";

    public static void insertDate(DataSource ds, String nomSpectacle, LocalDateTime dateSpectacle) throws SQLException {
        System.out.println(dateSpectacle.getDayOfMonth() + "/" + dateSpectacle.getMonth() + "/" + dateSpectacle.getYear()
                + "   " + dateSpectacle.getHour() + ":" + dateSpectacle.getMinute());
        try (Connection conn = ds.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(INSERT_DATE_SQL);
            stmt.setString(1, nomSpectacle);
            stmt.setDate(2, new Date(Date.from(dateSpectacle.atZone(ZoneId.systemDefault()).toInstant()).getTime()));
            stmt.executeUpdate();
        }
    }

    public static List<Spectacle> allDates(DataSource ds) throws SQLException {
        List<Spectacle> res = new ArrayList<>();
        try (Connection conn = ds.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(ALL_DATES_SQL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String nomSpectacle = rs.getString(1);
                // la date (JJ/MM/AA) du spectacle
                Date spectacleDate = rs.getDate(2);
                // l'heure du spectacle
                Time spectacleTime = rs.getTime(2);
                // transformation de de la date et l'heure en un objet LocalDateTime
                LocalDateTime ldt = LocalDateTime.of(spectacleDate.toLocalDate(), spectacleTime.toLocalTime());
                res.add(new Spectacle(nomSpectacle,ldt));
            }
            return res;
        }
    }

}
