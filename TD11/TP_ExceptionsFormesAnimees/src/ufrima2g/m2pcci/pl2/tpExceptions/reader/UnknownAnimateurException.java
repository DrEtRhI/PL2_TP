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
public class UnknownAnimateurException extends DessinFileReaderException{
    
    public UnknownAnimateurException(String animationType){
        super(animationType + " : est une animation on connue");
    }
    
}
