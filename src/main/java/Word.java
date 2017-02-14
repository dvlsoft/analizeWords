import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Set;

public class Word {
    public Set<Character> charSet;
    public int wordLength;
    public int totalVowel;
    public int count = 1;


    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("(");

        Iterator<Character> iterator = charSet.iterator();
        if (iterator.hasNext()){
            sb.append("{");
            for (; ; ) {
                sb.append(iterator.next());
                if (!iterator.hasNext()){
                    sb.append('}');
                    break;
                }
                sb.append(',').append(' ');
            }
        }

        DecimalFormat formatter = new DecimalFormat("#.##");
        sb.append(", ");
        sb.append(wordLength);
        sb.append(") -> ");
        sb.append(formatter.format(((double)totalVowel)/count));

        return sb.toString();
    }
}
