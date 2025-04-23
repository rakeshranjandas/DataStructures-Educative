package sliding_window;

public class MinimumWindowSubsequence {

    public static String minWindow(String s, String t) {

        // Greedily find the block that contains the subsequence.
        // When you find the subsequnce, greedily go in reverse to shorten that.

        int ti = 0;
        int resSize = Integer.MAX_VALUE;
        int resStart = 0;
        int resEnd = 0;

        for (int si = 0; si < s.length(); si++) {
            if (t.charAt(ti) == s.charAt(si)) {
                ti++;

                if (ti == t.length()) {
                    // Found subsequence
                    // Try shortening it

                    int rti = t.length() - 1;

                    for (int rsi = si;; rsi--) {
                        if (t.charAt(rti) == s.charAt(rsi)) {
                            rti--;

                            if (rti == -1) {
                                int strSize = si - rsi + 1;

                                // Update res
                                if (strSize < resSize) {
                                    resSize = strSize;
                                    resStart = rsi;
                                    resEnd = si + 1;
                                }

                                // Next forward block check to start from si+1
                                si = rsi;

                                // Reset ti
                                ti = 0;

                                break;
                            }
                        }
                    }
                }
            }
        }

        return s.substring(resStart, resEnd);

    }

    public static void main(String[] args) {
        String s1 = "AGhHRCDrNRgJfDinGmSXCdHqfLzzofCYKgdpfQkbYWEmDdTFQWIHENAiDdEijOUEKMuXxoxlvSvpfEuCBXGEePkmaVqaYCRZojYBWGIQOdtdwwqcjCLYIGrbzehLyfAQMrAEjmCyqVvrxsDwYYoUIpPuMBUtsxYAaEOrsYHxqTPIpmgdluiiJIGYWjwFyqtTwBuWrihzUvImXrTdbmzxLikJcVUiNqJDkqGJCWTbZofHrhwwtxnQXHYDMpClmQJLxKdEdleshkZdqMQYKKqsnYMxOECgVlAkHPHBYTqPNGzBtjDYpuryZmePgTdmuQPzuiDZaURAOXuYtUeIrdZFfpBkveGnRJxcmMXvOaZOAdMXHLVjeKATRoykLIxDslNlphqKxPHgJpnzDqEVonLoTfmDwHhWpgIUbreLBYRgKVCMtTPHIYpyvSoHKbIjCbNNybDZNNJGJOVKUcUhwszUUMNSuyGVgtDDzAEDWqNvNHprSrFQBFRnqSUkMnbaVowLwMqtnqqJYJJleZaosPURnNGPTiPxPTSnecEokOlfrbPxjcISyqQXAdWXyCMUFxDrrJXWsjefOIbyjMguHRAEfXkVQGLJStpGSKwqFXXRHDtZVOPInBJXrtwJOItWsZhpIqIcShmqpiROPaqUzxLIxAISyNHPhXzxiyipotWylrcsDlygFHkceURUPWkkPlnsBfwqHgdRLZdLeGqSNXZBgzqlotzfntNgpRNDdKYSNrwFiVCebKgMJEpmdusoGuYlPKbzLoJdldXEqRKOJwKYfSyYtaywUYndExXdNZFjRcvTzcjoZuEXNxqihqlVBIAIGKcVCmrsEcWWkRGmVrzSlBQQkfHGPqddZDcPVIKjLeCQmifLSfLWHKVPZdmeooJXYnzrkxRbLdtvxWJDPMWzITauwgMrcWYftYHHHMnJYCNOYHiKCFvtccUByuvMFhzroZoAJURadipgXIaaWSxFLyTHbYgyZmvIZeIAzFxn";
        String s2 = "BtlnSJNo";

        System.out.println(minWindow(s1, s2));
    }
}
