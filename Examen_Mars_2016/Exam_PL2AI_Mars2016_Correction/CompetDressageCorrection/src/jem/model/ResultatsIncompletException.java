/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jem.model;

/**
 *
 * @author Philippe Genoud - UGA Université Grenoble Alpes - Lab LIG STeamer
 */
public class ResultatsIncompletException extends RuntimeException {

    public ResultatsIncompletException(String mess) {
        super(mess);
    }

}
