package com.labforward.api.hello;


import static com.labforward.api.constants.Constants.DEFAULT_ID;
import static com.labforward.api.constants.Constants.DEFAULT_MESSAGE;

import com.labforward.api.core.exception.EntityValidationException;
import com.labforward.api.hello.domain.Greeting;
import com.labforward.api.hello.service.HelloWorldService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloWorldServiceTest {
	
	private static final String HELLO_LUKE = "Hello Luke";

	@Autowired
	private HelloWorldService helloService;
	

	@Test
	public void getDefaultGreetingIsOK() {
		Optional<Greeting> greeting = helloService.getDefaultGreeting();
		Assert.assertTrue(greeting.isPresent());
		Assert.assertEquals(DEFAULT_ID, greeting.get().getId());
		Assert.assertEquals(DEFAULT_MESSAGE, greeting.get().getMessage());
	}
	
	@Test
	public void getGreetingsWithNullReturnsEmpty() {
		final String invalidId = "InvalidID";
		Optional<Greeting> greeting = helloService.getGreeting(invalidId);
		Assert.assertFalse(greeting.isPresent());
	}

	@Test(expected = EntityValidationException.class)
	public void createGreetingWithEmptyMessageThrowsException() {
		final String EMPTY_MESSAGE = "";
		helloService.createGreeting(new Greeting(EMPTY_MESSAGE));
	}

	@Test(expected = EntityValidationException.class)
	public void createGreetingWithNullMessageThrowsException() {
		helloService.createGreeting(new Greeting(null));
	}

	@Test
	public void createGreetingOKWhenValidRequest() {
		Greeting request = new Greeting(HELLO_LUKE);

		Greeting created = createGreeting(request);
		Assert.assertEquals(HELLO_LUKE, created.getMessage());
	}
	
	@Test
	public void updateGreetingOKWhenValidRequest() {
		Greeting request = new Greeting(HELLO_LUKE);
		Greeting created = createGreeting(request);
		
		Assert.assertEquals(HELLO_LUKE, created.getMessage());
		
		created.setMessage("Updated Message");
		Optional<Greeting> updated = helloService.updateGreeting(created);
		
		Assert.assertTrue(updated.isPresent());
		Assert.assertEquals(created.getId(), updated.get().getId());
		Assert.assertEquals(created.getMessage(), updated.get().getMessage());
	}
	
	@Test
	public void updateGreetingReturnsEmptyWhenIdInvalid() {
		Greeting request = new Greeting(HELLO_LUKE);
		request.setId("InvalidId");
		Optional<Greeting> updated = helloService.updateGreeting(request);
		
		Assert.assertFalse(updated.isPresent());
	}
	
	private Greeting createGreeting(Greeting greeting) {
		return helloService.createGreeting(greeting);
	}
}
