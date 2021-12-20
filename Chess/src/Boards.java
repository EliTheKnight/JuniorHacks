import java.awt.*;
import java.util.ArrayList;

public class Boards {

    private int[] squares;
    private ArrayList<Integer> takenPieces;
    private boolean whiteMove, shortCastle, longCastle;
    
    public Boards(){
        squares = new int[64];
        takenPieces = new ArrayList<>();
        shortCastle = false;
        longCastle = false;
        whiteMove = false;
    }

    public Boards(int[] board, boolean move){
        squares = board;
        takenPieces = new ArrayList<>();
        shortCastle = false;
        longCastle = false;
        whiteMove = move;
    }

    public Boards(int[] board, ArrayList<Integer> takenPieces, boolean move){
        squares = board;
        this.takenPieces = takenPieces;
        shortCastle = false;
        longCastle = false;
        whiteMove = move;
    }

    public void makeMove(int piece, int start, int end, boolean promote, String promotion){
        if (!promote){
            makeMove(piece, start, end);
            return;
        }
        if (promotion.equals("queen")){
            setSquare(start, 0);
            setSquare(end, (piece/8)*8 + 5);
            switchMove();
        }
        else if (promotion.equals("rook")){
            setSquare(start, 0);
            setSquare(end, (piece/8)*8 + 4);
            switchMove();
        }
        else if (promotion.equals("knight")){
            setSquare(start, 0);
            setSquare(end, (piece/8)*8 + 3);
            switchMove();
        }
        else if (promotion.equals("bishop")){
            setSquare(start, 0);
            setSquare(end, (piece/8)*8 + 2);
            switchMove();
        }

    }

    public void makeMove(int piece, int start, int end){
        if (shortCastle){
            int dist = Math.abs(start-end);
            if (piece % 8 == 6 && dist != 1 && dist != 8 && dist != 7 && dist != 9) {
                setSquare(start, 0);
                setSquare(end, piece);
                setSquare((end - start) / 2 + start, squares[(end - start) / 2 + end]);
                setSquare((end - start) / 2 + end, 0);
                shortCastle = false;
                switchMove();
                return;
            }
        }

        if(longCastle){
            int dist = Math.abs(start-end);
            if (piece % 8 == 6 && dist != 1 && dist != 8 && dist != 7 && dist != 9) {
                setSquare(start, 0);
                setSquare(end, piece);
                setSquare((end - start) / 2 + start, squares[(end - start) + end]);
                setSquare((end - start) + end, 0);
                longCastle = false;
                switchMove();
                return;
            }
        }

        squares[start] = 0;
        if (squares[end] != 0){
            takenPieces.add(squares[end]);
        }
        squares[end] = piece;

        shortCastle = false;
        longCastle = false;
        switchMove();
    }

    public ArrayList<Move> generateMoves(){
        if (whiteMove){return generateMovesWhite();}
        return generateMovesBlack();
    }

    public ArrayList<Move> generateMovesBlack(){
        ArrayList<Move> moves = new ArrayList();
        for (int i = 0; i<64; i++){
            if (squares[i] > 16){
                int piece = squares[i];
                for (int j = 0; j < 64; j++){
                    if (isLegalMove(piece, i, j)){
//                        System.out.println(piece + " " + i + " " + j);
                        Move move = new Move(piece, i, j);
                        moves.add(move);
                    }
                }
            }
        }
        return moves;
    }

    public ArrayList<Move> generateMovesWhite(){
        ArrayList<Move> moves = new ArrayList();
        for (int i = 0; i<64; i++){
            if (squares[i] < 16 && squares[i] != 0){
                int piece = squares[i];
                for (int j = 0; j < 64; j++){
                    if (isLegalMove(piece, i, j)){
                        Move move = new Move(piece, i, j);
                        moves.add(move);
                    }
                }
            }
        }
        return moves;
    }

    public boolean isLegalMove(int piece, int start, int end) {

        if (end == start){return false;}
        if (piece == 0) {return false;}
        if (end<0 || end > 63) {return false;}

        if (piece % 8 == 1) {
            return legalPawnMove(piece, start, end);
        }
        if (piece % 8 == 2) {
            return legalBishopMove(piece, start, end);
        }
        if (piece % 8 == 3) {
            return legalKnightMove(piece, start, end);
        }
        if (piece % 8 == 4) {
            return legalRookMove(piece, start, end);
        }
        if (piece % 8 == 5) {
            return legalQueenMove(piece, start, end);
        }
        if (piece % 8 == 6) {
            return legalKingMove(piece, start, end);
        }
        return false;
    }

