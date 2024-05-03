import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSV {
    private List<String> headers;
    private List<List<String>> records;

    public CSV() {
        this.headers = new ArrayList<>();
        this.records = new ArrayList<>();
    }

    public void read_csv(String path) {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            if ((line = br.readLine()) != null) {
                headers.addAll(Arrays.asList(line.split(",")));
            }
            while ((line = br.readLine()) != null) {
                records.add(Arrays.asList(line.split(",")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getHeaders() {
        return headers;
    }

    public List<List<String>> getRecords() {
        return records;
    }
}
