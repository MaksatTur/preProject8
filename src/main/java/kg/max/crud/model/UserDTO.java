package kg.max.crud.model;

import java.util.List;

public class UserDTO {

    private long id;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private int age;
    private List<String> roles;

    public UserDTO() {
    }

    public UserDTO(String email, String password, String firstname, String lastname, int age, List<String> roles) {
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.roles = roles;
    }



    public UserDTO(User user){
        this.id = user.getId();
        this.email = user.getUsername();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.password = user.getPassword();
        this.age = user.getAge();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
