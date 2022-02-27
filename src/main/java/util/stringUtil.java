package util;

public class stringUtil {
    public static boolean isEmpty(String s1){
        return s1==null||"".equals(s1);
    }
    public static boolean isNotEmpty(String s1){
        return !isEmpty(s1);
    }
}
