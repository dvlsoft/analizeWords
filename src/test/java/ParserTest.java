import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ParserTest {
    private List<String> testList = new ArrayList<String>() {{
        addAll(Arrays.asList("Platon","bamboo"));
    }};
    private String testString = "made";
    private String testString2 = "boats";


    @Test
    public void testParser(){
        HashMap<Integer,List<String>> testMap = new HashMap<>();
        testMap.put(6,testList);
        testMap.put(4,new ArrayList<String>(){{add (testString);}});
        testMap.put(5,new ArrayList<String>(){{add (testString2);}});

        List<String> resultList = new ArrayList<>();
        resultList.add("({a, e}, 4) -> 2");
        resultList.add("({a, o}, 5) -> 2");
        resultList.add("({a, o}, 6) -> 2.5");

        Assert.assertEquals(resultList,Parser.collectStrings(testMap));
    }
}
