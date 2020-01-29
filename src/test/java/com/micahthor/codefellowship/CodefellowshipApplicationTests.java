package com.micahthor.codefellowship;

import com.micahthor.codefellowship.controllers.ApplicationUserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class CodefellowshipApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ApplicationUserController applicationUserController;

	@Test
	void contextLoads() {

	}

	@Test
	public void homePage() throws Exception {
		this.mockMvc.perform(get("/"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("<h1>CodeFellowship - A Place For Friends</h1>")))
				.andExpect(content().string(containsString("<li><a href=\"/\">Home</a></li>")));
	}

	@Test
	public void loginPage() throws Exception {
		this.mockMvc.perform(get("/login"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("<form action=\"/login\" method=\"POST\">")))
				.andExpect(content().string(containsString("<a href=\"/logout\" class=\"CurrentLoggedInUserName\">Log out</a>")));
	}

	@Test
	public void signupPage() throws Exception {
		this.mockMvc.perform(get("/signup"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("<p>Name                   <input type=\"text\" name=\"userName\"></p>")))
				.andExpect(content().string(containsString("<li><a href=\"/userProfile\">My Profile</a></li>")));
	}

	@Test
	public void UserProfilePage() throws Exception {
		this.mockMvc.perform(get("/userProfile"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">")))
				.andExpect(content().string(containsString("<li><a href=\"/userProfile\">My Profile</a></li>")));
	}

}
