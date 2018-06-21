package util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

import cargo.CommentsDatum;
import cargo.CommentsDatum_;
import cargo.CommentsRootObject;
import cargo.LikesDatum;
import cargo.LikesRootObject;
import cargo.SharesDatum;
import cargo.SharesRootObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class ReadGraphAPIFeed {	
	final String fbURL = "https://graph.facebook.com/v3.0/9465008123/posts?fields=";
	final String fbToken = "EAACEdEose0cBAILxAdjJOXaZA6i9ohtzsePPu3m9dyCEadEXn0eZBGwoFcYHbp4XPyo8O4yoG0b842cXfJLlZAGdCU1ZBEBmJsKQyJoK78HLEL6oTMzx8QvBf8a53clj2qZCIQOj6vkWEVG6KxvIb7Tr2CuZAmL6L9ZBcRJ4ZCHdgajt9iWKDdVZAvYQHWYoNnnWzWhOSROxpfAZDZD";
	
	
	 private static SSLSocketFactory createSslSocketFactory() throws Exception {
	        TrustManager[] byPassTrustManagers = new TrustManager[] { new X509TrustManager() {
	            public X509Certificate[] getAcceptedIssuers() {
	                return new X509Certificate[0];
	            }
	            public void checkClientTrusted(X509Certificate[] chain, String authType) {
	            }
	            public void checkServerTrusted(X509Certificate[] chain, String authType) {
	            }
	        } };
	        SSLContext sslContext = SSLContext.getInstance("TLS");
	       sslContext.init(null, byPassTrustManagers, new SecureRandom());
	        return sslContext.getSocketFactory();
	    }

	public static String readUrl(String urlString) throws IOException {
		BufferedReader reader = null;
		try {
			URL url = new URL(urlString);
			HttpsURLConnection con=(HttpsURLConnection)url.openConnection();
			SSLSocketFactory sslSocketFactory = createSslSocketFactory();
			con.setSSLSocketFactory(sslSocketFactory);
			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			StringBuffer buffer = new StringBuffer();
			int read;
			char[] chars = new char[1024];
			while ((read = reader.read(chars)) != -1)
				buffer.append(chars, 0, read);

			return buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			reader.close();
		}
		return "";
	}
	
	public List<String> getCommentsList(){
		Gson gson = new GsonBuilder().create();
		List<String> commentsList = new ArrayList<String>();
		try{
			String commentsJson = readUrl(fbURL + "comments{created_time}&until=2018-06-06&since=2018-05-05&access_token=" + fbToken);
			CommentsRootObject commentsRootObject = gson.fromJson(commentsJson, CommentsRootObject.class);
			for (CommentsDatum tempCommentsDatum : commentsRootObject.getData()) {
				for (CommentsDatum_ tempCommentsDatum_ : tempCommentsDatum.getComments().getData()) {
					//System.out.println(tempCommentsDatum_.getCreatedTime());
					commentsList.add(tempCommentsDatum_.getCreatedTime());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return commentsList;
	}
	
	public List<String> getLikesList(){
		Gson gson = new GsonBuilder().create();
		List<String> likesList = new ArrayList<String>();
		try{
			String likesJson = readUrl(fbURL + "likes,created_time&until=2018-06-06&since=2018-05-05&access_token=" + fbToken);
			LikesRootObject likesRootObject = gson.fromJson(likesJson, LikesRootObject.class);
			for (LikesDatum tempLikesDatum : likesRootObject.getData()) {
				//System.out.println(tempLikesDatum.getCreatedTime());
				likesList.add(tempLikesDatum.getCreatedTime());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return likesList;
	}
	
	public List<String> getSharesList(){
		Gson gson = new GsonBuilder().create();
		List<String> sharesList = new ArrayList<String>();
		try{
			String sharesJson = readUrl(fbURL + "shares,created_time&until=2018-06-06&since=2018-05-05&access_token=" + fbToken);
			SharesRootObject sharesRootObject = gson.fromJson(sharesJson, SharesRootObject.class);
			for (SharesDatum tempSharesDatum : sharesRootObject.getData()) {
				//System.out.println(tempSharesDatum.getShares().getCount());
				sharesList.add(tempSharesDatum.getCreatedTime());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sharesList;
	}

	public String convertMaptoJson(Map<String, Map<String, Integer>> finalCountMap) {
		
		Gson gsonobj = new Gson();
		String jsonStr = gsonobj.toJson(finalCountMap);
		return jsonStr;
	}
	
	
	public String convertMaptoJsonObj(Map<String, Map<String, Map<String, Integer>>> mondayMap) {
		
		Gson gsonobj = new Gson();
		String jsonStr = gsonobj.toJson(mondayMap);
		//return jsonStr;
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(jsonStr);
		String prettyJsonString = gson.toJson(je);
		
		return prettyJsonString;
	}
	
}
