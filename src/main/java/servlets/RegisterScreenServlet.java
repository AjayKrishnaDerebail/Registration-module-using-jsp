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

      String name = request.getParameter("userName");
      String password = request.getParameter("password");
      String email = request.getParameter("email");

      try {
        Thread.sleep(3000);
        RegistrationRepo registrationRepo = new RegistrationRepo();
        int result = registrationRepo.setUserValuesInDb(name, password, email);
        if(result==1)
          out.println("success");
        else
          out.println("failure");
      } catch (RuntimeException e) {
        System.out.println("Error in setting values" + e.getMessage());
      } catch (InterruptedException e) {
          throw new RuntimeException(e);
      }

    }

  }
}