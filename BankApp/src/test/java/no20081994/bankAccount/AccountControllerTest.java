package no20081994.bankAccount;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import no20081994.bankAccount.Controller.AccountController;


@RunWith(SpringRunner.class)
@WebMvcTest(value = AccountController.class)
public class AccountControllerTest {

	@Autowired
	MockMvc mvc;

	@MockBean
	AccountController controller;

	@Test
	public void testGetAllAccounts() throws Exception {

		String URL = "/account";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URL).accept(MediaType.APPLICATION_JSON_VALUE);

		MvcResult result = mvc.perform(requestBuilder).andReturn();

		assertEquals(result.getResponse().getStatus(), 200);
	}

	
	
	@Test
	public void testGetAccountById() throws Exception {

		String id = "1";
		String URL = "/account/" + id;

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URL).contentType(MediaType.APPLICATION_JSON_VALUE);
		
		MvcResult result = mvc.perform(requestBuilder).andReturn();

		assertEquals(result.getResponse().getStatus(), 200);

	}
	
	/*
	 * @Test public void testGetAllCustomers() throws Exception {
	 * 
	 * String URL = "/customer";
	 * 
	 * RequestBuilder requestBuilder =
	 * MockMvcRequestBuilders.get(URL).accept(MediaType.APPLICATION_JSON_VALUE);
	 * 
	 * MvcResult result = mvc.perform(requestBuilder).andReturn();
	 * 
	 * assertEquals(result.getResponse().getStatus(), 200); }
	 */
	
	/*
	 * @Test public void testGetCustomerById() throws Exception {
	 * 
	 * String id = "1"; String URL = "/customer/" + id;
	 * 
	 * RequestBuilder requestBuilder =
	 * MockMvcRequestBuilders.get(URL).contentType(MediaType.APPLICATION_JSON_VALUE)
	 * ; MvcResult result = mvc.perform(requestBuilder).andReturn();
	 * 
	 * assertEquals(result.getResponse().getStatus(), 200);
	 * 
	 * }
	 */
	

	/*
	 * @Test public void testDeleteCustomerByAccId() throws Exception {
	 * 
	 * String id = "1"; String URL = "/customer/" + id;
	 * 
	 * 
	 * RequestBuilder requestBuilder =
	 * MockMvcRequestBuilders.delete(URL).contentType(MediaType.
	 * APPLICATION_JSON_VALUE);
	 * 
	 * MvcResult result = mvc.perform(requestBuilder).andReturn();
	 * 
	 * assertEquals(result.getResponse().getStatus(), 200);
	 * 
	 * }
	 */

}
