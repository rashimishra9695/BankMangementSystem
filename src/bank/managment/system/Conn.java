package bank.managment.system;
import java.sql.*;

public class Conn {
    Connection c;
    Statement s;
    
    public Conn(){
        try{
          c=DriverManager.getConnection("jdbc:mysql:///bankmanagementsystem","W3_86770_Rashi","manager");
          s=c.createStatement();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
}
