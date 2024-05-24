package hm.sb_squash_rest.controller;

import java.io.IOException;

import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import hm.sb_squash_rest.dto.ChangeDto;
import hm.sb_squash_rest.service.AppService;

@RestController
public class AppController {

	private AppService service;

	@Autowired
	public AppController(AppService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/change")
	public ChangeDto changeToEur() throws JDOMException, IOException {
		
		ChangeDto changeDto = service.getChange();
		return changeDto;
	}
	
}
