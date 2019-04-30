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
    public void setUserId(int userID) {
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
    public String toString() {
        return "UserDTO [userId=" + userID + ", role=" + role + ", isAdmin=" + isAdmin + "]";
    }
}
