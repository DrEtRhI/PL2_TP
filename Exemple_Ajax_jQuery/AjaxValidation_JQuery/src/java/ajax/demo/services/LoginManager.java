/*
 * Copyright UJF Grenoble - 2009
 */
package ajax.demo.services;

import java.util.HashMap;
import java.util.Map;

/**
 * Gère les login utilisateur
 * @author Philippe.Genoud@imag.fr
 */
public class LoginManager {

    /** les informations de login/passwd sont stockées dans une map
     *  ceci dans un but de simplicité, bien entendu  dans une application plus
     *  réaliste, il faudrait utiliser une table dans une base de données.
     */
    private static Map<String,String> accounts = new HashMap<String,String>();

    static {
        accounts.put("toto38","toto38passwd");
        accounts.put("titi","titipasswd");
    }

    /**
     * verifie que le userId n'est pas utilisé
     * @param userId le user id  à vérifier
     * @return true si le user id n'est pas utilisé, false sinon
     */
    public static boolean validateUserId(String userId) {
        return ! accounts.containsKey(userId.trim());
    }

    /**
     * crée un compte pour un nouvel user id
     * @param userId l'user id pour le compte à créer
     * @param passwd le mot de passe associé à ce user-id
     * @return true si le compte a pu être crée, false sinon
     */
    public static boolean createAccount(String userId, String passwd) {
        if (validateUserId(userId)) {
            accounts.put(userId.trim(),passwd);
            return true;
        }
        else
            return false;
    }
}
