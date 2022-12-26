package lessons.lesson09;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        String jsonString = "C:/Users/LEGION/Downloads/journal_ru.json";
        Gson gson = new Gson();
        FileReader reader = new FileReader(jsonString);
        List<DayJournal> dayJournal = gson.fromJson(reader, new TypeToken<List<DayJournal>>() {
        }.getType());
        Set<String> eventsSet = new HashSet<>();
        String key;
        /**
         метод для создания Листа
         */
        List<String> eventsList = new ArrayList<>();
        for (DayJournal journal : dayJournal) {
            eventsList.add(journal.toString());
        }
        /**
         метод для создания Сета
         */
        for (DayJournal journal : dayJournal) {
            String key1 = journal.getEvents().toString();
            key = key1.substring(1, key1.length() - 1);
            String[] values = key.split(",");
            for (int i = 0; i < values.length; i++) {
                values[i] = values[i].trim();
            }
            eventsSet.addAll(Arrays.asList(values));
        }
        /**
         тест для
         корреляции
         */
        for (String soloEvent : eventsSet) {
            double correlation = phi(tableFor(soloEvent, eventsList));
            if (correlation > 0.1 || correlation < -0.1) {
                System.out.println(soloEvent + "    : " + correlation);
            }
        }
        eventsList = returnPeanut(eventsList);
        eventsList.forEach(System.out::println);
        System.out.println("Для нового события: арахис-зубы : " + phi(tableFor("арахис-зубы", eventsList)));
    }
    /** тест для нового листа
    */
    public static List<String> returnPeanut(List<String> events) {
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
    /**
    тест для
    формулы Фи
     */
    public static double phi(int[] table) {
        return (table[3] * table[0] - table[2] * table[1]) /
                Math.sqrt((table[2] + table[3]) *
                        (table[0] + table[1]) *
                        (table[1] + table[3]) *
                        (table[0] + table[2]));
    }
    /**
     тест для
     стола
     */
    public static int[] tableFor(String event, List<String> journal) {
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
}
