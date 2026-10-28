package ru.zainab.customerservice.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Service
public class JwtService {
    private PublicKey publicKey;
    @Value("${jwt.public-key-path}")
    private String publicKeyPath;

    @PostConstruct
    public void init() throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        Path path = Paths.get(publicKeyPath);
        String text = Files.readString(path);
        text = text.replace("-----BEGIN PUBLIC KEY-----", "");
        text = text.replace("-----END PUBLIC KEY-----", "");
        byte[] keyBytes = Base64.getMimeDecoder().decode(text);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        publicKey = keyFactory.generatePublic(keySpec);
    }

    public boolean isTokenValid(String token) {
       try {
           Jwts.parser()
                   .verifyWith(publicKey)
                   .build()
                   .parseSignedClaims(token);
           return true;
       }catch (JwtException e){
           return false;
       }
    }
}
