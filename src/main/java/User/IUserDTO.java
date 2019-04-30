package User;

public interface IUserDTO {
    int getUserID();

    void setUserID(int userID);

    String getRole();

    void setRole(String role);

    boolean getAdminStatus();

    int getAdminStatus(int status);

    void setAdminStatus(int status);
}
