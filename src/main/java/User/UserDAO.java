package User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO{

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s174878?"
                + "user=s174878&password=oQmcxREHUVWvN9gai9C4K");
    }

    @Override
    public void createUser(IUserDTO user) throws DALException {
        try (Connection c = createConnection()){
            // Insert a new user
            PreparedStatement stmtUser = c.prepareStatement(
                    "INSERT INTO users VALUES (?,?,?)");
            stmtUser.setInt(1, user.getUserId());
            stmtUser.setString(2, user.getUserName());
            stmtUser.setString(3, user.getIni());
            stmtUser.executeUpdate();

            // Insert all roles
            PreparedStatement stmtRoles = c.prepareStatement(
                    "INSERT INTO roles VALUES (?,?)");
            stmtRoles.setInt(1, user.getUserId());
            for (int i = 0; i < user.getRoles().size(); i++){
                stmtRoles.setString(2, user.getRoles().get(i));
                stmtRoles.executeUpdate();
            }

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
                    "SELECT * FROM users WHERE userID = ?");
            stmtUsers.setInt(1, userId);

            PreparedStatement stmtRoles = c.prepareStatement(
                    "SELECT * FROM roles WHERE userID = ?");
            stmtRoles.setInt(1, userId);

            ResultSet userSet = stmtUsers.executeQuery();

            // If there is a matching userID, insert into user object with all roles
            if(userSet.next()){
                user.setUserId(userId);
                user.setUserName(userSet.getString("userName"));
                user.setIni(userSet.getString("ini"));

                ResultSet roleSet = stmtRoles.executeQuery();

                while (roleSet.next()){   // Save roles as long as there are new to fetch
                    user.addRole(roleSet.getString("role_name"));
                }

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

            // Prepare statements for users and roles
            PreparedStatement stmtUser = c.prepareStatement(
                    "SELECT * FROM users");
            PreparedStatement stmtRoles = c.prepareStatement(
                    "SELECT * FROM roles WHERE userID = ?");

            ResultSet userSet = stmtUser.executeQuery();

            // While new user save the values
            while(userSet.next()){
                IUserDTO user = new UserDTO();
                user.setUserId(userSet.getInt("userID"));
                user.setUserName(userSet.getString("userName"));
                user.setIni(userSet.getString("ini"));

                stmtRoles.setInt(1, userSet.getInt("userID"));

                ResultSet roleSet = stmtRoles.executeQuery();

                // Save roles while there are new to fetch
                while (roleSet.next()){
                    user.addRole(roleSet.getString("role_name"));
                }
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
            // Update username and initials where username match

            PreparedStatement stmtUser = c.prepareStatement(
                    "UPDATE users SET userName = ?, ini = ? WHERE userID = ?");
            stmtUser.setString(1, user.getUserName());
            stmtUser.setString(2, user.getIni());
            stmtUser.setInt(3, user.getUserId());
            stmtUser.executeUpdate();

            // Delete all roles where userID match
            PreparedStatement stmtRolesDel = c.prepareStatement(
                    "DELETE FROM roles WHERE userID = ?");
            stmtRolesDel.setInt(1, user.getUserId());
            stmtRolesDel.executeUpdate();

            // Create new roles to userID
            PreparedStatement stmtRoles = c.prepareStatement(
                    "INSERT INTO roles VALUES (?,?)");
            stmtRoles.setInt(1, user.getUserId());
            for (int i = 0; i < user.getRoles().size(); i++){
                stmtRoles.setString(2, user.getRoles().get(i));
                stmtRoles.executeUpdate();
            }

        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
    }

    @Override
    public void deleteUser(int userID) throws DALException {
        try (Connection c = createConnection()){
        // Delete roles where userID match
        PreparedStatement stmtRolesDel = c.prepareStatement(
                "DELETE FROM roles WHERE userID = ?");
        stmtRolesDel.setInt(1, userId);
        stmtRolesDel.executeUpdate();

        // Delete user where userID match
        PreparedStatement stmtUserDel = c.prepareStatement(
                "DELETE FROM users WHERE userID = ?");
        stmtUserDel.setInt(1, userId);
        stmtUserDel.executeUpdate();

        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
    }

    @Override
    public void isAdmin(IUserDTO user) throws DALException {

    }

}
