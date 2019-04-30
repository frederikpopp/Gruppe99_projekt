package User;

import java.util.ArrayList;
import java.util.List;

public class UserDTO implements IUserDTO {
    private int	userId;
    private String userName;
    private String ini;
    private List<String> roles;

    public UserDTO() {
        this.roles = new ArrayList<>();
    }

    @Override
    public int getUserId() {
        return userId;
    }
    @Override
    public void setUserId(int userId) {
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
        return "UserDTO [userId=" + userId + ", userName=" + userName + ", ini=" + ini + ", roles=" + roles + "]";
    }
}
