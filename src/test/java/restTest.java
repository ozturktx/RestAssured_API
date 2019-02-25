import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matcher.*;
import static org.hamcrest.core.IsEqual.equalTo;

public class restTest {


    @BeforeClass
    public static void setUp()
    {
        baseURI="https://www.batch10-api.dev.cc/wp-json";
        basePath="/wp/v2";

    }

    //given rest end point https://api.github.com/users/ozturktx/followers
    //when I SEND A HTTP GET REquest
    //then i should get 200k result as a status

    @Test
    public void firstTest()
{
    when()
            .get("https://api.github.com/users/ozturktx/followers")
            .then()
            .statusCode(200);
}

    //given rest end point  https://www.batch10-api.dev.cc/wp-json/wp/v2/posts/
    //when I SEND A HTTP GET REquest
    //then i should get 200k result as a status
// we must use relaxhttps connection
    @Test
   public void secondTest()
   {

       given().relaxedHTTPSValidation().
               when()
               .get("/posts/")
               .then()
               .statusCode(200);
   }

    @Test
    public void thirdTest()
    {

        given().relaxedHTTPSValidation().
                when()
                .get("/posts/")
                .getBody()
                .prettyPrint();

    }


    // id test
    @Test
    public void fourthTest()
    {

        given().relaxedHTTPSValidation().
                when()
                .get("/posts/15")
                .then()
                .statusCode(200)
                .and()
                .body("id",equalTo(15))
                .body("title.rendered",equalTo("This is my second title"));

    }

    // id title test
    @Test
    public void fifthTest()
    {

        given().relaxedHTTPSValidation().
                when()
                    .get("/posts/15")
                .then()
                    .statusCode(200)
                .and()
                    .body("id",equalTo(15))
                    .body("title.rendered",equalTo("This is my second title"));

    }

    // use key as a parameter
    @Test
    public void sixthTest()
    {

        given().relaxedHTTPSValidation().
                when()
                .log().all() // shows the log
                .get("/posts/{id}",15)
                .then()
                .statusCode(200)
                .and()
                .body("id",equalTo(15))
                .body("title.rendered",equalTo("This is my second title"));

    }

    @Test
    public void BaseURI_Test()
    {



        given().relaxedHTTPSValidation().
                when()
                .get("/posts/")
                .then()
                .statusCode(200);
    }
}
