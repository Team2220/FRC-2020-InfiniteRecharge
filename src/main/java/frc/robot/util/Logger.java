package frc.robot.util;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Logger {
    public static void log(String message){
        // using Date class
        Date date = new Date();
        System.out.println(sdf.format(date)+" " + message  );
    }
    private static final DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss:SSSS");   
}