public class Tag {
    int pos;
    int len;
    char n_char;

    public Tag(int pos, int len, char n_char) {
        this.pos = pos;
        this.len = len;
        this.n_char = n_char;
    }
    public Tag(){}

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public char getN_char() {
        return n_char;
    }

    public void setN_char(char n_char) {
        this.n_char = n_char;
    }
}
