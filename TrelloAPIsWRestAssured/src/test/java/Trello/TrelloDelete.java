package Trello;

import io.restassured.http.ContentType;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static Trello.TrelloCreate.token;
import static Trello.TrelloCreate.key;
import static io.restassured.RestAssured.given;

public class TrelloDelete {
    @Test(priority = 1)
    public void deleteChecklist(ITestContext contextChecklist){
        String idChecklist = (String) contextChecklist.getAttribute("ChecklistId");

        given().
                contentType(ContentType.JSON)
                .with()
                .queryParam("key", key)
                .queryParam("token", token)
                .pathParam("idChecklist",idChecklist)
                .when()
                .delete("https://api.trello.com/1/checklists/{idChecklist}");
    }
    @Test(priority = 2)
    public void deleteCard(ITestContext contextCard){
        String idCard = (String) contextCard.getAttribute("cardId");

        given().
                contentType(ContentType.JSON)
                .with()
                .queryParam("key",key)
                .queryParam("token",token)
                .pathParam("idCard",idCard)
                .when()
                .delete("https://api.trello.com/1/cards/{idCard}");
    }
    @Test(priority = 3)
    public void deleteList(ITestContext contextList){
        String idList = (String) contextList.getAttribute("listId");

        given().
                contentType(ContentType.JSON)
                .with()
                .queryParam("key",key)
                .queryParam("token",token)
                .pathParam("idList",idList)
                .when()
                .delete("https://api.trello.com/1/lists/{idList}");
    }
    @Test(priority = 4)
    public void deleteBoard(ITestContext contextBoard){
        String idBoard = (String) contextBoard.getAttribute("boardId");

        given().
                contentType(ContentType.JSON)
                .with()
                .queryParam("key",key)
                .queryParam("token",token)
                .pathParam("idBoard",idBoard)
                .when()
                .delete("https://api.trello.com/1/boards/{idBoard}");
    }
}
