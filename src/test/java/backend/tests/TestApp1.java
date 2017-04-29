package backend.tests;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import backend.rest.DataProcess;

public class TestApp1 {

	@Test
	public void testPrintHelloWorld() {
		//System.out.println(DataProcess.dataCalc(DataProcess.convertInput("{4,9,8,11,7,20,14}"), "2"));
		//function convertInput
		
		List<String> testList= Arrays.asList("4","9","8","11","7","20","14");
	
		Assert.assertEquals(DataProcess.convertInput("{4,9,8,11,7,20,14}"),testList);
		
		Assert.assertEquals(DataProcess.DataValidator(testList, "2"), true);
		
		Assert.assertEquals(DataProcess.dataCalc(testList, "2"), 50);
	}
}
