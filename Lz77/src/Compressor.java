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

    private void StringToBinaryFile(String byte_string) {
        String bitString = byte_string;
        String filePath = "output.bin"; // Define the path to the output binary file

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            // Convert the bit string to bytes and write to the binary file
            byte[] bytes = new byte[bitString.length() / 8];
            for (int i = 0; i < bitString.length(); i += 8) {
                String byteString = bitString.substring(i, i + 8);
                byte b = (byte) Integer.parseInt(byteString, 2);
                bytes[i / 8] = b;
            }
            fos.write(bytes);

            System.out.println("Binary file has been created.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String compress(String text){
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
//            final_bit_stirng+= Integer.toBinaryString('\n');
        }
        StringToBinaryFile(final_bit_stirng);
        return final_bit_stirng;
    }
}