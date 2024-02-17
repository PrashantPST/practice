package dsa.design;

import java.util.ArrayList;
import java.util.List;

public class Codec {

    private static final String DELIMITER = "#";

    // Encodes a list of strings to a single string.
    public String encode(List<String> strings) {
        StringBuilder sb = new StringBuilder();
        for (String str : strings) {
            sb.append(str.length()).append(DELIMITER).append(str);
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> ans = new ArrayList<>();
        int start = 0;
        while (start < s.length()) {
            int delimiterIndex = s.indexOf(DELIMITER, start);
            if (delimiterIndex == -1) {
                throw new IllegalArgumentException("invalid input");
            }
            int len = Integer.parseInt(s.substring(start, delimiterIndex));
            ans.add(s.substring(delimiterIndex + 1, delimiterIndex + len + 1));
            start = delimiterIndex + len + 1;
        }
        return ans;
    }
}