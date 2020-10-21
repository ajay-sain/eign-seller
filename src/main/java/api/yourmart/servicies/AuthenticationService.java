package api.yourmart.servicies;

import api.yourmart.constants.CommonConstants;
import api.yourmart.models.JWTUserCredentials;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationService {
	public boolean authenticate(String authCredentials) {
		String token = authCredentials.substring(7);
		JWTUserCredentials credentials = parseToken(token);
		if(credentials == null )
			return false;
		return true;
	}
	
	
	
	public JWTUserCredentials parseToken(String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey( CommonConstants.SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();

            JWTUserCredentials credentials = new JWTUserCredentials();
            credentials.setId((String) body.get("userId"));
            credentials.setRole((String) body.get("role"));

            return credentials;

        } catch (Exception e) {
            return null;
        }
    }
	
	
	
	public String generateToken(JWTUserCredentials credentials) {
        Claims claims = Jwts.claims().setSubject(credentials.getId());
        claims.put("userId", credentials.getId() + "");
        claims.put("role", credentials.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, CommonConstants.SECRET_KEY)
                .compact();
    }
	
	
	
	
}
