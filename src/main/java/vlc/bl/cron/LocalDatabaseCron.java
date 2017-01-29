package vlc.bl.cron;

import vlc.common.util.Constants;
import vlc.ldb.soap.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class LocalDatabaseCron extends HttpServlet {

    private static final Logger log = Logger.getLogger(LocalDatabaseCron.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Received GET request");
        log.info("Endpoint: " + Constants.LOCAL_DATABASE_WSDL);
        LocalDatabase service = new LocalDatabaseService().getLocalDatabaseImplPort();

        log.info("Cleaning database...");
        service.resetDB();

        log.info("Creating entities...");

        UserTO user = new UserTO();
        user.setName("Gianvito");
        user.setSurname("Taneburgo");
        user.setBirthday("04-02-1993");
        user.setTelegramId("45845150");
        user = service.createUser(user);

        UserTO user2 = new UserTO();
        user2.setName("Gabriele");
        user2.setSurname("C.");
        user2.setBirthday("04-02-1993");
        user2.setTelegramId("254736676");
        service.createUser(user2);

        DoctorTO doctor = new DoctorTO();
        doctor.setName("Francesco");
        doctor.setSurname("Marzotta");
        doctor.setBirthday("12-04-1962");
        doctor.setTelegramId("45845150");
        doctor = service.createDoctor(doctor);

        ActivityTO activity1 = new ActivityTO();
        activity1.setDescription("Go out for a walk!");
        service.createActivity(activity1);

        ActivityTO activity2 = new ActivityTO();
        activity2.setDescription("Go to the grocery store!");
        activity2 = service.createActivity(activity1);

        Date tomorrowDate = new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000);
        Date yesterdayDate = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
        SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);

        UserActivityTO userActivity = new UserActivityTO();
        userActivity.setActivityId(activity1.getId());
        userActivity.setUserId(user.getId());
        userActivity.setDetails("Buy 4 eggs and 2 apples.");
        userActivity.setTargetValue(100L);
        userActivity.setCurrentValue(0L);
        userActivity.setDeadlineDate(formatter.format(tomorrowDate));
        userActivity.setCompleted(false);
        service.createUserActivity(userActivity);

        UserActivityTO userActivity2 = new UserActivityTO();
        userActivity2.setActivityId(activity2.getId());
        userActivity2.setUserId(user.getId());
        userActivity2.setDetails("Daily goal: 5 km!");
        userActivity2.setTargetValue(100L);
        userActivity2.setCurrentValue(100L);
        userActivity2.setDeadlineDate(formatter.format(yesterdayDate));
        userActivity2.setCompleted(true);
        service.createUserActivity(userActivity2);

        AppointmentTO appointment = new AppointmentTO();
        appointment.setUserId(user.getId());
        appointment.setDoctorId(doctor.getId());
        appointment.setDate(formatter.format(tomorrowDate));
        appointment.setLocation("Via Tauro, 42");
        service.createAppointment(appointment);

        AppointmentTO appointment2 = new AppointmentTO();
        appointment2.setUserId(user.getId());
        appointment2.setDoctorId(doctor.getId());
        appointment2.setDate(formatter.format(yesterdayDate));
        appointment2.setLocation("Via Tauro, 42");
        service.createAppointment(appointment2);

        MeasurementTO measurement = new MeasurementTO();
        measurement.setUserId(user.getId());
        measurement.setType("Minimum blood pressure");
        measurement.setDate("29-01-2017");
        measurement.setValue(80d);
        service.createMeasurement(measurement);
        measurement.setUserId(user.getId());
        measurement.setType("Maximum blood pressure");
        measurement.setDate("29-01-2017");
        measurement.setValue(120d);
        service.createMeasurement(measurement);

        log.info("Entities created.");
    }
}
