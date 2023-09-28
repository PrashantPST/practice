package dsa;

import java.util.ArrayList;
import java.util.List;

public class Codec {

    private static final String DELIMITER = "#";

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str.length()).append(DELIMITER).append(str);
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> ans = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            int j = s.indexOf(DELIMITER, i);
            int len = Integer.parseInt(s.substring(i, j));
            ans.add(s.substring(j + 1, j + len + 1));
            i = j + len + 1;
        }
        return ans;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs));