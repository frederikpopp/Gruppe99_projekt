package User;

import java.util.List;

public interface IUserDTO {
    int getUserId();

    void setUserId(int userID);

    String getRole();

    void setRole(String role);

    boolean isAdmin();

    void setAdmin(boolean admin);
}
