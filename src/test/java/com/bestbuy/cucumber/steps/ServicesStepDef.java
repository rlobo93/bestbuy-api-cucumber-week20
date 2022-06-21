package com.bestbuy.cucumber.steps;

import com.bestbuy.studentinfo.ServiceSteps;
import com.bestbuy.utils.TestUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

public class ServicesStepDef {
    public static String name = "Custom Pc Building" + TestUtils.getRandomValue();
    public static int serviceID;

    ValidatableResponse response;

    @Steps
    ServiceSteps serviceSteps;

    @When("^This will create a New service$")
    public void thisWillCreateANewService() {
        response = serviceSteps.createService(name);
        response.log().all().statusCode(201);
        serviceID = response.log().all().extract().path("id");
        System.out.println(serviceID);
    }

    @And("^I Verify if the service was added to the application$")
    public void iVerifyIfTheServiceWasAddedToTheApplication() {
        HashMap<String, Object> productMap = serviceSteps.getServiceInfoByFirstName(name);
        Assert.assertThat(productMap, hasValue(name));
        System.out.println(productMap);
    }

    @And("^I Update the service information$")
    public void iUpdateTheServiceInformation() {
        name = name + "_updated";
        serviceSteps.updateStudent(serviceID, name);

        HashMap<String, Object> productMap = serviceSteps.getServiceInfoByFirstName(name);
        Assert.assertThat(productMap, hasValue(name));
        System.out.println(productMap);
    }

    @Then("^I service the product by ID$")
    public void iServiceTheProductByID() {
        serviceSteps.deleteService(serviceID).statusCode(200);
        serviceSteps.getServiceById(serviceID).statusCode(404);
    }
}
