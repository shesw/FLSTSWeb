package test;

import com.sina.cloudstorage.services.scs.model.ObjectMetadata;

import utils.SinaStoreSDK;

/**
 * @author Administrator
 *
 */
public class ObjectTest {
	
	
	public static void main(String args[]) {
		SinaStoreSDK sss = new SinaStoreSDK();
		
		//sss.putObjectWithCustomRequestHeader("music-store", "markdownPic/test.txt", "e:/test.txt");
		//sss.deleteObject("music-store", "markdownPic/test.txt");
		ObjectMetadata bmd = sss.getObjectMeta("music-store", "song_info.txt");
	}
	
}
