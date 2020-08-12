package com.labforward.api.hello.controller;

import static com.labforward.api.constants.Constants.DEFAULT_ID;
import static com.labforward.api.constants.Constants.GREETING_NOT_FOUND;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.labforward.api.core.exception.ResourceNotFoundException;
import com.labforward.api.hello.domain.Greeting;
import com.labforward.api.hello.service.HelloWorldService;

@RestController
public class HelloController {

	private HelloWorldService helloWorldService;

	public HelloController(HelloWorldService helloWorldService) {
		this.helloWorldService = helloWorldService;
	}

	@GetMapping(value = "/hello")
	@ResponseBody
	public Greeting helloWorld() {
		return getGreeting(DEFAULT_ID);
	}

	@GetMapping(value = "/hello/{id}")
	@ResponseBody
	public Greeting getGreeting(@PathVariable String id) {
		return helloWorldService.getGreeting(id).orElseThrow(() -> new ResourceNotFoundException(GREETING_NOT_FOUND));
	}

	@PostMapping(value = "/hello")
	@ResponseBody
	public Greeting createGreeting(@RequestBody Greeting request) {
		return helloWorldService.createGreeting(request);
	}

	@PutMapping(value = "/hello")
	@ResponseBody
	public Greeting updateGreeting(@RequestBody Greeting request) {
		return helloWorldService.updateGreeting(request)
				.orElseThrow(() -> new ResourceNotFoundException(GREETING_NOT_FOUND));
	}
}
