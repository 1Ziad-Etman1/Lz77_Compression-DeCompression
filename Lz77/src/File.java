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
        char ascii = (char)Integer.parseInt(char_bytes, 2); // Convert binary to int
        return ascii;
    }

    public String num_to_byte(int num) {
        return Integer.toBinaryString(num);
    }

    public String char_to_byte(char character) {
        int asci = (int) character; // Get the ASCII code
        return String.format("%8s", Integer.toBinaryString(asci)).replace(' ', '0');
    }

    public ArrayList<String> readBinaryFile() {
        ArrayList<String> binaryStrings = new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream(path)) {
            int data;
            StringBuilder currentString = new StringBuilder();
            int bitCount = 0;

            while ((data = fileInputStream.read()) != -1) {
                // Append the binary representation of the byte to the current string
                currentString.append(String.format("%8s", Integer.toBinaryString(data & 0xFF)).replace(' ', '0'));
                bitCount += 8;

                // If the current string contains 16 bits, add it to the list
                if (bitCount >= 16) {
                    binaryStrings.add(currentString.substring(0, 16));
                    currentString.delete(0, 16);
                    bitCount -= 16;
                }
            }

            // If there are remaining bits in the current string, add them to the list
            if (bitCount > 0) {
                binaryStrings.add(currentString.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return binaryStrings;
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

    public ArrayList<String> read_to_compress(){
        ArrayList<String> text = new ArrayList<String>();
        String s = "";
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(path)
            );
            // store the content in string
            while ((s = br.readLine()) != null){
                text.addLast(s);
            }
            br.close();
        }
        catch (Exception ex){

        }
        return text;
    }

    public ArrayList<Tag> read_to_decompress(){

        // the array for tags to be returned
        ArrayList<Tag> tags = new ArrayList<Tag>();

        // the array for tags as bytes to be converted
        ArrayList<String> arr = new ArrayList<String>();
        arr.addAll(readBinaryFile());
//        System.out.println("the Strings will be decompressed: ");
//        for (int i = 0; i < arr.size(); i++) {
//            System.out.println(arr.get(i));
//        }
        // add the data as tags to the array


        for (int j = 0; j < arr.size(); j++) {
            tags.addLast(convert_bytes_to_tag(arr.get(j)));
//            System.out.println((j+1) + "from " + arr.size());
            System.out.println(arr.get(j));
            System.out.println("<"+tags.get(j).position+"-"+tags.get(j).length+"-"+ tags.get(j).symbol+">");
        }


//        for (Tag printtag : tags){
//            System.out.println(printtag.position+"-"+printtag.length+"-"+ printtag.symbol);
//        }


        return tags;
    }

    public void write(String _path, String content) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(_path))) {
            bw.write(content);
            bw.close();
        } catch (IOException ex) {
            ex.printStackTrace(); // Print the exception stack trace for debugging
        }
    }



}