package com.example.iotdevicemanagementbackend.pojo;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentPBEConfig;

public class JasyptUtils {
    public static final String PBE_ALGORITHMS_MD5_DES = "PBEWITHMD5ANDDES";

    public static String encrypt(String userPassword, String password) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        EnvironmentPBEConfig config = new EnvironmentPBEConfig();
        config.setAlgorithm(PBE_ALGORITHMS_MD5_DES);
        config.setPassword(password);
        encryptor.setConfig(config);
        return encryptor.encrypt(userPassword);
    }

    public static String decrypt(String userPassword, String password) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        EnvironmentPBEConfig config = new EnvironmentPBEConfig();
        config.setAlgorithm(PBE_ALGORITHMS_MD5_DES);
        config.setPassword(password);
        encryptor.setConfig(config);
        return encryptor.decrypt(userPassword);
    }
}
