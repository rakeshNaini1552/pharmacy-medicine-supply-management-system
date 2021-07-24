package com.authorization.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JwtUtil {

	private String secretkey = "sampletest";

	public String extractUsername(String token) {
		log.info("begin");
		log.debug("token", token);
		String extractClaim = extractClaim(token, Claims::getSubject);
		log.debug("extract claim ", extractClaim);
		log.info("end");
		return extractClaim;
	}

	public Date extractExpiration(String token) {
		log.info("begin");
		Date expiryDate = extractClaim(token, Claims::getExpiration);
		log.debug("extract date ", expiryDate);
		log.info("end");
		return expiryDate;

	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		log.info("Begin");
		log.debug("token", token);
		final Claims claims = extractAllClaims(token);
		log.debug("claims", claims);
		T apply = claimsResolver.apply(claims);
		log.debug("apply ", apply);
		log.info("END");
		return apply;
	}

	private Claims extractAllClaims(String token) {
		log.info("START");
		log.debug("TOKEN ", token);
		Claims claims = Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token).getBody();
		log.debug("CLAIMS ", claims);
		log.info("END");
		return claims;
	}

	private Boolean isTokenExpired(String token) {
		log.info("START");
		log.debug("TOKEN {}:", token);
		boolean isTokenExpired = extractExpiration(token).before(new Date());
		log.debug("CHECK TOKEN EXPIRATION {}:", isTokenExpired);
		log.info("END");
		return isTokenExpired;

	}

	public String generateToken(UserDetails userDetails) {

		log.info("BEGIN");
		log.debug("USER DETAILS {}:", userDetails);
		Map<String, Object> claims = new HashMap<>();
		log.debug("CLAIMS {}:", claims);
		String createToken = createToken(claims, userDetails.getUsername());
		log.debug("CREATE TOKEN {}:", createToken);
		log.info("END");
		return createToken;
	}

	private String createToken(Map<String, Object> claims, String subject) {
		log.info("START");
		log.debug("CLAIMS ", claims);
		log.debug("SUBJECT ", subject);
		String token = Jwts.builder().setClaims(claims).setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))// token for 30 mins
				.signWith(SignatureAlgorithm.HS256, secretkey).compact();
		log.debug("TOKEN :", token);
		log.info("END");
		return token;
	}

	public Boolean validateToken(String token, UserDetails userDetails) {

		log.info("BEGIN");
		log.debug("TOKEN {}:", token);
		log.debug("USER DETAILS {}:", userDetails);
		final String username = extractUsername(token);
		log.debug("USERNAME{}:", username);
		log.info("END");
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	public Boolean validateToken(String token) {
		log.info("START");
		log.debug("TOKEN {}:", token);
		log.info("START");
		return !isTokenExpired(token);
	}
}