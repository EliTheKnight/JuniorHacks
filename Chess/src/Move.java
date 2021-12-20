public class Move {

    private int piece, start, end;

    public Move(int piece, int start, int end){
        this.piece = piece;
        this.start = start;
        this.end = end;
    }

    public int getPiece(){return piece;}
    public int getStart(){return start;}
    public int getEnd(){return end;}

}
