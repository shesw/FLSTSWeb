package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.HttpUtil;
import utils.SinaStoreSDK;

/**
 * Servlet implementation class songInfo
 */
@WebServlet("/songInfo")
public class songInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public songInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bucketName = "music-store";
		int expires = 60*24;
		
		String albumId = request.getParameter("albumId");
		String songId = request.getParameter("id");
		
		SinaStoreSDK sdkUtil = new SinaStoreSDK();
		HttpUtil hu = new HttpUtil();
		
		String path = "flsts_v2/songs/album_"+albumId+"/"+songId+"/"+songId;
		String mp3Path = path+".mp3";
		String jpgPath = path+".jpg";
		String txtPath = path+".txt";
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(hu.request(sdkUtil.generateUrl(bucketName,txtPath,expires)));
		
		sb.insert(sb.length()-1, ",\"mp3Path\":"+"\""+sdkUtil.generateUrl(bucketName, mp3Path, expires)+"\"");
		sb.insert(sb.length()-1, ",\"jpgPath\":"+"\""+sdkUtil.generateUrl(bucketName, jpgPath, expires)+"\"");
		
		String str = sb.toString();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.append(str.substring(str.indexOf("{")));
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
