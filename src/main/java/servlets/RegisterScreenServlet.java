package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.*;
import storage.RegistrationRepo;

/**
 * @MultipartConfig Annotation that may be specified on a jakarta. servlet. Servlet class,
 * indicating that instances of the Servlet expect requests that conform to the multipart/ form-data
 * MIME type. Basically gives info to servlet that we are going to handle text inputs , file ,
 * videos @Parameter Part (file variable-name) throws @ServletException
 */
@MultipartConfig
public class RegisterScreenServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    response.setContentType("text/html");

    try (PrintWriter out = response.getWriter()) {
      String name = request.getParameter("userName");
      String password = request.getParameter("password");
      String email = request.getParameter("email");
      Part file = request.getPart("file");
      String fileName = file.getSubmittedFileName();
      out.println(fileName);
      try {
        Thread.sleep(3000);
        RegistrationRepo registrationRepo = new RegistrationRepo();
        int result = registrationRepo.setUserValuesInDb(name, password, email, fileName);
        if (result == 1) {
          out.println("success");
          InputStream inputStream = file.getInputStream();
          byte[] data = new byte[inputStream.available()];
          try {
            int bytes = inputStream.read(data); // throws IOException
            if (bytes > 0) {
              String path =
                  request.getServletContext().getRealPath("/")
                      + "images"
                      + File.separator
                      + fileName;
              System.out.println(path);
              try (FileOutputStream fileOutputStream = new FileOutputStream(path)) {
                fileOutputStream.write(data);
              } catch (Exception e) {
                System.out.println(
                    "Something went wrong while writing data to fileOutputStream" + e);
              }
            } else {
              System.out.println("No data to read");
            }
          } catch (IOException ie) {
            System.out.println("Io exception caught" + ie);
          }
        } else {
          out.println("failure");
        }
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
