package com.tsys.poc.userservice.config;

import com.tsys.poc.userservice.exception.AuthException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.util.Date;

@Component
public class JWTValidator {

	private KeyStore keyStore;
	@Value("${jwt.expiration.time}")
	private Long jwtExpirationInMillis;

	@PostConstruct
	public void init() throws AuthException {
		try {
			keyStore = KeyStore.getInstance("JKS");
			InputStream resourceAsStream = getClass().getResourceAsStream("/springblog.jks");
			keyStore.load(resourceAsStream, "secret".toCharArray());
		} catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
			throw new AuthException("Exception occurred while loading keystore");
		}

	}

	private boolean isValidSignature(String token) throws SignatureException, ExpiredJwtException,
			UnsupportedJwtException, MalformedJwtException, AuthException, IllegalArgumentException {
		Jwts.parser().setSigningKey(getPublicKey()).parseClaimsJws(token);
		return true;
	}

	private PublicKey getPublicKey() throws AuthException {
		try {
			return keyStore.getCertificate("springblog").getPublicKey();
		} catch (KeyStoreException ex) {
			throw new AuthException("Exception occured while retriving public key from keystore");
		}
	}

	public boolean validateToken(HttpServletRequest request, String actualUsername) {

		String jwtToken = getJwtFromRequest(request);

		try {
			if (isValidSignature(jwtToken)) {
				Claims claims = Jwts.parser().setSigningKey(getPublicKey()).parseClaimsJws(jwtToken).getBody();
				Date expiryTime = claims.getExpiration();
				Date issueTime = claims.getIssuedAt();
				String username = claims.getSubject();

				if (expiryTime.after(new Date()) && username.equalsIgnoreCase(actualUsername)) {
					return true;
				}

			} else {
				return false;
			}

		} catch (Exception e) {
			return false;
		}
		return false;

	}

	private String getJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");

		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}

		return bearerToken;
	}

}
