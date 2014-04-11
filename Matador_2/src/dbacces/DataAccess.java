package dbacces;

import java.sql.*;
import java.util.ArrayList;

//=== Provides a simple interface to execute SQL statements
//=== 2014 Henrik Hauge

public class DataAccess
{
  // Uses MySQL database named "test" on local machine as default
  private String driver		= "com.mysql.jdbc.Driver";
  private String database_url	= "jdbc:mysql://mysql7.gigahost.dk:3306/jakobsabinsky_matador";//Default schema: test.
  private String username	= "jakobsabinsky";
  private String password	= "Helena11";
   
  //=== Execute a SELECT statement
  //    Returns a 2-dimensional Array of Objects 
  //    representing the resulting relation  
  public Object[][] executeQuery(String query) throws SQLException
  {  
    if (query == null)
      return null;
 
    Connection connection 	= null;
    Statement statement 	= null;
    ResultSet rs 		= null;
    
    Object[][] res 	= null;
    
    try
    {
      // Connect
      Class.forName(driver);
      connection = DriverManager.getConnection(database_url, username, password);                   
      statement = connection.createStatement();  
          
      // execute the query
      rs = statement.executeQuery(query);         
    
      // get number of columns in the result (= number of attributes)
      ResultSetMetaData metaData = rs.getMetaData();
      int numberOfColumns = metaData.getColumnCount();      
  
      // transfer resultset to ArrayList of array of Objects
      ArrayList<Object[]> al = new ArrayList<Object[]>();
      while (rs.next())
      {
        Object[] row = new Object[numberOfColumns]; 
        for (int i = 1; i <= numberOfColumns; i++)
        {
        	row[i-1] = rs.getObject(i);
        }
        al.add(row);
      } 
      res = al.toArray(new Object[0][0]); // convert to 2-dim array
    }
    catch (Exception e)
    {
       e.printStackTrace();
       System.exit(1);
    }
    finally
    {
      rs.close();
      statement.close();
      connection.close();  
    }   
    return res; 
  }
    
  //===	Execute an insert, update or delete statement 
  //	Returns the number of rows affected
  public int executeUpdate(String query) throws SQLException
  {
    if( query == null)
      return -1;
      
    Statement statement 	= null;
    Connection connection 	= null;
    
    int rows 				= 0;
    
    try 
    {  
      //=== connect
      Class.forName(driver);
      connection = DriverManager.getConnection(database_url, username, password);                   
      statement = connection.createStatement();
  
      //=== execute statement                      
       rows = statement.executeUpdate(query);       
    }
    catch (Exception e)
    {
       e.printStackTrace();
       System.exit(1);
    }
    finally
    {  
      statement.close();
      connection.close();        
    } 
    return rows;
  }  
}



