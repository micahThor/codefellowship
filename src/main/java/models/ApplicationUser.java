package models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.awt.*;
import java.util.Date;

@Entity
public class ApplicationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Autowired private PasswordEncoder passwordEncoder;

    private String userName;
    private String password;

    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String bio;
    private Image profilePicture;

    public ApplicationUser(String userName, String password, String firstName, String lastName, Date dateOfBirth, String bio, Image profilePicture) {
        this.userName = userName;
        this.password = passwordEncoder.encode(password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
        this.profilePicture = profilePicture;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getBio() {
        return bio;
    }

    public Image getProfilePicture() {
        return profilePicture;
    }
}
