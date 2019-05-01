package User;

import Utilities.DAO;

import java.util.List;

public interface IUserDAO extends DAO {

    //Create
    void createUser(IUserDTO user) throws DALException;
    //Read
    IUserDTO getUser(int userID) throws DALException;
    List<IUserDTO> getUserList() throws DALException;
    boolean isAdmin(int userID)throws DALException;;
    //Update
    void updateUser(IUserDTO user) throws DALException;
    //Delete
    void deleteUser(int userId) throws DALException;

}
