import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class Journal {
    private ArrayList<String> entries = new ArrayList<>();
    void add(String text) { entries.add(text); }
    String getText() {
        StringBuilder sb = new StringBuilder();
        for (String entry : entries) {
            sb.append("- ").append(entry).append("\n");
        }
        return sb.toString();
    }
}
class JournalSaver {
    Journal journal;
    JournalSaver(Journal journal) {
        this.journal = journal;
    }
    void print() {
        System.out.println(journal.getText());
    }
    void saveToFile(String filename) {   // ← 이게 두 번째 책임!
        String j = journal.getText();
        try (FileWriter fw = new FileWriter(filename)) {
            fw.write(j);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}