package jem.model;

import java.util.ArrayList;
import java.util.List;
import javax.security.auth.login.FailedLoginException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test unitaires pour la classe GestionnaireEpreuve.
 *
 * @author Philippe Genoud (Université Grenoble Alpes - laboratoire LIG STeamer)
 */
public class GestionnaireEpreuveTest {

    static final int NB_JUGES = 7;
    static final int NB_CAVALIERS = 5;

    static List<Cavalier> engages;
    static List<Juge> juges;

    private GestionnaireEpreuve ge;

    /**
     * crée une liste de cavaliers avec leur monture. Les cavaliers de cette
     * liste sont : Prénom1 NOM1 pour Cheval1 Prénom2 NOM2 pour Cheval2 ...
     *
     * @param nbCavaliers le nombre de cavaliers de la liste
     * @return la liste des cavaliers
     */
    private static List<Cavalier> creerListeCavaliers(int nbCavaliers) {
        List<Cavalier> res = new ArrayList<>();
        for (int i = 1; i <= nbCavaliers; i++) {
            res.add(new Cavalier("CAVALIER" + i, "Prénom" + i, "FR", new Equide("Cheval" + i, 10 + i)));
        }
        return res;
    }

    /**
     * crée une liste de Juges. Les juges de cette liste sont :<br>
     * <code>
     * ------------------------------------<br>
     * | NO| NOM | PRENOM| PAYS| PASSWORD |<br>
     * ------------------------------------<br>
     * | 1 |JUGE1| Juju1 | USA | password1|<br>
     * ------------------------------------<br>
     * | 2 |JUGE2| Juju2 | USA | password2|<br>
     * ------------------------------------<br>
     * | 3 |JUGE1| Juju3 | ... | ... |<br>
     *
     * @param nbCavaliers le nombre de cavaliers de la liste
     * @return la liste des cavaliers
     */
    private static List<Juge> creerListeJuges(int nbJuges) {
        List<Juge> res = new ArrayList<>();
        for (int i = 1; i <= nbJuges; i++) {
            res.add(new Juge(i, "JUGE" + i, "Juju" + i, "USA", "password" + i));
        }
        return res;
    }

    @BeforeClass
    public static void setUpClass() {
        // code exécuté au chargement de la classe de Test
        // permet d'initialiser la liste des engagés
        System.out.println("before class");
        engages = creerListeCavaliers(NB_CAVALIERS);
        juges = creerListeJuges(NB_JUGES);
    }

    @Before
    public void setUp() {
        // code exécuté avant chaque méthode de test, permet de réinitialiser
        // le gestionnaire d'épreuve
        ge = new GestionnaireEpreuve(juges, engages);
    }

    /**
     * Méthode utilitaire : affecte au cavalier en piste une note identique pour
     * tous les juges.
     *
     * @param note la note à attribuer
     */
    public void attribuerNote(double note) {
        for (int noJuge = 1; noJuge <= ge.getNbJuges(); noJuge++) {
            ge.enregistrerNote(noJuge, note);
        }
    }

    /**
     * Test des méthode getNbJuge et getJuge.
     */
    @Test
    public void testJuges() {
        int nbrejuges = ge.getNbJuges();
        assertEquals(7, nbrejuges);
        for (int i = 1; i <= nbrejuges; i++) {
            Juge juge = ge.getJuge(i);
            assertEquals("JUGE" + i, juge.getNom());
            assertEquals("Juju" + i, juge.getPrenom());
        }
    }

    /**
     * teste la méthode pour authentifier un juge
     */
    @Test
    public void testAuthentifier() throws FailedLoginException {
        Juge juge = ge.authentifier(1,"password1");
        assertEquals("JUGE1", juge.getNom());
        assertEquals("Juju1", juge.getPrenom());
        try {
            ge.authentifier(1,"password1");
            fail("juge1 est déja connecté une exception devrait être lancée");
        } catch (FailedLoginException ex) {
            // si l'exception est lancée, tout est OK
        }
        try {
            ge.authentifier(2,"passwrd2");
            fail("le password est incorrect une exception devrait être lancée");
        } catch (FailedLoginException ex) {
            // si l'exception est lancée, tout est OK
        }
    }

    /**
     * Test de la méthode enregistrerNote.
     */
    @Test
    public void testEnregistrerNote() {
        System.out.println("enregistrerNote");
        // fait entrer le premier cavalier en piste
        ge.cavalierSuivant();
        // enregistre les notes pour le cavalier en piste
        // pour chaque juge on vérifie que la note enregistrée est la bonne
        double somme = 0;
        for (int noJuge = 1; noJuge <= NB_JUGES; noJuge++) {
            double note = Math.random() * 100;
            somme = somme + note;
            ge.enregistrerNote(noJuge, note);
            assertEquals(note, ge.getResultatCavalierEnPiste().getNote(noJuge), 0.0);
        }
        // une fois toutes les notes enregistrées on vérifie que la note finale
        // pour le cavalier en piste est bien la moyenne des notes entrées.
        assertEquals(somme / NB_JUGES, ge.getResultatCavalierEnPiste().getNoteFinale(), 0.0);
    }

