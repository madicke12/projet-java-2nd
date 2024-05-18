package tree;
public interface Entry {
    String getName();
    void addEntry(Entry entry);
    void printList(String prefix);
}