/**
 * Java file contaiing a class that has database
 * functions to select and insert information into a database
 */
package Database;

import java.sql.*;
import java.util.HashMap;

/**
 * Java class containing data base insert and select functions.
 *
 */
public class DBFunctions {

    /**
     * Function returns a list of files containg the search word.
     * @param keyword search word
     * @return hashmap containg file names containg the search word.
     */
    public HashMap<String, Integer> getFileNamesContainingKeyword(String keyword) {
        HashMap<String, Integer> files = new HashMap<>();
        Connection connection = DBConnection.getDBConnection();
        PreparedStatement statement = null;
        String sql = "SELECT `file_name`, `word`, `count` FROM `keyword_tags` WHERE `word` = ?";
        try {
            statement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            System.out.println("Error creating select statement: ");
            e.printStackTrace();
        }
        ResultSet rs = null;
        try {
            statement.setString(1, keyword);
            rs = statement.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error executing sql: ");
            e.printStackTrace();
        }
        try {
            while (rs.next()) {
                String fileName = rs.getString("file_name");
                int count = rs.getInt("count");
                files.put(fileName, count);
            }
        } catch (SQLException e) {
            System.out.println("Error processing results: ");
            e.printStackTrace();
        }


        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error closing database connection: ");
            e.printStackTrace();
        }
        if (files.size() == 0){
            files.put("No files found",0);
        }
        return files;
    }

    /**
     * Function to insert words and number of occurances into
     * the database
     *
     * @param fileName
     * @param data hashmap of words and occurances
     */
    public void insertKeywordData(String fileName, HashMap<String, Integer> data) {
        Connection connection = DBConnection.getDBConnection();
        PreparedStatement statement = null;
        String sql = "INSERT INTO `keyword_tags`(`file_name`, `word`, `count`) " +
                "VALUES (?,?,?)";
        try {
            statement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            System.out.println("Error creating insert statement: ");
            e.printStackTrace();
        }
        try {
            for (String word :
                    data.keySet()) {
                int count = data.get(word);
                statement.setString(1, fileName);
                statement.setString(2, word);
                statement.setInt(3, count);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error closing database connection: ");
            e.printStackTrace();
        }

    }
}
