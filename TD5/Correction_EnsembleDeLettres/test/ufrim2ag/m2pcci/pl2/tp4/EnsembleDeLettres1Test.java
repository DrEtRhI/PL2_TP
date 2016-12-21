/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufrim2ag.m2pcci.pl2.tp4;

import ufrim2ag.m2pcci.poo.ensembles.EnsembleDeLettres1;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author genoud
 */
public class EnsembleDeLettres1Test {

    //##########################################################################
    // méthodes utilitaires permettant de factoriser du code entre les différents
    // cas de test.
    //##########################################################################
    /**
     * vérifie la validité d'un ensemble vide. Les éléments de vérification
     * sont:
     *
     * - que le cardinal de l'ensemble est bien 0
     *
     * - que la représentation textuelle de l'ensemble est bien "{}".
     *
     * - qu'aucune lettre de l'alphabet n'est présente dans l'ensemble.
     *
     * @param e l'ensemble à vérifier
     */
    private void verifierVide(EnsembleDeLettres1 e) {
        assertTrue("ensemble non vide", e.estVide());
        assertEquals("{}", e.toString());
        assertEquals("cardinal non nul", 0, e.cardinal());
        for (char lettre = 'a'; lettre <= 'z'; lettre++) {
            assertFalse(lettre + " ne devrait pas appartenir à {}", e.contient(lettre));
        }
    }

