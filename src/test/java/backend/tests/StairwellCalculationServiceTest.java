package backend.tests;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import backend.rest.DataProcess;
import backend.rest.DataValidationException;

public class StairwellCalculationServiceTest {

	
	public void testPrintHelloWorld() throws  DataValidationException,NumberFormatException {

		testExampleInput();

		// test if data inputed is with correct type
		testWrongFlights();
		testWrongSteps();

		// test if data inputed is not missing
		testMissingStepsParam();
		testMissingFlights();

		// test data in range
		testStepsNotInCorrectRanage();
		testStepsInCorrectRanage();

		testFlightsNotInCorrectRange();
		testFlightsInCorrectRange();
	}
	@Test
	public void testFlightsInCorrectRange() {
		try {
			for(int i =1; i<=30;i++)
			{
				DataProcess.dataRangeValidator(DataProcess.convertFlightsInput("{"+i+"}"), 2);
				
			}
				
		} catch (DataValidationException e) {
			fail();//if excpetion is thrown, means that method fail
		}
		
	}
	@Test(expected = DataValidationException.class)
	public void testFlightsNotInCorrectRange() throws NumberFormatException, DataValidationException {
		DataProcess.dataRangeValidator(DataProcess.convertFlightsInput("{0}"), 2);
		DataProcess.dataRangeValidator(DataProcess.convertFlightsInput("{-1}"), 2);
		DataProcess.dataRangeValidator(DataProcess.convertFlightsInput("{31}"), 2);
		DataProcess.dataRangeValidator(DataProcess.convertFlightsInput("{310}"), 2);
	}
	@Test
	public void testStepsInCorrectRanage() {
		try {
			DataProcess.dataRangeValidator(DataProcess.convertFlightsInput("{17}"), 2);
			DataProcess.dataRangeValidator(DataProcess.convertFlightsInput("{17}"), 3);
		} catch (DataValidationException e) {
			fail();//if excpetion is thrown, means that method fail
		}
		
	}

	
	public void testStepsNotInCorrectRanage() throws DataValidationException {
		
	try {
		DataProcess.dataRangeValidator(DataProcess.convertFlightsInput("{17}"), 1);
		DataProcess.dataRangeValidator(DataProcess.convertFlightsInput("{17}"), 4);
		
	} catch (Exception e) {
		Assert.assertEquals( e.getMessage(), "Steps per Stride must be between 2 and 3");
	}
		
	}

	@Test(expected = DataValidationException.class)
	public void testMissingFlights() throws DataValidationException {
		DataProcess.testMissingFlightParam("");
		DataProcess.testMissingFlightParam(null);
		
	}
	@Test(expected = DataValidationException.class)
	public void testMissingStepsParam() throws DataValidationException {
		DataProcess.testMissingStepsParam("");
		DataProcess.testMissingStepsParam(null);
	}

	@Test(expected = DataValidationException.class)
	public void testWrongSteps() throws DataValidationException {
		String[] stepsPerStride = new String[]{"1a","a1","11a","{1,a7}","2a"};
		for (String string : stepsPerStride ) {
			DataProcess.convertStepsPerStrideInput(string);
		}
	}
	@Test(expected = DataValidationException.class)
	public void testWrongFlights() throws NumberFormatException, DataValidationException {
		//expecting exception for following flights inputs
		String[] myStringArray = new String[]{"{17a}","{a17}","{1a7}","{1,a7}","{a1,7}","22,{17,88,}","22,{17,88,}55"};
		for (String string : myStringArray) {
			DataProcess.convertFlightsInput(string);
		}
	}

	public void testExampleInput() throws NumberFormatException, DataValidationException {
		//Testing those inputs, that are given by Sagax Technical Test example 
		
		String inputflights  = "{17}";
		String inputStepsPerStride = "3";
		String expectedOutput = "6";
		Assert.assertEquals(job(inputflights,inputStepsPerStride ), expectedOutput);
	
		inputflights  = "{17,17}";
		inputStepsPerStride = "3";
		expectedOutput = "14";
		Assert.assertEquals(job(inputflights,inputStepsPerStride ), expectedOutput);
		
		
		inputflights  = "{4,9,8,11,7,20,14}";
		inputStepsPerStride = "2";
		expectedOutput = "50";
		Assert.assertEquals(job(inputflights,inputStepsPerStride ), expectedOutput);
	}

	public String job(String listofStairwells,String stepsPerStride) throws NumberFormatException, DataValidationException {
		int[] listofStairw = DataProcess.convertFlightsInput(listofStairwells);
		int steps = DataProcess.convertStepsPerStrideInput(stepsPerStride);
		DataProcess.dataRangeValidator(listofStairw, steps);
		String output = String.valueOf(DataProcess.dataCalc(listofStairw, steps));
		return output;
	}
}
