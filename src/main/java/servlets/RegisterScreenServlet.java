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
        // Pause execution for 3 seconds
        Thread.sleep(3000);

        RegistrationRepo registrationRepo = new RegistrationRepo();
        int result = registrationRepo.setUserValuesInDb(name, password, email, fileName);

        if (result == 1) {
          out.println("success");

          // Process the file upload
          handleFileUpload(request, file, fileName);

        } else {
          out.println("failure");
        }

      } catch (InterruptedException e) {
        throw new RuntimeException("Thread was interrupted", e);
      }
    }
  }

  /**
   * Handles the file upload process.
   *
   * @param request  the HttpServletRequest object
   * @param file     the Part object representing the uploaded file
   * @param fileName the name of the file to be saved
   */
  private void handleFileUpload(HttpServletRequest request, Part file, String fileName) {
    try {
      InputStream inputStream = file.getInputStream();
      byte[] data = new byte[inputStream.available()];

      int bytesRead = inputStream.read(data);
      if (bytesRead > 0) {
        // Define the path for the uploaded file
        String path = request.getServletContext().getRealPath("/")
                + "images"
                + File.separator
                + fileName;

        System.out.println("Saving file to: " + path);
        saveFile(path, data);
      } else {
        System.out.println("No data to read from the uploaded file");
      }
    } catch (IOException e) {
      System.out.println("IOException occurred while reading file input stream: " + e.getMessage());
    }
  }

  /**
   * Saves the uploaded file to the specified path.
   *
   * @param path the path where the file will be saved
   * @param data the byte array containing the file data
   */

  private void saveFile(String path, byte[] data) {
    try (FileOutputStream fileOutputStream = new FileOutputStream(path)) {
      fileOutputStream.write(data);
      System.out.println("File saved successfully");
    } catch (IOException e) {
      System.out.println("Failed to write data to file: " + e.getMessage());
    }
  }
}
