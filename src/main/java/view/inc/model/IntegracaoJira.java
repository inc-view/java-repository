package view.inc.model;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class IntegracaoJira {

    public void enviarMensagem(String ipComputador, String nome){
        String jiraURL = "https://incview.atlassian.net/rest/api/2/issue/";

        PreemptiveBasicAuthScheme basicAuth = new PreemptiveBasicAuthScheme();
        basicAuth.setUserName("Breno dos Reis Delmondes");
        basicAuth.setPassword("Messi100$");

        RestAssured.authentication = basicAuth;

        String descricao = """
                Processo Ilícito encontrado:
                Ip do Computador: %s /
                 Nome do Funcionário: %s\uD83D\uDEA8
                """.formatted(ipComputador, nome);
        descricao = descricao.replaceAll("\n", " ");


        String jsonBody =
                "{"
                + "\"fields\": {"
                + "\"project\": {\"key\": \"HDIV\"},"
                + "\"summary\": \"Processo Ilícito\","
                + "\"description\": \"" + descricao + "\","
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
