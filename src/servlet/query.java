package servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.DataHandler;
import utils.RecordUtil;
import utils.SessionUtil;
import utils.Settings;

/**
 * Servlet implementation class query
 */
@WebServlet("/query")
public class query extends HttpServlet {
	private static final long serialVersionUID = 1L;
    RecordUtil recordUtil = new RecordUtil();
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public query() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		SessionUtil sessionUtil = new SessionUtil();
		if(sessionUtil.checkSession(request, response)) {
			return;
		}
	
		
		Writer out = response.getWriter();
		
		String date = request.getParameter("date");
		if(date==null) {
			response.setStatus(417);
			out.append("hahaah");
			return;
		}
		
		
		String recordsSB = recordUtil.queryRecords(date);
		if(Pattern.matches("^[0-9]+", recordsSB)) {
			response.setStatus(Integer.parseInt(recordsSB));
			return;
		}
		
		out.append(recordsSB);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
