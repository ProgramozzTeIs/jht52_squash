package hm.sb_squash_rest.parser;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.springframework.stereotype.Repository;

import hm.sb_squash_rest.dto.ChangeDto;

@Repository
public class XMLParser {

	public ChangeDto getChange(String xmlFormat) throws JDOMException, IOException {
		
		ChangeDto changeDto = null;
		SAXBuilder sb = new SAXBuilder();
		StringReader reader = new StringReader(xmlFormat);
		Document doc = sb.build(reader);
		
		Element rootElement = doc.getRootElement();
		Element valuta = rootElement.getChild("valuta");
		List<Element> items = valuta.getChildren("item");
		Element vetel = items.get(0).getChild("vetel");
		double vetelDouble = Double.parseDouble(vetel.getValue());
		
		changeDto = new ChangeDto(vetelDouble);
		
		return changeDto;
	}
	
	
	

}
