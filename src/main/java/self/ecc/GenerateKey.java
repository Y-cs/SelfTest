package self.ecc;

import org.apache.commons.codec.binary.Base64;

import java.io.Serializable;
import java.security.*;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;

/**
 * @Author: yuanchangshuai
 * @Date: 2020/11/25 14:52
 */
public class GenerateKey implements Serializable {

    private final static String ALGORITHM = EccConstant.ALGORITHM;
    private final static String PROVIDER = EccConstant.PROVIDER;

    static class Keys {
        public String publicKey;
        public String privateKey;
    }

    static {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    }

    public static Keys getGenerateKey() throws NoSuchProviderException, NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM, PROVIDER);
        keyPairGenerator.initialize(256, new SecureRandom());
        KeyPair kp = keyPairGenerator.generateKeyPair();
        ECPublicKey publicKey = (ECPublicKey) kp.getPublic();
        ECPrivateKey privateKey = (ECPrivateKey) kp.getPrivate();
        Keys keys = new Keys();
        keys.publicKey=Base64.encodeBase64String(publicKey.getEncoded());
        keys.privateKey=Base64.encodeBase64String(privateKey.getEncoded());
        return keys;
    }

}
