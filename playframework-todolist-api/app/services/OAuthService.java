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
package services;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.apache.commons.codec.binary.Base64;

import play.Play;
import play.libs.ws.WS;
import security.CheckOAuthTokenBO;

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.AsyncHttpClient.BoundRequestBuilder;
import com.ning.http.client.Response;

/**
 * 
 * @author Titouan COMPIEGNE
 *
 */
public class OAuthService {

	private static final String oauthUrl = Play.application().configuration().getString("oauth.url");
	private static final String clientId = Play.application().configuration().getString("oauth.client.id");
	private static final String clientSecret = Play.application().configuration().getString("oauth.client.secret");
	
	public static String resourceOwnerPasswordCredentialsGrant(String username, String password) throws InterruptedException, ExecutionException, IOException {
		AsyncHttpClient client = (AsyncHttpClient) WS.client().getUnderlying();
		
		BoundRequestBuilder req = client.preparePost(oauthUrl + "/oauth/token");
		req.addQueryParameter("grant_type", "password");
		req.addQueryParameter("client_id", clientId);
		req.addQueryParameter("client_secret", clientSecret);
		req.addQueryParameter("username", username);
		req.addQueryParameter("password", password);
		
		Response response = req.execute().get();
		return response.getResponseBody();
	}
	
	public static CheckOAuthTokenBO checkTokenAuthentication(String token) throws InterruptedException, ExecutionException, IOException {
		String binaryData = clientId+":"+clientSecret;
		AsyncHttpClient client = (AsyncHttpClient) WS.client().getUnderlying();
		BoundRequestBuilder req = client.preparePost(oauthUrl + "/oauth/check_token");
		req.addHeader("Content-Type","application/x-www-form-urlencoded");
		req.addHeader("Authorization", "Basic " + Base64.encodeBase64String(binaryData.getBytes()).toString());
		req.addQueryParameter("token", token);

		Response response = req.execute().get();
		CheckOAuthTokenBO checkAuthTokenBO = new CheckOAuthTokenBO();
		checkAuthTokenBO.setResponseCode(response.getStatusCode());
		checkAuthTokenBO.setResponseBody(response.getResponseBody());
		
		return checkAuthTokenBO;
	}
}
