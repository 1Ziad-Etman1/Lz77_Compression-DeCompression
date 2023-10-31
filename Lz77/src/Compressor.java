import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.*;
import java.io.IOException;

public class Compressor {
    private String search_buffer = "";
    private String look_ahead_buffer = "";
    private int MAX_SEARCH_WINDOW_SIZE = 12;
    private List<Tag> taglist= new ArrayList<Tag>();

    public String pop_back_str (String str){
        String stringWithoutFirstCharacter = str.substring(1);
        return stringWithoutFirstCharacter;
    }

    private void StringToBinaryFile(String bitString, String filePath) {
        try (FileOutputStream fos = new FileOutputStream(filePath);
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {

            int length = bitString.length();
            if (length % 8 != 0) {
                throw new IllegalArgumentException("Input bit string length must be a multiple of 8.");
            }

            for (int i = 0; i < length; i += 8) {
                String byteString = bitString.substring(i, i + 8);
                byte b = (byte) Integer.parseInt(byteString, 2);
                bos.write(b);
            }

            System.out.println("Binary file has been created.");

        } catch (IOException e) {
            e.printStackTrace(); // Consider providing user-friendly error handling.
        }
    }


    public String compress(){

        File file = new File("/run/media/phantom/New Volume/University/Data Compression/Assignments/Assignment 1/Lz77_Compression-DeCompression/Lz77/src/test.txt");
        ArrayList<String> arr = new ArrayList<String>();
        String text = "";
        arr.addAll(file.read_to_compress());
        for (int i = 0; i < arr.size(); i++) {
            text+=arr.get(i);
        }

        for(int i=0;i<text.length();i++){
            look_ahead_buffer = look_ahead_buffer += text.charAt(i);
            if (!search_buffer.contains(look_ahead_buffer)){
                Tag tag = new Tag(0,0,text.charAt(i));
                search_buffer = search_buffer += text.charAt(i);
                if (search_buffer.length() >MAX_SEARCH_WINDOW_SIZE) {
                    search_buffer = search_buffer.substring(1);
                }
                taglist.add(tag);
            }
            else if (search_buffer.contains(look_ahead_buffer)){
                int j=i+1;
                while( j<text.length()){
                    look_ahead_buffer+=text.charAt(j);
                    if (search_buffer.contains(look_ahead_buffer)){
                        j++;
                        continue;}
                    else {break;}
                }
                String look_ahead_buffer_sub_string = look_ahead_buffer.substring(0,look_ahead_buffer.length()-1);
                int search_buffer_index =0 ;
                if(i>MAX_SEARCH_WINDOW_SIZE){
                    search_buffer_index = search_buffer.lastIndexOf(look_ahead_buffer_sub_string)+(i-MAX_SEARCH_WINDOW_SIZE);
                }
                else {
                    search_buffer_index = search_buffer.lastIndexOf(look_ahead_buffer_sub_string);
                }
                int tagposition = i - search_buffer_index;
                int taglength = look_ahead_buffer.length()-1;
                char symbol = look_ahead_buffer.charAt(look_ahead_buffer.length()-1);
                Tag tag = new Tag(tagposition,taglength,symbol);
                taglist.add(tag);
                search_buffer += look_ahead_buffer;
                if(search_buffer.length()>MAX_SEARCH_WINDOW_SIZE){
                    while( search_buffer.length() >MAX_SEARCH_WINDOW_SIZE){
                        search_buffer = pop_back_str(search_buffer);
                    }
                }
                i=j;
            }
            look_ahead_buffer ="";

        }



        String final_bit_stirng = "";
        for (Tag tag : taglist){
            final_bit_stirng += tag.convert_to_bytes();
//            System.out.println(tag.position+"-"+tag.length+"-"+ tag.symbol);
//            final_bit_stirng+= Integer.toBinaryString('\n');
//            System.out.println(tag.convert_to_bytes());
        }

        StringToBinaryFile(final_bit_stirng,"/run/media/phantom/New Volume/University/Data Compression/Assignments/Assignment 1/Lz77_Compression-DeCompression/Lz77/src/output.bin");
        System.out.println("Compressed: " + final_bit_stirng);
        return final_bit_stirng;
    }
}