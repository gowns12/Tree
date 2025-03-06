package com.example.tree.api;

import com.example.tree.AcceptanceTest;
import com.example.tree.letter.dto.LetterRequest;
import com.example.tree.letter.dto.LetterResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LetterApiTest extends AcceptanceTest {
    @Test
    void createLetterTest() {
        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(new LetterRequest("test-content","test-nickname"))
                .when().post()
                .then().log().all()
                .statusCode(200);
    }

    @Test
    void readLetterTest() {
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(new LetterRequest("test-content","test-nickname"))
                .when().post("/api/letter")
                .then();

        RestAssured
                .given().log().all()
                .pathParam("letter_id", "1")
                .when().get("/api/letter/{letter_id}")
                .then().log().all()
                .statusCode(200);
    }

    @Test
    void readAllLetterTest() {
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(new LetterRequest("test-content1","test-nickname1"))
                .when().post("/api/letter")
                .then();
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(new LetterRequest("test-content2","test-nickname2"))
                .when().post("/api/letter")
                .then();

        List<LetterResponse> letterResponseList =
                RestAssured
                        .given().log().all()
                        .pathParam("tree_id","1")
                        .when().get("/api/letter/{tree_id}")
                        .then().log().all()
                        .statusCode(200)
                        .extract().jsonPath()
                        .getList(".", LetterResponse.class);

        Assertions.assertThat(letterResponseList.get(0).content()).isEqualTo("test-content1");
        Assertions.assertThat(letterResponseList.get(1).content()).isEqualTo("test-content2");
    }

    @Test
    void deleteLetterTest() {
        RestAssured
                .given().log().all()
                .pathParam("letter_id","1")
                .when().delete("/api/letter/{letter_id}")
                .then().log().all()
                .statusCode(200);
    }
}
