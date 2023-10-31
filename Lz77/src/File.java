import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Scanner;

public class File {

    String path;

    public File(String _path) {
        Scanner in = new Scanner(System.in);

        // Validate the path
        while (true) {
            if (isValidFilePath(_path)) {
                this.path = _path;
                break;
            } else {
                System.out.print("Invalid path\nRe enter it: ");
                _path = in.next();
            }
        }
    }


    public boolean isValidFilePath(String path) {
        try {
            Path normalizedPath = Paths.get(path).normalize();
            return normalizedPath.isAbsolute() && Files.isRegularFile(normalizedPath);
        } catch (InvalidPathException | SecurityException e) {
            return false;
        }
    }



    public int byte_to_num (String num_bytes){
        int num = Integer.parseInt(num_bytes, 2); // Convert binary to int
        return num;
    }

    public char byte_to_char (String char_bytes){
        char asci = (char)Integer.parseInt(char_bytes, 2); // Convert binary to int
        return asci;
    }

    public String num_to_byte(int num) {
        return Integer.toBinaryString(num);
    }

    public String char_to_byte(char character) {
        int asci = (int) character; // Get the ASCII code
        return String.format("%8s", Integer.toBinaryString(asci)).replace(' ', '0');
    }

    public Tag convert_bytes_to_tag (String bytes){
        // get the bits of the char
        String ch_bytes = bytes.substring(8);
        // get the bits of the position
        String pos_bytes = bytes.substring(0, 4);
        // get the bits of the length
        String len_bytes = bytes.substring(4, 8);

        // convert them into numbers
        int pos = byte_to_num(pos_bytes);
        int len = byte_to_num(len_bytes);
        // convert to char
        char ch = byte_to_char(ch_bytes);

        //add them to a tag and return it
        Tag tag = new Tag(pos,len,ch);
        return tag;
    }
    public ArrayList<Tag> read(){

        // the array for tags to be returned
        ArrayList<Tag> tags = new ArrayList<Tag>();

        // the array for tags as bytes to be converted
        ArrayList<String> arr = new ArrayList<String>();

        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(path)
            );

            String s = "";
            int i = 0;

            // store each line (tag in bytes) in the array
            while ((s = br.readLine()) != null){
                arr.add(i,s);
                i++;
            }
            br.close();

        }
        catch (Exception ex){

        }

        // add the data as tags to the array
        for (int j = 0; j < arr.size(); j++) {
            tags.addLast(convert_bytes_to_tag(arr.get(j)));
        }

        //print the tags content
//        for (Tag printtag : tags){
//            System.out.println(printtag.getPosition()+"-"+printtag.getLength()+"-"+ printtag.getSymbol());
//        }


        return tags;
    }

    public void write(String _path, String content){
        try{
            BufferedWriter bw = new BufferedWriter(
                    new FileWriter(_path)
            );
            bw.write(content);
        } catch (Exception ex){

        }
    }



}