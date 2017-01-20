package ufrima2g.m2pcci.pl2.tpExceptions.reader;

/**
 * racine de la hiérarchies d'exceptions pouvant être provoquées
 * lors de la lecture d'un fichier de description de formes.
 * 
 * @author Philippe Genoud - UJF Grenoble - Lab LIG-Steamer
 */
public class DessinFileReaderException extends Exception{

    public DessinFileReaderException() {
    }
    
    public DessinFileReaderException(String mess) {
        super(mess);
    }
}
