package com.valid.login.util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class Util {
	
	public static final String ISSUER = "https://valid.com";
	public static final String KEY = "V@l1d2018*";
	public static final long EXP_TIME = 86400000; // 1 day	
	
	
	
	private Util() {
		//Default private constructor
	}

	public static String getHashedPassword(String password) {
		String hash = null;
		try {
		    MessageDigest digest = MessageDigest.getInstance("SHA-512");
		    digest.reset();
		    digest.update(password.getBytes(StandardCharsets.UTF_8));
		    hash = String.format("%040x", new BigInteger(1, digest.digest()));
		} catch (Exception e) {
		    LoggerUtil.getLogger(Util.class).error(e.getMessage(),e);
		}
		return hash;		
	}
	
	public static String getToken(String username){
		return  Jwts.builder().setIssuedAt(new Date()).setIssuer(ISSUER)
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXP_TIME))
				.signWith(SignatureAlgorithm.HS512, KEY).compact();
	}
	
	public static boolean validateToken(String token) {		
		String user = Jwts.parser()
                .setSigningKey(KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
		
		if(user!=null && !user.isEmpty()) {
			LoggerUtil.getLogger(Util.class).info("Resource requested by {}",user);
			return true;
		}else {
			LoggerUtil.getLogger(Util.class).info("Resource requested by invalid User/Token");
			return false;
		}
	}
	
}
