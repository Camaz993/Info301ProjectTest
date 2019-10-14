package contracts.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import contracts.controller.AccountController;
import contracts.domain.User;
import contracts.service.AccountService;
import contracts.service.ContractService;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class AccountServiceUnitTests {

	@Mock
	private AccountService accountService;
	
	@Mock
	private ContractService contractService;
	
	@InjectMocks
	private AccountController accountController;

    @SuppressWarnings("unused")
	private MockMvc mockMvc;
	
	@Before
    public void setUp() {
    	InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    	viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".html");
    	MockitoAnnotations.initMocks(this);
    	mockMvc = MockMvcBuilders.standaloneSetup(accountController)
    			.setViewResolvers(viewResolver)
    			.build();
    }
	
	@Test
	public void testUserExists() throws Exception {
		@SuppressWarnings("unused")
		List<User> users = new ArrayList<>();
		User user1 = new User();
		User user2 = new User();
		user1.setUsername("user1");
		user2.setUsername("user2");
		Mockito.when(accountService.userExists("user1")).thenReturn(true);
		boolean result = accountService.userExists("user1");
		assertEquals(result, accountService.userExists("user1"));
	}
	
	@Test
	public void testFindUser() throws Exception {
		@SuppressWarnings("unused")
		List<User> users = new ArrayList<>();
		User user1 = new User();
		User user2 = new User();
		user1.setUsername("user1");
		user2.setUsername("user2");
		Mockito.when(accountService.findUser("user1")).thenReturn(user1);
		User result = accountService.findUser("user1");
		assertEquals(result, user1);
	}
	
	
	
	

}
