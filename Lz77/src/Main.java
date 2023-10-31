import java.lang.String;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        //Compression
        Compressor compressor = new Compressor();
        File to_compress = new File("/run/media/phantom/New Volume/University/Data Compression/Assignments/Assignment 1/Lz77_Compression-DeCompression/Lz77/src/test.txt");
        compressor.compress();

        //Decompression
        File to_decompress = new File("/run/media/phantom/New Volume/University/Data Compression/Assignments/Assignment 1/Lz77_Compression-DeCompression/Lz77/src/output.bin");
        ArrayList<Tag> tags = new ArrayList<Tag>();
        tags.addAll(to_decompress.read_to_decompress());
        Decompressor decompressor = new Decompressor(tags);
        decompressor.decompress();
        System.out.println(decompressor.decompressed);

//        for (Tag printtag : tags){
//            System.out.println(printtag.position+"-"+printtag.length+"-"+ printtag.symbol);
//        }

    }

}