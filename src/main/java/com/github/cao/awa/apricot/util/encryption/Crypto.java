package com.github.cao.awa.apricot.util.encryption;

import com.github.cao.awa.apricot.anntations.Stable;
import com.github.cao.awa.apricot.util.time.TimeUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Stable
public class Crypto {
    private static final Logger DEBUG = LogManager.getLogger("Debugger");
    private static final byte[] KEY_VI = "0000000000000000".getBytes();

    static {
        Security.setProperty("crypto.policy",
                             "unlimited"
        );
    }

    public static byte[] decrypt(byte[] content, byte[] cipher) throws Exception {
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(Cipher.DECRYPT_MODE,
                      new SecretKeySpec(cipher,
                                        "AES"
                      ),
                      new IvParameterSpec(KEY_VI)
        );
        return instance.doFinal(content);
    }

    public static byte[] encrypt(byte[] content, byte[] cipher) throws Exception {
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(Cipher.ENCRYPT_MODE,
                      new SecretKeySpec(cipher,
                                        "AES"
                      ),
                      new IvParameterSpec(KEY_VI)
        );
        return instance.doFinal(content);
    }

    public static byte[] encrypt(byte[] content, RSAPublicKey publicKey) throws Exception {
        Cipher instance = Cipher.getInstance("RSA");
        instance.init(Cipher.ENCRYPT_MODE,
                      publicKey
        );
        return instance.doFinal(content);
    }

    public static byte[] decrypt(byte[] content, RSAPrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE,
                    privateKey
        );
        return cipher.doFinal(content);
    }
}
