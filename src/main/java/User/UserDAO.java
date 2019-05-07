package User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static Utilities.ConnectionHandler.createConnection;

public class UserDAO implements IUserDAO{

    @Override
    public void createUser(IUserDTO user) throws DALException {
        try (Connection c = createConnection()){
            // Insert a new user
            PreparedStatement stmtUser = c.prepareStatement(
                    "INSERT INTO users VALUES (?,?,?)");
            stmtUser.setInt(1, user.getUserID());
            stmtUser.setString(2, user.getRole());
            stmtUser.setInt(3, user.getAdminStatus(1));
            stmtUser.executeUpdate();
        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
    }

    @Override
    public IUserDTO getUser(int userID) throws DALException {
        try (Connection c = createConnection()){
            // Select user with matching userID
            IUserDTO user = new UserDTO();
            PreparedStatement stmtUsers = c.prepareStatement(
                    "SELECT * FROM users WHERE user_ID = ?");
            stmtUsers.setInt(1, userID);

            ResultSet userSet = stmtUsers.executeQuery();

            // If there is a matching userID, insert into user object
            if(userSet.next()){
                user.setUserID(userID);
                user.setRole(userSet.getString("role_name"));
                user.setAdminStatus(userSet.getInt("is_admin"));
            }
            return user;
        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
    }

    @Override
    public List<IUserDTO> getUserList() throws DALException {
        try (Connection c = createConnection()){
            List<IUserDTO> users = new ArrayList<IUserDTO>();
            // Prepare statements for users
            PreparedStatement stmtUser = c.prepareStatement(
                    "SELECT * FROM users");

            ResultSet userSet = stmtUser.executeQuery();

            // While new user save the values
            while(userSet.next()){
                IUserDTO user = new UserDTO();
                user.setUserID(userSet.getInt("user_ID"));
                user.setRole(userSet.getString("role_name"));
                user.setAdminStatus(userSet.getInt("is_admin"));

                users.add(user);     // Add user to list
            }
            return users;
        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
    }

    @Override
    public void updateUser(IUserDTO user) throws DALException {
        try (Connection c = createConnection()){
            // Update role and admin status where userID match
            PreparedStatement stmtUser = c.prepareStatement(
                    "UPDATE users SET role_name = ?, is_admin = ? WHERE user_ID = ?");
            stmtUser.setString(1, user.getRole());
            stmtUser.setInt(2, user.getAdminStatus(1));
            stmtUser.setInt(3, user.getUserID());
            stmtUser.executeUpdate();

        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
    }

    @Override
    public void deleteUser(int userID) throws DALException {
        try (Connection c = createConnection()) {
            // Delete user where userID match
            PreparedStatement stmtUserDel = c.prepareStatement(
                    "DELETE FROM users WHERE user_ID = ?");
            stmtUserDel.setInt(1, userID);
            stmtUserDel.executeUpdate();
        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
    }

    @Override
    public boolean isAdmin(int userID) throws DALException {
          try (Connection c = createConnection()) {
          // Prepare statements for users
          PreparedStatement stmtAdminStatus = c.prepareStatement(
                    "SELECT is_admin FROM users WHERE user_ID = ?");
          stmtAdminStatus.setInt(1, userID);

          ResultSet adminRead = stmtAdminStatus.executeQuery();

          // While new user save the value
          if(adminRead.getInt("is_admin") == 0) return false;
          else return true;

        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
    }
}
