/*******************************************************************************
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Titouan COMPIEGNE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *******************************************************************************/
package security;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import play.libs.F.Promise;
import play.mvc.Action;
import play.mvc.Http.Context;
import services.OAuthService;

/**
 * 
 * @author Titouan COMPIEGNE
 *
 */
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
		CheckOAuthTokenBO checkAuthTokenBO = checkTokenAuthentication(authToken);
		if (checkAuthTokenBO.getResponseCode() != 200) {
			return Promise.pure(unauthorized(checkAuthTokenBO.getResponseBody()));
		}
		return delegate.call(ctx);
	}
	
	private static CheckOAuthTokenBO checkTokenAuthentication(String token) throws InterruptedException, ExecutionException, IOException {
		return OAuthService.checkTokenAuthentication(token);
	}
	
	private static String getAuthToken(String authorizationHeader) {
		return authorizationHeader.substring(HEADER_AUTH_BEARER.length()).trim();
	}

}
