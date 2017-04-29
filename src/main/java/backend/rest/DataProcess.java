package backend.rest;

import java.util.Arrays;
import java.util.List;

public class DataProcess {
	public static List<String> convertInput(String stairwells) {
		if (stairwells.contains("{")) {
			stairwells = stairwells.replaceAll("\\{", "");
		}
		if (stairwells.contains("}")) {
			stairwells = stairwells.replaceAll("\\}", "");
		}

		List<String> items = null;

		if (!stairwells.contains(",")) {
			items = Arrays.asList(stairwells);
		} else {
			items = Arrays.asList(stairwells.split("\\s*,\\s*"));
		}
		return items;

	}

	public static boolean DataValidator(List<String> items, String stepsPerStride) {
		boolean validating = true;
		//validate 1 parameter
		for (String string : items) {
			try {
				Long.parseLong(string);
			} catch (NumberFormatException nfe) {
				validating = false;
			}
		}
		
		//validate 2 parameter
		try {
			int speed = Integer.parseInt(stepsPerStride);
			if(speed<2||speed>3)validating = false;
			} catch (NumberFormatException nfe) {
			validating = false;
		}
		
		return validating;
	}

	public static long dataCalc(List<String> converted, String StepsPerStride) {
		long strides=0;
		
		for(int i = 0 ; i<converted.size();i++)
		{
			if(i>0) strides+=2;

			converted.get(i);
			if(Long.parseLong(converted.get(i))%Integer.parseInt(StepsPerStride)!=0)
			{
				strides += Math.floor(Long.parseLong(converted.get(i))/Integer.parseInt(StepsPerStride))+1;
			}
			else
			{
				strides += Long.parseLong(converted.get(i))/Integer.parseInt(StepsPerStride);
			}
		}
		return strides;
	}

}
