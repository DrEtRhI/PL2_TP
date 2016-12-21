package ufrim2ag.m2pcci.poo.comptes;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author genoud
 */
public class CompteExceptTest {

    // objet personnes utilisés par tous les comptes de test
    private static final Personne TITULAIRE_1 = new Personne("NOM1", "Prenom1", "Ville1");
    private static final Personne TITULAIRE_2 = new Personne("NOM2", "Prenom2", "Ville2");

    // comptes déclarés en static pour le cas de test du numéro de compte
    private static final Compte COMPTE1 = new Compte(TITULAIRE_1);
    private static final Compte COMPTE2 = new Compte(TITULAIRE_2, 1000, 300, 500);
    private static final Compte COMPTE3 = new Compte(TITULAIRE_1, 1000, 500, 300);

    private Compte c1;

    @Before
    public void setUp() {
        c1 = new Compte(TITULAIRE_1, 1000, 500, 700);
    }

    //==========================================================================
    // Tests des constructeurs et de toutes les méthodes de consultation
    // (accesseurs).
    //
    // ces cas de test utilisent les comptes déclarés en static, en effet
    // ces comptes sont créés en premier (au chargement de la classe)
    // ce qui fait que l'on peut être sûr de leur numéros. Ce qui
    // n'est pas le cas des numéros de compte créés localement à une méthode
    // ces numéros dépendent du nombre de compte déjà créés et il peut
    // varier si on modifie le code des différentes méthodes de test.
    //==========================================================================
    /**
     * Test du constructeur CompteExcept(Personne titulaire).
     *
     * Vérifie que le compte COMPTE1 ainsi créé a bien les bons attributs.
     */
    @Test
    public void testCompteExceptTitulaire() {
        assertEquals("NOM1", COMPTE1.getTitulaire().getNom());
        assertEquals("Prenom1", COMPTE1.getTitulaire().getPrenom());
        assertEquals("Ville1", COMPTE1.getTitulaire().getAdresse());
        assertEquals(0, COMPTE1.getSolde(), 0.0);
        assertEquals(Compte.DEFAULT_DECOUVERT_MAX, COMPTE1.getDecouvertMaxAutorisé(), 0.0);
        assertEquals(Compte.DEFAULT_DEBIT_MAX, COMPTE1.getDebitMaxAutorisé(), 0.0);
        assertEquals(1, COMPTE1.getNumero());
        assertEquals(Compte.DEFAULT_DECOUVERT_MAX, COMPTE1.getDebitAutorise(), 0.0);
        assertFalse(COMPTE1.estAdecouvert());
        assertEquals(0, COMPTE1.getDecouvert(), 0.0);
    }

    /**
     * Test du constructeur CompteExcept(Personne titulaire, double
     * depotInitial, double decouvertMaxAutorisé, double debitMaxAutorisé).
     *
     * Vérifie que le compte COMPTE2 ainsi créé a bien les bons attributs.
     */
    @Test
    public void testCompteExceptTitulaireEtDetail1() {
        assertEquals("NOM2", COMPTE2.getTitulaire().getNom());
        assertEquals("Prenom2", COMPTE2.getTitulaire().getPrenom());
        assertEquals("Ville2", COMPTE2.getTitulaire().getAdresse());
        assertEquals(1000, COMPTE2.getSolde(), 0.0);
        assertEquals(500, COMPTE2.getDebitMaxAutorisé(), 0.0);
        assertEquals(300, COMPTE2.getDecouvertMaxAutorisé(), 0.0);
        assertEquals(2, COMPTE2.getNumero());
        assertEquals(500, COMPTE2.getDebitAutorise(), 0.0);
        assertFalse(COMPTE2.estAdecouvert());
        assertEquals(0, COMPTE2.getDecouvert(), 0.0);
    }

    /**
     * Test du constructeur CompteExcept(Personne titulaire, double
     * depotInitial, double decouvertMaxAutorisé, double debitMaxAutorisé).
     *
     * Vérifie qu'un compte ne peut être ainsi créé si le solde intial est
     * négatif.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCompteExceptTitulaireEtDetail2() {
        new Compte(TITULAIRE_1, -1000, 500, 700);
    }

    /**
     * Test du constructeur CompteExcept(Personne titulaire, double
     * depotInitial, double decouvertMaxAutorisé, double debitMaxAutorisé).
     *
     * Vérifie qu'un compte ne peut être ainsi créé si le decouvert autorisé est
     * négatif.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCompteExceptTitulaireEtDetail3() {
        new Compte(TITULAIRE_1, 1000, -500, 700);
    }

    /**
     * Test du constructeur CompteExcept(Personne titulaire, double
     * depotInitial, double decouvertMaxAutorisé, double debitMaxAutorisé).
     *
     * Vérifie qu'un compte ne peut être ainsi créé si le débit max autorisé est
     * négatif.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCompteExceptTitulaireEtDetail4() {
        new Compte(TITULAIRE_1, 1000, 500, -700);
    }

    //==========================================================================
    // test de modifieurs (setters)
    //==========================================================================
    /**
     * Teste la méthode setDebitMaxAutorisé d'un CompteExcept
     */
    @Test
    public void testSetDecouvertMaxAutorisé1() {
        System.out.println("setDecouvertMaxAutorisé");
        c1.setDecouvertMaxAutorisé(1000);
        assertEquals(1000, c1.getDecouvertMaxAutorisé(), 0.0);
        assertEquals(700, c1.getDebitAutorise(), 0.0);
    }

