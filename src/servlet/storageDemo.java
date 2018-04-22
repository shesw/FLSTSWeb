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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class storageDemo
 */
@WebServlet("/storageDemo")
public class storageDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public storageDemo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String fileName = "/records/records1/";
		//String fileName = "E:/song_info.txt";
		File file = new File(fileName);
		if(!file.exists()) {
			file.mkdirs();
		}
		
		File filePath = new File(fileName+"demo.txt");
		
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath)));
		
		try {
			bw.write("asdfjsakgjsfasdkfjsklfj");
			bw.flush();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			bw.close();
		}
		
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
		PrintWriter out = response.getWriter();
		
		try {
			String str = br.readLine();
			out.println(str);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		br.close();
		
		
	}

}
