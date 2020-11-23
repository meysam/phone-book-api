package digital.paisley.phonebook;

import digital.paisley.phonebook.dto.ContactDto;
import digital.paisley.phonebook.dto.GitHubUserDto;
import digital.paisley.phonebook.entities.Contact;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.json.JSONException;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class TestSupport extends Assertions {

    public TestSupport() {
    }

    static final String BASE_OAUTH_URI = "https://paisley.digital:8443";
    static final String BASE_URI = "http://localhost";
    static final String ACCESS_TOKEN = "access_token";
    static final Object CLIENT_ID_VALUE = "paisley-client";
    static final Object GRANT_TYPE_PASSWORD = "password";
    static final Object GRANT_TYPE_CLIENT = "client_credentials";
    static final Object USERNAME = "meysam@paisley.digital";
    static final Object PASSWORD = "meysam-paisley";
    static final Object CLIENT_SECRET_VALUE = "fb79dff0-b096-4b39-a6d5-683718fd538e";

    protected JSONObject getTokenByPasswordFlow() throws JSONException {
        Response response =
                given()
                        .contentType("application/x-www-form-urlencoded")
                        .formParam("client_id", CLIENT_ID_VALUE)
                        .formParam("client_secret", CLIENT_SECRET_VALUE)
                        .formParam("grant_type", GRANT_TYPE_PASSWORD)
                        .formParam("username", USERNAME)
                        .formParam("password", PASSWORD)
                        .when()
                        .post(BASE_OAUTH_URI + "/auth/realms/paisley/protocol/openid-connect/token");
        return new JSONObject(response.getBody().asString());
    }

    protected JSONObject getTokenByClientFlow() throws JSONException {
        Response response =
                given()
                        .contentType("application/x-www-form-urlencoded")
                        .formParam("client_id", CLIENT_ID_VALUE)
                        .formParam("client_secret", CLIENT_SECRET_VALUE)
                        .formParam("grant_type", GRANT_TYPE_CLIENT)
                        .when()
                        .post(BASE_OAUTH_URI + "/auth/realms/paisley/protocol/openid-connect/token");
        return new JSONObject(response.getBody().asString());
    }

    protected GitHubUserDto gitHubUserDto(){
        return new GitHubUserDto(0, "user", 0,null);
    }

    protected ContactDto getContactDto(){
        return  ContactDto.builder()
                .address("Address Test")
                .firstName("Meysam")
                .lastName("Tamkin")
                .phoneNumber("+098 (707) 777-1234")
                .email("meysam.tamkin@gmail.com")
                .build();
    }

    protected Contact getContact(){
        return  Contact.builder()
                .address("Address Test")
                .firstName("Meysam")
                .lastName("Tamkin")
                .email("meysam.tamkin@gmail.com")
                .build();
    }


//    protected List<Dummy> getListDummies() {
//        List<Dummy> dummies = new ArrayList<>();
//        dummies.add(getDummy(0L));
//        return dummies;
//    }


}
