public class Tag {
    private int position;
    private int length;
    private char symbol;

    public Tag(int pos,int length,char symbol){
        this.position = pos;
        this.length = length;
        this.symbol = symbol;
    }
    private String calculate_zeros(int size){
        String temp = "";
        for (int i=4;i>size;i--){
            temp+="0";

        }
        return temp;
    }
    public String convert_to_bytes(){

        String position_binarystring = Integer.toBinaryString(this.position);
        if (position_binarystring.length()<4){
            position_binarystring = calculate_zeros(position_binarystring.length()) + position_binarystring;
        }
        String length_binarystring = Integer.toBinaryString(this.length);
        if (length_binarystring.length()<4){
            length_binarystring = calculate_zeros(length_binarystring.length()) + length_binarystring;
        }
        String symbol_binarystring = Integer.toBinaryString(this.symbol);
        symbol_binarystring = String.format("%8s", symbol_binarystring).replace(' ', '0');
        String Byte_string = position_binarystring+length_binarystring+ symbol_binarystring;
        return Byte_string;
    }
}