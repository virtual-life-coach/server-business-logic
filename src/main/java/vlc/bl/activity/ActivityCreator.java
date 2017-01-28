package vlc.bl.activity;

import vlc.common.util.Constants;
import vlc.ldb.soap.ActivityTO;
import vlc.ldb.soap.LocalDatabase;
import vlc.ldb.soap.UserActivityTO;
import vlc.ldb.soap.UserTO;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ActivityCreator {

    public static UserActivityTO createActivity(LocalDatabase service, UserTO userSoap) {
        ActivityTO activitySoap = new ActivityTO();
        activitySoap.setDescription("Lose weight");
        activitySoap = service.createActivity(activitySoap);

        UserActivityTO userActivitySoap = new UserActivityTO();
        userActivitySoap.setActivityId(activitySoap.getId());
        userActivitySoap.setUserId(userSoap.getId());
        userActivitySoap.setDetails("2 kg in 10 days would be an awesome result!");
        userActivitySoap.setTargetValue(2L);
        userActivitySoap.setCurrentValue(0L);
        Date inTenDays = new Date(System.currentTimeMillis() + 10 * 24 * 60 * 60 * 1000);
        SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
        userActivitySoap.setDeadlineDate(formatter.format(inTenDays));
        userActivitySoap.setCompleted(false);
        return service.createUserActivity(userActivitySoap);
    }
}
