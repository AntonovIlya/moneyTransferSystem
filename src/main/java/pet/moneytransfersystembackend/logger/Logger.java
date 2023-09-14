package pet.moneytransfersystembackend.logger;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Logger {

    private Calendar calendar = new GregorianCalendar();
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");

    public void log(LogLevel logLevel, String cls, String message) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(simpleDateFormat.format(System.currentTimeMillis()));
        stringBuilder.append(" - ");
        stringBuilder.append(cls);
        stringBuilder.append(" - ");
        stringBuilder.append(logLevel);
        stringBuilder.append(" - ");
        stringBuilder.append(message);
        write(stringBuilder.toString());
    }

    public void write(String log) {
        try(FileWriter writer = new FileWriter("log.txt", true))
        {
            writer.write(log);
            writer.append('\n');
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        System.out.println(log);
    }

}
