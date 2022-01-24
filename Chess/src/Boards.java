import java.awt.*;
import java.util.ArrayList;

public class Boards {

    private int[] squares;
    private boolean whiteMove, blackCanLC, blackCanSC, whiteCanLC, whiteCanSC;
    public static int pawn = 100, bishop = 300, knight = 300, rook = 500, queen = 900, king = Integer.MAX_VALUE;
    public ArrayList<Move> game;

    public Boards(int[] board, boolean move, boolean blackCanLC, boolean blackCanSC, boolean whiteCanLC, boolean whiteCanSC, ArrayList<Move> moves){
        this.whiteCanLC = whiteCanLC;
        this.whiteCanSC = whiteCanSC;
        this.blackCanLC = blackCanLC;
        this.blackCanSC = blackCanSC;
        squares = board;
        whiteMove = move;
        game = moves;
    }

    public void makeMove(Move move){
        int piece = move.getPiece();
        int start = move.getStart();
        int end = move.getEnd();
        switchMove();

        if (move.getType() == 1){
            game.add(new Move(piece, start, end, true, 1, squares[end], end));
            squares[start] = 0;
            squares[end] = piece;
            return;
        }

        //short castle
        if ((move.getType() == 2)&&((piece == 22 && blackCanSC)||(piece == 14 && whiteCanSC))){

            int dist = Math.abs(start-end);
            if (dist == 16) {
                game.add(new Move(piece, start, end, true, 2 , squares[end + 8], end + 8));
                squares[start] = 0;
                squares[end] = piece;
                squares[start + 8] = squares[end + 8];
                squares[end + 8] = 0;
                return;
            }
        }



        // long castle
        if((move.getType() == 4)&&((piece == 22 && blackCanLC)||(piece == 14 && whiteCanLC))){
            int dist = Math.abs(start-end);
            if (dist == 16) {
                game.add(new Move(piece, start, end, true, 4, squares[(end - start) + end], (end - start) + end));
                squares[start] = 0;
                squares[end] = piece;
                squares[(end - start) / 2 + start] = squares[(end - start) + end];
                squares[(end - start) + end] = 0;
                return;
            }
        }

        // after king or rook moves no castle
        if (piece % 8 == 6 || piece % 8 == 4){
            if(piece > 16){
                if (squares[end] == 0){
                    game.add(new Move(piece, start, end, blackCanLC, blackCanSC));
                }
                else {
                    game.add(new Move(piece, start, end, squares[end], end, blackCanLC, blackCanSC));
                }
                blackCanSC = false;
                blackCanLC = false;
            }
            else{
                if (squares[end] == 0){
                    game.add(new Move(piece, start, end, whiteCanLC, whiteCanSC));
                }
                else {
                    game.add(new Move(piece, start, end, squares[end], end, whiteCanLC, whiteCanSC));
                }
                whiteCanSC = false;
                whiteCanLC = false;
            }
            squares[start] = 0;
            squares[end] = piece;
            return;
        }

        //en passant
        if ((game.size() > 0) && ((game.get(game.size()-1).getPiece() == 9 && game.get(game.size()-1).getEnd() == end - 1 && game.get(game.size()-1).getEnd() == game.get(game.size()-1).getStart() - 2) || (game.get(game.size()-1).getPiece() == 17 && game.get(game.size()-1).getEnd() - 1 == end && game.get(game.size()-1).getEnd() - 2 == game.get(game.size()-1).getStart()))){
            squares[start] = 0;
            squares[game.get(game.size()-1).getEnd()] = 0;
            squares[end] = piece;
            game.add(new Move(piece, start, end, true, 3, game.get(game.size()-1).getPiece() , game.get(game.size()-1).getEnd()));
            return;
        }

        if (squares[end] == 0){
            game.add(new Move(piece, start, end));
        }
        else {
            game.add(new Move(piece, start, end, squares[end], end));
        }
        squares[start] = 0;
        squares[end] = piece;

    }

