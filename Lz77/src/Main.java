import java.io.FileInputStream;
import java.io.IOException;
import java.lang.String;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        String text = "ABAABABAABBBBBBBBBBBBA";
        Compressor compressor = new Compressor();
        System.out.println(compressor.compress(text));
//        File readFile = new File("/z77/Lz77_Compression-DeCompression-main/Lz77/src/test.txt");
        String filePath = "output.bin"; // Specify the path to your binary file

    }
    //        ArrayList<Tag> tags = new ArrayList<Tag>();
//        tags.addAll(readFile.read());
//        for (Tag printtag : tags){
//            System.out.println(printtag.getPosition()+"-"+printtag.getLength()+"-"+ printtag.getSymbol());
//        }
}