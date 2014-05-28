package business;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import persistence.transaction.Transaction;
import persistence.transaction.daoTransaction.CreateExperimentTransaction;

/**
 * Servlet implementation class CreateExperimentServlet
 */
@WebServlet("/CreateExperimentServlet")
public class CreateExperimentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateExperimentServlet() {
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
		Long courseId = Long.parseLong(request.getParameter("courseId"));
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String duration = request.getParameter("duration");
		String purpose = request.getParameter("purpose");
		String demand = request.getParameter("demand");
		String description = request.getParameter("description");
				
		Transaction transaction = new CreateExperimentTransaction();
		try {
			transaction.execute(id, courseId, name, type, duration, purpose, demand, description);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setStatus(500);
		}
		
		HttpSession session = request.getSession();
		synchronized(session){
			session.removeAttribute("experimentCourseId");
		}
		
		response.sendRedirect("GetExperimentsServlet?courseId="+courseId);
		
	}

}