    public void unmakeMove(){
        Move move = game.get(game.size()-1);
        int piece = move.getPiece();
        int start = move.getStart();
        int end = move.getEnd();
        switchMove();

        if (piece % 8 == 6 || piece % 8 == 4){
            if (piece>16) {
                if (move.getlC())
                    blackCanLC = true;
                if (move.getsC())
                    blackCanSC = true;
            }else {
                if (move.getlC())
                    whiteCanLC = true;
                if (move.getsC())
                    whiteCanSC = true;
            }
        }

        if (!move.getSpecial()){
            if (move.getTaken() == 0){
                squares[end] = 0;
                squares[start] = piece;
                game.remove(game.size()-1);
                return;
            }
            squares[end] = 0;
            squares[start] = piece;
            squares[move.getSquareTaken()] = move.getTaken();
            game.remove(game.size()-1);
            return;
        }


        int specialType = move.getType();
        //promotion
        if (specialType == 1){
            squares[end] = 0;
            squares[start] = (piece/8)*8 + 1;

            if (move.getTaken() != 0){
                squares[move.getSquareTaken()] = move.getTaken();
            }
            game.remove(game.size()-1);
            return;
        }
        //castle
        else if (specialType == 2 || specialType == 4){

            squares[end] = 0;
            squares[start] = piece;
            if (end > start){
                //short
                squares[start + 8] = 0;
                squares[start + 16] = 0;
            }
            else{
                //long
                squares[start - 8] = 0;
                squares[start - 16] = 0;
                squares[start - 24] = 0;
                squares[end-16] = 0;
            }
            squares[move.getSquareTaken()] = move.getTaken();
            game.remove(game.size()-1);
            return;
        }
        //en passant
        else if (specialType == 3){
            squares[end] = 0;
            squares[start] = piece;
            squares[move.getSquareTaken()] = move.getTaken();
            game.remove(game.size()-1);
            return;
        }

    }

    public ArrayList<Move> generateMoves(){
        if (whiteMove){return generateMovesWhite();}
        return generateMovesBlack();
    }

    public ArrayList<Move> generateMovesBlack(){
        ArrayList<Move> moves = new ArrayList<>();
        for (int i = 0; i<64; i++){
            if (squares[i] > 16){
                int piece = squares[i];
                if (piece % 8 == 1){
                    for (int j = i-9; j <= i+9; j++){
                        if (isLegalMove(piece, i, j)){
                            if (j % 8 == 7){
                                Move queen = new Move(21, i, j, true, 1, squares[j] , j);
                                Move knight = new Move(19, i, j, true, 1, squares[j] , j);
                                Move bishop = new Move(18, i, j, true, 1, squares[j] , j);
                                Move rook = new Move(20, i, j, true, 1, squares[j] , j);
                                moves.add(queen);
                                moves.add(knight);
                                moves.add(rook);
                                moves.add(bishop);
                            }
                            else {
                                Move move = new Move(piece, i, j);
                                moves.add(move);
                            }
                        }
                    }
                }
                else if (piece % 8 == 2){
                    for (int j = i % 7; j < 64; j +=7){
                        if (isLegalMove(piece, i, j)){
                            Move move = new Move(piece, i, j);
                            moves.add(move);
                        }
                    }
                    for (int j = i % 9; j < 64; j +=9){
                        if (isLegalMove(piece, i, j)){
                            Move move = new Move(piece, i, j);
                            moves.add(move);
                        }
                    }
                }
                else if (piece % 8 == 3){
                    for (int j = i-17; j < 64; j ++){
                        if (isLegalMove(piece, i, j)){
                            Move move = new Move(piece, i, j);
                            moves.add(move);
                        }
                    }
                }
                else if (piece % 8 == 4){
                    for (int j = i % 8; j < 64; j +=8){
                        if (isLegalMove(piece, i, j)){
                            Move move = new Move(piece, i, j);
                            moves.add(move);
                        }
                    }
                    for (int j = i-7; j < i+7; j ++){
                        if (isLegalMove(piece, i, j)){
                            Move move = new Move(piece, i, j);
                            moves.add(move);
                        }
                    }
                }
                else if (piece % 8 == 5){
                    for (int j = i % 7; j < 64; j +=7){
                        if (isLegalMove(piece, i, j)){
                            Move move = new Move(piece, i, j);
                            moves.add(move);
                        }
                    }
                    for (int j = i % 9; j < 64; j +=9){
                        if (isLegalMove(piece, i, j)){
                            Move move = new Move(piece, i, j);
                            moves.add(move);
                        }
                    }
                    for (int j = i % 8; j < 64; j +=8){
                        if (isLegalMove(piece, i, j)){
                            Move move = new Move(piece, i, j);
                            moves.add(move);
                        }
                    }
                    int a = (i/8)*8;
                    for (int j = a; j < a+7; j++){
                        if (isLegalMove(piece, i, j)){
                            Move move = new Move(piece, i, j);
                            moves.add(move);
                        }
                    }
                }
                else if (piece % 8 == 6){
                    for (int j = i-9; j <= i + 9; j ++){
                        if (isLegalMove(piece, i, j)){
                            Move move = new Move(piece, i, j);
                            moves.add(move);
                        }
                    }
                    if (isLegalMove(piece, i, i+16)){
                        Move move = new Move(piece, i, i+16, true, 2, 0, 0);
                        moves.add(move);
                    }
                    if (isLegalMove(piece, i, i-16)){
                        Move move = new Move(piece, i, i-16, true, 2, 0, 0);
                        moves.add(move);
                    }
                }
            }
        }
        return moves;
    }