    /**
     * Teste que la méthode setDebitMaxAutorisé n'accepte pas un argument
     * négatif
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetDecouvertMaxAutorisé2() {
        c1.setDecouvertMaxAutorisé(-1000);
    }

    /**
     * Teste la méthode setDebitMaxAutorisé d'un CompteExcept
     */
    @Test
    public void testSetDebitMaxAutorisé1() {
        System.out.println("setDecouvertMaxAutorisé");
        c1.setDebitMaxAutorisé(1000);
        assertEquals(1000, c1.getDebitMaxAutorisé(), 0.0);
        assertEquals(1000, c1.getDebitAutorise(), 0.0);
    }

    /**
     * Teste que la méthode setDebitMaxAutorisé n'accepte pas un argument
     * négatif
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetDebitMaxAutorisé2() {
        c1.setDebitMaxAutorisé(-1000);
    }

    //==========================================================================
    // test des opération débiter, créditer, virer
    //==========================================================================
    /**
     * Ce test verifie que si le compte est crédité d'une somme positive son
     * solde a bien été incrémenté
     */
    @Test
    public void testCrediter1() {
        // on verifie que si le compte est crédité d'une somme positive
        // son solde a bien été incrémenté 
        double soldeInitial = c1.getSolde();
        c1.crediter(12345);
        assertEquals(soldeInitial + 12345, c1.getSolde(), 0.0);
    }

    /**
     * verifie qu'on ne peut crediter un comptes avec une valeur négative
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCrediter2() {
        c1.crediter(-55512345);
    }

    /**
     * Ce test verifie que si le compte est débité d'une somme positive son
     * solde a bien été décrémenté
     */
    @Test
    public void testDebiter1() {
        double soldeInitial = c1.getSolde();
        c1.debiter(450);
        assertEquals(soldeInitial - 450, c1.getSolde(), 0.0);
    }

    /**
     * verifie que l'on ne peut debiter un comptes avec une valeur négative
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDebiter2() {
        c1.debiter(-55);
    }

    /**
     * verifie que l'on peut pas débiter le compte d'un montant supérieur au
     * debit autorisé
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDebiter3() {
        c1.debiter(c1.getDebitAutorise() + 10);
    }

    /**
     * vérifie que on ne peut pas débiter un compte si la somme retirée fait
     * dépasser le découvert max autorisé
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDebiter4() {
        assertEquals(1000, c1.getSolde(), 0.0);
        c1.debiter(700);
        assertEquals(300, c1.getSolde(), 0.0);
        c1.debiter(700);
        assertEquals(-400, c1.getSolde(), 0.0);
        assertEquals(400, c1.getDecouvert(), 0.0);
        c1.debiter(200);  // erreur car le découvert max autorisé est 500
    }

    /**
     * vérifie que le virement d'un compte vers un autre modifie correctement le
     * solde du compte débiteur et du compte créditeur.
     */
    @Test
    public void testVirerVers1() {
        Compte c2 = new Compte(TITULAIRE_2);
        // le solde de c2 est 0
        // le solde du c1 est de 1000, le debit max autorisé 700, le découvert max autorisé 500
        c1.virerVers(c2, 700);
        assertEquals(700, c2.getSolde(), 0.0);
        assertEquals(300, c1.getSolde(), 0.0);
    }

    /**
     * vérifie que le virement d'un compte vers un autre ne peut être fait si la
     * somme à virer est négative.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testVirerVers2() {
        Compte c2 = new Compte(TITULAIRE_2);
        c1.virerVers(c2, -700);
    }

    /**
     * vérifie que le virement d'un compte vers un autre ne peut être fait si le
     * montant est supérieur au debit autorisé,
     */
    @Test(expected = IllegalArgumentException.class)
    public void testVirerVers3() {
        Compte c2 = new Compte(TITULAIRE_2);
        c1.virerVers(c2, c1.getDebitAutorise() + 10);
    }

    /**
     * vérifie que on ne peut pas faire un virement depuis un compte si la somme
     * retirée fait dépasser le découvert max autorisé
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDebiter5() {
        Compte c2 = new Compte(TITULAIRE_2);
        assertEquals(1000, c1.getSolde(), 0.0);
        c1.virerVers(c2, 700);
        assertEquals(300, c1.getSolde(), 0.0);
        assertEquals(700, c2.getSolde(), 0.0);
        c1.virerVers(c2, 700);
        assertEquals(-400, c1.getSolde(), 0.0);
        assertEquals(400, c1.getDecouvert(), 0.0);
        assertTrue(c1.estAdecouvert());
        assertEquals(1400, c2.getSolde(), 0.0);
        c1.virerVers(c2, 200);  // erreur car le découvert max autorisé est 500
    }

    /**
     *
     */
    @Test
    public void testGetNumero() {

        System.out.println("getNumero");
        assertEquals(1, COMPTE1.getNumero());
        assertEquals(2, COMPTE2.getNumero());
        assertEquals(3, COMPTE3.getNumero());
    }

}
