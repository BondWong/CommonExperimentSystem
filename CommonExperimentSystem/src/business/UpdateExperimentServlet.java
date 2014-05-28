package business;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import persistence.transaction.Transaction;
import persistence.transaction.daoTransaction.UpdateExperimentTransaction;

/**
 * Servlet implementation class UpdateExperimentServlet
 */
@WebServlet("/UpdateExperimentServlet")
public class UpdateExperimentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateExperimentServlet() {
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
		Long experimentId = Long.parseLong(request.getParameter("experimentId"));
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String duration = request.getParameter("duration");
		String purpose = request.getParameter("purpose");
		String demand = request.getParameter("demand");
		String description = request.getParameter("description");
		
		Transaction transaction = new UpdateExperimentTransaction();
		try {
			transaction.execute(experimentId, name, type, duration, purpose, demand, description);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpSession session = request.getSession();
		synchronized(session){
			session.removeAttribute("experimentCourseId");
		}
		
		response.sendRedirect("GetExperimentsServlet?courseId="+request.getParameter("courseId"));
	}
	
}
