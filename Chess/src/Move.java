public class Move {

    public final int promotion = 1, shortCastle = 2, enPassant = 3, longCastle = 4;
    private int piece, start, end, taken, squareTaken, type;
    private boolean special, lC, sC;

    public Move(int piece, int start, int end){
        this.piece = piece;
        this.start = start;
        this.end = end;
        special = false;
        taken = 0;
        squareTaken = 0;
        lC = false;
        sC = false;
    }

    public Move(int piece, int start, int end, boolean lC, boolean sC){
        this.piece = piece;
        this.start = start;
        this.end = end;
        special = false;
        taken = 0;
        squareTaken = 0;
        this.lC = lC;
        this.sC =sC;
    }

    public Move(int piece, int start, int end, int taken, int squareTaken){
        this.piece = piece;
        this.start = start;
        this.end = end;
        special = false;
        this.taken = taken;
        this.squareTaken = squareTaken;
        lC = false;
        sC = false;
    }

    public Move(int piece, int start, int end, int taken, int squareTaken, boolean lC, boolean sC){
        this.piece = piece;
        this.start = start;
        this.end = end;
        special = false;
        this.taken = taken;
        this.squareTaken = squareTaken;
        this.lC = lC;
        this.sC =sC;
    }

    public Move(int piece, int start, int end, boolean special, int type, int taken, int squareTaken){
        this.piece = piece;
        this.start = start;
        this.end = end;
        this.special = special;
        this.type = type;
        this.taken = taken;
        this.squareTaken = squareTaken;
        lC = false;
        sC = false;
    }

    public int getPiece(){return piece;}
    public int getStart(){return start;}
    public int getEnd(){return end;}
    public boolean getSpecial(){return special;}
    public int getTaken() {
        return taken;
    }
    public int getSquareTaken() {
        return squareTaken;
    }
    public int getType() {
        return type;
    }
    public boolean getlC() {
        return lC;
    }
    public boolean getsC() {
        return sC;
    }

    public boolean equalsMove(Move move){
        return piece == move.getPiece() && start == move.getStart() && end == move.getEnd();
    }

    public String toString(){
        return piece + " " + start + " " + end + " " +special + " " + taken + " " + squareTaken + " " + type + " " + lC + " " + sC;
    }
}
