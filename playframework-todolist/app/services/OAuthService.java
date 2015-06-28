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
