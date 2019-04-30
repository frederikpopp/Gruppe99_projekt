package User;

import java.util.List;

public interface IUserDAO {

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

    public class DALException extends Exception {
        //Til Java serialisering...
        private static final long serialVersionUID = 7355418246336739229L;

        public DALException(String msg, Throwable e) {
            super(msg,e);
        }

        public DALException(String msg) {
            super(msg);
        }

    }
}
