package Server.Utility;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHasher {
    public static String hashPassword(String password){
        try {
            byte[] bytes = password.getBytes();
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] hashedBytes = md.digest(bytes);
            BigInteger integers = new BigInteger(1, hashedBytes);
            String newPassword = integers.toString(16);
            while(newPassword.length() < 32){
                newPassword = "0" + newPassword;
            }
            return newPassword;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
