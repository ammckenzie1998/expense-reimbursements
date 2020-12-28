package com.ex.ers.utilities;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

/**
 * Class to handle password hashing related actions
 */
public class HashingUtil {
    /**
     * Method to hash a password string. Returns a password package
     * @param password the desired password
     * @return a password package containing the salt and hash
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public PasswordPackage hashPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException, DecoderException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String saltString = Hex.encodeHexString(salt);

        KeySpec keySpec = new PBEKeySpec(password.toCharArray(), saltString.getBytes(), 65536, 64*8);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] hash = secretKeyFactory.generateSecret(keySpec).getEncoded();
        String hashString = Hex.encodeHexString(hash);

        PasswordPackage passwordPackage = new PasswordPackage(saltString,hashString);
        return passwordPackage;
    }

    /**
     * Compares a password string by converting it into a hash using a salt and testing for equality agains the expected hash
     * @param password the desired password
     * @param salt the salt used to create the expected hash
     * @param expectedHash the hash previously calculated
     * @return true if equal, false if not
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public boolean comparePassword(String password,String salt, String expectedHash) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 64*8);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] hash = secretKeyFactory.generateSecret(keySpec).getEncoded();
        String hashString = Hex.encodeHexString(hash);

        if(expectedHash.equals(hashString)){
            return true;
        }
        else{
            return false;
        }
    }
}
