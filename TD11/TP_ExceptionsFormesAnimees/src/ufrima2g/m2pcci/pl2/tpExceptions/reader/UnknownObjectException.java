package ufrima2g.m2pcci.pl2.tpExceptions.reader;

/**
 *
 * @author Philippe Genoud - UJF Grenoble - Lab LIG-Steamer
 */
public class UnknownObjectException extends DessinFileReaderException {

    public UnknownObjectException(String formeType) {
        super(formeType + " est un type d'objet non connu");
    }
}
