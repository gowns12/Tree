//package com.example.tree.api;
//
//import com.example.tree.AcceptanceTest;
//import com.example.tree.User.UserRequest;
//import com.example.tree.letter.dto.LetterRequest;
//import com.example.tree.tree.createTreeRequest;
//import io.restassured.RestAssured;
//import io.restassured.http.ContentType;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//
//public class LetterApiTest extends AcceptanceTest {
//
//    @BeforeEach
//    void setTree(){
//        RestAssured
//                .given()
//                .contentType(ContentType.JSON)
//                .body(new createTreeRequest("test"))
//                .when().post("/trees")
//                .then();
//
//        RestAssured
//                .given()
//                .contentType(ContentType.JSON)
//                .body(new UserRequest("testid","testpw","testnick"))
//                .when().post("/users/signup")
//                .then();
//
//        RestAssured
//                .given()
//                .contentType(ContentType.JSON)
//                .body(new UserRequest("testID","testPW","testNick"))
//                .when().post("/users/signup")
//                .
//
//    }
//
//    @Test
//    void createLetterTest() {
//        RestAssured
//                .given().log().all()
//                .contentType(ContentType.JSON)
//                .body(new LetterRequest("test-content","test-nickname",1L))
//                .when().post("/api/letters")
//                .then().log().all()
//                .statusCode(200);
//    }
//
//    @Test
//    void readLetterTest() {
//
//        RestAssured
//                .given()
//                .contentType(ContentType.JSON)
//                .body(new LetterRequest("test-content","test-nickname",1L))
//                .when().post("/api/letters")
//                .then();
//
//        RestAssured
//                .given().log().all()
//                .pathParam("letter_id", "1")
//                .when().get("/api/letters/{letter_id}")
//                .then().log().all()
//                .statusCode(200);
//    }
//
//
//    @Test
//    void deleteLetterTest() {
//        RestAssured
//                .given()
//                .contentType(ContentType.JSON)
//                .body(new LetterRequest("test-content","test-nickname",1L))
//                .when().post("/api/letters")
//                .then();
//
//        RestAssured
//                .given().log().all()
//                .pathParam("letter_id","1")
//                .when().delete("/api/letters/{letter_id}")
//                .then().log().all()
//                .statusCode(200);
//    }
//}
