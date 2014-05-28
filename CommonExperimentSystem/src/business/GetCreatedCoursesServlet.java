package business;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Course;
import persistence.transaction.Transaction;
import persistence.transaction.daoTransaction.GetCreatedCoursesTransaction;

/**
 * Servlet implementation class GetCreatedCoursesServlet
 */
@WebServlet("/GetCreatedCoursesServlet")
public class GetCreatedCoursesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCreatedCoursesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		HttpSession session = request.getSession();
		System.out.println(id);
		Transaction transaction = new GetCreatedCoursesTransaction();
		boolean hasCourses = false;
		synchronized(session){
			hasCourses = session.getAttribute("createdCourses")==null?false:true;
		}
		if(!hasCourses){
			Set<Course> courses = null;
			try {
				courses = (Set<Course>) transaction.execute(id);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			synchronized(session){
				session.setAttribute("createdCourses", courses);
			}
		}
		
		response.sendRedirect("professor_course_management.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
