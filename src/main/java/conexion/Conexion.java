package conexion;

import config.Server;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private String url = String.format("jdbc:mysql://%s:%s/%s",
            Server.HOST, Server.PORT, Server.DATABASE);
    private String user = Server.USER;
    private String password = Server.PASSWORD;
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            new Conexion();
        }
        return connection;
    }

    private Conexion() {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al conectar a la BD");
            System.out.println(ex);
        }
    }
}
