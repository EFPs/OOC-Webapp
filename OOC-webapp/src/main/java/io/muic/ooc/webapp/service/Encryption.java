package io.muic.ooc.webapp.service;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Created by Boat on 3/8/2018 AD.
 */
public class Encryption {

    private static int logRounds = 12;



    public static String encoder(String password){
            return BCrypt.hashpw(password, BCrypt.gensalt(logRounds));
        }

        public static boolean hashChecker(String password ,String hash){
            return BCrypt.checkpw(password, hash);
        }


}
