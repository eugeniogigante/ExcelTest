
import java.sql.*;
public class TestSQLLite {
	public static void main( String args[] ) {
	      Connection c = null;
	      
	      try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:C:/sqlite/db/chinook.db");
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Opened database successfully");
	   }

	 /**
     * Connect to the test.db database
     * @return the Connection object
     */
	
	private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:C://sqlite/db/test.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
	
	/**
     * select all rows in the warehouses table
     */
    public void selectAll(){
        String sql = "SELECT id, name, capacity FROM warehouses";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" + 
                                   rs.getString("name") + "\t" +
                                   rs.getDouble("capacity"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	public void getCapacityGreaterThan(double capacity){
        String sql = "SELECT id, name, capacity "
                   + "FROM warehouses WHERE capacity > ?";
 
 try (Connection conn = this.connect();
      PreparedStatement pstmt  = conn.prepareStatement(sql)){
     
     // set the value
     pstmt.setDouble(1,capacity);
     //
     ResultSet rs  = pstmt.executeQuery();
     
     // loop through the result set
     while (rs.next()) {
         System.out.println(rs.getInt("id") +  "\t" + 
                            rs.getString("name") + "\t" +
                            rs.getDouble("capacity"));
     }
 } catch (SQLException e) {
     System.out.println(e.getMessage());
 }
}

}
