package tree;
public class FileEntry implements Entry {
    private final String name;

    public FileEntry(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addEntry(Entry entry) {
        throw new UnsupportedOperationException("Cannot add entries to files");
    }

    @Override
    public void printList(String prefix) {
        System.out.println(prefix + getName());
    }
}