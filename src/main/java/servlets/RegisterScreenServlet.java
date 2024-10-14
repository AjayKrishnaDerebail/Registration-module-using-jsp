package servlets;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import storage.RegistrationRepo;

public class RegisterScreenServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    response.setContentType("text/html");

    try (PrintWriter out = response.getWriter()) {
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<title> Register Servlet ! </title>");
      out.println("</head>");
      out.println("<body>");

      String name = request.getParameter("userName");
      String password = request.getParameter("password");
      String email = request.getParameter("email");

      out.println(name);
      out.println(password);
      out.println(email);

      try {
        Thread.sleep(3000);
        RegistrationRepo.setUserValuesInDb(name, password, email);
      } catch (RuntimeException e) {
        System.out.println("Error in setting values" + e.getMessage());
      } catch (InterruptedException e) {
          throw new RuntimeException(e);
      }

        out.println("</body>");
      out.println("<html>");
    }

  }
}
