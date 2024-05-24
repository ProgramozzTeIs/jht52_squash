package hm.sb_squash_rest.service;

import java.io.IOException;

import org.jdom2.JDOMException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import hm.sb_squash_rest.dto.ChangeDto;
import hm.sb_squash_rest.parser.XMLParser;

@Service
public class AppService {

	private XMLParser parser;

	public AppService(XMLParser parser) {
		super();
		this.parser = parser;
	}

	public ChangeDto getChange() throws JDOMException, IOException {
		
		ChangeDto changeDto = null;
		
		RestTemplate rt = new RestTemplate();
		String xmlFormat = rt.getForObject("http://api.napiarfolyam.hu/?valuta=eur", String.class);
		
		changeDto = parser.getChange(xmlFormat);
		return changeDto;
	}
	
	
	
}
