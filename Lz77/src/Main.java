import java.lang.String;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        String text = "ABAABABAABBBBBBBBBBBBA";
//        Compressor compressor = new Compressor();
//        System.out.println(compressor.compress(text));
        File readFile = new File("/run/media/phantom/New Volume/University/Data Compression/Assignments/Assignment 1/Lz77_Compression-DeCompression/Lz77/src/compressed.txt");

        ArrayList<Tag> tags = new ArrayList<Tag>();
        tags.addAll(readFile.read());
//        for (Tag printtag : tags){
//            System.out.println(printtag.getPosition()+"-"+printtag.getLength()+"-"+ printtag.getSymbol());
//        }
    }
}