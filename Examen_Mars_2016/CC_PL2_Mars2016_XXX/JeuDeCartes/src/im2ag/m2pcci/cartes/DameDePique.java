package im2ag.m2pcci.cartes;

/**
 *
 * @author Philippe Genoud - UGA Université Grenoble Alpes - Lab LIG STeamer
 */
public class DameDePique {

    // le tableau des joueurs
    private static final Joueur[] lesJoueurs = {
        new Joueur("Ouest"),
        new Joueur("Est"),
        new Joueur("Nord"),
        null 
    };

    // le jeu de cartes utilisé pour jouer
    private static final JeuDeCartes jeu = new JeuDeCartes();

    public static void main(String[] args) {
        
        // creation du 4ème joueur, si il a donné son nom comme argument de la 
        // ligne de commandes, on utilise celui-ci, sinon on utilise le nom
        // par défaut "Sud".
        String nomJoueur;
        if (args.length > 0) {
            nomJoueur = args[0];
        } else {
            nomJoueur = "Sud";
        }
        lesJoueurs[3] = new Joueur(nomJoueur);
        
        // on melange le jeu
        jeu.melanger();
        
        // on distribue les cartes aux différents joueurs
        for (int i = 0; i < JeuDeCartes.NB_CARTES_COULEUR; i++) {
            for (Joueur lesJoueur : lesJoueurs) {
                lesJoueur.prendre(jeu.distribuerCarte());
            }
        }
        
        // affiche les jeux triés des différents joueurs
        for (Joueur joueurs : lesJoueurs){
            System.out.println(joueurs.getNom());
            System.out.println(joueurs.toString());
        }
        
        // ensuite il faudrait programmer une partie du 4ème joueur contre
        // les joueurs Ouest, Nord, Est gérés par la machine... 
        // mais cela, c'est une autre histoire....
    }
}
