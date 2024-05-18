package tree;
import java.io.File;

public class DirectoryTree {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Donner le chemin du fichier");
            return;
        }

        String directoryPath = args[0];
        DirectoryEntry rootDir = new DirectoryEntry(directoryPath); 

        buildDirectoryTree(rootDir, new File(directoryPath));

        rootDir.printList(""); 
    }

    private static void buildDirectoryTree(DirectoryEntry parentDir, File directory) {
        for (File child : directory.listFiles()) {
            if (child.isDirectory()) {
                DirectoryEntry childDir = new DirectoryEntry(child.getName());
                parentDir.addEntry(childDir);
                buildDirectoryTree(childDir, child);
            } else {
                parentDir.addEntry(new FileEntry(child.getName()));
            }
        }
    }
}
