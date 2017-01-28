package vlc.bl.dispatcher;

import vlc.common.connectors.Telegram;
import vlc.common.to.QuoteTO;
import vlc.common.util.Constants;
import vlc.ldb.soap.LocalDatabase;
import vlc.ldb.soap.LocalDatabaseService;
import vlc.ldb.soap.UserTO;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class RequestDispatcher {

    public static void sendDailyQuote() {
        LocalDatabase service = new LocalDatabaseService().getLocalDatabaseImplPort();
        List<UserTO> users = service.listUsers();
        for (UserTO user : users) {
            String randomQuote = getRandomQuote();
            Telegram.sendMessage(user.getTelegramId(), randomQuote);
        }
    }

    private static String getRandomQuote() {
        WebTarget resource = ClientBuilder.newClient().target(Constants.EXTERNAL_ADAPTER_ENDPOINT + "quote");
        Response response = resource.request().accept(MediaType.APPLICATION_JSON).get();
        response.bufferEntity();
        QuoteTO quoteTO = response.readEntity(QuoteTO.class);
        return quoteTO.getQuote();
    }
}