    /**
     * Test des methodes getCavalierEnPiste, cavalierSuivant et
     * epreuveEstTerminee.
     */
    @Test
    public void testGetCavalierEnPiste() {
        System.out.println("getCavalierEnPiste");

        int noCaval = 0;
        Cavalier cavalier;
        while ((cavalier = ge.cavalierSuivant()) != null) {
            noCaval++;
            assertEquals(cavalier, ge.getCavalierEnPiste());
            assertEquals("CAVALIER" + noCaval, cavalier.getNom());
            attribuerNote(80);
        }
        assertEquals(NB_CAVALIERS, noCaval);
    }

    @Test
    public void testGetCavalierEnPiste1() {
        System.out.println("getCavalierEnPiste");

        int noCaval = 0;
        while (!ge.epreuveTerminee()) {
            noCaval++;
            Cavalier cavalier = ge.cavalierSuivant();
            assertEquals(cavalier, ge.getCavalierEnPiste());
            assertEquals("CAVALIER" + noCaval, cavalier.getNom());
            attribuerNote(80);
        }
        assertEquals(NB_CAVALIERS, noCaval);
    }

    /**
     * Test des méthodes enregistrerResultat, getClassementCavalierEnPiste et
     * getClassement.
     */
    @Test
    public void testEnregistrerResultat() {
        System.out.println("enregistrerResultat");
        // on verifie que le classement est vide au départ
        assertEquals(0, ge.getClassement().length);
        // fait entrer le premier cavalier en piste
        ge.cavalierSuivant();
        // on verifie que l'on ne peut pas passer au cavalier suivant si 
        // toutes les notes du cavalier n'on pas été attribuées
        try {
            ge.cavalierSuivant();
            fail("exception ResultatsIncompletException aurait du être levée");
        } catch (ResultatsIncompletException ex) {
            // OK l'exception a bien été levée
        }
        // on attribue la note de 60 au cavalier en piste (cavalier de nom NOM1)
        attribuerNote(60);
        // on verifie que le classement contient un résultat
        assertEquals(1, ge.getClassement().length);
        // on verifie que le classement provisoire du cavalier en piste est 1er
        assertEquals(1, ge.getClassementCavalierEnPiste());

        // on passe au cavalier suivant (cavalier de nom NOM2)
        ge.cavalierSuivant();
        // on lui attribue la note de 40 et on enregistre son résultat
        attribuerNote(40);
        // on verifie que le classement provisoire de ce cavalier en piste est 2ème
        assertEquals(2, ge.getClassementCavalierEnPiste());
        // on verifie qu'au classement provisoire il ya bien deux cavaliers classé et que
        // le cavalier NOM1 est bien premier
        Resultat[] classement = ge.getClassement();
        assertEquals(2, classement.length);
        assertEquals("CAVALIER1", classement[0].getCavalier().getNom());

        // on passe au cavaliers suivants (NOM3, NOM4 et NOM5)
        // en leur attribuant respectivement les notes 80, 35, 45) 
        ge.cavalierSuivant();  // cavalier NOM3
        attribuerNote(80);

        ge.cavalierSuivant();  // cavalier NOM4
        attribuerNote(35);

        ge.cavalierSuivant();  // cavalier NOM5
        attribuerNote(45);

        // on vérifie que le classement est bien le bon, c'est à dire 
        // 1: NOM3 avec note de 80
        // 2: NOM1 avec note de 60
        // 3: NOM5 avec note de 45
        // 4: NOM2 avec note de 40
        // 5: NOM4 avec note de 35
        classement = ge.getClassement();
        assertEquals("CAVALIER3", classement[0].getCavalier().getNom());
        assertEquals(80, classement[0].getNoteFinale(), 0.0);
        assertEquals("CAVALIER1", classement[1].getCavalier().getNom());
        assertEquals(60, classement[1].getNoteFinale(), 0.0);
        assertEquals("CAVALIER5", classement[2].getCavalier().getNom());
        assertEquals(45, classement[2].getNoteFinale(), 0.0);
        assertEquals("CAVALIER2", classement[3].getCavalier().getNom());
        assertEquals(40, classement[3].getNoteFinale(), 0.0);
        assertEquals("CAVALIER4", classement[4].getCavalier().getNom());
        assertEquals(35, classement[4].getNoteFinale(), 0.0);
    }

}
