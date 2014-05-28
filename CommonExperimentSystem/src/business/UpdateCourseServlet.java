package business;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import persistence.transaction.Transaction;
import persistence.transaction.daoTransaction.UpdateCourseTransaction;

/**
 * Servlet implementation class UpdateCourseServlet
 */
@WebServlet("/UpdateCourseServlet")
public class UpdateCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCourseServlet() {
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
		Long courseId = Long.parseLong(request.getParameter("courseId"));
		String name = request.getParameter("name");
		String duration = request.getParameter("duration");
		String classTime = request.getParameter("classTime");
		
		Transaction transaction = new UpdateCourseTransaction();
		try {
			transaction.execute(courseId, name, duration, classTime);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String id = "";
		HttpSession session = request.getSession();
		synchronized(session){
			session.removeAttribute("createdCourses");
			id = ((User)session.getAttribute("id")).getId();
			System.out.println(id);
		}
		
		response.sendRedirect("GetCreatedCoursesServlet?id="+id);
		
	}

}
