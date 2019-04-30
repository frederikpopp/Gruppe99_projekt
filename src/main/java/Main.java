public class Main {

    public static void main(String[] args) {
      UserDAOImpls174878 DB = new UserDAOImpls174878();
      List<UserDTO> persons = new ArrayList<>();
      UserDTO person = new UserDTO();
      person.setUserId(1234);
      person.setUserName("Saxo");
      person.setIni("SP");
      //person.addRole("Class President");
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
