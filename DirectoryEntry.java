package tree;
import java.util.ArrayList;
import java.util.List;

public class DirectoryEntry implements Entry {
    private final String name;
    private final List<Entry> entries;

    public DirectoryEntry(String name) {
        this.name = name;
        this.entries = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addEntry(Entry entry) {
        entries.add(entry);
    }

    @Override
    public void printList(String prefix) {
        System.out.println(prefix + getName());
        for (Entry entry : entries) {
            entry.printList(prefix + "  "); 
        }
    }
}