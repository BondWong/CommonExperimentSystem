package business;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Experiment;
import model.User;
import persistence.transaction.Transaction;
import persistence.transaction.daoTransaction.GetExperimentsTransaction;

/**
 * Servlet implementation class GetExperimentsServlet
 */
@WebServlet("/GetExperimentsServlet")
public class GetExperimentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetExperimentsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("GetExperimentsServlet");
		Long courseId = Long.parseLong(request.getParameter("courseId"));
		String courseName = request.getParameter("courseName");
		
		HttpSession session = request.getSession();
		if(courseName!=null){
			courseName = new String(courseName.getBytes("ISO-8859-1") ,"utf8");
			synchronized(session){
				session.setAttribute("courseName", courseName);
			}
		}
		boolean hasExperiment = false;
		
		synchronized(session){
			hasExperiment = session.getAttribute("experimentCourseId")!=courseId?false:true;
		}
		
		if(!hasExperiment){
			List<Experiment> experiments = null;
			Transaction transaction = new GetExperimentsTransaction();
			try {
				experiments = (List<Experiment>) transaction.execute(courseId);
				System.out.println(experiments.size());
				} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			synchronized(session){
				session.setAttribute("experiments", experiments);
				session.setAttribute("experimentCourseId", courseId);
			}
		}
			
		synchronized(session){
			response.sendRedirect(((User)session.getAttribute("id")).getUserType().name().toLowerCase() + "_experiment_management.jsp");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
