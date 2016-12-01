/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testensembledelettres;

/**
 *
 * @author thierrye
 */
public class EnsembleDeLettres {
    
    private boolean ensemble[];
    
    
    /**
     * Créer un ensemble vide
     */
    public EnsembleDeLettres(){
        
    }
    
    /**
     * Créé un ensemble à partir d'éléments tirés aléatoirement
     * @param a paramètre non fixé pour le moment, à voir lors de la
     * réalisation du constructeur
     */
    public EnsembleDeLettres(int a){
        
    }
    
    /**
     * Créé un ensemble à partir d'une chaine de caractère
     * @param s chaine de caractère pour initialiser l'ensemble
     */
    public EnsembleDeLettres(String s){
        
    }
    
    /**
     * Affiche l'ensemble entre accolade et les éléments séparés par des
     * espaces
     */
    public void afficher(){
        
    }
    
    /**
     * Test si l'ensemble est vide
     * @return true si l'ensemble est vide false sinon
     */
    public boolean estVide(){
        return false;
    }
    
    /**
     * Renvoie le nombre d'éléments présents dans l'ensemble
     * @return le nombre d'éléments de l'ensemble;
     */
    public int cardinal(){
        return 0;
    }
    
    /**
     * Test si le caractère donné en paramètre est présent dans l'ensemble
     * @param a caractère à chercher dans l'ensemble
     * @return true si le caractère a est présent sinon false
     */
    public boolean estPresent (char a){
        return false;
    }
    
    /**
     * Créé un nouvelle ensemble formé de l'intersection de deux ensembles
     * @param e ensemble utilisé pour créer l'intersetion avec l'ensemble écoutant
     * @return le nouvelle ensemble créé
     */
    public EnsembleDeLettres intersection(EnsembleDeLettres e){
        return null;
    }
    
    /**
     * Créé un nouvelle ensemble formé de l'union de deux ensembles
     * @param e ensemble utilisé pour créer l'union avec l'ensemble écoutant
     * @return le nouvelle ensemble créé
     */
    public EnsembleDeLettres union(EnsembleDeLettres e){
        return null;
    }
    
    /**
     * Créé un nouvelle ensemble formé de la différence de deux ensembles
     * @param e ensemble utilisé pour créer la différence avec l'ensemble écoutant
     * @return le nouvelle ensemble créé 
     */
    public EnsembleDeLettres difference(EnsembleDeLettres e){
        return null;
    }
    
    /**
     * Créé un nouvelle ensemble formé de l'union disjointe de deux ensembles
     * @param e ensemble utilisé pour créer l'union disjointe avec l'ensemble écoutant
     * @return le nouvelle ensemble créé
     */
    public EnsembleDeLettres unionDisjointe(EnsembleDeLettres e){
        return null;
    }
    
    
}
