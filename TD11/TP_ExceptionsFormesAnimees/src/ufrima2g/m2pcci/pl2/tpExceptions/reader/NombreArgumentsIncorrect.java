package ufrima2g.m2pcci.pl2.tpExceptions.reader;

/**
 * Exception levée lorsque lors de la lecture d'une description de formes
 * le nombres d'arguments décrivant l'objet ne correspond pas à ce qui est
 * attendu.
 * 
 * @author Philippe Genoud - UJF Grenoble - Lab LIG-Steamer
 */
public class NombreArgumentsIncorrect extends DessinFileReaderException {

    /**
     * 
     * @param typeObjet le type de l'objet décrit
     * @param nbArgsEffectifs le nombre d'argument contenus dans la description
     * @param nbArgsAttendus le nombre d'arguments que la description devrait contenir
     */
    public NombreArgumentsIncorrect(String typeObjet, int nbArgsEffectifs, int nbArgsAttendus) {
        super("nombre d'arguments incorrect\n"
                + nbArgsEffectifs + " arguments au lieu de " + nbArgsAttendus 
                + " attendus  pour un " + typeObjet 
        );
    }
}
