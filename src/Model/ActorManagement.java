package Model;

/**
 * Created by USER on 3/11/2017.
 */
public class ActorManagement {
//    static String encryptPassword(String password){
//        String encrpytPassword = "";
//        int rand = (int)((Math.random()*(9)) + 1);
//        for(int i = password.length()-1 ; i >= 0 ; i--){
//            char iter = password.charAt(i);
//            if(iter >= 65 && iter <= 90){
//                iter = (iter + "").toLowerCase().charAt(0);
//            }else if(iter >= 97 && iter <= 122){
//                iter = (iter + "").toUpperCase().charAt(0);
//            }
//            iter = (char)(iter + rand);
//            encrpytPassword += iter;
//        }
//        encrpytPassword += rand;
//        return encrpytPassword;
//    }
//
//    static String decryptPassword(String encryptPassword){
//        String password = "";
//        int rand = Integer.parseInt(encryptPassword.charAt(encryptPassword.length()-1)+"");
//        for(int i = encryptPassword.length()-2 ; i >= 0 ; i--){
//            char iter = encryptPassword.charAt(i);
//            if(iter >= 65 && iter <= 90){
//                iter = (iter + "").toLowerCase().charAt(0);
//            }else if(iter >= 97 && iter <= 122){
//                iter = (iter + "").toUpperCase().charAt(0);
//            }
//            iter = (char)(iter - rand);
//            password += iter;
//        }
//        return password;
//    }
}
