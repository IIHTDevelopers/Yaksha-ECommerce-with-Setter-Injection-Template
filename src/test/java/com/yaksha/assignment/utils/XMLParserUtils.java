package com.yaksha.assignment.utils;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLParserUtils {

	/**
	 * Parses the given XML file and checks if the required beans and properties are
	 * present.
	 *
	 * @param filePath              Full path to the XML configuration file (e.g.,
	 *                              "src/main/resources/applicationContext.xml").
	 * @param expectedBeanId        The expected bean id to be found in the XML.
	 * @param expectedProperty      The expected property name in the bean (e.g.,
	 *                              name).
	 * @param expectedPropertyValue The expected value for the property (e.g.,
	 *                              "Laptop").
	 * @return true if all checks pass, false otherwise.
	 */
	public static boolean checkXMLStructure(String filePath, String expectedBeanId, String expectedProperty,
			String expectedPropertyValue) {

		try {
			// Parse the XML file
			File xmlFile = new File(filePath);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(true); // Important to handle namespaces
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(xmlFile);
			document.getDocumentElement().normalize();

			// Check for the required bean ID
			NodeList beanNodes = document.getElementsByTagName("bean");
			for (int i = 0; i < beanNodes.getLength(); i++) {
				Node beanNode = beanNodes.item(i);
				if (beanNode.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) beanNode;
					String beanId = element.getAttribute("id");

					// If the bean matches the expectedBeanId, check the property
					if (beanId.equals(expectedBeanId)) {
						NodeList propertyNodes = element.getElementsByTagName("property");
						for (int j = 0; j < propertyNodes.getLength(); j++) {
							Node propertyNode = propertyNodes.item(j);
							if (propertyNode.getNodeType() == Node.ELEMENT_NODE) {
								Element propertyElement = (Element) propertyNode;
								String propertyName = propertyElement.getAttribute("name");
								String propertyValue = propertyElement.getAttribute("value");

								// Check if the expected property and value match
								if (propertyName.equals(expectedProperty)
										&& propertyValue.equals(expectedPropertyValue)) {
									return true;
								}
							}
						}
					}
				}
			}

			// If no matching bean or property is found, return false
			return false;

		} catch (Exception e) {
			System.out.println("Error parsing the XML file: " + filePath);
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Check if the property exists for a specific bean.
	 *
	 * @param filePath      Full path to the XML configuration file.
	 * @param beanId        The ID of the bean to check.
	 * @param propertyName  The name of the property to check.
	 * @param expectedValue The expected value of the property.
	 * @return true if the property exists and matches the expected value.
	 */
	public static boolean checkPropertyExists(String filePath, String beanId, String propertyName,
			String expectedValue) {
		try {
			File xmlFile = new File(filePath);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(true); // Important to handle namespaces
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(xmlFile);
			document.getDocumentElement().normalize();

			// Get all bean nodes from the XML
			NodeList beanNodes = document.getElementsByTagName("bean");

			// Loop through each bean to check for the required properties
			for (int i = 0; i < beanNodes.getLength(); i++) {
				Node beanNode = beanNodes.item(i);
				if (beanNode.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) beanNode;
					String beanIdValue = element.getAttribute("id");

					// Check if the current bean matches the required beanId
					if (beanId.equals(beanIdValue)) {
						NodeList propertyNodes = element.getElementsByTagName("property");

						// Loop through all properties of the bean
						for (int j = 0; j < propertyNodes.getLength(); j++) {
							Node propertyNode = propertyNodes.item(j);
							if (propertyNode.getNodeType() == Node.ELEMENT_NODE) {
								Element propertyElement = (Element) propertyNode;
								String propertyNameValue = propertyElement.getAttribute("name");
								String propertyValue = propertyElement.getAttribute("value");

								// Check if the property name and value match the expected values
								if (propertyName.equals(propertyNameValue) && propertyValue.equals(expectedValue)) {
									return true; // Property exists with the expected value
								}
							}
						}
					}
				}
			}

			// If no matching bean is found
			System.out.println("Bean " + beanId + " not found.");
			return false; // Bean does not exist
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false; // In case of any exception
	}

}
