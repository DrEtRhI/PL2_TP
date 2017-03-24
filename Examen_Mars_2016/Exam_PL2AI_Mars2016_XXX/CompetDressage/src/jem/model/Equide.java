package jem.model;

/**
 * Représente un cheval.
 *
 * @author Philippe Genoud - UGA Université Grenoble Alpes - Lab LIG STeamer
 */
public class Equide {

    private final String nom; // le nom du cheval 
    private final int age; // l'age (en années) du cheval 

    /**
     *
     * @param nom le nom du cheval
     * @param age l'age (en années) du cheval
     */
    public Equide(String nom, int age) {
        this.nom = nom;
        this.age = age;
    }

    public String getNom() {
        return nom;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return nom + " (" + age + " ans)";
    }
}
