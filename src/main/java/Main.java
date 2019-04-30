public class Main
{
    public static void main(String[] args) {
      UserDAO DB = new UserDAO();
      List<UserDTO> users = new ArrayList<>();
      UserDTO person = new UserDTO();
      person.setUserId(1234);
      person.setRole("Projektleder");
      person.setAdminStatus(0);
      try {
          DB.createUser(person);

          persons = DB.getUserList();

          for (int i = 0; i < persons.size(); i++) {
              System.out.println(persons.get(i).toString());
          }
          //person = DB.getUser(1);
          //System.out.println(person.toString());

      } catch (IUserDAO.DALException ex) {
          Logger.getLogger(UseDB.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
}
