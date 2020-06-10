package las3007.assignment.utilis;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    private static final String FILE_PATH = "config/test.properties";

    public static String getValue(String key) throws IOException {
        File file = new File(FILE_PATH);

        FileInputStream fis = new FileInputStream(file);
        Properties properties = new Properties();
        properties.load(fis);

        return properties.getProperty(key);
    }
}
