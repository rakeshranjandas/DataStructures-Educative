package sliding_window;

import java.util.*;

public class RepeatedDnaSequences {

    public static class Hasher {
        private LinkedList<Character> list = new LinkedList<>();
        private Long hash = 0L;
        private Map<Character, Integer> charToInt = Map.of(
                'A', 1,
                'C', 2,
                'T', 3,
                'G', 4);

        public void add(char ch) {
            list.add(ch);
            hash = hash * 10 + (charToInt.get(ch));
        }

        public void remove() {
            list.removeFirst();
            hash = hash % 1000000000L;
        }

        public Long getHash() {
            return hash;
        }

        public String getString() {

            Iterator<Character> itr = list.iterator();
            StringBuilder str = new StringBuilder();

            while (itr.hasNext()) {
                str.append(itr.next());
            }

            return str.toString();
        }
    }

    public static List<String> findRepeatedDnaSequences(String s) {
        int slen = s.length();
        if (slen <= 10) {
            return new ArrayList<String>();
        }

        Hasher hasher = new Hasher();
        Set<Long> set = new HashSet<>();
        Map<Long, String> map = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            hasher.add(s.charAt(i));
        }

        set.add(hasher.getHash());

        for (int i = 1; i + 10 <= slen; i++) {
            hasher.remove();
            hasher.add(s.charAt(i + 10 - 1));

            if (set.contains(hasher.getHash())) {
                map.put(hasher.getHash(), hasher.getString());
            }

            set.add(hasher.getHash());
        }

        List<String> output = new ArrayList<>();
        for (String val : map.values()) {
            output.add(val);
        }

        return output;

    }

    // Driver code
    public static void main(String[] args) {
        List<String> testCases = Arrays.asList(
                "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",
                "AAAAAAAAAAAAA",
                "ACGTACGTACGTACGTACGTACGTACGTACGT",
                "GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG",
                "GTACGTACGTACGCCCCCCCCGGGGG");

        for (int i = 0; i < testCases.size(); i++) {
            System.out.println((i + 1) + ".\tInput: \"" + testCases.get(i) + "\"");

            List<String> result = findRepeatedDnaSequences(testCases.get(i));
            System.out.println("\n\tOutput: " + result);
            System.out.println("-" + new String(new char[100]).replace('\0', '-') + "\n");
        }
    }
}
