package business;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import persistence.transaction.Transaction;
import persistence.transaction.daoTransaction.GetReportsTransaction;

/**
 * Servlet implementation class GetReportLinksServlet
 */
@WebServlet("/GetReportLinksServlet")
public class GetReportLinksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetReportLinksServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Long experimentId = Long.parseLong(request.getParameter("experimentId"));
		Transaction transaction = new GetReportsTransaction();
		
		List<String> reports = null;
		try {
			reports = (List<String>) transaction.execute(experimentId);
			System.out.println(reports);
			if(reports.size() == 0)
				reports = null;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpSession session = request.getSession();
		synchronized(session){
			session.setAttribute("reports", reports);
		}
		
		response.sendRedirect("download_report.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
