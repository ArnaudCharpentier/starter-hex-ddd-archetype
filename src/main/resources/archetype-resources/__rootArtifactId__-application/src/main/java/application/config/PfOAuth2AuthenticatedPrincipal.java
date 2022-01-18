package ${package}.application.config;

import com.nimbusds.openid.connect.sdk.claims.UserInfo;

import org.springframework.security.oauth2.core.DefaultOAuth2AuthenticatedPrincipal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PfOAuth2AuthenticatedPrincipal {

	private static final List<String> RESERVED_ATTRIBUTES = Arrays.asList("roles", "name", "habilitations");

	public static DefaultOAuth2AuthenticatedPrincipal buildPrincipal(UserInfo userInfo){
		return new DefaultOAuth2AuthenticatedPrincipal(
				userInfo.getName(),
				buildPrincipalAttributes(userInfo),
				new PfIDPAuthoritiesConverter().convert(userInfo));
	}

	private static Map<String, Object> buildPrincipalAttributes(UserInfo userInfo){
		Map<String, Object> attributes =  new HashMap<>();
		userInfo.getStandardClaimNames().stream()
				.filter(claim -> !RESERVED_ATTRIBUTES.contains(claim) && userInfo.getClaim(claim) != null)
				.forEach(claim -> attributes.put(claim, userInfo.getClaim(claim)));
		return attributes;
	}
}
