package com.fileutil.unmarshal;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class UnmarshallerUtil {

	public static Object unmarshal(File file, String packageName)
			throws JAXBException {
		try {
			JAXBContext jc = JAXBContext.newInstance(com.fileutil.entity.ReportNote.class);
			Unmarshaller u = jc.createUnmarshaller();
			SAXParserFactory spf = SAXParserFactory.newInstance();

			spf.setXIncludeAware(true);
			spf.setNamespaceAware(true);
			spf.setValidating(true);

			XMLReader xr = spf.newSAXParser().getXMLReader();
			SAXSource source = new SAXSource(xr, new InputSource(
					new FileInputStream(file)));
			return u.unmarshal(source);
		} catch (FileNotFoundException e) {
			throw new JAXBException(e);
		} catch (SAXException e) {
			throw new JAXBException(e);
		} catch (ParserConfigurationException e) {
			throw new JAXBException(e);
		}
	}
}
