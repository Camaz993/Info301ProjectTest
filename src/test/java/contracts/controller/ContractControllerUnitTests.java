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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import contracts.domain.Contract;
import contracts.domain.User;
import contracts.service.AccountService;
import contracts.service.ContractService;

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
public class ContractControllerUnitTests {
	
		@Mock
		private ContractService contractService;
		
		@Mock
		private AccountService accountService;
		
		@InjectMocks
        private ContractController contractController;
		
		@InjectMocks
		private AccountController accountController;
	
        private MockMvc mockMvc;
        
        @Before
        public void setUp() {
        	InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        	viewResolver.setPrefix("/templates/");
            viewResolver.setSuffix(".html");
        	MockitoAnnotations.initMocks(this);
        	mockMvc = MockMvcBuilders.standaloneSetup(contractController)
        			.setViewResolvers(viewResolver)
        			.build();
        }
        
        @SuppressWarnings({ "unchecked", "rawtypes" })
		@Test
        public void testFindAll() throws Exception {
        	List<Contract> contracts = new ArrayList<>();
        	contracts.add(new Contract());
        	contracts.add(new Contract());
        	when(contractService.getAllContracts()).thenReturn((List) contracts);
        	mockMvc.perform(get("/search_contracts"))
        		.andExpect(status().isOk())
        		.andExpect(view().name("search_contracts"))
        		.andExpect(model().attribute("contracts", hasSize(2)));      	
        }
        
        @Test
        public void testAddContractForm() throws Exception {
        	mockMvc.perform(get("/add_contracts"))
    		.andExpect(status().isOk())
    		.andExpect(view().name("add_contracts"));	
        }
        
        @SuppressWarnings({ "rawtypes", "unchecked" })
		@Test
        public void testFindUsers() throws Exception {
        	List<User> users = new ArrayList<>();
        	users.add(new User());
        	users.add(new User());
        	when(contractService.getAllUsers()).thenReturn((List) users);
        	mockMvc.perform(get("/add_contracts"))
        		.andExpect(status().isOk())
        		.andExpect(view().name("add_contracts"))
        		.andExpect(model().attribute("users", hasSize(2)));
        }
        
        @Test
        public void testNewestContract() throws Exception {
        	List<Contract> contracts = new ArrayList<>();
        	Contract contract1 = new Contract();
            Contract contract2 = new Contract();
            contract1.setRequestid(1);
            contract2.setRequestid(2);
        	contracts.add(contract1);
        	contracts.add(contract2);
        	Mockito.when(contractService.findNewestContract()).thenReturn(contract2.getRequestid());
        	Integer resultid = contractService.findNewestContract();
        	assertEquals(contract2.getRequestid(), resultid);
        }
        
        @Test
        public void testShowStatus() throws Exception {
        	mockMvc.perform(get("/add_status"))
        	.andExpect(status().isOk())
    		.andExpect(view().name("add_status"));
        }
        
        @Test
        public void mostRecentTest() throws Exception {
        	mockMvc.perform(get("/"))
        	.andExpect(status().isOk())
    		.andExpect(view().name("index"));			
        }
        
        @Test
        @WithMockUser(username = "test")
        public void assigntoUser() throws Exception {
        	Contract contract1 = new Contract();
        	contract1.setRequestid(1);
        	User user1 = new User();
        	user1.setUserid(1);
        	contract1.setUserid(user1);
        	Mockito.when(contractService.update(contract1)).thenReturn(contract1);
        	mockMvc.perform(post("/assign/" + contract1.getRequestid()))
        		.andExpect(redirectedUrl("/my_contracts"))
        		.andExpect(status().isFound());
        }
        
        
        
        

}