    public boolean legalPawnMove(int piece, int start, int end) {
        if (piece > 16) {
            if (end == start + 2) {
                if (start % 8 == 1 && squares[start + 1] == 0 && squares[end] == 0) {
                    return true;
                }
            }
            if (end == start + 1) {
                if ( squares[end] == 0) {
                    return true;
                }
            }
            if (end == start - 7 || end == start + 9) {
                return squares[end] > 0 && squares[end] < 16;
            }
        } else {
            if (end == start - 2 && squares[start - 1] == 0 && squares[end] == 0) {
                if (start % 8 == 6) {
                    return true;
                }
            }
            if (end == start - 1) {
                if ( squares[end] == 0) {
                    return true;
                }
            }
            if (end == start + 7 || end == start - 9) {
                return squares[end] > 16;
            }
        }
        return false;
    }

    public boolean legalBishopMove(int piece, int start, int end) {

        if (piece > 16) {
            if ( squares[end] > 16) {
                return false;
            }
            int rControl = Math.abs((end%8) - (start%8));
            int fControl = Math.abs((end/8)-(start/8));
            if (end % 7 == start % 7) {
                int sevControl = Math.abs(end-start)/7;
                if (sevControl != rControl || sevControl != fControl){return false;}
                if (end > start) {

                    for (int i = start + 7; i < end; i += 7) {
                        if ( squares[i] != 0) {
                            return false;
                        }
                    }
                }
                if (end < start) {
                    for (int i = start - 7; i > end; i -= 7) {
                        if ( squares[i] != 0) {
                            return false;
                        }
                    }
                }
            } else if (end % 9 == start % 9) {
                int nineControl = Math.abs(end-start)/9;
                if (nineControl != rControl || nineControl != fControl){return false;}

                if (end > start) {
                    for (int i = start + 9; i < end; i += 9) {
                        if ( squares[i] != 0) {
                            return false;
                        }
                    }
                }
                if (end < start) {
                    for (int i = start - 9; i > end; i -= 9) {
                        if ( squares[i] != 0) {
                            return false;
                        }
                    }
                }
            } else {
                return false;
            }
        } else {
            if ( squares[end] < 16 && squares[end] > 0) {
                return false;
            }
            int rControl = Math.abs((end%8) - (start%8));
            int fControl = Math.abs((end/8)-(start/8));
            if (end % 7 == start % 7) {
                int sevControl = Math.abs(end-start)/7;
                if (sevControl != rControl || sevControl != fControl){return false;}

                if (end > start) {
                    for (int i = start + 7; i < end; i += 7) {
                        if ( squares[i] != 0) {
                            return false;
                        }
                    }
                }
                if (end < start) {
                    for (int i = start - 7; i > end; i -= 7) {
                        if ( squares[i] != 0) {
                            return false;
                        }
                    }
                }
            } else if (end % 9 == start % 9) {
                int nineControl = Math.abs(end-start)/9;
                if (nineControl != rControl || nineControl != fControl){return false;}

                if (end > start) {
                    for (int i = start + 9; i < end; i += 9) {
                        if ( squares[i] != 0) {
                            return false;
                        }
                    }
                }
                if (end < start) {
                    for (int i = start - 9; i > end; i -= 9) {
                        if ( squares[i] != 0) {
                            return false;
                        }
                    }
                }
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean legalKnightMove(int piece, int start, int end) {
        if (piece > 16) {
            if ( squares[end] > 16) {
                return false;
            }
        } else {
            if ( squares[end] < 16 && squares[end] > 0) {
                return false;
            }
        }
        if (end == start + 6 || end == start - 6 || end == start + 10 || end == start - 10 || end == start + 15 || end == start - 15 || end == start + 17 || end == start - 17){
            return Math.abs((end % 8) - (start % 8)) < 3 && Math.abs((end / 8) - (start / 8)) < 3;
        }
        return false;
    }

    public boolean legalRookMove(int piece, int start, int end) {
        int r = end / 8;
        int f = end % 8;
        int rank = start / 8;
        int file = start % 8;
        if (piece > 16) {
            if ( squares[end] > 16) {
                return false;
            }
        }
        else {
            if ( squares[end] < 16 && squares[end] > 0) {
                return false;
            }
        }
        if (r != rank && f != file) {
            return false;
        }
        if (rank == r) {
            if (file > f) {
                for (int i = f; i < file; i++) {
                    if ( squares[r * 8 + i] != 0 && r * 8 + i != end) {
                        return false;
                    }
                }
            }
            if (file < f) {
                for (int i = file; i < f; i++) {
                    if ( squares[r * 8 + i + 1] != 0 && r * 8 + i+1 != end) {
                        return false;
                    }
                }
            }
        }
        if (file == f) {
            if (rank > r) {
                for (int i = r; i < rank; i++) {
                    if ( squares[i * 8 + f] != 0 && i * 8 + f != end) {
                        return false;
                    }
                }
            }
            if (rank < r) {
                for (int i = rank; i < r; i++) {
                    if ( squares[(i + 1) * 8 + f] != 0 && (i+1) * 8 + f != end) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean legalQueenMove(int piece, int start, int end) {
        return legalRookMove(piece, start, end) || legalBishopMove(piece, start, end);
    }

    public boolean legalKingMove(int piece, int start, int end) {
        if (piece > 16) {
            if ( squares[end] > 16) {
                return false;
            }
            if (start == 32 && end == start+16 && squares[end+8] == 20 && squares[start + 8] == 0 && squares[start+16] == 0){shortCastle = true; return true;}
            if (start == 32 && end == start-16 && squares[end-16] == 20 && squares[start - 8] == 0 && squares[start-16] == 0 && squares[start-24] == 0){longCastle = true; return true;}
        }
        else {
            if ( squares[end] < 16 && squares[end] != 0) {
                return false;
            }
            if (start == 39 && end == start+16 && squares[end+8] == 12 && squares[start + 8] == 0 && squares[start+16] == 0){shortCastle = true; return true;}
            if (start == 39 && end == start-16 && squares[end-16] == 12 && squares[start - 8] == 0 && squares[start-16] == 0 && squares[start-24] == 0){longCastle = true; return true;}
        }
        if (end == start + 1 || end == start - 1 || end == start + 7 || end == start - 7 || end == start + 8 || end == start - 8 || end == start + 9 || end == start - 9){
            return Math.abs((end % 8) - (start % 8)) < 2 && Math.abs((end / 8) - (start / 8)) < 2;
        }
        return false;
    }

    public int[] getSquares() {

        return squares.clone();
//        return squares;
    }

    public ArrayList<Integer> getTakenPieces() {
        ArrayList<Integer> copy = new ArrayList<>();
        for (Integer a: takenPieces){
            copy.add(a);
        }
        return copy;
    }

    public void setSquare(int index, int value){
        squares[index] = value;
    }

    public void switchMove(){
        whiteMove = !whiteMove;
    }

    public boolean getMove() {
        return whiteMove;
    }

    public void drawBoard(Graphics2D g2, ArrayList<Image> sprites){
        Color brown = new Color(59, 32, 3, 255);
        int l = 96;
        //draw board
        for (int r = 0; r < 8; r++) {
            for (int f = 0; f < 8; f++) {
                if ((f + r) % 2 == 0) {
                    g2.setColor(Color.WHITE);
                } else {
                    g2.setColor(brown);
                }

                g2.fillRect((r * l), (f * l), l, l);

            }
        }
        //draw pieces
        for (int r = 0; r < 8; r++) {
            for (int f = 0; f < 8; f++) {
                if (squares[(r * 8 + f)] == 0) {
                    continue;
                }
                g2.drawImage(sprites.get( squares[r * 8 + f]), r * l, f * l, l, l, null);
            }
        }
    }

    public void setSquares(int[] squares) {
        this.squares = squares;
    }

    public void setTakenPieces(ArrayList<Integer> takenPieces1) {
        takenPieces.clear();
        takenPieces = takenPieces1;
    }

    public void setWhiteMove(boolean whiteMove) {
        this.whiteMove = whiteMove;
    }

}
