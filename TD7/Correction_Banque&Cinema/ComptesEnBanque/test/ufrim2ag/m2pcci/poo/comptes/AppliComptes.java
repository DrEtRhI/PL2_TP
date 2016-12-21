package ufrim2ag.m2pcci.poo.comptes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Un programme interactif de test de la classe Compte
 *
 * @author Philippe Genoud - UGA - Lab LIG-Steamer
 */
public class AppliComptes {

    /**
     * une liste pour stocker les comptes crées.
     */
    private static final List<Compte> listeComptes = new ArrayList<>();

    private static final Scanner sc = new Scanner(System.in);

    private static int lireEntier(String message) {
        System.out.print(message);
        return sc.nextInt();
    }

    private static String lireChaine(String message) {
        System.out.print(message);
        return sc.next();
    }

    private static double lireDouble(String message) {
        System.out.print(message);
        return sc.nextDouble();
    }

    private static boolean lireOuiNon(String message) {
        System.out.print(message);
        return sc.next().toUpperCase().startsWith("O");
    }

    /**
     * affiche un menu proposant les différentes opérations possibles sur la
     * liste
     */
    private static void affMenu() {
        System.out.println("-----------------------------------------------");
        System.out.println("1 : créer un compte ");
        System.out.println("2 : débiter un compte ");
        System.out.println("3 : créditer un compte ");
        System.out.println("4 : transférer d'un compte vers l'autre ");
        System.out.println("5 : afficher un compte ");
        System.out.println("6 : consulter le solde d'un compte ");
        System.out.println("0 : quitter l'application ");

    }

    private static void creerCompte() {
        String nomTit = lireChaine("Nom du titulaire : ");
        String prenomTit = lireChaine("Prénom du titulaire : ");
        String adrTit = lireChaine("Adresse du titulaire : ");
        double depotInitial = lireDouble("depot initial : ");
        double dec = lireDouble("découvert autorisé : ");
        double debit = lireDouble("débit maximal autorisé : ");
        listeComptes.add(new Compte(new Personne(nomTit, prenomTit, adrTit),depotInitial, dec, debit));
    }

    private static Compte selectionnerCompte() {
        int no = lireEntier("Numero du compte : ");
        if (no > 0 && no <= listeComptes.size()) {
            return listeComptes.get(no - 1);
        } else {
            System.out.println("numéro de compte incorrect");
            return null;
        }
    }

    private static void debiterCompte() {
        Compte c = selectionnerCompte();
        if (c != null) {
            double s = lireDouble("montant à débiter :");
            c.debiter(s);
        }
    }

    private static void crediterCompte() {

        Compte c = selectionnerCompte();
        if (c != null) {
            double s = lireDouble("montant à débiter :");
            c.crediter(s);
        }
    }

    private static void virement() {
        System.out.println("Compte à débiter : ");
        Compte c1 = selectionnerCompte();
        if (c1 != null) {
            System.out.println("Compte à créditer : ");
            Compte c2 = selectionnerCompte();
            if (c2 != null) {
                double s = lireDouble("montant à transférer :");
                c1.virerVers(c2, s);
            }
        }
    }

    private static void afficherCompte() {
        Compte c = selectionnerCompte();
        if (c != null) {
            System.out.println(c);
        }
    }

    private static void afficherSoldeCompte() {
        Compte c = selectionnerCompte();
        if (c != null) {
            System.out.println("Solde  : " + c.getSolde());
        }
    }

    public static void main(String[] args) {

        boolean encore = true;
        do {
            affMenu();
            int rep = lireEntier("\nVotre choix : ");
            switch (rep) {
                case 0:
                    encore = !lireOuiNon("Voulez vous vraimment quitter l'application O/N ");
                    break;
                case 1:
                    creerCompte();
                    break;
                case 2:
                    debiterCompte();
                    break;
                case 3:
                    crediterCompte();
                    break;
                case 4:
                    virement();
                    break;
                case 5:
                    afficherCompte();
                    break;
                case 6:
                    afficherSoldeCompte();
                    break;
                default:
                    System.out.println("Mauvais numéro de commande");
                    break;
            } // fin du switch
        } while (encore);
    }

}
