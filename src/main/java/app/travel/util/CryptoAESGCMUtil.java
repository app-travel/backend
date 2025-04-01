package app.travel.util;

import app.travel.value.AppCoreValue;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.stereotype.Component;
import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CryptoAESGCMUtil {

    static{
        Security.addProvider(new BouncyCastleProvider());
    }

    AppCoreValue appCoreValue;

    static CryptoAESGCMUtil cryptoAESGCMUtil;
    static SecretKey secretKey;

    @PostConstruct
    void init(){
        cryptoAESGCMUtil = this;
        secretKey = Keys.hmacShaKeyFor(cryptoAESGCMUtil.appCoreValue.getServerSecretKey().getBytes(StandardCharsets.UTF_8));
    }

    static String ALGORITHM = "AES/GCM/NoPadding";
    static String PROVIDER = "BC";
    static Integer TAG_LENGTH_BIT = 128;
    static Integer IV_LENGTH = 12;
    static byte[] IV = new byte[IV_LENGTH];

    public static String encode(String originalText) throws Exception {

        Cipher cipher = Cipher.getInstance(ALGORITHM, PROVIDER);

        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(TAG_LENGTH_BIT, IV);

        cipher.init(Cipher.ENCRYPT_MODE, secretKey, gcmParameterSpec);

        byte[] encryptedByte = cipher.doFinal(originalText.getBytes());

        return Base64.getEncoder().encodeToString(encryptedByte);
    }

    public static String decode(String encryptedText) throws Exception {

        Cipher cipher = Cipher.getInstance(ALGORITHM, PROVIDER);

        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(TAG_LENGTH_BIT, IV);

        cipher.init(Cipher.DECRYPT_MODE, secretKey, gcmParameterSpec);

        byte[] decryptedByte = cipher.doFinal(Base64.getDecoder().decode(encryptedText));

        return new String(decryptedByte);
    }

}
