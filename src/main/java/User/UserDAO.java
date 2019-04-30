package User;

import java.sql.*;
import java.util.List;

public class UserDAO implements IUserDAO{

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s174878?"
                + "user=s174878&password=oQmcxREHUVWvN9gai9C4K");
    }

    public boolean isAdmin(){
        boolean admin = true;
        return admin;
    }


    public void test() {
        System.out.println("test");
    }

    @Override
    public void createUser(IUserDTO user) throws DALException {

    }

    @Override
    public IUserDTO getUser(int userId) throws DALException {
        return null;

    }

    @Override
    public IUserDTO getUserByIni(String initials) throws DALException {
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
    public void deleteUser(int userId) throws DALException {

    }

}
