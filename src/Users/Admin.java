/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Users;

/**
 *
 * @author ASUS TUFF
 */
public class Admin extends Person{
    public Admin() {}

    public Admin(String firstName, String lastName, String username, String email, String password, String city, DateOfBirth DOB,String CNIC) {
        super(firstName, lastName, username, email, password, city, DOB,CNIC);
    }

    @Override
    public String toString() {
        return "{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", city='" + city + '\'' +
                ", DOB=" + DOB +'}';
    }
}
