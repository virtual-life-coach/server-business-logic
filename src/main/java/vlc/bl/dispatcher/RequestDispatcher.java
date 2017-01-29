package vlc.bl.dispatcher;

import vlc.bl.activity.ActivityCreator;
import vlc.common.connectors.Telegram;
import vlc.common.to.QuoteTO;
import vlc.common.util.Constants;
import vlc.ldb.soap.*;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Logger;

public class RequestDispatcher {

    private static Logger log = Logger.getLogger(RequestDispatcher.class.getName());

    public static void sendDailyQuote() {
        LocalDatabase service = new LocalDatabaseService().getLocalDatabaseImplPort();
        List<UserTO> usersSoap = service.listUsers();
        log.info("Users retrieved: " + usersSoap);
        for (UserTO userSoap : usersSoap) {
            Telegram.sendMessage(userSoap.getTelegramId(), getRandomQuote());
        }
    }

    public static void sendDailyActivity() {
        LocalDatabase service = new LocalDatabaseService().getLocalDatabaseImplPort();
        List<UserTO> usersSoap = service.listUsers();
        for (UserTO userSoap : usersSoap) {
            UserActivityTO userActivitySoap = ActivityCreator.createActivity(service, userSoap);
            Telegram.sendMessage(userSoap.getTelegramId(), "Hey, champ! There is a new activity for you. " +
                    "Have a look at it! Id: " + userActivitySoap.getId());
        }
    }

    private static String getRandomQuote() {
        WebTarget resource = ClientBuilder.newClient().target(Constants.EXTERNAL_ADAPTER_ENDPOINT +
                "_ah/api/adapter/v1/quote");
        Response response = resource.request().accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON).get();
        response.bufferEntity();
        QuoteTO quoteTO = response.readEntity(QuoteTO.class);
        return quoteTO.getQuote();
    }
}