    public ArrayList<Move> generateMovesWhite(){
        ArrayList<Move> moves = new ArrayList<>();
        for (int i = 0; i<64; i++){
            if (squares[i] < 16 && squares[i] != 0){
                int piece = squares[i];
                if (piece % 8 == 1){
                    for (int j = i-9; j <= i+9; j++){
                        if (isLegalMove(piece, i, j)){
                            if (j % 8 == 0){
                                Move queen = new Move(13, i, j, true, 1, squares[j] , j);
                                Move knight = new Move(11, i, j, true, 1, squares[j] , j);
                                Move bishop = new Move(10, i, j, true, 1, squares[j] , j);
                                Move rook = new Move(12, i, j, true, 1, squares[j] , j);
                                moves.add(queen);
                                moves.add(knight);
                                moves.add(bishop);
                                moves.add(rook);
                            }
                            else {
                                Move move = new Move(piece, i, j);
                                moves.add(move);
                            }
                        }
                    }
                }
                else if (piece % 8 == 2){
                    for (int j = i % 7; j < 64; j +=7){
                        if (isLegalMove(piece, i, j)){
                            Move move = new Move(piece, i, j);
                            moves.add(move);
                        }
                    }
                    for (int j = i % 9; j < 64; j +=9){
                        if (isLegalMove(piece, i, j)){
                            Move move = new Move(piece, i, j);
                            moves.add(move);
                        }
                    }
                }
                else if (piece % 8 == 3){
                    for (int j = i-17; j < 64; j ++){
                        if (isLegalMove(piece, i, j)){
                            Move move = new Move(piece, i, j);
                            moves.add(move);
                        }
                    }
                }
                else if (piece % 8 == 4){
                    for (int j = i % 8; j < 64; j +=8){
                        if (isLegalMove(piece, i, j)){
                            Move move = new Move(piece, i, j);
                            moves.add(move);
                        }
                    }
                    for (int j = i-7; j < i+7; j ++){
                        if (isLegalMove(piece, i, j)){
                            Move move = new Move(piece, i, j);
                            moves.add(move);
                        }
                    }
                }
                else if (piece % 8 == 5){
                    for (int j = i % 7; j < 64; j +=7){
                        if (isLegalMove(piece, i, j)){
                            Move move = new Move(piece, i, j);
                            moves.add(move);
                        }
                    }
                    for (int j = i % 9; j < 64; j +=9){
                        if (isLegalMove(piece, i, j)){
                            Move move = new Move(piece, i, j);
                            moves.add(move);
                        }
                    }
                    for (int j = i % 8; j < 64; j +=8){
                        if (isLegalMove(piece, i, j)){
                            Move move = new Move(piece, i, j);
                            moves.add(move);
                        }
                    }
                    int a = (i/8)*8;
                    for (int j = a; j < a+7; j++){
                        if (isLegalMove(piece, i, j)){
                            Move move = new Move(piece, i, j);
                            moves.add(move);
                        }
                    }
                }
                else if (piece % 8 == 6){
                    for (int j = i-9; j <= i + 9; j ++){
                        if (isLegalMove(piece, i, j)){
                            Move move = new Move(piece, i, j);
                            moves.add(move);
                        }
                    }
                    if (isLegalMove(piece, i, i+16)){
                        Move move = new Move(piece, i, i+16, true, 2, 0, 0);
                        moves.add(move);
                    }
                    if (isLegalMove(piece, i, i-16)){
                        Move move = new Move(piece, i, i-16, true, 4, 0, 0);
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
                if (squares[end] == 0) {
                    return true;
                }
            }
            if (end == start - 7 || end == start + 9) {
                if (squares[end] > 0 && squares[end] < 16)
                    return true;
                return game.get(game.size()-1).getPiece() == 9 && game.get(game.size()-1).getEnd() == end - 1 && game.get(game.size()-1).getEnd() == game.get(game.size()-1).getStart() - 2;
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
                if(squares[end] > 16)
                    return true;
                if (game.size() < 1)
                    return false;
                return game.get(game.size()-1).getPiece() == 17 && game.get(game.size()-1).getEnd() - 1 == end && game.get(game.size()-1).getEnd() - 2 == game.get(game.size()-1).getStart();
            }
        }
        return false;
    }

    public boolean legalBishopMove(int piece, int start, int end) {

        if (piece > 16){
            if (squares[end] > 16){ return false;}
        } else {
            if (squares[end] < 16 && squares[end] > 0){return false;}
        }
        if (Math.abs((start % 8) - (end % 8)) != Math.abs((start/8)-(end / 8))){
            return false;
        }

        int bigSquare = Integer.max(start, end);
        int smallSquare = Integer.min(start, end);
        if (end % 7 == start % 7){
            for (int i = smallSquare+7; i < bigSquare; i+=7){
                if (squares[i] != 0)
                    return false;
            }
        }
        else if (end % 9 == start % 9){
            for (int i = smallSquare+9; i < bigSquare; i+=9){
                if (squares[i] != 0)
                    return false;
            }
        }
        else{
            return false;
        }
        return true;
    }

    public boolean legalKnightMove(int piece, int start, int end) {
        int difference = Math.abs(end-start);
        if (piece > 16) {
            if ( squares[end] > 16) {
                return false;
            }
        } else {
            if (squares[end] < 16 && squares[end] > 0) {
                return false;
            }
        }
        if (difference == 6 || difference == 10 || difference == 15 || difference == 17){
            return Math.abs((end % 8) - (start % 8)) < 3 && Math.abs((end / 8) - (start / 8)) < 3;
        }
        return false;
    }

    public boolean legalRookMove(int piece, int start, int end) {
        int r = end / 8;
        int f = end % 8;
        int rank = start / 8;
        int file = start % 8;
        int bigSquare = Integer.max(start, end);
        int smallSquare = Integer.min(start,end);

        if (r != rank && f != file) {
            return false;
        }
        if (piece > 16){
            if (squares[end] > 16){
                return false;
            }
            if (r == rank){
                for (int i = smallSquare + 1; i < bigSquare; i++){
                    if (squares[i] != 0)
                        return false;
                }
            }
            else{
                for (int i = smallSquare + 8; i < bigSquare; i+=8){
                    if (squares[i] != 0)
                        return false;
                }
            }
        }else{
            if (squares[end] < 16 && squares[end] != 0){
                return false;
            }
            if (r == rank){
                for (int i = smallSquare + 1; i < bigSquare; i++){
                    if (squares[i] != 0)
                        return false;
                }
            }
            else{
                for (int i = smallSquare + 8; i < bigSquare; i+=8){
                    if (squares[i] != 0)
                        return false;
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
            if (start == 32 && end == start+16 && squares[end+8] == 20 && squares[start + 8] == 0 && squares[start+16] == 0 && blackCanSC){return true;}
            if (start == 32 && end == start-16 && squares[end-16] == 20 && squares[start - 8] == 0 && squares[start-16] == 0 && squares[start-24] == 0 && blackCanLC){return true;}
        }
        else {
            if ( squares[end] < 16 && squares[end] != 0) {
                return false;
            }
            if (start == 39 && end == start+16 && squares[end+8] == 12 && squares[start + 8] == 0 && squares[start+16] == 0 && whiteCanSC){return true;}
            if (start == 39 && end == start-16 && squares[end-16] == 12 && squares[start - 8] == 0 && squares[start-16] == 0 && squares[start-24] == 0 && whiteCanLC){return true;}
        }
        if (end == start + 1 || end == start - 1 || end == start + 7 || end == start - 7 || end == start + 8 || end == start - 8 || end == start + 9 || end == start - 9){
            return Math.abs((end % 8) - (start % 8)) < 2 && Math.abs((end / 8) - (start / 8)) < 2;
        }
        return false;
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

    public ArrayList<Move> movesNoCheck(){
        ArrayList<Move> pseudoLegalMoves = generateMoves();

        for (int i = 0; i < pseudoLegalMoves.size(); i++){

            makeMove(pseudoLegalMoves.get(i));
            ArrayList<Move> newMoves = generateMoves();
            for (Move move: newMoves){
                if ((getMove() && squares[move.getEnd()] == 22) || (!getMove() && squares[move.getEnd()] == 14)){
                    pseudoLegalMoves.remove(i);
                    i--;
                    break;
                }
            }
            unmakeMove();
        }

        return pseudoLegalMoves;
    }

    public int Evaluate(Boards board){
        int whiteEval = CountMaterial(true, board);
        int blackEval = CountMaterial(false, board);

        int evaluation = whiteEval-blackEval;
        int perspective = 1;
        if (!board.getMove())
            perspective = -1;

        return evaluation * perspective;

    }

    public int CountMaterial(boolean white, Boards board){
        int material = 0;
        int color = 2;
        if (white){color = 1;}
        for (Integer square: board.getSquares()){
            if (square/8 == color){
                int piece = square % 8;
                if (piece == 1){
                    material += pawn;
                    continue;
                }
                if (piece == 2){
                    material += knight;
                    continue;
                }
                if (piece == 3){
                    material += bishop;
                    continue;
                }
                if (piece == 4){
                    material += rook;
                    continue;
                }
                if (piece == 5){
                    material +=queen;
                    continue;
                }
                if (piece == 6){
                    material += king;
                    continue;
                }
            }
        }
        return material;
    }

    public int Search(int depth, int alpha, int beta, Boards board){
        if (depth == 0){
            return Evaluate(board);
        }

        ArrayList<Move> moves = board.generateMoves();

        if (moves.size() == 0){
            if (board.getMove()){ return Integer.MIN_VALUE; }
            return 0;
        }

        for (Move move: moves){
            Boards newBoard = new Boards(board.getSquares(), board.getMove(), board.getBlackCanLC(), board.getBlackCanSC(), board.getWhiteCanLC(), board.getWhiteCanSC(), board.game);
            newBoard.makeMove(move);
//            System.out.println(move.getPiece());
            int evaluation = -Search(depth-1, -beta, -alpha, newBoard);
            if (evaluation >= beta){
                //move too good, trim branch
                return beta;
            }
            alpha = Integer.max(alpha, evaluation);
        }
        return alpha;
    }

    public Move bestMove(ArrayList<Move> moves, Boards board){
        Move bestMove = moves.get(0);
        int bestScore = Integer.MAX_VALUE;
        for (Move move: moves){
            Boards a = new Boards(board.getSquares(), board.getMove(), board.getBlackCanLC(), board.getBlackCanSC(), board.getWhiteCanLC(), board.getWhiteCanSC(), board.game);
            a.makeMove(move);
            int moveScoreGuess = Search(4, Integer.MIN_VALUE, Integer.MAX_VALUE, a);
            System.out.println(moveScoreGuess);

//            int movePiece = move.getPiece();
//            int capturePiece = board.getSquares()[move.getEnd()];
//
//            //capturing valuable piece with not as valuable piece
//            if (capturePiece != 0){
//                moveScoreGuess = 10 * capturePiece - movePiece;
//            }
//
//            //promoting a pawn
//            if ((movePiece == 9 && move.getEnd() % 8 == 0) || (movePiece == 17 && move.getEnd() % 8 == 7)) {
//                moveScoreGuess += 6;
//            }

            if (moveScoreGuess < bestScore){
                bestScore = moveScoreGuess;
                bestMove = move;
            }

        }
        return bestMove;
    }

    public int moveGenerationTest(int depth){
        if (depth == 0){
            return 1;
        }

        ArrayList<Move> moves = movesNoCheck();

        //duplicate check
//        for (int i = 0; i < moves.size(); i++){
//            for (int j = i+1; j < moves.size(); j++){
//                if (moves.get(i).equalsMove(moves.get(j)))
//                    System.out.println(moves.get(i));
//            }
//        }

//        Move a = moves.get(0);
//        if (depth == 4){
//            moves.clear();
//            moves.add(a);
//        }

        int numPositions = 0;

        for (Move move: moves){
            makeMove(move);
            numPositions += moveGenerationTest(depth-1);
            unmakeMove();
        }

        if (depth == 4)
            System.out.println(numPositions);

        return numPositions;
    }

    public int[] getSquares() {
        return squares.clone();
    }

    public void switchMove(){
        whiteMove = !whiteMove;
    }

    public boolean getMove() {
        return whiteMove;
    }

    public boolean getBlackCanLC() {
        return blackCanLC;
    }

    public boolean getBlackCanSC() {
        return blackCanSC;
    }

    public boolean getWhiteCanLC() {
        return whiteCanLC;
    }

    public boolean getWhiteCanSC() {
        return whiteCanSC;
    }

}
