package User;

import java.util.List;

public interface IUserDTO {
    int getUserID();

    void setUserID(int userID);

    String getRole();

    void setRole(String role);

    boolean getAdminStatus();

    void setAdminStatus(int status);
}
