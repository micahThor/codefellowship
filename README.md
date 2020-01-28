# CodeFellowship App
<!-- Short summary or background information -->
### Create an application that uses **Spring Framework**.  

Project dependencies include *Thymeleaf*, *Spring Web*, *Spring Devtools*, *Java Persistence API*, *Postgres*, and *Spring Security*.  

#### This project contains these **routes**:  
     /           : Home route.  Displays currently logged in user and navigation to log in or sign up.
     /login      : Login route.  Displays a form for registered users to log in.
     /signup     : Signup route.  Displays a form for users to sign up to the application.
     
    
#### How to **run** Songr:
  1. Clone this repo.
  2. Navigate terminal to repo folder location on local machine.
  3. Enter *./gradlew BootRun*
  4. Open web browser of choice
  5. Navigate to *http://localhost:8080*
  6. Visit each page by appending route names above.

## LAB 16 : 28 JAN 2020  
- The site should have a splash page at the root route (/) that contains basic information about the site, as well as a link to the “sign up” page.  
- An ApplicationUser should have a username, password (will be hashed using BCrypt), firstName, lastName, dateOfBirth, bio, and any other fields you think are useful.  
- The site should allow users to create an ApplicationUser on the “sign up” page.
    - Your Controller should have an @Autowired private PasswordEncoder passwordEncoder; and use that to run passwordEncoder.encode(password) before saving the password into the new user.  
- The site should have a page which allows viewing the data about a single ApplicationUser, at a route like /users/{id}.  
    - This should include a default profile picture, which is the same for every user, and their basic information.  
- Using the above cheat sheet, add the ability for users to log in to your app.
- When a user is logged in, the app should display the user’s username on every page (probably in the heading).
- Ensure that your homepage, login, and registration routes are accessible to non-logged in users. All other routes should be limited to logged-in users.
- The site should be well-styled and attractive.
- The site should use reusable templates for its information. (At a minimum, it should have one Thymeleaf fragment that is used on multiple pages.)
- The site should have a non-whitelabel error handling page that lets the user know, at minimum, the error code and a brief message about what went wrong.
- Ensure that user registration also logs users into your app automatically.
- Add a Post entity to your app.
    - A Post has a body and a createdAt timestamp.
    - A logged-in user should be able to create a Post, and a post should belong to the user that created it.
      - hint: this is a relationship between two pieces of data
- A user’s posts should be visible on their profile page.
