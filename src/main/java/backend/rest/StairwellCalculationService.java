package backend.rest;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/input/{listFlights}/{stepsPerStride}")
public class StairwellCalculationService {

	@GET
	@Produces("text/html")
	public Response getUser(@PathParam("listFlights") String listFlights,
			@PathParam("stepsPerStride") String stepsPerStride) {

		try {
			//list of methods, that converts,test & calculates the data
			//in case of fail, DataValidationException is thrown and catch block here is executed. 
			
			DataProcess.testMissingFlightParam(listFlights);
			DataProcess.testMissingStepsParam(stepsPerStride);
			int[] listofStairw = DataProcess.convertFlightsInput(listFlights);
			int steps = DataProcess.convertStepsPerStrideInput(stepsPerStride);
			DataProcess.dataRangeValidator(listofStairw, steps);
			String output = String.valueOf(DataProcess.dataCalc(listofStairw, steps));

			// return in json format
			JsonObject personObject = Json.createObjectBuilder().add("listFlights", listFlights)
					.add("StepsPerStride", stepsPerStride)
					.add("NrOfStrides", output).build();
			return Response.status(200).entity(personObject.toString()).build();
		} 
		catch (DataValidationException e) 
		{
			String error = e.getMessage();
			JsonObject personObject = Json.createObjectBuilder().add("<span class='label label-danger'>error</span>", error).build();
			return Response.status(200).entity(personObject.toString()).build();
		}

	}

}