    /**
     * vérifie la validité d'un ensemble. Les éléments de vérification sont :
     *
     * - que l'ensemble n'est pas vide
     *
     * - que le cardinal de l'ensemble est bien égal à la longueur attendue
     *
     * - que chaque lettre attendue dans l'ensemble est bien présente
     *
     * - que la représentation textuelle de l'ensemble est bien celle attendue.
     *
     * @param e l'ensemble à vérifier
     * @param letters un chaîne contenant toutes les lettres (et uniquement
     * elles) de l'ensemble
     * @param stringRep la représentation textuelle attendue pour l'ensemble
     */
    private void verifierEnsemble(EnsembleDeLettres1 e, String letters, String stringRep) {
        assertFalse(e.estVide());
        // on verifie que le cardinal de l'ensemble est correct
        assertTrue("cardinal n'est pas egal à la longueur de la chaine fournie ", e.cardinal() == letters.length());
        // on verifie que chaque caractère de la chaine est présent dans l'ensemble
        char[] chars = letters.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            assertTrue("absence de l'un des caractères dans l'ensemble", e.contient(chars[i]));
        }
        assertEquals(stringRep, e.toString());
    }

    //##########################################################################
    // Les cas de test
    //##########################################################################
    //--------------------------------------------------------------------------
    // Cas de test pour les constructeurs. On verifie que les ensembles crées 
    // sont bien conforme à ce qui est attendu. Ces cas de test, outre les
    // constructeurs vérifient aussi les méthodes estVide(), cardinal() et
    // contient().
    //--------------------------------------------------------------------------
    /**
     * Ce cas de test vérifie la construction d'un ensemble vide. Sont testés:
     *
     * - le fait que l'ensemble est bien vide - que son cardinal est nul - que
     * sa réprésentation textuelle est bien la chaîne "{}"
     */
    @Test
    public void testEnsembleVide() {
        verifierVide(new EnsembleDeLettres1());
        verifierVide(new EnsembleDeLettres1(""));
        verifierVide(new EnsembleDeLettres1(0));
    }

    /**
     * Ce cas de test vérifie qu'un ensemble construit à partir d'une chaîne de
     * caractères est bien conforme.
     *
     * Trois cas sont considérés:
     *
     * - création d'un ensemble avec la chaîne "abcdeflmh"
     *
     * - création d'un ensemble avec la chaîne "aB1cçdEfLm*h$"
     *
     * - création d'un ensemble avec la chaîne "aB1cçdEfLm*h$B1cçdEfLm*"
     */
    @Test
    public void testEnsembleString() {

        // vérification de la correction d'un ensemble construit à partir d'une
        // chaine ne contenant que des lettres minuscules sans répétition
        verifierEnsemble(new EnsembleDeLettres1("abcdeflmh"), "abcdeflmh", "{a,b,c,d,e,f,h,l,m}");

        // vérification de la correction d'un ensemble construit à partir d'une
        // chaine contenant des lettres minuscules et majuscules  sans répétition et aussi
        // des caractères non alphabétiques
        verifierEnsemble(new EnsembleDeLettres1("aB1cçdEfLm*h$"), "abcdeflmh", "{a,b,c,d,e,f,h,l,m}");

        // vérification de la correction d'un ensemble construit à partir d'une
        // chaine contenant des lettres minuscules et majuscules  avec répétition et aussi
        // des caractères non alphabétiques
        verifierEnsemble(new EnsembleDeLettres1("aB1cçdEfLm*h$B1cçdEfLm*"), "abcdeflmh", "{a,b,c,d,e,f,h,l,m}");
    }

    /**
     * Ce cas de test vérifie que lorsque l'on génére un ensemble de lettres au
     * hasard, il contient bien le nombre de lettres annoncé.
     */
    @Test
    public void testEnsembleHasard() {
        for (int i = 0; i <= EnsembleDeLettres1.NB_LETTRES_ALPHABET; i++) {
            assertEquals(i, new EnsembleDeLettres1(i).cardinal());
        }
    }

    //--------------------------------------------------------------------------
    // cas de test pour les méthodes redéfinies equals et hashcode.
    // on verifient qu'elles sont bien conforment aux spécification Java
    // (voir javadoc de java.lang.Object
    //--------------------------------------------------------------------------
    @Test
    public void testEquals() {
        EnsembleDeLettres1 e1 = new EnsembleDeLettres1("azerty");

        // equals est reflexive
        assertTrue(e1.equals(e1));
        // equals avec null donne toujours faux
        assertFalse(e1.equals(null));
        // equals avec un objet d'un autre type donne toujours faux 
        assertFalse(e1.equals("azerty"));

        // equals avec un objet ensemble different donne faux et est symétrique
        EnsembleDeLettres1 e2 = new EnsembleDeLettres1("azertyuiop");
        assertFalse(e1.equals(e2));
        assertFalse(e2.equals(e1));

        // equals avec objet ensemble de même valeur donne vrai est est symétrique
        e2 = new EnsembleDeLettres1("azerty");
        assertTrue(e1.equals(e2));
        assertTrue(e2.equals(e1));
    }

    @Test
    public void testHashCode() {
        EnsembleDeLettres1 e1 = new EnsembleDeLettres1("azerty");

        // teste que hashCode est invariant. Deux appels de hashCode sur le 
        // même objet renvoient toujours la même valeur
        int hash1 = e1.hashCode();
        int hash2 = e1.hashCode();
        assertEquals(hash1, hash2);

        // teste que hashcode pour deux  objets de même valeur donne une valeur
        // identique
        EnsembleDeLettres1 e2 = new EnsembleDeLettres1("azerty");
        assertEquals(hash1, e2.hashCode());

        // teste que hashCode pour deux objets de valeur différente donne une valeur
        // différente (ce test est discutable, car la spec ne le garanti pas, même
        // si la probabilité que les hashCode soient identiques doit être le plus
        // failbe possible).
        e2 = new EnsembleDeLettres1("azertyuio");
        assertNotEquals(hash1, e2.hashCode());
    }

    /**
     * teste que toutes les lettres d'un ensemble lui appartiennent et que
     * toutes les autres ne lui appartiennent pas
     */
    @Test
    public void testAppartient1() {
        String s1 = "azertyuiop";
        String s2 = "qsdfghjklmwxcvbn";
        EnsembleDeLettres1 el1 = new EnsembleDeLettres1(s1);

        // on verifie que chaque lettre de la chaine ayant servi a créér l'ensemble 
        // appartient à celui-ci
        char[] charsS1 = s1.toCharArray();
        for (int i = 0; i < charsS1.length; i++) {
            assertTrue("" + charsS1[i] + " devrait appartenir à {" + s1 + "}", el1.contient(charsS1[i]));
        }

        // on verifie que chaque lettre d'une chaîne totalement différente de la
        // premiere n'apartient pas à l'ensemble
        char[] charsS2 = s2.toCharArray();
        for (int i = 0; i < charsS2.length; i++) {
            assertFalse("" + charsS2[i] + " ne devrait pas appartenir à {" + s1 + "}", el1.contient(charsS2[i]));
        }
    }

    /**
     * teste qu'un caractère qui n'est pas une lettre n'appartient pas à un
     * ensemble
     */
    @Test
    public void testAppartient3() {
        String s1 = "azertyuiopqsdfghjklmwxcvbn";
        EnsembleDeLettres1 el1 = new EnsembleDeLettres1(s1);
        String s2 = "1234567890&#'{([-|]=)}";

        char[] charsS2 = s2.toCharArray();
        for (int i = 0; i < charsS2.length; i++) {
            assertFalse("" + charsS2[i] + " ne devrait pas appartenir à {" + s1 + "}", el1.contient(charsS2[i]));
        }
    }

    //--------------------------------------------------------------------------
    // cas de test pour l'ajout d'une lettre à un ensemble
    //--------------------------------------------------------------------------
    @Test
    public void testAjouter() {
        EnsembleDeLettres1 e = new EnsembleDeLettres1("azerty");
        e.ajouter('k');
        verifierEnsemble(e, "aekrtyz", "{a,e,k,r,t,y,z}");

        e.ajouter('H');
        verifierEnsemble(e, "aekrtyz", "{a,e,k,r,t,y,z}");
        e.ajouter('$');
        verifierEnsemble(e, "aekrtyz", "{a,e,k,r,t,y,z}");
    }

    //--------------------------------------------------------------------------
    // cas de test pour l'inclusion.
    //--------------------------------------------------------------------------
    /**
     * teste le cas où le premier ensemble est inclus dans le second
     */
    @Test
    public void testEstInclus1() {
        EnsembleDeLettres1 el1 = new EnsembleDeLettres1("azerty");
        EnsembleDeLettres1 el2 = new EnsembleDeLettres1("azertyuiop");
        assertTrue("{azerty} devrait être inclus dans {azertyuiop} ", el1.estInclus(el2));
        assertFalse("{azertyuiop} ne devrait être inclus dans {azerty}", el2.estInclus(el1));
    }

    /**
     * test inclusion avec un ensemble vide
     */
    @Test
    public void testEstInclus2() {
        EnsembleDeLettres1 el1 = new EnsembleDeLettres1("azerty");
        EnsembleDeLettres1 el3 = new EnsembleDeLettres1();
        assertTrue("{} devrait être inclus dans {azerty} ", el3.estInclus(el1));
        assertFalse("{azerty} ne devrait être inclus dans {}", el1.estInclus(el3));
    }

    /**
     * teste inclusion d'un ensemble avec lui même et avec un ensemble identique
     */
    @Test
    public void testEstInclus3() {
        EnsembleDeLettres1 el1 = new EnsembleDeLettres1("azerty");
        assertTrue("ensemble pas inclus dans lui même", el1.estInclus(el1));

        EnsembleDeLettres1 el2 = new EnsembleDeLettres1("azerty");
        assertTrue("ensemble pas inclus dans un ensemble identique", el1.estInclus(el2));
        assertTrue("ensemble indentique pas inclus dans l'ensemble", el2.estInclus(el1));
    }

    /**
     * teste inclusion de deux ensembles disjoints
     */
    @Test
    public void testEstInclus4() {
        EnsembleDeLettres1 el1 = new EnsembleDeLettres1("azerty");
        EnsembleDeLettres1 el2 = new EnsembleDeLettres1("qsdfghjk");
        assertFalse("ensemble pas inclus dans un ensemble disjoint", el1.estInclus(el2));
        assertFalse("ensemble disjoint pas inclus dans l'ensemble", el2.estInclus(el1));
    }

    //----------------------------------------------------------------------------
    // cas de test pour l'intersection
    // les trois méthodes d'intersection proposées son testées de manire identique.
    //----------------------------------------------------------------------------
    /**
     * test l'intersection de deux ensembles, 6 cas sont testés :
     *
     * - intersection d'un ensemble avec l'ensemble vide
     *
     * - intersection d'un ensemble avec lui même ou avec un ensemble identique
     *
     * - intersection d'un ensemble avec un ensemble identique
     *
     * -intersection de deux ensembles, le premier étant includ dans l'autre
     *
     * - intersection de deux ensemblesdisjoints,
     *
     * - intersection de deux ensembles, les ensembles etant différents mais non
     * disjoints
     *
     * Cette méthode est paramétrée par la méthode d'intersection à invoquer
     * (cela permet d'effectuer les mêmes tests avec les méthodes intersection&,
     * intersection2 et intersection3. Pour cela on utilsie l'API java
     * d'introspection (de reflection en anglais) qui permet de manipuler les
     * membres d'une classes en tant qu'objets.
     *
     * @param nomMethode nom de la methode d'intersection à exécuter
     */
    private void testIntersection(String nomMethode) {
        try {
            EnsembleDeLettres1 eVide = new EnsembleDeLettres1();
            EnsembleDeLettres1 ens1 = new EnsembleDeLettres1("azerty");
            EnsembleDeLettres1 ens2;
            EnsembleDeLettres1 ensInter;

            // on utilise l'API de reflection pour récupérer et ensuite exécuter la méthode
            // d'intersection "nomMethode"
            Method m = EnsembleDeLettres1.class.getMethod(nomMethode, EnsembleDeLettres1.class);

            // cas 1: teste de l'intersection avec l'ensemble vide
            ensInter = (EnsembleDeLettres1) m.invoke(ens1, eVide);
            assertEquals(eVide, ensInter);
            // on vérifie que c'est symétrique
            ensInter = (EnsembleDeLettres1) m.invoke(eVide, ens1);
            assertEquals(eVide, ensInter);

            // cas 2: teste de l'intersection de l'ensemble avec lui même
            ensInter = (EnsembleDeLettres1) m.invoke(ens1, ens1);
            assertEquals(ens1, ensInter);

            // cas 3: teste l'intersection de l'ensemble avec un ensemble identique
            ens2 = new EnsembleDeLettres1("azerty");
            ensInter = (EnsembleDeLettres1) m.invoke(ens1, ens2);
            assertEquals(ens1, ensInter);
            // on vérifie que c'est symétrique
            ensInter = (EnsembleDeLettres1) m.invoke(ens2, ens1);
            assertEquals(ens1, ensInter);

            // cas 4: teste que quand le premier ensemble est inclus dans le second
            // l'intersection correspond bien au premier et que l'ensemble obtenu
            // est bien un ouvel objet.
            ens2 = new EnsembleDeLettres1("azertyuiop");
            ensInter = (EnsembleDeLettres1) m.invoke(ens1, ens2);
            assertEquals(ens1, ensInter);
            assertNotSame(ens1, ensInter);
            // on verifie que c'est symétrique
            ensInter = (EnsembleDeLettres1) m.invoke(ens2, ens1);
            assertEquals(ens1, ensInter);

            // cas 5: teste que l'intersection de deux ensembles disjoints est bien vide
            ens2 = new EnsembleDeLettres1("qsdfghj");
            assertTrue("intersection d'ensemble disjoints n'est pas vide ", ((EnsembleDeLettres1) m.invoke(ens1, ens2)).estVide());
            // on vérifie que c'est symétrique
            assertTrue("intersection d'ensemble disjoints n'est pas vide ", ((EnsembleDeLettres1) m.invoke(ens2, ens1)).estVide());

            // cas 6: teste l'intersection de de deux ensembles non disjoint
            String inter = "wxcvbn";
            ens1 = new EnsembleDeLettres1("azerty" + inter);
            ens2 = new EnsembleDeLettres1("qsdfgh" + inter);
            EnsembleDeLettres1 expctedEns = new EnsembleDeLettres1(inter);
            ensInter = (EnsembleDeLettres1) m.invoke(ens1, ens2);
            assertEquals(expctedEns, ensInter);
            // on verifie que c'est symétrique
            ensInter = (EnsembleDeLettres1) m.invoke(ens2, ens1);
            assertEquals(expctedEns, ensInter);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            // invoke peut lancer ces exceptions, mais ici cela ne devrait jamais arriver
            fail("exception " + ex.getMessage() + "n'aurait pas du être lancée");
        }
    }

    /**
     * Teste la méthode intersection1 pour l'intersection de deux ensembles,
     */
    @Test
    public void testIntersection1() {
        testIntersection("intersection1");
    }

    /**
     * Teste la méthode intersection2 pour l'intersection de deux ensembles
     */
    @Test
    public void testIntersection2() {
        testIntersection("intersection2");
    }

    /**
     * Teste la méthode intersection3 pour l'intersection de deux ensembles.
     */
    @Test
    public void testIntersection3() {
        testIntersection("intersection3");
    }

    //--------------------------------------------------------------------------
    // Cas de test de l'union
    //--------------------------------------------------------------------------
    @Test
    public void testUnion() {
        EnsembleDeLettres1 e1 = new EnsembleDeLettres1("azerty");

        // teste que l'union d'un ensemble avec l'ensemble vide donne le même
        // ensemble
        EnsembleDeLettres1 eVide = new EnsembleDeLettres1();
        assertEquals(e1, e1.union(eVide));
        assertEquals(e1, eVide.union(e1));

        // teste que l'union d'un ensemble e2 inclus dans e3 donne bien e3
        EnsembleDeLettres1 e2 = new EnsembleDeLettres1("qwerty");
        EnsembleDeLettres1 e3 = new EnsembleDeLettres1("aeqrtwyz");
        assertEquals(e3, e1.union(e2));
        assertEquals(e3, e2.union(e1));

        // teste de l'union de deux ensembles disjoints
        e1 = new EnsembleDeLettres1("abcde");
        e2 = new EnsembleDeLettres1("qrstwxyz");
        e3 = new EnsembleDeLettres1("abcdeqrstwxyz");
        assertEquals(e3, e1.union(e2));
        assertEquals(e3, e2.union(e1));

        // teste l'union de deux ensembles dont l'intersection n'est pas vide
        e1 = new EnsembleDeLettres1("abcde");
        e2 = new EnsembleDeLettres1("bdhjklm");
        e3 = new EnsembleDeLettres1("abcdehjklm");
        assertEquals(e3, e1.union(e2));
        assertEquals(e3, e2.union(e1));
    }

    //--------------------------------------------------------------------------
    // Cas de test de la différence
    //--------------------------------------------------------------------------
    @Test
    public void testDifference() {
        EnsembleDeLettres1 e1 = new EnsembleDeLettres1("azerty");

        // teste la difference avec l'ensemble vide
        EnsembleDeLettres1 eVide = new EnsembleDeLettres1();
        assertEquals(e1, e1.difference(eVide));
        assertEquals(eVide, eVide.difference(e1));

        // teste la différence d'un ensemble avec lui même
        assertEquals(eVide, e1.difference(e1));

        // teste la différence avec un ensemble non disjoint
        EnsembleDeLettres1 e2 = new EnsembleDeLettres1("qwerty");
        assertEquals(new EnsembleDeLettres1("az"), e1.difference(e2));
        assertEquals(new EnsembleDeLettres1("qw"), e2.difference(e1));

        // teste la différence avec un ensemble incluant l'ensemble
        e2 = new EnsembleDeLettres1("azertybdw");
        assertEquals(eVide, e1.difference(e2));
        assertEquals(new EnsembleDeLettres1("bdw"), e2.difference(e1));
    }

    //--------------------------------------------------------------------------
    // Cas de test de l'union disjointe
    //--------------------------------------------------------------------------
    @Test
    public void testUnionDisjointe() {

        EnsembleDeLettres1 e1 = new EnsembleDeLettres1("azerty");

        // teste union disjointe avec un ensemble vide
        EnsembleDeLettres1 eVide = new EnsembleDeLettres1();
        assertEquals(e1, e1.unionDisjointe(eVide));
        assertEquals(e1, eVide.unionDisjointe(e1));
        assertEquals(eVide, eVide.unionDisjointe(eVide));

        // teste union disjointe avec d'un ensemble avec lui même
        assertEquals(eVide, e1.unionDisjointe(e1));
        // teste union disjointe avec d'un ensemble identique
        EnsembleDeLettres1 e2 = new EnsembleDeLettres1("azerty");
        assertEquals(eVide, e1.unionDisjointe(e2));

        // teste union disjointe avec un ensembles disjoint
        e2 = new EnsembleDeLettres1("qsdfghjk");
        EnsembleDeLettres1 res = new EnsembleDeLettres1("azertyqsdfghjk");
        assertEquals(res, e1.unionDisjointe(e2));
        assertEquals(res, e2.unionDisjointe(e1));

        // teste union disjointe avec un ensemble non disjoint
        e2 = new EnsembleDeLettres1("ertyqsdf");
        res = new EnsembleDeLettres1("azqsdf");
        assertEquals(res, e1.unionDisjointe(e2));
        assertEquals(res, e2.unionDisjointe(e1));
    }

}
