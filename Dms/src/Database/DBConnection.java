/**
 * File containg a java class for a database connection
 */
package Database;

import java.sql.*;

/**
 * Class containg a function to connect to the local mysql database
 */
public class DBConnection {

    /**
     * Function to connect to local mysql database
     * preset username and password
     *
     * @return JDBC Connection
     */
    public static Connection getDBConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/attorney_dms", "root", "noahjon21");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}