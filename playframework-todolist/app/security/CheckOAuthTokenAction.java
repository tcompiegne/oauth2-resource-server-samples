package security;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import models.CheckAuthTokenBO;
import play.libs.F.Promise;
import play.mvc.Action;
import play.mvc.Http.Context;
import services.OAuthService;

public class CheckOAuthTokenAction extends Action<CheckOAuthToken> {

	private static final String HEADER_AUTH_BEARER = "Bearer";
	
	@Override
	public Promise call(Context ctx) throws Throwable {
		String authorizationHeader = ctx.request().getHeader("Authorization");
		if (authorizationHeader == null) {
			return Promise.pure(unauthorized("No Authorization found."));
		}
		if (!authorizationHeader.startsWith(HEADER_AUTH_BEARER)) {
			return Promise.pure(unauthorized("Wrong Authorization method."));
		}
		String authToken = getAuthToken(authorizationHeader);
		CheckAuthTokenBO checkAuthTokenBO = checkTokenAuthentication(authToken);
		if (checkAuthTokenBO.getResponseCode() != 200) {
			return Promise.pure(unauthorized(checkAuthTokenBO.getResponseBody()));
		}
		return delegate.call(ctx);
	}
	
	private static CheckAuthTokenBO checkTokenAuthentication(String token) throws InterruptedException, ExecutionException, IOException {
		return OAuthService.checkTokenAuthentication(token);
	}
	
	private static String getAuthToken(String authorizationHeader) {
		return authorizationHeader.substring(HEADER_AUTH_BEARER.length()).trim();
	}

}
