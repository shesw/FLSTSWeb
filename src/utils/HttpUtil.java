package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {
	private HttpURLConnection connection;
	private URL url;
	public String request(String path,boolean isJson) {
		String result = "";
		try {
			url = new URL(path);
			connection = (HttpURLConnection) url.openConnection();
			connection.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
			StringBuilder info = new StringBuilder();
			String str;
			while( (str=br.readLine()) != null ) {
				if(isJson){
					info.append(str);
				}else{
					info.append(str+"<br>");
				}
				
			}
			result = info.toString();
			br.close();
			connection.disconnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.disconnect();
		return result;
	}

}
