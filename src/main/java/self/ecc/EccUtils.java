package self.ecc;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @Author: yuanchangshuai
 * @Date: 2020/11/25 17:24
 */
public class EccUtils {

    public static byte[] encrypt(byte[] data, String publicKey)
            throws Exception {
        byte[] keyBytes = Base64.decodeBase64(publicKey);

        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(EccConstant.ALGORITHM);

        ECPublicKey pubKey = (ECPublicKey) keyFactory.generatePublic(x509KeySpec);

        Cipher cipher = Cipher.getInstance(EccConstant.TRANSFORMATION, EccConstant.PROVIDER);
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);

        return cipher.doFinal(data);
    }

    public static byte[] decrypt(byte[] data, String privateKey) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(privateKey);

        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(EccConstant.ALGORITHM);

        ECPrivateKey priKey = (ECPrivateKey) keyFactory.generatePrivate(pkcs8KeySpec);

        Cipher cipher = Cipher.getInstance(EccConstant.TRANSFORMATION, EccConstant.PROVIDER);
        cipher.init(Cipher.DECRYPT_MODE, priKey);

        return cipher.doFinal(data);
    }

}
