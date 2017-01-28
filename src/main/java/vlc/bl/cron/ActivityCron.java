package vlc.bl.cron;

import vlc.bl.dispatcher.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class ActivityCron extends HttpServlet {

    private static final Logger log = Logger.getLogger(ActivityCron.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Received GET request");
        RequestDispatcher.sendDailyActivity();
    }
}
