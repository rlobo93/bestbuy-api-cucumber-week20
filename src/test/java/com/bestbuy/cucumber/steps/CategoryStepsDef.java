package com.bestbuy.cucumber.steps;

import com.bestbuy.studentinfo.CategoriesSteps;
import com.bestbuy.utils.TestUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

public class CategoryStepsDef {


    static String name_1;
    static String id_1;
    static String categoryID;
    static ValidatableResponse response;

    @Steps
    CategoriesSteps categoriesSteps;


    @When("^I creates new category record using \"([^\"]*)\",\"([^\"]*)\"$")
    public void iCreatesNewCategoryRecordUsing(String name,String id)  {
        id = id + TestUtils.getRandomValue();
        response=categoriesSteps.createCategory(name,id);
        id_1 =response.log().all().extract().path("id");
        name_1 = response.log().all().extract().path("name");
        //categoryID = response.log().all().extract().path("id");
        //System.out.println("category id is: " +categoryID);

    }

    @And("^Verify if the Category was added to the application$")
    public void verifyIfTheCategoryWasAddedToTheApplication() {
        response.statusCode(201);

//        categoryID = response.log().all().extract().path("id");
//        HashMap<String, ?> categoryMap = categoriesSteps.getCategoryInfoByName(categoryID);
//        Assert.assertThat(categoryMap, hasValue(name));
//        System.out.println(categoryMap);


    }


    @And("^Update the Category information$")
    public void updateTheCategoryInformation( ) {
        name_1 = name_1 + "_updated";

       //response= categoriesSteps.updatingCategory(name_1, id_1);
       categoriesSteps.updatingCategoryRecord( name_1, id_1).log().all().statusCode(200);



    }


    @And("^I Delete the product by ID$")
    public void iDeleteTheProductByID() {
        categoriesSteps.deleteCategory(id_1).statusCode(200);
        categoriesSteps.getCategoryByID(id_1).statusCode(404);
    }


}
