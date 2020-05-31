package stepDefination;
import java.text.ParseException;
import base.Base;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Scenarios extends Base {

	// ******************************hooks********

	@Before
	public void SetUp(Scenario s) {
		scn = s;
	}

	@After
	public void CleanUp() {
		reqSpec = null;
		response = null;
	}

	// *******************Given********************
	@Given("Foreign Exchange Rates API is up and running")
	public void givenAPI() {
		// Putting base URI value in requestSpecification object
		scn.write("URI under test -" + bundle.getString("uri"));
		reqSpec = getURI(bundle.getString("uri"));

	}

	// ***********When*****************************
	@When("Hit the API with end point as {string}")
	public void hitAPI(String endPoint) {
		scn.write("Hiting API with specific endpoint " + endPoint + " and getting response");
		// Getting response through get method
		response = reqSpec.get(endPoint);
		scn.write("Request of the response is "+response);

	}

	// *******Then*********************************

	@Then("API should return the status code as {int}")
	public void getStatusCode(Integer expected) {
		// Assertion of status code
		scn.write("Extracting status code to validate");
		response.then().assertThat().statusCode(expected);
		scn.write("Reponse code recived "+expected);

	}
	
	@Then("base value should be {int}")
	public void getStatusCodeWithBaseValueAndVerify(Integer expected) {
		// Assertion of status code
		scn.write("Extracting status code to validate");
		response.then().assertThat().statusCode(expected);
		if(expected == 200) {
			scn.write("validating correct status");
		}
		else if (expected == 400) {
			scn.write("Verifying error message for incorrect endpoint");
		}
		else {
			scn.write("No input found");
		}
		
		scn.write("Reponse code recived "+expected);

	}
	@Then("API should return the result as {string}")
	public void verifyResponseWithRespectToDate(String dt) throws ParseException {
		// Assertion of status code
		scn.write("Extracting status code to validate");
		if(dt.equals("200")) {
			int date=Integer.parseInt(dt);
			response.then().assertThat().statusCode(date);
			scn.write("validating correct response ");
		}
		else if (dt.equals("currentDateData")) {
			validatingFutureDateResponse();//To verify if we are using future date still we are getting data of current date
		}
		else {
			scn.write("No input found");
		}		
		scn.write("Reponse code recived"+dt);

	}

}
