package sliding_window;

public class LongestRepeatingCharacterReplacement {

    private static int findLongestOfStreak(String s, int k, char ch) {

        int li = 0;
        int cost = 0;
        int maxStreak = 0;

        for (int ri = 0; ri < s.length(); ri++) {
            cost += s.charAt(ri) != ch ? 1 : 0;

            while (cost > k) {
                cost -= s.charAt(li) != ch ? 1 : 0;
                li++;
            }

            maxStreak = Math.max(ri - li + 1, maxStreak);
        }

        return maxStreak;
    }

    public static int longestRepeatingCharacterReplacement(String s, int k) {

        int res = 0;
        for (char ch = 'a'; ch <= 'z'; ch++) {
            res = Math.max(res, findLongestOfStreak(s, k, ch));
        }

        return res;
    }

    public static void main(String[] args) {
        String s = "aaacbbbaabab";
        int k = 2;

        System.err.println(longestRepeatingCharacterReplacement(s, k));
    }

}