package Trello;

import io.restassured.http.ContentType;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class TrelloCreate {

    public static final String key = "";
    public static final String token = "";


    @Test(priority = 1)
    public void createBoard(ITestContext contextBoard){

        String idBoard =

            given().
                    contentType(ContentType.JSON)
                    .with()
                    .queryParam("name","First Board")
                    .queryParam("key",key)
                    .queryParam("token",token)
                    .when()
                    .post("https://api.trello.com/1/boards/")
                    .jsonPath().getString("id");

        System.out.println("Generated Board id is: " + idBoard);
        contextBoard.setAttribute("boardId", idBoard);
    }
    @Test(priority = 2)
    public void createList(ITestContext contextBoard,ITestContext contextList){

        String idBoard = (String) contextBoard.getAttribute("boardId");
        String idList =

            given().
                    contentType(ContentType.JSON)
                    .with()
                    .queryParam("name","First List")
                    .queryParam("key",key)
                    .queryParam("token",token)
                    .queryParam("idBoard", idBoard)
                    .when()
                    .post("https://api.trello.com/1/lists/")
                    .jsonPath().getString("id");

        System.out.println("Generated List id is: " + idList);
        contextList.setAttribute("listId", idList);
    }
    @Test(priority = 3)
    public void createCard(ITestContext contextList,ITestContext contextCard){
        String idList = (String) contextList.getAttribute("listId");
        String idCard =

            given().
                    contentType(ContentType.JSON)
                    .with()
                    .queryParam("name","First Card")
                    .queryParam("key",key)
                    .queryParam("token",token)
                    .queryParam("idList",idList)
                    .when()
                    .post("https://api.trello.com/1/cards/")
                    .jsonPath().getString("id");

        System.out.println("Generated Card id is: " + idCard);
        contextCard.setAttribute("cardId", idCard);
    }
    @Test(priority = 4)
    public void createChecklist(ITestContext contextCard,ITestContext contextChecklist){
        String idCard = (String) contextCard.getAttribute("cardId");
        String idChecklist =

            given().
                    contentType(ContentType.JSON)
                    .with()
                    .queryParam("name","First Board")
                    .queryParam("key",key)
                    .queryParam("token",token)
                    .queryParam("idCard",idCard)
                    .when()
                    .post("https://api.trello.com/1/checklists/")
                    .jsonPath().getString("id");

        System.out.println("Generated Checklist id is: " + idChecklist);
        contextChecklist.setAttribute("ChecklistId", idChecklist);
    }
}
