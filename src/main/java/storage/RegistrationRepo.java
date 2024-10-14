package storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationRepo {
  public int setUserValuesInDb(String name, String password, String email) {
    System.out.println("Hello world!");
    try (Connection conn = ConnectionProvider.getConnection()) {

      String insertQuery = "INSERT INTO registration_module(name,password,email) values(?,?,?)";

      try (PreparedStatement insertStatement = conn.prepareStatement(insertQuery)) {
        insertStatement.setString(1, name);
        insertStatement.setString(2, password);
        insertStatement.setString(3, email);

        insertStatement.executeUpdate();
        return 1;
      } catch (SQLException ie) {
        System.out.println(ie.getMessage());
      }
    } catch (ClassNotFoundException e) {
      System.out.println("Exception is " + e.getMessage());
    } catch(Exception e){
      System.out.println("Should never come here !");
      throw new RuntimeException(e);
    }
    return 0;
  }
}
