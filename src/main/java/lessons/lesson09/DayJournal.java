package lessons.lesson09;

import java.util.List;

public class DayJournal {

    public DayJournal() {

    }
    private List<String> events;
    private boolean squirrel;

    @Override
    public String toString() {
        return "DayJournal{" +
                "events=" + events +
                ", squirrel=" + squirrel +
                '}';
    }
}
