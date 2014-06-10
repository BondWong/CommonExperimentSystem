package business;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import persistence.transaction.Transaction;
import persistence.transaction.daoTransaction.DeleteExperimentTransaction;

/**
 * Servlet implementation class DeleteExperimentServlet
 */
@WebServlet("/DeleteExperimentServlet")
public class DeleteExperimentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteExperimentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Long experimentId = Long.parseLong(request.getParameter("experimentId"));
		Long courseId = Long.parseLong(request.getParameter("courseId"));
		
		Transaction transaction = new DeleteExperimentTransaction();
		try {
			transaction.execute(experimentId, courseId);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpSession session = request.getSession();
		synchronized(session){
			session.removeAttribute("experiments");
			session.removeAttribute("experimentCourseId");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("GetExperimentsServlet");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
