package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;

public class RecordUtil {
	private static final String RECORDS_PATH = new Settings().recordsPath;
	private SinaStoreSDK sss = new SinaStoreSDK();
	private String remainPath = RECORDS_PATH+"/remain.txt";
	private String bucketName = "music-store";
	private String pathCloud = "lab324data/records";
	private String remainPathCloud = pathCloud+"/remain.txt";
	
	public RecordUtil() {
		// TODO Auto-generated constructor stub
		
		if(!new File(RECORDS_PATH).exists()) {
			new File(RECORDS_PATH).mkdirs();
		}
	}
	
	public int resetRemain(String remain) {
		
		File remainFile = new File(remainPath);
		
		if(!remainFile.exists()) {
			try {
				sss.getObject(bucketName,remainPathCloud, remainPath);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			remainFile = new File(remainPath);
		}
		
		remainFile = new File(remainPath);
		try {
			BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(remainFile), "utf-8"));
			bWriter.write(remain);
			bWriter.flush();
			bWriter.close();
			sss.putObject(bucketName, remainPathCloud, remainPath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 500;
		}
		
		return 200;
	}
	
	public int postRecord(String date, String item, String change) {
		
		double remain;
		File remainFile = new File(remainPath);
		
		if(!remainFile.exists()) {
			try {
				sss.getObject(bucketName,remainPathCloud, remainPath);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 500;
			}
			remainFile = new File(remainPath);
		}
		
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(remainFile), "utf-8"));
			remain = Double.parseDouble(bufferedReader.readLine());
			bufferedReader.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 500;
		}
		
		

		String recordName = "/record"+(date.replaceAll("-", "").substring(0,6))+".txt";
		String recordPath = RECORDS_PATH+recordName;
		File recordFile = new File(recordPath);
		BufferedWriter bWriter;
		boolean first = !recordFile.exists();
		try {
			//bWriter = new BufferedWriter(new FileWriter(recordFile,true));
			bWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(recordFile,true),"utf-8"));
			if (first) {
				bWriter.append("日期..............项目........金额......余额");
			}		
			bWriter.newLine();
			remain -= Double.parseDouble(change);
			DecimalFormat dFormat = new DecimalFormat("#.00");
			String change1 = dFormat.format(Double.parseDouble(change));
			String remain1 = dFormat.format(remain);
			
			
			bWriter.append(date+"........"+item+"........"+change1+"........"+remain1);
			bWriter.flush();
			bWriter.close();
			sss.putObject(bucketName, pathCloud+recordName, recordPath);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 500;
		}
		
		//�������
		try {
			//BufferedWriter bWriter2 = new BufferedWriter(new FileWriter(remainFile));
			BufferedWriter bWriter2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(remainFile),"utf-8"));
			bWriter2.write(remain+"");
			bWriter2.flush();
			bWriter2.close();
			sss.putObject(bucketName, remainPathCloud, remainPath);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 500;
		}
		
		return 200;
	}
	
	public String queryRecords(String date) {
		StringBuilder recordsSB = new StringBuilder("[");
		int statusCode = 200;
		String name = "/record"+date+".txt";
		String filePathCloud = pathCloud+name;
		String filePath = RECORDS_PATH+name;
		File fileName = new File(filePath);
		if(!fileName.exists()) {
			try {
				sss.getObject(bucketName, filePathCloud, filePath);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "417";
			}
			fileName = new File(filePath);
		}

		try {
			BufferedReader bReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "utf-8"));
			String string = bReader.readLine();
			DataHandler dHandler = new DataHandler();
			while((string = bReader.readLine())!=null) {
				if("".equals(string)) {
					continue;
				}
				recordsSB.append(dHandler.handle(string));
			}
			recordsSB.delete(recordsSB.length()-1,recordsSB.length());
			recordsSB.append("]");
			bReader.close();
			
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			statusCode = 417;
		} catch (Exception e) {
			// TODO: handle exception
			statusCode = 500;
		}
		
		if(statusCode!=200) {
			return statusCode+"";
		}
		return recordsSB.toString();
	}

}
