package contracts.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import contracts.domain.Contract;
import contracts.domain.User;
import contracts.service.AccountService;
import contracts.service.ContractService;
import contracts.service.IContractService;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class AccountControllerUnitTests {

	@Mock
	private AccountService accountService;
	
	@Mock
	private ContractService contractService;
	
	@InjectMocks
	private AccountController accountController;

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
    public void testCreateAccount() throws Exception {
    	mockMvc.perform(get("/create_account"))
    	.andExpect(status().isOk())
		.andExpect(view().name("create_account"));
    }
	
	@Test
    public void testLoginForm() throws Exception {
    	mockMvc.perform(get("/login"))
    	.andExpect(status().isOk())
		.andExpect(view().name("login"));
    }
	
	@Test
    public void testManageUsers() throws Exception {
		List<User> users = new ArrayList<>();
		users.add(new User());
		users.add(new User());
		Mockito.when(contractService.getAllUsers()).thenReturn((List)users);
    	mockMvc.perform(get("/manage_users"))
    	.andExpect(status().isOk())
		.andExpect(view().name("manage_users"));
    }

}
