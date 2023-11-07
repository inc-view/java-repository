package view.inc.model;

import com.slack.api.Slack;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.request.chat.ChatPostMessageRequest.ChatPostMessageRequestBuilder;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import java.io.IOException;

public class Integracao {
    public static void main(String[] args) throws IOException, SlackApiException {
        String token = "xapp-1-A065BJQ1TC0-6159120823810-c76a3d20b44b4eeefc52c82a731c8d0dfe613bd3da2ccc5b458f932cf2f75586";
        String channelId = "C064FAZ39GW"; // O ID do canal para o qual você deseja enviar a mensagem

        Slack slack = Slack.getInstance();
        ChatPostMessageRequestBuilder messageRequest = ChatPostMessageRequest.builder()
                .token(token)
                .channel(channelId)
                .text("Sua mensagem aqui");

        ChatPostMessageResponse response = slack.methods().chatPostMessage(messageRequest.build());

        if (response.isOk()) {
            System.out.println("Mensagem enviada com sucesso!");
        } else {
            System.err.println("Erro ao enviar a mensagem: " + response.getError());
        }
    }
}