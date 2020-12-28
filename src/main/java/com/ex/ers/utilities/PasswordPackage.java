package com.ex.ers.utilities;

/**
 * Represents a password hash and its salt
 */
public class PasswordPackage {
    private String salt;
    private String hash;

    public PasswordPackage(){}

    public PasswordPackage(String salt, String hash) {
        this.salt = salt;
        this.hash = hash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
