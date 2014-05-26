package business;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		Long experimentId = Long.parseLong(request.getParameter("experimentId"));
		setUp();
		String link = null;
		try {
			link = process(request);
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
		factory = new DiskFileItemFactory();
    	File repository = (File) this.getServletContext()
				.getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(repository);
		upload = new ServletFileUpload(factory);
		upload.setSizeMax(1024*1024*10);
	}
	
	private String process(HttpServletRequest request) throws FileUploadException,
		FileUploadBase.FileSizeLimitExceededException, 
		Exception{
		String id = request.getParameter("id");
		
		List<FileItem> items = upload.parseRequest(request);
		Iterator<FileItem> iter = items.iterator();
		
		String link = null;
		while (iter.hasNext()){
			FileItem item = iter.next();
			
			if(item.isFormField()){
				continue;
			}
			
			File dir = new File(root + "experimentReports");
			if(!dir.exists()){
				dir.mkdir();
			}
			
			String contentType = item.getContentType();
			File uploaddedFile = new File(root + "experimentReports/" + 
					id + "." + contentType.substring(contentType.indexOf("/")+1, contentType.length()));
			
			item.write(uploaddedFile);
			
			link = uploaddedFile.getAbsolutePath();
		}
		
		return link;
	}
	
}
