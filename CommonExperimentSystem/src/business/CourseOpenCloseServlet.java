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
import persistence.transaction.daoTransaction.CourseOpenCloseTransaction;

/**
 * Servlet implementation class CourseOpenCloseServlet
 */
@WebServlet("/CourseOpenCloseServlet")
public class CourseOpenCloseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseOpenCloseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Long courseId = Long.parseLong(request.getParameter("courseId"));
		boolean open = Boolean.parseBoolean(request.getParameter("open"));
		
		System.out.println(open);
		Transaction transaction = new CourseOpenCloseTransaction();
		try {
			transaction.execute(courseId, open);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String id = "";
		HttpSession session = request.getSession();
		synchronized(session){
			session.removeAttribute("createdCourses");
			id = ((User)session.getAttribute("id")).getId();
		}
		
		response.sendRedirect("GetCreatedCoursesServlet?id="+id);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
