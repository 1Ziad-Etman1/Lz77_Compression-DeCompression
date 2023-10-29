import java.util.ArrayList;
import java.lang.String;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static String pop_back_str (String str){
        String stringWithoutFirstCharacter = str.substring(1);
        return stringWithoutFirstCharacter;
    }
    public static void main(String[] args) {
        char[] text = "ABAABABAABBBBBBBBBBBBA".toCharArray();
        int s_window = 15;
        ArrayList<Tag> tags = new ArrayList<Tag>() ;
        int length = 1;
        String search_window_str = "";
        String lookahead_str = "";
        String test = "";
        System.out.println(text.length);
        for (int i = 0; i < text.length ; i+=length) {
            search_window_str = search_window_str + text[i];
            System.out.println(search_window_str);
//            for (int j = i-1; j > i-s_window ; j--) {
//
//                for (int k = j; k < i-j ; k++) {
//
//                }
//            }
            if (i>=s_window){
                int search_window_str_len = search_window_str.length();
                for (int j = 0; j < search_window_str_len - s_window ; j++) {
                    search_window_str = pop_back_str(search_window_str);
                }
            }
        }

    }
}