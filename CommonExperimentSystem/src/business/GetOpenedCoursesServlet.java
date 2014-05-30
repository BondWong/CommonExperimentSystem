package business;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Course;
import persistence.transaction.Transaction;
import persistence.transaction.daoTransaction.GetOpenedCoursesTransaction;

/**
 * Servlet implementation class GetCourses
 */
@WebServlet("/GetOpenedCourses")
public class GetOpenedCoursesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetOpenedCoursesServlet() {
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
		boolean hasCourse = false;
		synchronized(session){
			hasCourse = session.getAttribute("openedCourses")==null?false:true;
		}
		
		if(!hasCourse){
			Transaction transaction = new GetOpenedCoursesTransaction();
			List<Course> courses = null;
			try {
				courses = (List<Course>) transaction.execute(id);
				System.out.println(courses);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			synchronized(session){
				if(courses.size()==0)
					courses = null;
				session.setAttribute("openedCourses", courses);
			}
		}
		 
		response.sendRedirect("applyCourse.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
