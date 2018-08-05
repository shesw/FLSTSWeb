package servlet;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import utils.SinaStoreSDK;

/**
 * Servlet implementation class uploadObj
 */
@WebServlet("/uploadObj")
public class uploadObj extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public uploadObj() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		SinaStoreSDK sss = new SinaStoreSDK();
		System.out.println("图片存放");
	    request.setCharacterEncoding("UTF-8");
	    
	    boolean isMultipart = ServletFileUpload.isMultipartContent(request);
	    if (isMultipart){
	      String dir = "uploadPic";
	      
	      File dirFile = new File(dir);
	      if (!dirFile.exists()) {
	        dirFile.mkdirs();
	      }

	      try{
	        FileItemFactory factory = new DiskFileItemFactory();
	        ServletFileUpload fileUpload = new ServletFileUpload(factory);
	        
	        List<FileItem> items = fileUpload.parseRequest(request);
	        String formName = "";
	        
	        for (FileItem item : items) {
	          if (item.isFormField())
	          {
	            String name = item.getFieldName();
	            formName = URLDecoder.decode(item.getString(), "utf-8");
	            System.out.println(name+" "+formName);
	           
	          }
	          else
	          {	
	              String longFileName = item.getName();
	              char indexChar = '\\';
	              String fileName = item.getName().substring(longFileName.lastIndexOf(indexChar) + 1, longFileName.length());
	              System.out.println(fileName);

	              String picName =  "pre"+formName+fileName;
	              
	              File saveFile = new File(dirFile, picName);
	              item.write(saveFile);
	              sss.putObject("music-store", "favorPic/"+picName, dir+'/'+picName);
	          }
	        }
	        
	      }
	      catch (Exception e)
	      {
	        e.printStackTrace();
	      }
	       
		  System.out.println("end");
		  FileUtils.deleteDirectory(dirFile);
	    }
	    
	}

}
