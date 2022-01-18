package ${package}.application.config;

import com.nimbusds.openid.connect.sdk.claims.UserInfo;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class allows to map authorities (scopes and roles for example) from
 * UserInfo
 * You may adapt this converter depending on where the OpenID Provider
 * puts the claims
 * If you don't provide such Converter, Spring by default loads the list of
 * authorities with the scopes found inside of either the scope or scp claim
 *
 * @author Ippon
 */
public class PfIDPAuthoritiesConverter implements Converter<UserInfo, Collection<GrantedAuthority>> {

	private static String ROLES_CLAIMS 	= "roles";
	private static String SCOPES_CLAIMS = "scopes";

	@Override
	public Collection<GrantedAuthority> convert(UserInfo userInfo) {
		List<String> roles = userInfo.getStringListClaim(ROLES_CLAIMS) != null ?  userInfo.getStringListClaim(ROLES_CLAIMS): Collections.emptyList();
		List<String> scopes = userInfo.getStringListClaim(SCOPES_CLAIMS) != null ?  userInfo.getStringListClaim(SCOPES_CLAIMS): Collections.emptyList();
		List<GrantedAuthority> rolesCollection = roles.stream().map(roleName -> "ROLE_" + roleName).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
		List<GrantedAuthority> scopesCollection = scopes.stream().map(scopeName -> "SCOPE_" + scopeName).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
		List<GrantedAuthority> grantedAuthorities = rolesCollection;
		grantedAuthorities.addAll(scopesCollection);
		return grantedAuthorities;
	}
}
