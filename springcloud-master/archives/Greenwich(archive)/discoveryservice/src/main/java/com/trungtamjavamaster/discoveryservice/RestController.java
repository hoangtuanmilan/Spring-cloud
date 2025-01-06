package com.trungtamjavamaster.discoveryservice;

import org.springframework.web.bind.annotation.GetMapping;

import brave.Tracer;

@org.springframework.web.bind.annotation.RestController
public class RestController {

	Tracer tracer;

	public RestController(Tracer tracer) {
		this.tracer = tracer;
	}

	@GetMapping("/test")
	public String test() {
		tracer.currentSpan().tag("Error", "TESSST");
		return "TEST";
	}

}
