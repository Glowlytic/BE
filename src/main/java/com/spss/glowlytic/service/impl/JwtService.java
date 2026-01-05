package com.spss.glowlytic.service.impl;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.signerKey}")
    private String SIGNER_KEY;

    public String generateToken(String username, boolean isRefresh) {
        try {
            JWSSigner signer = new MACSigner(SIGNER_KEY.getBytes());
            Instant now = Instant.now();
            Instant expirationTime = isRefresh
                    ? now.plus(7, ChronoUnit.DAYS)
                    : now.plus(15, ChronoUnit.MINUTES);

            JWTClaimsSet.Builder claimsBuilder = new JWTClaimsSet.Builder()
                    .subject(username)
                    .issueTime(Date.from(now))
                    .expirationTime(Date.from(expirationTime));
            claimsBuilder.claim("type", isRefresh ? "REFRESH" : "ACCESS");
            JWTClaimsSet claimsSet = claimsBuilder.build();
            SignedJWT signedJWT = new SignedJWT(
                    new JWSHeader(JWSAlgorithm.HS256),
                    claimsSet
            );
            signedJWT.sign(signer);
            return signedJWT.serialize();

        } catch (JOSEException e) {
            throw new RuntimeException("Error generating JWT", e);
        }
    }

    public Date extractExpiration(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            return signedJWT.getJWTClaimsSet().getExpirationTime();
        } catch (ParseException e) {
            throw new RuntimeException("Cannot parse JWT to extract expiration", e);
        }
    }

    public long getRemainingTime(String token) {
        Date expiration = extractExpiration(token);
        long diff = expiration.getTime() - System.currentTimeMillis();
        return diff > 0 ? diff : 0;
    }
}