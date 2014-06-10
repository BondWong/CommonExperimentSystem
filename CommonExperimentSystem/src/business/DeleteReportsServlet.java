package business;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.transaction.Transaction;
import persistence.transaction.daoTransaction.GetAllReportLinksTransaction;

/**
 * Servlet implementation class DeleteReportsServlet
 */
@WebServlet("/DeleteReportsServlet")
public class DeleteReportsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static String root;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteReportsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() {
    	root = getServletConfig().getServletContext()
        		.getRealPath("/") + "/experimentReports/";
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Long experimentId = Long.parseLong(request.getParameter("experimentId"));
		
		Transaction transaction = new GetAllReportLinksTransaction();
		List<String> reportLinks = new ArrayList<String>();
		try {
			reportLinks = (List<String>) transaction.execute();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(reportLinks);
		for(String link : reportLinks) {
			System.out.println(link);
			if(link.contains("-"+experimentId)){
				File f = new File(root + link);
				f.deleteOnExit();
			}
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("DeleteExperimentServlet");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
