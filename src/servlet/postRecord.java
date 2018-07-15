package servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.RecordUtil;
import utils.SessionUtil;
import utils.Settings;

/**
 * Servlet implementation class postRecord
 */
@WebServlet("/postRecord")
public class postRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private RecordUtil rUtil = new RecordUtil();
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public postRecord() {
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


		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		SessionUtil sessionUtil = new SessionUtil();
		if(sessionUtil.checkSession(request, response)) {
			return;
		}
	
	
		String date = request.getParameter("date");
		String item = request.getParameter("item");
		String change = request.getParameter("change");
		String mode = request.getParameter("mode");
		if( (mode==null || mode.equals("post")) && (date==null || item==null || change==null) ) {
			response.setStatus(417);
			return;
		}
		
		if( mode!=null && mode.equals("reset") && change==null ) {
			response.setStatus(418);
			return;
		}
		

		int statusCode = 500;
		
		
		if(! (mode==null)) {
			switch (mode) {
			case "reset":
				statusCode = rUtil.resetRemain(change);
				break;
			default:
				statusCode = rUtil.postRecord(date, item, change);
				break;
			}
		}else {
			statusCode = rUtil.postRecord(date, item, change);
		}
		
		response.setStatus(statusCode);
		
	}

}
