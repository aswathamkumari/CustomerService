package com.customer;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
class CustomerServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private WebApplicationContext context;
	
	@Before
	public void setUp()
	{
		
	}
	

}
