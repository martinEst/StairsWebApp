package backend.rest;

public class DataProcess {

	public static void testMissingFlightParam(String param) throws DataValidationException {
		if (param == null || param.length() == 0||param.equalsIgnoreCase("_"))
			throw new DataValidationException("Missing Flights");
	}

	public static void testMissingStepsParam(String param) throws DataValidationException {
		if (param == null || param.length() == 0||param.equalsIgnoreCase("_"))
			throw new DataValidationException("Missing Steps Per Stride");
	}

	public static int[] convertFlightsInput(String stairwells) throws NumberFormatException, DataValidationException {
	
		
		if (stairwells.contains("{")) {
			if(stairwells.indexOf("{")!=0) throw new DataValidationException("incorrect position of opening curly brace { ");
			stairwells = stairwells.replaceAll("\\{", "");
		}
		if (stairwells.contains("}")) {
			if(stairwells.indexOf("}")!=stairwells.length()-1) throw new DataValidationException("incorrect position of closing curly brace- }");
			stairwells = stairwells.replaceAll("\\}", "");
		}
		
		String[] items =  stairwells.split("\\s*,\\s*");
		int[] results = new int[items.length];
		
		for (int i = 0; i < items.length; i++) {
		       	dataTypeValidator(items[i]);
		       	results[i] = Integer.parseInt(items[i]);
		    }
		return results;
	}
	
	public static void dataTypeValidator(String items) throws DataValidationException {
		try {
			Integer.parseInt(items);
		} catch (NumberFormatException nfe) {
			throw new DataValidationException("Incorrect number:"+items);
		}
	}
	public static void dataRangeValidator(int[] items, int stepsPerStride) throws DataValidationException {
		for (int i : items) {
			if(i<1||i>30) throw new DataValidationException("Flights must be between 1 and 30");
		}	
		if(stepsPerStride<2||stepsPerStride>3)throw new DataValidationException("Steps per Stride must be between 2 and 3");
	}
	
	public static long dataCalc(int[] flights, int stepsPerStride) {
		int strides = 0;

		for (int i = 0; i < flights.length; i++) {
			if (i > 0)
				strides += 2;

			if ((flights[i]) % stepsPerStride != 0) {
				strides += Math.floor((flights[i]) / stepsPerStride) + 1;
			} else {
				strides += (flights[i]) / stepsPerStride;
			}
		}
		return strides;
	}

	public static int convertStepsPerStrideInput(String stepsPerStride) throws DataValidationException {
		dataTypeValidator(stepsPerStride);
		return Integer.parseInt(stepsPerStride);
	}

}
