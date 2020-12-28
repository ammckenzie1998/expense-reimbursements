package com.ex.ers.tests;

import com.ex.ers.utilities.HashingUtil;
import com.ex.ers.utilities.PasswordPackage;
import org.apache.commons.codec.DecoderException;
import org.junit.Assert;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class PasswordHashingTest {

    @Test
    public void shouldCreateAPasswordPackage(){
        HashingUtil hashingUtil = new HashingUtil();
        try {
            PasswordPackage p = hashingUtil.hashPassword("password");
            Assert.assertNotNull(p);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (DecoderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldSuccessfullyCompare(){
        HashingUtil hashingUtil = new HashingUtil();
        try {
            PasswordPackage p = hashingUtil.hashPassword("password");
            Assert.assertTrue(hashingUtil.comparePassword("password",p.getSalt(),p.getHash()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException | DecoderException e) {
            e.printStackTrace();
        }
    }
}
