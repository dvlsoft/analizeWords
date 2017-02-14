import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Parser {

    public static void writeToFile(List<String> formattedString, String outputFile){
        try(PrintWriter writer = new PrintWriter(outputFile, "UTF-8")) {
            for(String item : formattedString){
                writer.println(item);
            }
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static List<String> readFile(String filename) throws IOException {
        Map<Integer, List<String>> wordsMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                putWordToMap(wordsMap, currentLine);
            }
        } catch (FileNotFoundException ex) {
            throw new FileNotFoundException("File " + filename + " not found");
        } catch (IOException ex) {
            throw new IOException("File " + filename + " has error during reading");
        }
        return collectStrings(wordsMap);
    }


    public static List<String> collectStrings (Map<Integer,List<String>> map){
        List<String> strings = new ArrayList<>();
        strings.addAll(map.entrySet().stream().map(Parser::getFormattedStringFromEntry).collect(Collectors.toList()));
        return strings;
    }

    // Create formatted string
    private static String getFormattedStringFromEntry(Map.Entry<Integer, List<String>> entry) {
        Word wordSet = new Word();
        wordSet.wordLength = entry.getKey();
        List<String> wordsList = entry.getValue();
        for (String value : wordsList) {
            Set<Character> charsSet = new HashSet<>();
            int vowelsCount = 0;
            for (char c : value.toCharArray()) {
                if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                    charsSet.add(c);
                    vowelsCount++;
                }
            }
            wordSet.charSet = charsSet;
            wordSet.totalVowel += vowelsCount;
        }
        wordSet.count=wordsList.size();
        return wordSet.toString();
    }

    // Help map to collect words
    private static void putWordToMap(Map<Integer, List<String>> wordsMap, String line) {
        String[] words = line.split(" ");
        trimDot(words);
        for (String word : words) {
            if (wordsMap.containsKey(word.length())) {
                wordsMap.get(word.length()).add(word.toLowerCase());
            } else {
                wordsMap.put(word.length(), new ArrayList<String>() {{
                            add(word.toLowerCase());
                        }}
                );
            }
        }
    }

    //Remove last dot
    private static void trimDot (String[] words){
        for (int i=0; i<words.length ; i++) {
            if(words[i].endsWith("."))
                words[i] = words[i].substring(0, words[i].length() - 1);
        }
    }
}
