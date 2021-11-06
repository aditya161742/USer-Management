package com.usermanagement.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usermanagement.models.Users;
import com.usermanagement.services.UserDetailsService;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ExtendWith(MockitoExtension.class)
//@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@ComponentScan(basePackages  = "com.tdd")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes = {UsersControllerTest.class})
public class UsersControllerTest {
	
	Logger logger = LoggerFactory.getLogger(UsersControllerTest.class);
	
	@Autowired
    MockMvc mockMvc;
	
	@InjectMocks
	UsersController userController;
	
	@Mock
	UserDetailsService userDetailsService;
	
	@BeforeEach
	public void setUp() {
		
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}
	
	//Test annotation should be org.junit.jupitor.api.Test if using junit 5
	@Test
	@Order(1)
	public void registerUserTest(){
		
		Users users = new Users("abc","abc@abc.com","123456");
		users.setId(1L);
		
		ObjectMapper mapper = new ObjectMapper();
		
		//Mockito.when(this.userDetailsService.save(users)).thenReturn(users);    // Note in this case user object is he parameter only then mock method is called but on controller there is another user object there method is not mocked. Mockito uses the equals for argument matching, try using ArgumentMatchers.any for the save method.
		 
		Mockito.when(this.userDetailsService.save(Mockito.any(Users.class))).thenReturn(users);
		
		try {
			
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/registerUser")
        		.contentType(MediaType.APPLICATION_JSON_VALUE)
        		.content(mapper.writeValueAsString(users)) 
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                /*.andExpect(MockMvcResultMatchers.jsonPath("$.username").value("abc"))*/;
		
		String content = result.getResponse().getContentAsString();   //Get response body  
		
		//logger.info(content); // OFF << FATAL << ERROR << WARN << INFO << DEBUG << TRACE << ALL
		//logger.error("hellloooooooooooooooooooo");   // By default slf4j logs are already there in projects. The info/debug we see in console is basically logs. By default all logs will print on the console window and not in files. If want to print logs in file we need to set the property logging.file or logging.path in the application.properties file	
		
		}
		catch(Exception ex) {
			//ex.printStackTrace();
			//System.out.println("error occured");
			//System.out.println(ex.getMessage());
		}
	}

}
