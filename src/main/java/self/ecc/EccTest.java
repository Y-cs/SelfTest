package self.ecc;


import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

/**
 * @Author: yuanchangshuai
 * @Date: 2020/11/25 14:23
 */
@Slf4j
public class EccTest {

    public static void main(String[] args) throws Exception {
        long time1 = System.currentTimeMillis();

        String data = "明文信息";

        System.out.println("明文:" + data);

        GenerateKey.Keys generateKey = GenerateKey.getGenerateKey();

        long time2 = System.currentTimeMillis();
        System.out.println("-----------------------------------------------------------------");
        System.out.println("生成密钥用时:"+(time2-time1));
        System.out.println("公钥:" + generateKey.publicKey);
        System.out.println("私钥:" + generateKey.privateKey);

        byte[] datas = data.getBytes(StandardCharsets.UTF_8);
        byte[] encrypt = EccUtils.encrypt(datas, generateKey.publicKey);

        long time3 = System.currentTimeMillis();
        System.out.println("-----------------------------------------------------------------");
        System.out.println("加密用时:"+(time3-time2));
        String encryptStr = new String(encrypt, StandardCharsets.UTF_8);
        System.out.println("密文:"+encryptStr);

        byte[] decrypt = EccUtils.decrypt(encrypt, generateKey.privateKey);

        long time4 = System.currentTimeMillis();
        System.out.println("-----------------------------------------------------------------");
        System.out.println("解密用时:"+(time4-time3));
        data = new String(decrypt, StandardCharsets.UTF_8);
        System.out.println("解密结果:"+data);
    }

}
