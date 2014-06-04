package business.management;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import persistence.transaction.Transaction;
import persistence.transaction.daoTransaction.LoginTransaction;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		System.out.println("LoginServlet");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		Transaction transaction  = new LoginTransaction();
		User user = null;
		try {
			user = (User) transaction.execute(id, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(user!=null){
			HttpSession session = request.getSession();
			synchronized(session){
				session.setAttribute("id", user);
			}
			response.sendRedirect(user.getUserType().name().toLowerCase() + "_index.jsp");
		} else{
			response.setContentType("application/json");
			response.getWriter().write("error");
		}
	}

}
