package view.inc.model;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class IntegracaoJira {

    public static void main(String[] args) {
        RestAssured.baseURI = "https://incview.atlassian.net";

        String endpoint = "/rest/api/2/issue/";

        String jsonBody = "{"
                + "\"fields\": {"
                + "\"project\": {\"key\": \"HDIV\"},"
                + "\"summary\": \"Processo Ilícito\","
                + "\"description\": \"Um processo indevido foi aberto\","
                + "\"issuetype\": {\"name\": \"Submit a request or incident\"}"
                + "}"
                + "}";

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body(jsonBody);

        Response response = request.post(endpoint);

        System.out.println("Código de status: " + response.getStatusCode());
        System.out.println("Resposta: " + response.asString());
    }
}
