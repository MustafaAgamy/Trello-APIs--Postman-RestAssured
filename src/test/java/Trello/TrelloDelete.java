package Trello;

import DataDriven.JSONClass;
import Interfaces.Configurations;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;

public class TrelloDelete {


    Configurations configurations = ConfigFactory.create(Configurations.class);

    private JSONClass jsonReader;
    private JSONClass jsonReaderTest;
    private final String CONFIGURATION_JSON_FILE = configurations.jsonFileName();
    private final String JSON_DATA_FILE = "TestData.json";

    private static Logger log;


    @BeforeMethod
    public void SetUp(){
        RestAssured.baseURI = configurations.baseUrl();
        jsonReader = new JSONClass(CONFIGURATION_JSON_FILE);
        jsonReaderTest = new JSONClass(JSON_DATA_FILE);
        log = LogManager.getLogger(TrelloDelete.class.getSimpleName());
    }

    @Test
    public void deleteChecklist(ITestContext context){
        String idChecklist = (String) context.getAttribute("ChecklistId");
//        String idChecklist = jsonReaderTest.readJson("ChecklistId");

        given().
                contentType(ContentType.JSON)
                .with()
                .queryParam("key", jsonReader.readJson("Key"))
                .queryParam("token", jsonReader.readJson("Token"))
                .pathParam("idChecklist",idChecklist)
                .when()
                .delete("/1/checklists/{idChecklist}")
                .then().statusCode(200);
        
        log.info("Deleted Checklist with id < " + idChecklist + " >");
    }
    @Test(dependsOnMethods = "deleteChecklist")
    public void deleteCard(ITestContext context){
        String idCard = (String) context.getAttribute("cardId");
//        String idCard = jsonReaderTest.readJson("cardId");

        given().
                contentType(ContentType.JSON)
                .with()
                .queryParam("key",jsonReader.readJson("Key"))
                .queryParam("token",jsonReader.readJson("Token"))
                .pathParam("idCard",idCard)
                .when()
                .delete("/1/cards/{idCard}")
                .then().statusCode(200);

        log.info("Deleted Card with id < " + idCard + " >");

    }
    @Test(dependsOnMethods = "deleteCard")
    public void deleteList(ITestContext context){
        String idList = (String) context.getAttribute("listId");
//        String idList = jsonReaderTest.readJson("listId");

        given().
                contentType(ContentType.JSON)
                .with()
                .queryParam("key",jsonReader.readJson("Key"))
                .queryParam("token",jsonReader.readJson("Token"))
                .queryParam("value","true")
                .pathParam("idList",idList)
                .when()
                .put("/1/lists/{idList}/closed")
                .then().statusCode(200);

        log.info("Deleted List with id < " + idList + " >");

    }
    @Test(dependsOnMethods = "deleteList")
    public void deleteBoard(ITestContext context){
        String idBoard = (String) context.getAttribute("boardId");
//        String idBoard = jsonReaderTest.readJson("idBoard");

        given().
                contentType(ContentType.JSON)
                .with()
                .queryParam("key",jsonReader.readJson("Key"))
                .queryParam("token",jsonReader.readJson("Token"))
                .pathParam("idBoard",idBoard)
                .when()
                .delete("/1/boards/{idBoard}")
                .then().statusCode(200);

        log.info("Deleted Board with id < " + idBoard + " >");

    }
}
