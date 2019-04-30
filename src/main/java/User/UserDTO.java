package User;

import java.util.ArrayList;
import java.util.List;

public class UserDTO implements IUserDTO {
    private int	userID;
    private boolean isAdmin;
    private String role;

    public UserDTO() {

    }

    @Override
    public int getUserID() {
        return userID;
    }

    @Override
    public void setUserID(int userID) {
        this.userId = userId;
    }

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public void setAdminStatus(int status) {
        if(status == 0) {
          this.isAdmin = FALSE;
        } else {
          this.isAdmin = TRUE;
        }
    }

    @Override
    public boolean getAdminStatus() {
        return isAdmin;
    }

    @Override
    public int getAdminStatus(int input) {
        if (input != 0) return 1;
        else return 0;
    }

    @Override
    public String toString() {
        return "UserDTO [userId=" + userID + ", role=" + role + ", isAdmin=" + isAdmin + "]";
    }
}