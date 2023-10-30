import java.lang.String;

public class Main {
    public static void main(String[] args) {

        String text = "ABAABABAABBBBBBBBBBBBA";
        Compressor compressor = new Compressor();
        System.out.println(compressor.compress(text));
    }
}