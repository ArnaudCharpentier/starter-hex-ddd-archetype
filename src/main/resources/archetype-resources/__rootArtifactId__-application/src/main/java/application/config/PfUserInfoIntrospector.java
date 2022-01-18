package ${package}.application.config;

import com.nimbusds.oauth2.sdk.ParseException;
import com.nimbusds.oauth2.sdk.http.HTTPResponse;
import com.nimbusds.oauth2.sdk.token.BearerAccessToken;
import com.nimbusds.openid.connect.sdk.UserInfoRequest;
import com.nimbusds.openid.connect.sdk.UserInfoResponse;
import com.nimbusds.openid.connect.sdk.claims.UserInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import java.io.IOException;
import java.net.URI;

public class PfUserInfoIntrospector implements OpaqueTokenIntrospector {

	private final String userInfoUri;

	private Logger log = LoggerFactory.getLogger(PfUserInfoIntrospector.class);

	public PfUserInfoIntrospector(String instrospectUri) {
		this.userInfoUri = instrospectUri;
	}

	@Override
	public OAuth2AuthenticatedPrincipal introspect(String token) {

		// Call IDP /userinfo endpoint to know who is behind the token
		try {
			HTTPResponse httpResponse = new UserInfoRequest(
					URI.create(this.userInfoUri),
					new BearerAccessToken(token))
					.toHTTPRequest()
					.send();

			// Parse the response
			UserInfoResponse userInfoResponse = UserInfoResponse.parse(httpResponse);

			if (! userInfoResponse.indicatesSuccess()) {
				log.error("Error occured while trying to retrieve user : [{}] {}",
					userInfoResponse.toErrorResponse().getErrorObject().getCode(),
					userInfoResponse.toErrorResponse().getErrorObject().getDescription());
			} else {
				/* Inject user information in session for Spring Security */
				UserInfo userInfo = userInfoResponse.toSuccessResponse().getUserInfo();
				return PfOAuth2AuthenticatedPrincipal.buildPrincipal(userInfo);
			}
		} catch (IOException | ParseException e) {
			log.error("IDP Technical Error", e);
		}
		return null;
	}
}
