package Database;
import java.sql.DriverManager;
import java.sql.Connection;

public class DbUtil {
    private Connection conn;
   
    public DbUtil(){
        try{
            Class.forName("com.mysql.jdbc.Driver");  
            String url="jdbc:mysql://localhost:3306/take_out_system?characterEncoding=utf-8&useSSL=false";            
            String user="root";
            String passWord="199931";
            conn=DriverManager.getConnection(url,user,passWord);    
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public Connection getConnection() {
    	return conn;
    }
}
