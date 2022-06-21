package com.bestbuy.cucumber.steps;

import com.bestbuy.studentinfo.ProductSteps;
import com.bestbuy.utils.TestUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

public class ProductStepsDef {

    static String name = "Nvidia" + TestUtils.getRandomValue();
    static String type = "Rtx Gpu" + TestUtils.getRandomValue();
    static int price = 2;
    static int shipping = 4;
    static String upc = "RTX";
    static String description = "Nvidia Rtx 3090ti Gpu";
    static String manufacturer = "Msi";
    static String model = "3090 ti";
    static String url = "www.msi.com";
    static String image = "11111111-12";
    static int productID;
    @Steps
    ProductSteps productSteps;

    @When("^This will create a New product$")
    public void thisWillCreateANewProduct() {
        ValidatableResponse response = productSteps.createProduct(name,type,price,shipping,upc,description,manufacturer,model,url,image);
        response.log().all().statusCode(201);
        productID = response.log().all().extract().path("id");
    }

    @And("^I Verify if the Product was added to the application$")
    public void iVerifyIfTheProductWasAddedToTheApplication() {
        HashMap<String,Object> productMap = productSteps.getProductInformation(name);

        Assert.assertThat(productMap, hasValue(name));

    }

    @And("^I Update the product information$")
    public void iUpdateTheProductInformation() {
        name = name + "_updated";
        productSteps.updateProduct(productID,price,shipping,name,type,upc,description,manufacturer,model,url,image);

        HashMap<String, Object> productMap = productSteps.getProductInformation(name);
        Assert.assertThat(productMap, hasValue(name));

    }

    @Then("^I Delete the product by using Product ID$")
    public void iDeleteTheProductByUsingProductID() {
        productSteps.deleteProduct(productID).statusCode(200);
        productSteps.getProductById(productID).statusCode(404);

    }
}
