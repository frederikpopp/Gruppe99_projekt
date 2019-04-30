package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserDAO implements IUserDAO{

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s174878?"
                + "user=s174878&password=oQmcxREHUVWvN9gai9C4K");
    }

    public void test() {
        System.out.println("test");
    }
}
