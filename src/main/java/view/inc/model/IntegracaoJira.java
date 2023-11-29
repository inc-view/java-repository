package view.inc.model;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class IntegracaoJira {

    public static void main(String[] args) {
        String jiraURL = "https://incview.atlassian.net/rest/api/2/issue/";

        PreemptiveBasicAuthScheme basicAuth = new PreemptiveBasicAuthScheme();
        basicAuth.setUserName("Breno dos Reis Delmondes");
        basicAuth.setPassword("Messi100$");

        RestAssured.authentication = basicAuth;

        String jsonBody = "{"
                + "\"fields\": {"
                + "\"project\": {\"key\": \"HDIV\"},"
                + "\"summary\": \"Processo Ilícito\","
                + "\"description\": \"Um processo indevido foi aberto\","
                + "\"issuetype\": {\"name\": \"Submit a request or incident\"}"
                + "}"
                + "}";

        Response response = RestAssured.given().contentType(ContentType.JSON).body(jsonBody).post(jiraURL);

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asPrettyString();

        System.out.println("Código de status: " + statusCode);
        System.out.println("Resposta: " + responseBody);
    }
}
