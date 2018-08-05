package utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class SinaCloudSign {

	public static void main(String args[]) throws UnsupportedEncodingException {
		String path = "/song_info.txt";
		SinaCloudSign sign = new SinaCloudSign.Builder().verb("GET")
				.canonicalizedResource("/music-store/song_info.txt")
				.date(1696569436+"")
				.expires(1696569436)
				.builder();
		System.out.println(sign.getUrl(path));
	}
	
	private String accessKey = "2o3w9tlWumQRMwg2TQqi";
	private String secretAccessKey = "01a03965e29bed4a51f51f57d10f4c60ba68a050";
	//String secretAccessKey = "1001hbk3aV";
	private String serverAddress = "http://sinacloud.net/music-store";
	private final Base64.Decoder decoder = Base64.getDecoder();
	private final Base64.Encoder encoder = Base64.getEncoder();
	private String verb;
	private String contentMD5;
	private String contentType;
	private String date;
	private String canonicalizedAmzHeader;
	private String canonicalizedResource;
	private long expires;
	public SinaCloudSign(String verb, String contentMD5,
			String contentType, String date, String canonicalizedAmzHeader, String canonicalizedResource,long expires) {
		this.verb = verb;
		this.contentMD5 = contentMD5;
		this.contentType = contentType;
		this.date = date;
		this.canonicalizedAmzHeader = canonicalizedAmzHeader;
		this.canonicalizedResource = canonicalizedResource;
		this.expires = expires;
	}
	static class Builder{
		private String verb = "";
		private String contentMD5 = "";
		private String contentType = "";
		private String date = "";
		private String canonicalizedAmzHeader = "";
		private String canonicalizedResource = "";
		private long expires = 1525098095;
		public SinaCloudSign builder() {
			return new SinaCloudSign(verb,contentMD5,contentType,date,canonicalizedAmzHeader,canonicalizedResource,expires);
		}
		public Builder verb(String verb) {
			this.verb = verb;
			return this;
		}
		public Builder contentMD5(String contentMD5) {
			this.contentMD5 = contentMD5;
			return this;
		}
		public Builder contentType(String contentType) {
			this.contentType = contentType;
			return this;
		}
		public Builder date(String date) {
			this.date = date;
			return this;
		}
		public Builder canonicalizedAmzHeader(String canonicalizedAmzHeader) {
			this.canonicalizedAmzHeader = canonicalizedAmzHeader;
			return this;
		}
		public Builder canonicalizedResource(String canonicalizedResource) {
			this.canonicalizedResource = canonicalizedResource;
			return this;
		}
		public Builder expires(long expires) {
			this.expires = expires;
			return this;
		}
	}
	public String getUrl(String path) throws UnsupportedEncodingException {	
		return serverAddress+path+""+"?KID=sina,"+accessKey+"&Expires="+expires+"&ssig="+URLEncoder.encode(getSignature(),"utf-8");
	}
	public String getSignature() throws UnsupportedEncodingException {
		String signature = "";
		String ssig = "";
		String stringToSign = verb + "\n" +
			               contentMD5 + "\n" +
			               contentType + "\n" +
			               date + "\n" +
			               canonicalizedAmzHeader +
			               canonicalizedResource;		
		System.out.println(stringToSign);
		try {
			signature = base64Encode( hamcsha1(secretAccessKey.getBytes("UTF-8"), stringToSign.getBytes("UTF-8") ) );
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ssig = signature.substring(4, 14); //截取10位字符, 从第5个开始, 第15个结束
		System.out.println(hamcsha1(secretAccessKey.getBytes(), stringToSign.getBytes("UTF-8") ));
		System.out.println(URLEncoder.encode(signature,"utf-8"));
		return ssig;
	}
	
	private String hamcsha1( byte[] key, byte[] data){	
		  try {
		      SecretKeySpec signingKey = new SecretKeySpec(key, "HmacSHA1");
		  Mac mac = Mac.getInstance("HmacSHA1");
		      mac.init(signingKey);
		      return byte2hex(mac.doFinal(data));
		  } catch (NoSuchAlgorithmException e) {
		       e.printStackTrace();
		  } catch (InvalidKeyException e) {
		       e.printStackTrace();
		  }
		 return null;
	}
	//二行制转字符串  
	private String byte2hex(byte[] b){
	    StringBuilder hs = new StringBuilder();
	    String stmp;
	    for (int n = 0; b!=null && n < b.length; n++) {
	        stmp = Integer.toHexString(b[n] & 0XFF);
	        if (stmp.length() == 1)
	            hs.append('0');
	        hs.append(stmp);
	    }
	    //return hs.toString().toUpperCase();
	    return hs.toString();
	}
	private String base64Encode(String str) {
		String encodedText = "";
		byte[] textByte;
		try {
			textByte = str.getBytes("UTF-8");
			encodedText = encoder.encodeToString(textByte);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encodedText;
	}
	private String base64Decode(String str) {
		String decodedText = "";
		byte[] textByte;
		try {
			decodedText = new String (decoder.decode(str),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return decodedText;
	}
}
