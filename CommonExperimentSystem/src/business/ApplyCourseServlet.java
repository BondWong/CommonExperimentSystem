package business;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import persistence.transaction.Transaction;
import persistence.transaction.daoTransaction.ApplyCourseTransaction;

/**
 * Servlet implementation class ApplyCourseServlet
 */
@WebServlet("/ApplyCourseServlet")
public class ApplyCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplyCourseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		Long courseId = Long.parseLong(request.getParameter("courseId"));
		
		Transaction transaction = new ApplyCourseTransaction();
		try {
			transaction.execute(id, courseId);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpSession session = request.getSession();
		synchronized(session){
			session.removeAttribute("openedCourses");
			session.removeAttribute("selectedCourses");
		}
		
		response.sendRedirect("GetSelectedCourseServlet?id="+id);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
