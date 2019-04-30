package User;

import java.sql.*;
import java.util.List;

public class UserDAO implements IUserDAO{

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s174878?"
                + "user=s174878&password=oQmcxREHUVWvN9gai9C4K");
    }

    @Override
    public void createUser(IUserDTO user) throws DALException {

    }

    @Override
    public IUserDTO getUser(int userID) throws DALException {
        return null;

    }

    @Override
    public List<IUserDTO> getUserList() throws DALException {
        return null;
    }

    @Override
    public void updateUser(IUserDTO user) throws DALException {

    }

    @Override
    public void deleteUser(int userID) throws DALException {

    }

    @Override
    public void isAdmin(IUserDTO user) throws DALException {

    }

}
