package com.micahthor.codefellowship.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
public class ApplicationUser implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany
    @JoinTable(
            name="friends",
            joinColumns = @JoinColumn(name="registered_user"),
            inverseJoinColumns = @JoinColumn(name="friends_followed")
    )
    private Set<ApplicationUser> friendsThatIAmFollowing;

    @ManyToMany(mappedBy = "friendsThatIAmFollowing")
    private Set<ApplicationUser> friendsThatAreFollowingMe;

    @OneToMany(mappedBy = "applicationUser")
    private List<Post> posts;

    private String userName;
    private String password;

    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String bio;
    private URL profilePicture;

    public ApplicationUser(String userName, String password, String firstName, String lastName, String dateOfBirth, String bio, URL profilePicture) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
        this.profilePicture = profilePicture;
    }

    public ApplicationUser() {}

    public void followAFriend(ApplicationUser userToFollow) {
        friendsThatIAmFollowing.add(userToFollow);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getBio() {
        return bio;
    }

    public URL getProfilePicture() {
        return profilePicture;
    }

    public long getId() {
        return id;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public String getUserName() {
        return userName;
    }

    public Set<ApplicationUser> getFriendsThatIAmFollowing() {
        return friendsThatIAmFollowing;
    }

    public Set<ApplicationUser> getFriendsThatAreFollowingMe() {
        return friendsThatAreFollowingMe;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
