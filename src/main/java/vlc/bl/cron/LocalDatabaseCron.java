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

        DoctorTO doctor = new DoctorTO();
        doctor.setName("Francesco");
        doctor.setSurname("Marzotta");
        doctor.setBirthday("12-04-1962");
        doctor.setTelegramId("45845150");
        doctor = service.createDoctor(doctor);

        ActivityTO activity = new ActivityTO();
        activity.setDescription("Go out for a walk!");
        service.createActivity(activity);
        activity.setDescription("Go to the grocery store!");
        activity = service.createActivity(activity);

        UserActivityTO userActivity = new UserActivityTO();
        userActivity.setActivityId(activity.getId());
        userActivity.setUserId(user.getId());
        userActivity.setDetails("Buy 4 eggs and 2 apples.");
        userActivity.setTargetValue(6L);
        userActivity.setCurrentValue(0L);
        Date tomorrowDate = new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh.mm.ss");
        userActivity.setDeadlineDate(formatter.format(tomorrowDate));
        userActivity.setCompleted(false);
        service.createUserActivity(userActivity);

        AppointmentTO appointment = new AppointmentTO();
        appointment.setUserId(user.getId());
        appointment.setDoctorId(doctor.getId());
        appointment.setDate("04-02-2017");
        appointment.setLocation("Via Sommarive, 78");
        service.createAppointment(appointment);

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
