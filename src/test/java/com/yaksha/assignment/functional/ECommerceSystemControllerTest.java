package com.yaksha.assignment.functional;

import static com.yaksha.assignment.utils.TestUtils.businessTestFile;
import static com.yaksha.assignment.utils.TestUtils.currentTest;
import static com.yaksha.assignment.utils.TestUtils.testReport;
import static com.yaksha.assignment.utils.TestUtils.yakshaAssert;

import java.io.IOException;

import org.junit.After;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yaksha.assignment.Product;
import com.yaksha.assignment.utils.XMLParserUtils;

public class ECommerceSystemControllerTest {

	@After
	public void afterAll() {
		testReport();
	}

	@Test
	public void testClassPathXmlContextLoadsProductCorrectly() throws IOException {
		// Load the context from the classpath-based XML configuration
		ApplicationContext contextClasspath = new ClassPathXmlApplicationContext("applicationContext.xml");

		// Retrieve the beans from the context
		Product product1 = contextClasspath.getBean("product1", Product.class);
		Product product2 = contextClasspath.getBean("product2", Product.class);

		// Assert that the products are correctly instantiated
		boolean product1NotNull = product1 != null;
		boolean product2NotNull = product2 != null;

		// Assert that the properties are set correctly
		boolean productDetailsCorrect = "Laptop".equals(product1.getName()) && 1000.0 == product1.getPrice();
		boolean product2DetailsCorrect = "Smartphone".equals(product2.getName()) && 500.0 == product2.getPrice();

		// Use yakshaAssert to validate the test result
		yakshaAssert(currentTest(),
				product1NotNull && product2NotNull && productDetailsCorrect && product2DetailsCorrect,
				businessTestFile);
	}

	@Test
	public void testOrderBeanPropertyFields() throws IOException {
		String filePath = "src/main/resources/applicationContext.xml";

		// Check if the "orderId" property exists and has the correct value
		boolean orderIdPropertyExists = XMLParserUtils.checkPropertyExists(filePath, "order1", "orderId", "ORD12345");

		// Check if the "totalAmount" property exists and has the correct value
		boolean totalAmountPropertyExists = XMLParserUtils.checkPropertyExists(filePath, "order1", "totalAmount",
				"0.0");

		// Validate the results using yakshaAssert
		yakshaAssert(currentTest(), orderIdPropertyExists && totalAmountPropertyExists, businessTestFile);
	}

	@Test
	public void testCustomerBeanPropertyFields() throws IOException {
		String filePath = "src/main/resources/applicationContext.xml";

		// Check if the "customerId" property exists and has the correct value
		boolean customerIdPropertyExists = XMLParserUtils.checkPropertyExists(filePath, "customer1", "customerId",
				"CUST001");

		// Check if the "name" property exists and has the correct value
		boolean namePropertyExists = XMLParserUtils.checkPropertyExists(filePath, "customer1", "name", "John Doe");

		// Validate the results using yakshaAssert
		yakshaAssert(currentTest(), customerIdPropertyExists && namePropertyExists, businessTestFile);
	}

	@Test
	public void testMissingPropertyInXMLShouldFailTest() throws IOException {
		String filePath = "src/main/resources/applicationContext.xml"; // Path to the XML file

		// Check if the 'product1' bean has the 'name' property set to a non-existent
		// value
		boolean result = XMLParserUtils.checkXMLStructure(filePath, "product1", "name", "Non-Existent Value");

		// Using yakshaAssert to validate the test result
		yakshaAssert(currentTest(), !result, businessTestFile);
	}
}
