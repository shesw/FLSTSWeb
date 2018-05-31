package utils;
import java.net.URL;
import java.util.Date;

import com.sina.cloudstorage.auth.AWSCredentials;
import com.sina.cloudstorage.auth.BasicAWSCredentials;
import com.sina.cloudstorage.services.scs.SCS;
import com.sina.cloudstorage.services.scs.SCSClient;

public class SinaStoreSDK {
	
	private String accessKey = "2o3w9tlWumQRMwg2TQqi";
	private String secretKey = "01a03965e29bed4a51f51f57d10f4c60ba68a050";
	private AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
	private SCS conn = new SCSClient(credentials);
	
	/* 生成url*/
	public String generateUrl(String bucketName, String path, int minutes){
	    Date expiration = new Date();       //过期时间
	    long epochMillis = expiration.getTime();
	    epochMillis += 1000*60*minutes;
	    expiration = new Date(epochMillis);   
	    URL presignedUrl = conn.generatePresignedUrl(bucketName, path, expiration, false);
	    return presignedUrl.toString();
	}
	

}
