package lessons.lesson18.new_were_squirrel;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Set;

public abstract class AbstractJournal {
    abstract List<String> events(List<DayJournal> objectList);
    abstract Set<String> eventsSet(List<DayJournal> objectList);

    abstract List<DayJournal> createJsonList(DayJournal journal,String jsonString) throws FileNotFoundException;

    abstract int [] tableFor(String event,List<String> journal);

    abstract double phi(int [] table);

    abstract String returnCorrelation(Set<String> soloEvents, List<String> events);
    abstract List<String> returnPeanuts(List<String>events);



}
