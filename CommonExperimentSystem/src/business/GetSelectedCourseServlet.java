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
import persistence.transaction.daoTransaction.GetSelectedCoursesTransaction;

/**
 * Servlet implementation class GetSelectedCourseServlet
 */
@WebServlet("/GetSelectedCourseServlet")
public class GetSelectedCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSelectedCourseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		boolean hasCourses = false;
		synchronized(session){
			hasCourses = session.getAttribute("selectedCourses")==null?false:true;
		}
		
		if(!hasCourses){
			String id = request.getParameter("id");
			Transaction transaction = new GetSelectedCoursesTransaction();
			Set<Course> selectedCourses = null;
			try {
				selectedCourses = (Set<Course>) transaction.execute(id);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			synchronized(session){
				session.setAttribute("selectedCourses", selectedCourses);
			}
		}
		
		response.sendRedirect("student_course_management.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
