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
            present = new boolean[26]; //le tableau de booléens est initialisé à faux pour toutes les lettres
        } else {
            present = new boolean[26];
            for (int i = 0; i < present.length; i++) {
                present[i] = Math.random() < 0.5; //pour chaque lettre du tableau de booléen, la valeur true ou false est déterminée aléatoirement
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
        s = s.toLowerCase().trim();//convertit s en lowercase et supprime les espace grâce à trim
        for (int i = 0; i < s.length(); i++) {
            if (charToIndice(s.charAt(i)) >= 0 && charToIndice(s.charAt(i)) < 26) {//insère dans la valeur true dans le tableau de booléen si la lettre de s à l'indice i fait partie de [a...z] 
                present[charToIndice(s.charAt(i))] = true;
            }
        }
    }

    /**
     * Affiche l'ensemble entre accolade et les éléments séparés par des espaces
     * @return retourne la chaine de caractère représentant le tableau
     */
    public String afficher() {
        String e;
        int j = present.length - 1;
        while (j > 0 && present[j] != true) {
            j--;
        }
        int max = j;
        e ="{";
        for (int i = 0; i < present.length; i++) {
            if (present[i] == true) {
                if (i != max) {
                    e += indiceToChar(i) + ",";
                } else {
                    e += "" + indiceToChar(i);
                }
            }
        }
        e += "}";
        return e;
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
     * Permet d'ajouter un indice à l'ensemble écoutant
     * @param a caractère à ajouter à l'ensemble 
     * @return l'ensemble écoutant
     */
    public EnsembleDeLettres ajouterCaractere(char a){
        if (!(this.present[charToIndice(a)])){
            this.present[charToIndice(a)] = true;
        }
        return this;
    }
    
    /**
     * Permet de tester si l'ensemble e est inclus dans l'ensemble écoutant
     *
     * @param e ensemble dont l'inclusion est testée
     * @return vrai si l'ensemble e est inclus dans l'ensemble écoutant, faux
     * s'il n'est pas inclus
     */
    public boolean inclus(EnsembleDeLettres e) {
        int i = 0;
        boolean ok = true;
        while (i < present.length && ok) {
            if (e.present[i]) {
                ok &= this.present[i];
            }
            i++;
        }
        return ok;
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
     * l'algorithme se base sur le tableau de booléens.
     *
     * @param e ensemble utilisé pour créer l'intersetion avec l'ensemble
     * écoutant
     * @return le nouvelle ensemble créé
     */
    public EnsembleDeLettres intersection(EnsembleDeLettres e) {
        EnsembleDeLettres e1 = new EnsembleDeLettres(true);
        for (int i = 0; i < present.length; i++) {
            e1.present[i] = (this.estPresent(indiceToChar(i)) && e.estPresent(indiceToChar(i)));
        }
        return e1;
    }

    /**
     * Créé un nouvelle ensemble formé de l'intersection de deux ensembles
     * l'algorithme se base sur la création de la chaine de caractère de l'intersection et
     * utilise cette dernière pour créer le nouvel ensemble. (objet de type String)
     *
     * @param e ensemble utilisé pour créer l'intersetion avec l'ensemble
     * écoutant
     * @return le nouvelle ensemble créé
     */
    public EnsembleDeLettres intersection1(EnsembleDeLettres e) {
        String chaineIntersection = "";
        for (int i = 0; i < present.length; i++) {
            if (this.present[i]) {
                if (e.present[i]) {
                    chaineIntersection += indiceToChar(i);
                } 
            }
        }
        EnsembleDeLettres e1 = new EnsembleDeLettres(chaineIntersection);
        return e1;
    }
    
    /**
     * Créé un nouvelle ensemble formé de l'intersection de deux ensembles
     * l'algorithme se base sur la création de la chaine de caractère de l'intersection et
     * utilise cette dernière pour créer le nouvel ensemble. (objet de type StringBuilder)
     * @param e ensemble utilisé pour créer l'intersetion avec l'ensemble
     * écoutant
     * @return le nouvelle ensemble créé
     */
    public EnsembleDeLettres intersection2(EnsembleDeLettres e) {
        StringBuilder chaineIntersection = new StringBuilder(100);
        for (int i = 0; i < present.length; i++) {
            if (this.present[i]) {
                if (e.present[i]) {
                    chaineIntersection.append(indiceToChar(i));
                } 
            }
        }
        EnsembleDeLettres e1 = new EnsembleDeLettres(chaineIntersection.toString());
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
            e1.present[i] = (this.present[i] || e.present[i]);
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
        EnsembleDeLettres e1 = new EnsembleDeLettres(true);
        for (int i = 0; i < present.length; i++) {
            if (this.present[i]) {
                if (e.present[i]) {
                    e1.present[i] = !(e.present[i]);
                } else {
                    e1.present[i] = !(e.present[i]);
                }
            }
        }
        return e1;
    }

    /**
     * Créé un nouvelle ensemble formé de l'union disjointe de deux ensembles
     * (sans les éléments présent dans les deux ensembles)
     *
     * @param e ensemble utilisé pour créer l'union disjointe avec l'ensemble
     * écoutant
     * @return le nouvelle ensemble créé
     */
    public EnsembleDeLettres unionDisjointe(EnsembleDeLettres e) {
        EnsembleDeLettres e1 = new EnsembleDeLettres(true);
        for (int i = 0; i < present.length; i++) {
            if (this.present[i]) {
                if (e.present[i]) {
                    e1.present[i] = !(this.present[i] || e.present[i]);
                } else {
                    e1.present[i] = (this.present[i] || e.present[i]);
                }
            } else {
                e1.present[i] = (this.present[i] || e.present[i]);
            }
        }
        return e1;
    }

    /**
     * Evalue si deux ensembles sont égaux, c'est à dire possède les mêmes
     * caractères
     *
     * @param e l'ensemble comparé à l'ensemble écoutant
     * @return true si l'ensemble e et l'ensemble écoutant sont égaux, false
     * sinon.
     */
    public boolean equal(EnsembleDeLettres e) {
        boolean ok = true;
        for (int i = 0; i < present.length; i++) {
            ok &= (this.present[i] == e.present[i]);
        }
        return ok;
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