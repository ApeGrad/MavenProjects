package lessons.lesson18.new_were_squirrel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class NewDayJournal extends AbstractJournal{
    static final String JSON_STRING = "C:/Users/LEGION/Downloads/journal_ru.json";
    @Override
    List<String> events(List<DayJournal> objectList) {
        List<String> eventsList = new ArrayList<>();
        for (DayJournal journal : objectList) {
            eventsList.add(journal.toString());
        }
        return eventsList;
    }
    @Override
    Set<String> eventsSet(List<DayJournal> objectList) {
        Set<String> eventsSetter = new HashSet<>();
        for (DayJournal day : objectList) {
            String key1 = day.getEvents().toString();
            String key = key1.substring(1, key1.length() - 1);
            String[] values = key.split(",");
            for (int i = 0; i < values.length; i++) {
                values[i] = values[i].trim();
            }
            eventsSetter.addAll(Arrays.asList(values));
        }
        return eventsSetter;
    }
    @Override
    List<DayJournal> createJsonList(DayJournal journal,String jsonString) throws FileNotFoundException {
        Gson gson = new Gson();
        FileReader reader = new FileReader(jsonString);
        List<DayJournal> dayJournal = gson.fromJson(reader, new TypeToken<List<DayJournal>>() {
        }.getType());
        return dayJournal;
    }
    @Override
    int[] tableFor(String event, List<String> journal) {
        int[] table = {0, 0, 0, 0};
        for (String entry : journal) {
            if (entry.contains(event) && entry.contains("false")) {
                table[1] += 1;
            }
            if (entry.contains("true") && entry.contains(event)) {
                table[3] += 1;
            }
            if (!entry.contains(event) && entry.contains("true")) {
                table[2] += 1;
            }
            if (entry.contains("false") && !entry.contains(event)) {
                table[0] += 1;
            }
        }
        return table;
    }

    @Override
    double phi(int[] table) {
        return (table[3] * table[0] - table[2] * table[1]) /
                Math.sqrt((table[2] + table[3]) *
                        (table[0] + table[1]) *
                        (table[1] + table[3]) *
                        (table[0] + table[2]));
    }
    @Override
    String returnCorrelation(Set<String> soloEvents, List<String> events) {
        for (String soloEvent : soloEvents) {
            double correlation = phi(tableFor(soloEvent, events));
            if (correlation > 0.1 || correlation < -0.1) {
                System.out.println(soloEvent + "    : " + correlation);
            }
        }
        return null;
    }

@Override
   List<String> returnPeanuts(List<String> events) {
        List<String> events1 = new ArrayList<>();
        for (String entry : events) {
            if (!entry.contains("ел арахис") && !entry.contains("чистил зубы") | entry.contains("чистил зубы")) {
                events1.add(entry);
            }
            if (entry.contains("ел арахис") && !entry.contains("чистил зубы")) {
                events1.add("арахис-зубы, true");
            }
        }
        return  events1;
    }

    public static void main(String[] args) throws FileNotFoundException {
        NewDayJournal newDayJournal = new NewDayJournal();
        List<DayJournal> journal = newDayJournal.createJsonList(new DayJournal(),JSON_STRING);
        List<String> events = newDayJournal.events(journal);
        Set<String> soloEvents = newDayJournal.eventsSet(journal);
        newDayJournal.returnCorrelation(soloEvents,events);
        events = newDayJournal.returnPeanuts(events);
        System.out.println("Для нового события: арахис-зубы :" + newDayJournal.phi(newDayJournal.tableFor("арахис-зубы",events)));
    }
}
