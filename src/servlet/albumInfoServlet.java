package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javabean.AlbumInfo;
import javabean.SongInfo;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import utils.HttpUtil;
import utils.SinaStoreSDK;

/**
 * Servlet implementation class albumInfoServlet
 */
@WebServlet("/albumInfoServlet")
public class albumInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public albumInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String bucketName = "music-store";
		int expires = 5;
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String albumId = request.getParameter("albumId");
		if(albumId==null) {
			return;
		}
		
		SinaStoreSDK sdkUtil = new SinaStoreSDK();
		HttpUtil httpUtil = new HttpUtil();
		
		String infoPath = sdkUtil.generateUrl(bucketName, "flsts_v2/songs/album_"+albumId+"/album_"+albumId+".txt", expires);
		String picPath = sdkUtil.generateUrl(bucketName, "flsts_v2/songs/album_"+albumId+"/"+albumId+".jpg", expires);
		//String introPath = sdkUtil.generateUrl(bucketName, "flsts_v2/songs/album_"+albumName+"/"+albumName+"_intro.txt", expires);
		StringBuilder info = new StringBuilder();
		info.append( httpUtil.request(infoPath,true) );
		info.insert(info.length()-1, ",\"logoSrc\":"+"\""+picPath+"\"");
		String str = info.toString();
//		JSONObject object = JSONObject.fromObject(str.substring(str.indexOf("{")));
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
