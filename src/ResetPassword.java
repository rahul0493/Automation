import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class ResetPassword {
	
    public void changePassword(String connectionName,String serverName,String serviceName, String port,String user, String systemPassword, String newPassword) {
        String Driver = "oracle.jdbc.driver.OracleDriver";
        String Database = "jdbc:oracle:thin:@"+serverName+":"+port+":"+serviceName;
        try {
            Class.forName(Driver);
            Connection conn = DriverManager.getConnection(Database, user , systemPassword);
            Statement st = conn.createStatement();
            st.execute("ALTER USER " + user + " IDENTIFIED BY " + newPassword);
            System.out.println("Password changed for user"+connectionName);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
        }
    }    
   
}
