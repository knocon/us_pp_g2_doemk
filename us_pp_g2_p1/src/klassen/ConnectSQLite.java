package klassen;

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.ResultSet;  
import java.sql.Statement;  

public class ConnectSQLite 
{  
 public static void main(String[] args) 
 {  
    Connection connection = null;  
    ResultSet resultSet = null;  
    Statement statement = null;  

    try 
    {  
        Class.forName("org.sqlite.JDBC");  
        connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\nocon\\git\\us_pp_g2_doemk\\us_pp_g2_p1\\database\\db.db");  
        statement = connection.createStatement();  
        System.out.println("Connection aufgebaut");
        resultSet = statement  
                .executeQuery("SELECT * FROM Account");  
        while (resultSet.next()) 
        {  
            System.out.println("Id:"  
                    + resultSet.getString("accId"));  
        }  
    } 
    catch (Exception e) 
    {  
        e.printStackTrace();  
    }
    finally 
    {  
        try 
        {  
            resultSet.close();  
            statement.close();  
            connection.close(); 
            System.out.println("Connection geschlossen");
        } 
        catch (Exception e) 
        {  
            e.printStackTrace();  
        }  
    }  
}  
}  