package me.jmll.imcreporteswebapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

  public static Connection getConnection() throws SQLException, ClassNotFoundException {
    String url = "jdbc:mysql://localhost:3306/imc_reportes";
    String user = "root";
    String password = "";

    Class.forName("com.mysql.cj.jdbc.Driver");
    return DriverManager.getConnection(url, user, password);
  }
}


