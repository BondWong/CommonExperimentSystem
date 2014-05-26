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
import persistence.transaction.daoTransaction.GetClassificationsTransaction;

/**
 * Servlet implementation class GetClassification
 */
@WebServlet("/GetClassification")
public class GetClassification extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetClassification() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Transaction transaction = new GetClassificationsTransaction();
		HttpSession session = request.getSession();
		boolean hasClassification = false;
		synchronized(session){
			hasClassification = session.getAttribute("classifications")==null?false:true;
		}
		
		if(!hasClassification){
			List<String> classifications = null;
			try {
				classifications = (List<String>) transaction.execute();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			synchronized(session){
				session.setAttribute("classifications", classifications);
			}
		}
		response.sendRedirect("admin_management.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
