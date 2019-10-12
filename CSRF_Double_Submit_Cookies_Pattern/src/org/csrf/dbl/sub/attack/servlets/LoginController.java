package org.csrf.dbl.sub.attack.servlets;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.csrf.dbl.sub.attack.persist.Database;
import org.csrf.dbl.sub.attack.utils.Lambdas;


/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginController() {
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
	  String username = request.getParameter("username");
    String password = request.getParameter("password");
    HttpSession session = request.getSession(true); // create a new session if not exists

    if (Database.isValidUser(username, password))
    {
      String csrfToken = generateCSRFToken(session.getId());
      response.addCookie(Lambdas.COOKIE_WITH_SESSION_ID.apply(session));
      response.addCookie(Lambdas.COOKIE_WITH_CSRF_ID.apply(csrfToken));

      session.removeAttribute("invalidCredentials");
      response.sendRedirect("./Home.jsp");
    }
    else
    {
      session.setAttribute("invalidCredentials", "Not_ok");
      response.sendRedirect("./Login.jsp");
    }
	}
	
  private String generateCSRFToken(String strClearText)
  {
    return strClearText + "." + getRandomString();
  }

  private String getRandomString()
  {
    UUID randomUuid = UUID.randomUUID();
    return randomUuid.toString();
  }

}
