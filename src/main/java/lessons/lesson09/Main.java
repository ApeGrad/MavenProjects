package lessons.lesson09;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        String jsonString = "C:/Users/LEGION/Downloads/journal_ru.json";
        Gson gson = new Gson();
        FileReader reader = new FileReader(jsonString);
        List<DayJournal> dayJournal = gson.fromJson(reader, new TypeToken<List<DayJournal>>(){}.getType());
        dayJournal.forEach(System.out::println);
    }
}
