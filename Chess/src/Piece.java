public class Piece {

    private int piece, square;
    public static final int none = 0, pawn = 1, bishop = 2, knight = 3, rook = 4, queen = 5, king = 6;
    public static final int black = 16, white = 8;


    public Piece(){
    }

    public Piece(int piece, int square){
        this.piece = piece;
        this.square = square;
    }

    public int getPiece() {
        return piece;
    }

    public int getSquare() {
        return square;
    }
}
