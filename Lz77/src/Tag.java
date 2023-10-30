public class Tag {
    private int position;
    private int length;
    private char symbol;

    public Tag(int pos,int length,char symbol){
        this.position = pos;
        this.length = length;
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public int getPosition() {
        return position;
    }

    public int getLength() {
        return length;
    }
}