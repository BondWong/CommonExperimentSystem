package business;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.transaction.Transaction;
import persistence.transaction.daoTransaction.IsReportSentTransaction;

/**
 * Servlet implementation class SentReportCheckServlet
 */
@WebServlet("/SentReportCheckServlet")
public class SentReportCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SentReportCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String experimentId = request.getParameter("experimentId");
		
		Transaction transaction = new IsReportSentTransaction();
		boolean isSent = false;
		try {
			isSent = (boolean) transaction.execute(id, experimentId);
			System.out.println(isSent);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(isSent){
			response.sendRedirect("report_sent.jsp");
		} else{
			response.sendRedirect("upExp.jsp?id=" + id + "&experimentId=" + experimentId);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
