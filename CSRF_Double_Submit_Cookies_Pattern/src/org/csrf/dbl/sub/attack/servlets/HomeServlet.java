package org.csrf.dbl.sub.attack.servlets;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Web servlet which authenticate for CSRF token before redirecting to the home
 * page
 * 
 * @author Pavani
 *
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet
{
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    response.getWriter().append("Served at: ").append(request.getContextPath());
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {

    String hiddenToken = request.getParameter("token");

    Optional<String> cookieValue = Stream.of(request.getCookies()).filter(c -> c.getName().equalsIgnoreCase("CSRF_TOKEN")).map(Cookie::getValue).findFirst();

    String csrfToken = cookieValue.get();

    if (csrfToken.equals(hiddenToken))
    {
      response.getWriter().append("Success!");
    }
    else
    {
      response.getWriter().append("ERROR!");
    }
  }

}
