/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package im2ag.m2pcci.demoweb.model;

import java.time.LocalDateTime;

/**
 *
 * @author Philippe GENOUD - Université Grenoble Alpes - Lab LIG-Steamer
 */
public class Spectacle {

    private final String nom;
    private final LocalDateTime date;

    public Spectacle(String nom, LocalDateTime date) {
        this.nom = nom;
        this.date = date;
    }

    public String getNom() {
        return nom;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public int getJour() {
        return date.getDayOfMonth();
    }

    public int getMois() {
        return date.getMonthValue();
    }

    public int getAnnee() {
        return date.getYear();
    }

    public int getHeure() {
        return date.getHour();
    }
    
    public int getMinute() {
        return date.getMinute();
    }
}
