package com.houbenz.redditclone.security;

import com.houbenz.redditclone.exception.SpringRedditException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.*;

@Service
public class JwtProvider {


    private KeyStore keyStore;
    //MUST HIDDEN (its available only because of tests)
    private final String KEY= "a9afdecd4e7246c9a056a95f35f2bb34";
    private JwtParser parser;

    @PostConstruct
    public void init(){
/*
        try {
            keyStore=KeyStore.getInstance("JKS");
            InputStream resourceAsStream = getClass().getResourceAsStream("/spring.jks");
            keyStore.load(resourceAsStream,"mypassword".toCharArray());
        } catch (KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException e) {
           throw new SpringRedditException("Exception occured while reading the keystore");
        }*/

        parser =Jwts.parserBuilder().setSigningKey(KEY.getBytes()).build();


    }

    public String generateToken(Authentication authentication){

        User principal = (User) authentication.getPrincipal();
        return Jwts
                .builder()
                .setSubject(principal.getUsername())
                //.signWith(getPrivatekey())
                .signWith(Keys.hmacShaKeyFor(KEY.getBytes()))
                .compact();
    }


    public boolean validateToken(String jwt){

        try {
            parser.parseClaimsJws(jwt);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            throw new SpringRedditException("jwt exception "+e.getMessage());
        }
    }


    public String getUsernameByJwt(String token){
        Claims claims = parser.parseClaimsJws(token).getBody();

        return claims.getSubject();
    }

    /*


    this for encryption, my case i'm only using hashing

    //NOT NEEDED FOR NOW
    private Key getPrivatekey() {

        try {
            return  (PrivateKey) keyStore.getKey("spring","mypassword".toCharArray());
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw new SpringRedditException("Exception occured when trying to read the from keystore");
        }
    }

    //NOT NEEDED FOR NOW
    private Key getPublicKey(){

    }
*/



}
