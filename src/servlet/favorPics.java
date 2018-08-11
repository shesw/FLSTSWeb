package servlet;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.DataHandler;
import utils.SinaStoreSDK;

/**
 * Servlet implementation class favorPics
 */
@WebServlet("/favorPics")
public class favorPics extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public favorPics() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String  key = request.getParameter("key");
		if(key == null) {
			response.setStatus(418);
			return;
		}
		response.setContentType("text/html;charset=utf-8");
		Writer out = response.getWriter();
		
		System.out.println(key);
		
		SinaStoreSDK sss = new SinaStoreSDK();
		String[] list = sss.listObjects("favorPic");
		
		List<String> urList = new DataHandler().getUrls(list, key);
		StringBuilder sb = new StringBuilder("{\"data\":[]}");
		for(int i=0; i<urList.size(); i++) {
			System.out.println(urList.get(i));
			sb.insert(sb.indexOf("[")+1, "\""+ urList.get(i) +"\",");
		}
		sb.delete(sb.length()-3, sb.length()-2);
		out.append(sb.toString());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
