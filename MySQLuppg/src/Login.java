import java.sql.*;

public class Login{
    //i choose to create this class in static so i can reach from all the other class
    public static Connection con;
    public static int loginCheck = 0;
    public static String userName;
    public static final int EJUTLÅNAD = 1;
    public static final int UTLÅNAD =0 ;

    //create a getCon that return the value
    public static Connection getCon() {
        return con;
    }

    public static Connection con(String userName, String password) {
        Login.userName = userName;

        try {
               //create a connection whit my database Bibliotek
               con = DriverManager.getConnection("jdbc:mysql://localhost/Bibliotek", userName, password);
                loginCheck = 1;
        } catch (Exception e) {
        }
        return con;
    }

    //use to logincheck
    public static int getLoginCheck(){
        return loginCheck;
    }

    //this is to get the right id
    public static int getLåntagareId(){
        try {
            PreparedStatement p = con.prepareStatement("SELECT LåntagareId FROM Låntagare WHERE Name = ? ");
            p.setString(1,userName);
            ResultSet set = p.executeQuery();
            if(set.next()){
                return set.getInt(1);
            };
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;

    }

}