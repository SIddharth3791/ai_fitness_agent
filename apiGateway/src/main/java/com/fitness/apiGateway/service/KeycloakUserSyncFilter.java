package com.fitness.apiGateway.service;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import com.fitness.apiGateway.model.RegisterRequest;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class KeycloakUserSyncFilter implements WebFilter {
	
	private final UserService userService;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		String userId = exchange.getRequest().getHeaders().getFirst("X-User-ID");
		String token = exchange.getRequest().getHeaders().getFirst("Authorization");
		RegisterRequest registerUser = getuserDetails(token);
		
		if(userId == null) {
			userId = registerUser.getKeycloakId();
		}
		
		if(userId != null && token != null) {
			
			final String finalUserId = userId; 
			
			return userService.validateUser(userId).flatMap(exist -> {
				if(!exist) {
					// Register User
					if(registerUser != null) {
						
						return userService.registerUser(registerUser).then(Mono.empty());
					}else {
					
						return Mono.empty();
					}
				}else {
					log.info("User Already Exists");
					return Mono.empty();
				}
			}).then(Mono.defer(() -> {
				ServerHttpRequest mutatedRequest = exchange.getRequest().mutate().header("X-User-ID", finalUserId).build();
				return chain.filter(exchange.mutate().request(mutatedRequest).build());
			}));
		}
		return null;
	}

	private RegisterRequest getuserDetails(String token) {
		try {
			String tokenwithoutBearer = token.replace("Bearer ", "").trim();
			
			SignedJWT signedJWT = SignedJWT.parse(tokenwithoutBearer);
			JWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();
			
			return RegisterRequest.builder()
					.email(claimsSet.getStringClaim("email"))
					.keycloakId(claimsSet.getStringClaim("sub"))
					.firstName(claimsSet.getStringClaim("given_name"))
					.lastName(claimsSet.getStringClaim("family_name")).build();
			
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	

}
