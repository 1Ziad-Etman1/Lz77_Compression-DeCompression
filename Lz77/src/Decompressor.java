import java.util.ArrayList;

public class Decompressor {
    ArrayList<Tag> tags;
    String decompressed;

    public Decompressor(ArrayList<Tag> _tags) {
        this.decompressed = "";
        this.tags = new ArrayList<Tag>();
        this.tags.addAll(_tags);
    }

    public String decompress(){
        String added_str = "";
        for (int i = 0; i < tags.size(); i++) {
            int pos = tags.get(i).position;
            int len = tags.get(i).length;
            char symbol = tags.get(i).symbol;
            if (len == 0 && pos == 0){
                added_str += symbol;
                decompressed += added_str;
                added_str = "";
                continue;
            } else {
                int target_index = decompressed.length() - pos;
                added_str = decompressed.substring(target_index, target_index+len)+symbol;
                decompressed += added_str;
                added_str = "";
            }
        }
//        System.out.println(decompressed);

        File file = new File("/run/media/phantom/New Volume/University/Data Compression/Assignments/Assignment 1/Lz77_Compression-DeCompression/Lz77/src/compressed.txt");
        file.write("src/decompressed.txt",decompressed);
        System.out.println("Decompressed File Created!");
        return decompressed;
    }

}
