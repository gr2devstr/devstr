package com.devstr.services;

public interface EncryptionToken {
    public String encrypt(String text) throws Exception;

    public String decrypt(String encryptText) throws Exception;
}
