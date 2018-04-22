package servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
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
import net.sf.json.JSONObject;


/**
 * Servlet implementation class songsListServlet
 */
@WebServlet("/songsListServlet")
public class songsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public songsListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		URL url = new URL("http://sinacloud.net/music-store/song_info.txt?KID=sina,2o3w9tlWumQRMwg2TQqi&Expires=1546275596&ssig=4zOsvELpoS");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.connect();
		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
		System.out.println(br.readLine()+"/r/n"+br.readLine());
		
//		String albumName = request.getParameter("albumName");
//		switch(albumName) {
//			case "dsksdwgj":
//				response.setCharacterEncoding("utf-8");
//				response.setContentType("application/json;charset=utf-8");
//				Writer writer = response.getWriter();
//				JSONObject res = new JSONObject();
//				List<SongInfo> songlist = new ArrayList<>();
//				songlist.add(new SongInfo("安且吉兮", "生忘"));
//				songlist.add(new SongInfo("生生忘", "生忘"));
//				songlist.add(new SongInfo("主旋律", "生忘"));
//				songlist.add(new SongInfo("夏天雨", "生忘"));
//				AlbumInfo albumInfo = new AlbumInfo("专辑简介",
//						"http://sinacloud.net/music-store/img/s13.jpg?KID=sina,2o3w9tlWumQRMwg2TQqi&Expires=1546275546&ssig=v9xt1D01ru",
//						songlist);
//				res = JSONObject.fromBean(albumInfo);
//				System.out.println(request.getParameter("albumName"));
//				writer.append(res.toString());
//				break;
//			default:
//			break;
//		
//		}
			
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
