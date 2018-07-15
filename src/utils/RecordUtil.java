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
	
	
	
	public int deleteRecord() {
		
		int dateInt = 0 ;
		
		File direct = new File(RECORDS_PATH);
		File[] files = direct.listFiles();
		for(int i=0;i<files.length;i++) {
			if(files[i].isFile()) {
				String name = files[i].getName();
				
				if(name.startsWith("record")) {
					String dateString = name.replaceAll("[^0-9]", "");				 
					dateInt = Integer.parseInt(dateString) > dateInt ? Integer.parseInt(dateString) : dateInt; 
					
				}							
			}
		}
		
		String date = dateInt+"";
		
		if(date.length()<4) {
			return 417;
		}
		
		String filePath= RECORDS_PATH+"/record"+date+".txt";
		String tempFilePath = RECORDS_PATH+"/temp.txt";
		File file = new File(filePath);
		File tempFile = new File(tempFilePath);
		double remain = -1;
		
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tempFile),"utf-8"));
			
			String lastLine = bufferedReader.readLine();
			String tempLine;
		
			int count = 0;
			while( (tempLine=bufferedReader.readLine()) !=null) {
				if("".equals(tempLine)) {
					continue;
				}
				bufferedWriter.append(lastLine);
				bufferedWriter.newLine();
				if(count>0) {
					remain = Double.parseDouble(lastLine.substring(lastLine.lastIndexOf("....")+4,lastLine.length()));	
				}
				lastLine = tempLine;
				count++;
			}
			
			bufferedReader.close();
			bufferedWriter.flush();
			bufferedWriter.close();
			
			
			BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(RECORDS_PATH+"/remain.txt")),"utf-8"));
			bWriter.write(remain+"");
			bWriter.flush();
			bWriter.close();
			
			if(count<=1) {
				file.delete();
				tempFile.delete();
				sss.deleteObject(bucketName, pathCloud+"/record"+date+".txt");
			}else {
				file.delete();
				tempFile.renameTo(file);
				file = new File(RECORDS_PATH+"/remain.txt");
				sss.putObject(bucketName, pathCloud+"/record"+date+".txt", RECORDS_PATH+"/record"+date+".txt");
			}
			
			sss.putObject(bucketName, remainPathCloud, RECORDS_PATH+"/remain.txt");
			
		}catch(FileNotFoundException e) {
			return 417;
		}catch (Exception e) {
			// TODO: handle exception
			return 500;
		}
		return 200;
	}
}
