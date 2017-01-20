/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufrima2g.m2pcci.pl2.tpExceptions.reader;

/**
 *
 * @author thierrye
 */
public class NombreArgumentsAnimationIncorrect extends DessinFileReaderException {
    
    /**
     * 
     * @param typeAnimation le type de l'objet décrit
     * @param nbArgsEffectifs le nombre d'argument contenus dans la description
     * @param nbArgsAttendus le nombre d'arguments que la description devrait contenir
     */
    public NombreArgumentsAnimationIncorrect(String typeAnimation, int nbArgsEffectifs, int nbArgsAttendus){
        super("nombre d'arguments incorrect\n"
                + nbArgsEffectifs + " arguments au lieu de " + nbArgsAttendus 
                + " attendus  pour un " + typeAnimation);
    }
}
