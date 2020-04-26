package com.nortal.summeruni.blog.jwt;

import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class JwtSecretKey {

    private static final String secretKey = "nortal-summeruni-secret-key-that-is-extremly-secire-etc-etc-etc";

    public static SecretKey getSecretKeyForSigning() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }
}
