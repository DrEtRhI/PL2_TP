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

    private boolean present[];

    /**
     * Créer un nouvel ensemble vide ou rempli aléatoirement
     *
     * @param vide true un ensemble vide, false un ensemble aléatoire
     */
    public EnsembleDeLettres(boolean vide) {
        if (vide == true) {
            present = new boolean[26];
        } else {
            present = new boolean[26];
            for (int i = 0; i < present.length; i++) {
                present[i] = Math.random() < 0.5;
            }
        }
    }

    /**
     * Créé un ensemble à partir d'une chaine de caractère
     *
     * @param s chaine de caractère pour initialiser l'ensemble
     */
    public EnsembleDeLettres(String s) {
        present = new boolean[26];
        s = s.toLowerCase().trim();
        for (int i = 0; i < s.length(); i++) {
            if (charToIndice(s.charAt(i)) >= 0 && charToIndice(s.charAt(i)) < 26) {
                present[charToIndice(s.charAt(i))] = true;
            }
        }
    }

    /**
     * Affiche l'ensemble entre accolade et les éléments séparés par des espaces
     */
    public void afficher() {
        int j = present.length - 1;
        while (j > 0 && present[j] != true) {
            j--;
        }
        int max = j;
        System.out.print("{");
        for (int i = 0; i < present.length; i++) {
            if (present[i] == true) {
                if (i != max) {
                    System.out.print(indiceToChar(i) + ",");
                } else {
                    System.out.print(indiceToChar(i));
                }
            }
        }
        System.out.println("}");
    }

    /**
     * Test si l'ensemble est vide
     *
     * @return true si l'ensemble est vide false sinon
     */
    public boolean estVide() {
        int i = 0;
        while (i < present.length && present[i] != true) {
            i++;
        }
        return !(i < present.length);
    }

    /**
     * Renvoie le nombre d'éléments présents dans l'ensemble
     *
     * @return le nombre d'éléments de l'ensemble;
     */
    public int cardinal() {
        int som = 0;
        for (int i = 0; i < present.length; i++) {
            if (present[i] == true) {
                som += 1;
            }
        }
        return som;
    }

    /**
     * Test si le caractère donné en paramètre est présent dans l'ensemble
     *
     * @param a caractère à chercher dans l'ensemble
     * @return true si le caractère a est présent sinon false
     */
    public boolean estPresent(char a) {
        //transforme un caractère majuscule en minuscule
        int valueLettre = (int) a;
        if (valueLettre >= 65 && valueLettre <= 90) {
            valueLettre += 32;
            a = (char) valueLettre;
        }
        //vérifie que le caractère demandé est bien un caractère dans l'intervalle [a...z]
        if (charToIndice(a) >= 0 && charToIndice(a) < 26) {
            return present[charToIndice(a)];
        }
        //dans le cas ou le caractère recherché n'est pas dans l'intervalle [a...z] renvoie false
        return false;
    }

    /**
     * Créé un nouvelle ensemble formé de l'intersection de deux ensembles
     *
     * @param e ensemble utilisé pour créer l'intersetion avec l'ensemble
     * écoutant
     * @return le nouvelle ensemble créé
     */
    public EnsembleDeLettres intersection(EnsembleDeLettres e) {
        EnsembleDeLettres e1 = new EnsembleDeLettres(true);
        for (int i = 0; i < present.length; i++) {
            if (((this.estPresent(indiceToChar(i))) == true) && ((e.estPresent(indiceToChar(i))) == true)) {
                    e1.present[i] = true;
            }else{
                    e1.present[i] = false;
            }
        }

        return e1;
    }

    /**
     * Créé un nouvelle ensemble formé de l'union de deux ensembles
     *
     * @param e ensemble utilisé pour créer l'union avec l'ensemble écoutant
     * @return le nouvelle ensemble créé
     */
    public EnsembleDeLettres union(EnsembleDeLettres e) {
        EnsembleDeLettres e1 = new EnsembleDeLettres(true);
        for (int i = 0; i < present.length; i++) {
            if (((this.estPresent(indiceToChar(i))) == true) || ((e.estPresent(indiceToChar(i))) == true)) {
                    e1.present[i] = true;
            }else{
                    e1.present[i] = false;
            }
        }

        return e1;
    }

    /**
     * Créé un nouvelle ensemble formé de la différence de deux ensembles
     *
     * @param e ensemble utilisé pour créer la différence avec l'ensemble
     * écoutant
     * @return le nouvelle ensemble créé
     */
    public EnsembleDeLettres difference(EnsembleDeLettres e) {
        return null;
    }

    /**
     * Créé un nouvelle ensemble formé de l'union disjointe de deux ensembles
     *
     * @param e ensemble utilisé pour créer l'union disjointe avec l'ensemble
     * écoutant
     * @return le nouvelle ensemble créé
     */
    public EnsembleDeLettres unionDisjointe(EnsembleDeLettres e) {
        return null;
    }

    private static int charToIndice(char lettre) {
        int indice;
        indice = (int) lettre;
        indice -= 97;
        return indice;
    }

    private static char indiceToChar(int i) {
        char lettre;
        i += 97;
        lettre = (char) i;
        return lettre;
    }

}
