package org.cms.scis.app.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class Util {
	public static String marshallForXMLRootElement(Object o) throws JAXBException, PropertyException {
		JAXBContext context = JAXBContext.newInstance(o.getClass());
		Marshaller marshaller = context.createMarshaller();
		StreamResult rs = new StreamResult(new StringWriter());
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.marshal(o, rs);
		return rs.getWriter().toString();
	}

	public static Object unmarshallForXMLRootElement(String xml, String clazz) throws ClassNotFoundException, JAXBException {
		JAXBContext context = JAXBContext.newInstance(Class.forName(clazz));
		ByteArrayInputStream input = new ByteArrayInputStream (xml.getBytes());
		Unmarshaller unmarshaller = context.createUnmarshaller();
		return unmarshaller.unmarshal(input);
	}

	public static String marshall(Object o) throws JAXBException, PropertyException
	    {
	        JAXBContext context = JAXBContext.newInstance(o.getClass().getPackage().getName());
	        Marshaller marshaller = context.createMarshaller();
	        StreamResult rs = new StreamResult(new StringWriter());
	        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	        marshaller.marshal(new JAXBElement( new QName("http://example.ffe.cms.gov/exchange/1.0", "Payload"), o.getClass(), o), rs);
	        //marshaller.marshal(o, rs);
	        return rs.getWriter().toString();
	    }

	public static <T> Object unmarshall(String xml, String clazz) throws ClassNotFoundException, JAXBException {
		JAXBContext context = JAXBContext.newInstance(Class.forName(clazz));
		ByteArrayInputStream input = new ByteArrayInputStream (xml.getBytes());
		Unmarshaller unmarshaller = context.createUnmarshaller();
		JAXBElement<T> obj = (JAXBElement<T>) unmarshaller.unmarshal(input);
		return  obj.getValue();

	}

	static Map<String, Schema> cachedSchemas = new HashMap<String, Schema>();

	/**
	 * It will validate the request xml with XSD.
	 *
	 * @param xmlString
	 * @param xsdUrl
	 * @return
	 * @throws Exception
	 */
	public static boolean isValidXml(String xmlAsString, String xsdFileName)
			throws SAXException, IOException {

		Schema schema = cachedSchemas.get(xsdFileName);
		if (schema == null) {
			URL xsdURL = Util.class.getClassLoader()
					.getResource(xsdFileName);
			String schemaLang = "http://www.w3.org/2001/XMLSchema";
			SchemaFactory factory = SchemaFactory.newInstance(schemaLang);
			schema = factory.newSchema(xsdURL);
			cachedSchemas.put(xsdFileName, schema);
		}

		Validator validator = schema.newValidator();
		Source src = new StreamSource(new java.io.StringReader(xmlAsString));
		validator.validate(src);
		return true;
	}

}
