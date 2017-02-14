import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class MainApp {
    public static final String INPUT_DEFAULT = "src/input.txt";
    public static final String OUTPUT_DEFAULT = "output.txt";


    public static void main(String[] args) {
        try {
            Parser.writeToFile(Parser.readFile(INPUT_DEFAULT),OUTPUT_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
