package org.jenkins.maven.integration;

import static org.junit.Assert.*;

import org.junit.Test;

public class JenkinsCalculatorTest {

	
	@Test
	public void addTwoNumbersTest() {
		JenkinsCalculator calculator = new JenkinsCalculator();
		assertEquals(11, calculator.addTwoNumbers(5, 10));		
	}
	
	@Test
	public void substractTwoNumbersTest() {
		JenkinsCalculator calculator = new JenkinsCalculator();
		assertEquals(5, calculator.substractTwoNumbers(10, 5));
	}

}
