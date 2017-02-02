/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testabstractclass;

/**
 *
 * @author EtRhI_PC
 */
public class ClasseC {
    
    ClasseA[] tab = new ClasseA[30];
    
    
    public ClasseC(){
        int i = 1;
        tab[i] = new ClasseB();
    }
}
