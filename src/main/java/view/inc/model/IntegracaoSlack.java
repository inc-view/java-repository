package view.inc.model;

import com.slack.api.Slack;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.request.chat.ChatPostMessageRequest.ChatPostMessageRequestBuilder;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;

import java.io.IOException;

public class IntegracaoSlack {
    public void enviarMensagem(String ipComputador, String nomeFuncionario) {
        String token = "xoxb-6151373049190-6243008597172-gShBD4e41OMboz51XsvcLttu";
        String channelId = "C064FAZ39GW";
        try {

            Slack slack = Slack.getInstance();
            ChatPostMessageRequestBuilder messageRequest = ChatPostMessageRequest.builder()
                    .token(token)
                    .channel(channelId)
                    .text("""
                            Processo Ilícito encontrado\uD83D\uDEA8
                            Ip do Computador: %s
                            Nome do funcionário: %s
                            """.formatted(ipComputador, nomeFuncionario) );

            ChatPostMessageResponse response = slack.methods().chatPostMessage(messageRequest.build());

            if (response.isOk()) {
                System.out.println("Mensagem enviada com sucesso!");
            } else {
                System.err.println("Erro ao enviar a mensagem: " + response.getError());
            }
        } catch (IOException | SlackApiException e) {
            e.printStackTrace();
        }
    }
}
