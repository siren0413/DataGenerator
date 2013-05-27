package com.trading.dataGenerator.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.trading.dataGenerator.domain.StockProfile;


public class GeneratorUtils {

	
	// logger
		private static Logger logger = null;

		static {
			init();
		}

		/**
		 * initialize the logger and set level.
		 */
		private static void init() {
			try {
				logger = GeneratorUtils.loggerFactory(GeneratorUtils.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	/**
	 * get configuration file specific property
	 * 
	 * @param property
	 *            filename
	 * @param key
	 * @return value
	 * @throws IOException
	 */
	public static String getProperty(String filename, String key) throws IOException {
		Properties properties = new Properties();
		properties.load(GeneratorUtils.class.getClassLoader().getResourceAsStream(filename));
		return (String) properties.get(key);
	}

	/**
	 * Generate specific logger instance, if the paran=clazz is null, return
	 * rootLogger, o/w return clazz logger.
	 * 
	 * @param clazz
	 * @return logger
	 */
	public static Logger loggerFactory(Class clazz) {
		if (clazz == null) {
			return Logger.getRootLogger();
		} else {
			return Logger.getLogger(clazz.getClass());
		}
	}
	
	/**
	 * Snapshot of XML file
	 * 
	 * @param web_url
	 */
	public static StringBuilder readXMLFileIntoString(String filename) {
		StringBuilder sb = new StringBuilder();
		try {
			File file = new File(filename);
			logger.info("Open URL input stream...");
			InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			logger.info("Starting read and write the content to StringBuilder");
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			logger.info("Closing input stream...");
			br.close();
			logger.info("Reading Complete.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb;
	}
	
	/**
	 * Snapshot of XML file
	 * 
	 * @param web_url
	 */
	public static StringBuilder readXMLFileIntoString(InputStream in) {
		StringBuilder sb = new StringBuilder();
		try {
			logger.info("Open URL input stream...");
			InputStreamReader isr = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			logger.info("Starting read and write the content to StringBuilder");
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			logger.info("Closing input stream...");
			br.close();
			logger.info("Reading Complete.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb;
	}
	
	/**
	 * parse the XML String into javabean
	 * 
	 * @param bean
	 * @param xml_str
	 * @throws ParserConfigurationException
	 */
	public static List XMLStockPaser(String xml_str) throws Exception {
		// get Document
		logger.info("Starting parse XML-String to javabean...");
		Document doc = getDocumentByString(xml_str);
		// start parse
		Element root = doc.getDocumentElement();
		NodeList quotes = root.getElementsByTagName("resource");
		List quoteList = new LinkedList();
		for (int i = 0; i < quotes.getLength(); i++) {
			// new bean instance
			StockProfile stockquote = new StockProfile();
			// get item
			Element quote = (Element) quotes.item(i);
			NodeList fields = quote.getElementsByTagName("field");
			for (int j = 0; j < fields.getLength(); j++) {
				Element tuple = (Element) fields.item(j);
				String name = tuple.getAttribute("name");
				logger.debug("XMLStockPaser: tuple name = " + name);
				Node content = tuple.getFirstChild();
				logger.debug("XMLStockPaser: tuple content = " + content.getNodeValue());
				BeanUtils.setProperty(stockquote, name, content.getNodeValue());
			}
			quoteList.add(stockquote);
		}
		logger.info("Parse XML-String to javabean complete!");
		return quoteList;
	}
	
	/**
	 * get the XML Document by XML-String
	 * 
	 * @param xml_str
	 * @return Document
	 * @throws Exception
	 */
	public static Document getDocumentByString(String xml_str) throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbf.newDocumentBuilder();
		InputStream inputStream = new ByteArrayInputStream(xml_str.getBytes());
		Document doc = builder.parse(inputStream);
		return doc;
	}
	
	/**
	 * get UUID
	 */
	public static String getId() {
		return UUID.randomUUID().toString();
	}

}
