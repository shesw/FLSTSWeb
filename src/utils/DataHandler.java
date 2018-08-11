package utils;

import java.util.ArrayList;
import java.util.List;

public class DataHandler {

	public String handle(String dateString) {
		String string;
		int index = 0;
		String date = dateString.substring(index, dateString.indexOf("....",index)).replace("-","");
		index = dateString.indexOf("....",index)+8;
		String item = dateString.substring(index,dateString.indexOf("....",index));
		index = dateString.indexOf("....",index)+8;
		String change = dateString.substring(index,dateString.indexOf("....",index));
		index = dateString.indexOf("....",index)+8;
		String remain = dateString.substring(index);
		
		string = "{"
				+ "\"date\":\""+date+"\","
				+ "\"item\":\""+item+"\","
				+ "\"change\":\""+change+"\","
				+ "\"remain\":\""	+remain+"\""
				+ "},";
		
		return string;
	}
	
	public List<String> getUrls(String[] list, String key) {
		SinaStoreSDK sss = new SinaStoreSDK();
		List<String> urls = new ArrayList<>();
		for(int i=0; i<list.length; i++) {
			String string = list[i];
			if(string.indexOf(key)!=-1) {
				String url = sss.generateUrl("music-store", string, 60*24);
				urls.add(url);
			}
		}
		
		return urls;
		
	}
	
}
