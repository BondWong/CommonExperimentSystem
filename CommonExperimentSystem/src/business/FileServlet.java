package business;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import persistence.transaction.Transaction;
import persistence.transaction.daoTransaction.SubmitReportTransaction;

/**
 * Servlet implementation class FileServlet
 */
@WebServlet("/FileServlet")
public class FileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String root;
	private DiskFileItemFactory factory;
	private ServletFileUpload upload;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() {
    	setUp();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uri = request.getParameter("uri");
		
		File file = new File(root + "/experimentReports/"+uri);
		InputStream fis  =   new  BufferedInputStream( new  FileInputStream(file));  
        byte [] buffer  =   new   byte [fis.available()];  
        fis.read(buffer);  
       	fis.close();
       	
		response.addHeader( "Content-disposition" ,  "attachment;filename="+file.getName());
		response.addHeader( "Content-length" ,  ""   +  file.length());
		response.setContentType( " application/octet-stream " );
		OutputStream toClient  =   new  BufferedOutputStream(response.getOutputStream());  
		toClient.write(buffer);
		toClient.flush();
		toClient.close();
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String name = "";
		synchronized(session) {
			name = ((User)session.getAttribute("id")).getName();
		}
		String id = request.getParameter("id");
		Long experimentId = Long.parseLong(request.getParameter("experimentId"));
		String link = null;
		try {
			link = process(request, id, name, experimentId);
		} catch (FileSizeLimitExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Transaction transaction = new SubmitReportTransaction();
		try {
			transaction.execute(id, experimentId, link);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void setUp(){
		System.out.println("setUp");
		factory = new DiskFileItemFactory();
    	File repository = (File) this.getServletContext()
				.getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(repository);
		upload = new ServletFileUpload(factory);
		upload.setSizeMax(1024*1024*10);
		root = getServletConfig().getServletContext()
        		.getRealPath("/");
	}
	
	private String process(HttpServletRequest request, String id, String name, Long experimentId) throws FileUploadException,
		FileUploadBase.FileSizeLimitExceededException, 
		Exception{
		List<FileItem> items = upload.parseRequest(request);
		Iterator<FileItem> iter = items.iterator();
		
		String link = null;
		while (iter.hasNext()){
			FileItem item = iter.next();
			
			if(item.isFormField()){
				continue;
			}
			
			File dir = new File(root + "/experimentReports");
			if(!dir.exists()){
				dir.mkdir();
			}
			
			String contentType = item.getContentType();
			File uploaddedFile = new File(root + "/experimentReports/" + 
					id + "-" + experimentId + "." + contentType.substring(contentType.lastIndexOf("/")+1, contentType.length()));
			
			item.write(uploaddedFile);
			
			link = id + "-" + name + "-" + experimentId + "." + contentType.substring(contentType.lastIndexOf("/")+1, contentType.length());
		}
		System.out.println(link);
		return link;
	}
	
}
