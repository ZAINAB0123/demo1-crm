package ru.zainab.authservice.secret;

import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtService {
    private PrivateKey privateKey;
    @Value("${jwt.private-key-path}")
    private String privateKeyPath;
    @Value("${jwt.access-token-expiration}")
    private Duration accessTokenExpiration;

    @PostConstruct
    public void init() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        Path path = Paths.get(privateKeyPath);
        String text = Files.readString(path);
        text = text.replace("-----BEGIN PRIVATE KEY-----", "");
        text = text.replace("-----END PRIVATE KEY-----", "");
        byte[] keyBytes = Base64.getMimeDecoder().decode(text);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        privateKey = keyFactory.generatePrivate(keySpec);
    }

    public String generateAccessToken(Authentication authentication) {
        Instant now = Instant.now();
        return Jwts.builder()
                .subject(authentication.getName())
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(accessTokenExpiration)))
                .signWith(privateKey)
                .compact();
    }

    public long getAccessTokenExpirationSeconds() {
        return accessTokenExpiration.getSeconds();
    }

}

