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
import persistence.transaction.daoTransaction.DeleteClassificationTransaction;

/**
 * Servlet implementation class DeleteClassificationServlet
 */
@WebServlet("/DeleteClassificationServlet")
public class DeleteClassificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteClassificationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String classification = request.getParameter("classification");
		classification  = new String(classification.getBytes("ISO-8859-1"),"utf8");
		Transaction transaction = new DeleteClassificationTransaction();
		try {
			transaction.execute(classification);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		synchronized(session){
			List<String> classifications = (List<String>)session.getAttribute("classifications");
			classifications.remove(classification);
			if(classifications.size() == 0)
				classifications = null;
			session.setAttribute("classifications", classifications);
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
