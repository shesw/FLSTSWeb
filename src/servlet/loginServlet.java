package servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javabean.Voter;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String name = "csl";
		String[] days = null;
		
		try {
			name = new String(request.getParameter("name").getBytes("utf-8"));
			days = request.getParameter("days").split(",");	
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		String filePath = "/records/records1/";
		File fp = new File(filePath);
		if(!fp.exists()) {
			fp.mkdirs();
		}
		
		String fileName = filePath+"records.txt";
		System.out.println(fileName);
		File file = new File(fileName);
		
		
//		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		//synchronized (this) {
//			try {
//				
//				String strings;
//				boolean flag = false;
//				while( (strings = br.readLine())!=null ) {
//					String[] strings2 = strings.split(":");
//					if(strings2[0].equals(name)) {
//						flag=true;
//						break;
//					}
//				}	
//			} catch (Exception e) {
//				// TODO: handle exception
//			}finally {
//				br.close();
//			}
//			
		//}
		
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true),"utf-8"));
		
		//synchronized (this) {
			try {
				bw.write(name+": ");
				for(int i=0;i<days.length;i++) {
					bw.write(days[i]+" ");
				}
				bw.newLine();
			} catch (Exception e) {
				// TODO: handle exception
			}finally {
				bw.flush();
				bw.close();
				System.out.println("ok");
			}	
		//}
		
	
		
			
//		PrintWriter out = response.getWriter();
//		
//		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
//		try {
//
//			String strings;
//			
//			while( (strings = br.readLine())!=null ) {
//				
//				String[] strings2 = strings.split(":");
//				
//				out.print(strings2[0]+": ");
//				
//				String[] strings3 = strings2[1].split(" ");
//				for(int i=0;i<strings3.length;i++) {
//					out.print(strings3[i]+" ");
//				}
//				out.println();
//				
//			}
//			
//			
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//		}finally {
//			br.close();
//		}
//		
//		
	}

}
