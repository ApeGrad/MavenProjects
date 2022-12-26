package lessons.lesson18.new_were_squirrel;

import java.util.List;

public class DayJournal {

    public DayJournal() {

    }
    private List<String> events;
    private boolean squirrel;

    @Override
    public String toString() {
        return events + "" +
                 squirrel;
    }

    public List<String> getEvents() {
        return events;
    }

    public boolean isSquirrel() {
        return squirrel;
    }
}
