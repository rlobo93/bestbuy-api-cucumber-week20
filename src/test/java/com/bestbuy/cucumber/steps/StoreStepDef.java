package com.bestbuy.cucumber.steps;

import com.bestbuy.studentinfo.StoreSteps;
import com.bestbuy.utils.TestUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

public class StoreStepDef {
    static String name = "Ron" + TestUtils.getRandomValue();
    static String type = "Jira" + TestUtils.getRandomValue();
    static String address = "108 london Road";
    static String address2 = "london road";
    static String city = "London";
    static String state = "London";
    static String zip = "1111";
    static int lat = 111;
    static int lng = 111;
    static String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9;Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8";
    static int storeID;

    ValidatableResponse response;

    @Steps
    StoreSteps storesSteps;

    @When("^This will create a new Store$")
    public void thisWillCreateANewStore() {
        HashMap<Object, Object> servicesData = new HashMap<>();
        response = storesSteps.createStore(name, type, address, address2, city, state, zip, lat, lng, hours,servicesData);
//        response.log().all().statusCode(201);
        storeID = response.log().all().extract().path("id");
        System.out.println(storeID);
    }

    @And("^I Verify if the store was added to the application$")
    public void iVerifyIfTheStoreWasAddedToTheApplication() {
        HashMap<String, ?> storeMap = storesSteps.getStoreInfoById(storeID);

    }

    @And("^I Update the store information$")
    public void iUpdateTheStoreInformation() {
        name = name + "_updated";
        HashMap<Object, Object> servicesData = new HashMap<>();
        storesSteps.updateStore(storeID,name, type, address, address2, city, state, zip, lat, lng, hours,servicesData);

    }

    @Then("^I service the store by ID$")
    public void iServiceTheStoreByID() {
        storesSteps.deleteStore(storeID).statusCode(200);
        storesSteps.getStoreByID(storeID).statusCode(404);
    }


}
