package backend.rest;

import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/input/{stairwells}/{stepsPerStride}")
public class HelloWorldService {

	@GET
	@Produces("text/html")
	public Response getUser(@PathParam("stairwells") String listofStairwells,
			@PathParam("stepsPerStride") String stepsPerStride) {

		// convert intput data to array
		List<String> converted = DataProcess.convertInput(listofStairwells);
		//validate & calculate 
		if (DataProcess.DataValidator(converted, stepsPerStride)) {
			String output = String.valueOf(DataProcess.dataCalc(converted, stepsPerStride));
			
			//JSONParser
			JsonObject personObject = Json.createObjectBuilder()
					.add("Stairwells",listofStairwells)
					.add("StepsPerStride",stepsPerStride)
					.add("NrOfStrides",output).build();
			return Response.status(200).entity(personObject.toString() ).build();
		} else {
			String error = "didnt pass validation";
			return Response.status(200).entity(error).build();
		}

	}

}
