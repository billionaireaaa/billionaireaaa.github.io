package ProblemSet;

/*
。*/
public class 最长回文字段 {
    public static void main(String[] args) {
        String s = "bacabdaca";
        System.out.println(longestPalindrome(s));
    }

    public static String longestPalindrome(String s) {
        char ch[] = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            ch[i] = s.charAt(i);
        }
        int max = 0;
        String s1 = "";
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = s.length() - 1; j > 0; j--) {
                if (ch[i] == ch[j]) {
                    if (j - i <= 2) {
                        if (j - i > max) {
                            max = j - i;
                            start = i;
                            end = j;
                        }
                    } else {
                        int ti = i;
                        int tj = j;
                        while (tj > ti && ch[ti] == ch[tj]) {
                            ti++;
                            tj--;
                        }
                        if (tj <= ti) {
                            if (j - i > max) {
                                max = j - i;
                                start = i;
                                end = j;
                            }
                        }
                    }
                }
            }
        }        for (int i = start; i <= end; i++) {
            s1 += ch[i];
        }
        return s1;
    }


}

