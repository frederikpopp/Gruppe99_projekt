package Test;

import User.IUserDAO;
import User.IUserDTO;
import User.UserDAO;
import User.UserDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

class UserDAOTest {

    IUserDAO userDAO = new UserDAO();

    @Test
    void jUnitUser() {
        try{
            // TEST createUser, getUser AND userList
            UserDTO testUser = new UserDTO();
            testUser.setUserID(212);
            testUser.setRole("Pharmacist");
            testUser.setAdminStatus(0);

            userDAO.createUser(testUser);
            IUserDTO receivedUser = userDAO.getUser(212);
            assertEquals(testUser.getUserID(), receivedUser.getUserID());
            assertEquals(testUser.getRole(), receivedUser.getRole());
            assertEquals(testUser.getAdminStatus(),receivedUser.getAdminStatus());

            List<IUserDTO> allUsers = userDAO.getUserList();
            boolean found = false;
            for (IUserDTO user: allUsers) {
                if(user.getUserID() == testUser.getUserID()){
                    assertEquals(testUser.getRole(),user.getRole());
                    assertEquals(testUser.getAdminStatus(), user.getAdminStatus());
                    found = true;
                }
            }
            if(!found){fail();}

            // TEST UPDATE
            testUser.setRole("Doctor");
            testUser.setAdminStatus(1);
            userDAO.updateUser(testUser);

            receivedUser = userDAO.getUser(212);
            assertEquals(testUser.getUserID(), receivedUser.getUserID());
            assertEquals(testUser.getRole(), receivedUser.getRole());
            assertEquals(testUser.getAdminStatus(),receivedUser.getAdminStatus());

            // TEST DELETE
            userDAO.deleteUser(testUser.getUserID());
            allUsers = userDAO.getUserList();

            for (IUserDTO user: allUsers) {
                if(user.getUserID() == testUser.getUserID()){
                    fail();
                }
            }


        }catch (Utilities.DAO.DALException e) {
            e.printStackTrace();
            fail();
        }
    }

}