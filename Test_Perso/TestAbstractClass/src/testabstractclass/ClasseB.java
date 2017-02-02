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
public class ClasseB extends ClasseA{

    
    public ClasseB(){
        System.out.println("Constructeur classe B");
    }
    
    @Override
    public void message() {
        System.out.println("Méthode message() redéfinie dans B");
    }
    
}
