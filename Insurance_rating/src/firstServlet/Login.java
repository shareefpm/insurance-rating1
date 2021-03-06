package firstServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out= response.getWriter();
		String uname=request.getParameter("uname");
		String pwd=request.getParameter("pwd");
		Userbeam details=new InsuranceDAO1().isUser(uname,pwd);
			if(details.getRole()==null) {
			out.print("Sorry Username or Password Error!");
			RequestDispatcher rd=request.getRequestDispatcher("Login.jsp");
			rd.include(request, response);
		}
		else {
			HttpSession session=request.getSession();
			session.setAttribute("uname", details.getUsername());
			if(details.getRole().equals("user")) {
				response.sendRedirect("Mainmenu_user.jsp");
			}
			else if(details.getRole().equals("admin"))
			{
				response.sendRedirect("Mainmenu_admin");
			}
			
		}
	}

}
