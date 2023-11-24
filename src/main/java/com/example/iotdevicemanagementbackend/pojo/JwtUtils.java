package com.example.iotdevicemanagementbackend.pojo;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Calendar;

public class JwtUtils {
    public static final int TOKEN_TIMEOUT = 3600;
    public static final String SECRET_KEY = "ILoveZheJiangUniversity!";

    public static String createToken(int userId) {
        Calendar expirationTime = Calendar.getInstance();
        expirationTime.add(Calendar.SECOND, TOKEN_TIMEOUT);
        String token = JWT.create()
                .withClaim("uid", userId)
                .withExpiresAt(expirationTime.getTime())
                .sign(Algorithm.HMAC256(SECRET_KEY));
        return token;
    }

    public static int verify(String token) {
        try {
            DecodedJWT verify = JWT.require(Algorithm.HMAC256(SECRET_KEY)).build().verify(token);
            return 0;
        } catch (SignatureVerificationException e) {
            return 1;
        } catch (TokenExpiredException e) {
            return 2;
        } catch (AlgorithmMismatchException e) {
            return 3;
        } catch (Exception e) {
            return 4;
        }
    }
}
