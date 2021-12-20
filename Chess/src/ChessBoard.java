import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.ArrayList;

public class ChessBoard extends JPanel {

    public Boards squares;
    public int[] square;
    public static ArrayList<Image> sprites;
    public static Image blackbishop, blackking, blackknight, blackpawn, blackqueen, blackrook, whitebishop, whiteking, whiteknight, whitepawn, whitequeen, whiterook, blank;
    public static int selectedPiece, startSquare, pressedPiece;

    public int mouseX, mouseY;
    public boolean whiteMove;

    public ChessBoard(int width, int height) {
        whiteMove = false;
        pressedPiece = 0;
        setSize(width, height);
        selectedPiece = 0;
        startSquare = 0;
        mouseX = 0;
        mouseY = 0;
        square = new int[64];
        sprites = new ArrayList<>();

        square[32] = Piece.black | Piece.king;
        square[0] = Piece.black | Piece.rook;
        square[8] = Piece.black | Piece.knight;
        square[16] = Piece.black | Piece.bishop;
        square[24] = Piece.black | Piece.queen;
        square[40] = Piece.black | Piece.bishop;
        square[48] = Piece.black | Piece.knight;
        square[56] = Piece.black | Piece.rook;
        square[1] = Piece.black | Piece.pawn;
        square[9] = Piece.black | Piece.pawn;
        square[17] = Piece.black | Piece.pawn;
        square[25] = Piece.black | Piece.pawn;
        square[33] = Piece.black | Piece.pawn;
        square[41] = Piece.black | Piece.pawn;
        square[49] = Piece.black | Piece.pawn;
        square[57] = Piece.black | Piece.pawn;

        square[7] = Piece.white | Piece.rook;
        square[15] = Piece.white | Piece.knight;
        square[23] = Piece.white | Piece.bishop;
        square[31] = Piece.white | Piece.queen;
        square[39] = Piece.white | Piece.king;
        square[47] = Piece.white | Piece.bishop;
        square[55] = Piece.white | Piece.knight;
        square[63] = Piece.white | Piece.rook;
        square[6] = Piece.white | Piece.pawn;
        square[14] = Piece.white | Piece.pawn;
        square[22] = Piece.white | Piece.pawn;
        square[30] = Piece.white | Piece.pawn;
        square[38] = Piece.white | Piece.pawn;
        square[46] = Piece.white | Piece.pawn;
        square[54] = Piece.white | Piece.pawn;
        square[62] = Piece.white | Piece.pawn;

        squares = new Boards(square, false);

        try {
            blank = ImageIO.read(new File("./ChessSprites/Blank.png"));
            blackbishop = ImageIO.read(new File("./ChessSprites/BlackBishop.png"));
            blackking = ImageIO.read(new File("./ChessSprites/BlackKing.png"));
            blackknight = ImageIO.read(new File("./ChessSprites/BlackKnight.png"));
            blackpawn = ImageIO.read(new File("./ChessSprites/BlackPawn.png"));
            blackqueen = ImageIO.read(new File("./ChessSprites/BlackQueen.png"));
            blackrook = ImageIO.read(new File("./ChessSprites/BlackRook.png"));
            whitebishop = ImageIO.read(new File("./ChessSprites/WhiteBishop.png"));
            whiteking = ImageIO.read(new File("./ChessSprites/WhiteKing.png"));
            whiteknight = ImageIO.read(new File("./ChessSprites/WhiteKnight.png"));
            whitepawn = ImageIO.read(new File("./ChessSprites/WhitePawn.png"));
            whitequeen = ImageIO.read(new File("./ChessSprites/WhiteQueen.png"));
            whiterook = ImageIO.read(new File("./ChessSprites/WhiteRook.png"));

            sprites.add(blank);
            sprites.add(blank);
            sprites.add(blank);
            sprites.add(blank);
            sprites.add(blank);
            sprites.add(blank);
            sprites.add(blank);
            sprites.add(blank);
            sprites.add(blank);
            sprites.add(whitepawn);
            sprites.add(whitebishop);
            sprites.add(whiteknight);
            sprites.add(whiterook);
            sprites.add(whitequeen);
            sprites.add(whiteking);
            sprites.add(blank);
            sprites.add(blank);
            sprites.add(blackpawn);
            sprites.add(blackbishop);
            sprites.add(blackknight);
            sprites.add(blackrook);
            sprites.add(blackqueen);
            sprites.add(blackking);

        } catch (Exception e) {
            e.printStackTrace();
        }

        squares.switchMove();
        setupMouseListener();
        repaint();
    }

    public void setupMouseMotionListener() {
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }

        });
    }

    public void setupMouseListener() {
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
                int r = e.getX() / 96;
                int f = e.getY() / 96;

                pressedPiece = squares.getSquares()[r*8+f];

                if (squares.getMove() && pressedPiece < 16 && pressedPiece != 0) {
                    selectedPiece = squares.getSquares()[r*8+f];
                    startSquare = r * 8 + f;
                    setupMouseMotionListener();
                    repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int r = e.getX() / 96;
                int f = e.getY() / 96;
                int drop = r * 8 + f;

                if (squares.getMove() && selectedPiece > 16){
                    selectedPiece = 0;
                    return;}
                if (!squares.getMove() && selectedPiece < 16 && selectedPiece != 0) {
                    selectedPiece = 0;
                    return;}

                boolean legal = false;
                ArrayList<Move> moves = squares.generateMoves();
                ArrayList<Move> legalMoves = clearChecks(moves);
                Move thisMove = buildMove(selectedPiece, startSquare, drop);

                for (Move moo: legalMoves){
                    if (thisMove.getPiece() == moo.getPiece() && thisMove.getStart() == moo.getStart() && thisMove.getEnd() == moo.getEnd()){
                        legal = true;
                        break;
                    }
                }

                if (legal) {
                    if ((thisMove.getPiece() == 9 && thisMove.getEnd() % 8 == 0) || (thisMove.getEnd() == 17 && thisMove.getEnd() % 8 == 7)) {
                        JOptionPane promotion = new JOptionPane();
                        squares.makeMove(thisMove.getPiece(), thisMove.getStart(), thisMove.getEnd(), true, promotion.getName());
                    }
                    else {
                        squares.makeMove(thisMove.getPiece(), thisMove.getStart(), thisMove.getEnd());
                    }
                    repaint();
                    ArrayList<Move> compMoves = squares.generateMoves();
                    ArrayList<Move> compLegalMoves = clearChecks(compMoves);
                    Move move = compLegalMoves.get((int)(Math.random() * compLegalMoves.size()));
                    squares.makeMove(move.getPiece(), move.getStart(), move.getEnd());
                } else {
                    squares.getSquares()[startSquare] = selectedPiece;
                }
                selectedPiece = 0;
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

        });
    }

    //TODO: if i click a piece and then click a squares.getSquares(), the piece disappears, I think it's a startsquare not being cleared issue

    public ArrayList<Move> clearChecks(ArrayList<Move> pseudoLegalMoves){

        Boards board = new Boards(squares.getSquares(), squares.getTakenPieces(), squares.getMove());

        for (int i = 0; i<pseudoLegalMoves.size(); i++){

            board.setSquares(squares.getSquares());
            board.setTakenPieces(squares.getTakenPieces());
            board.setWhiteMove(squares.getMove());

            board.makeMove(pseudoLegalMoves.get(i).getPiece(), pseudoLegalMoves.get(i).getStart(), pseudoLegalMoves.get(i).getEnd());

            Boards madeMove = new Boards(board.getSquares(), board.getMove());

            ArrayList<Move> a = board.generateMoves();

            for (Move b: a){
                madeMove.setSquares(board.getSquares());
                madeMove.setWhiteMove(board.getMove());

                madeMove.makeMove(b.getPiece(), b.getStart(), b.getEnd());

                if ((!madeMove.getMove() && board.getSquares()[b.getEnd()] == 22) || (madeMove.getMove() && board.getSquares()[b.getEnd()] == 14)){
                    System.out.println(pseudoLegalMoves.get(i).getPiece() + " " + pseudoLegalMoves.get(i).getStart() + " " + pseudoLegalMoves.get(i).getEnd());
                    pseudoLegalMoves.remove(i);
                    i--;
                    break;
                }
            }
        }

//        for (Move m: pseudoLegalMoves){
//            System.out.println(m.getPiece() + " " + m.getStart() + " " + m.getEnd());
//        }
        System.out.println();

        return pseudoLegalMoves;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Color brown = new Color(59, 32, 3, 255);
        int l = 96;

        //draw board
        squares.drawBoard(g2, sprites);

        //draw selectedPiece
        if (selectedPiece != 0) {
            if (squares.getMove() && selectedPiece < 16) {
                int f = startSquare % 8;
                int r = startSquare/ 8;
                if ((f + r) % 2 == 0) {
                    g2.setColor(Color.WHITE);
                } else {
                    g2.setColor(brown);
                }
                g2.fillRect((r * l), (f * l), l, l);
                g2.drawImage(sprites.get(selectedPiece), mouseX - l / 2, mouseY - l / 2, l, l, null);
            }
        }
    }

    public Move buildMove(int piece, int start, int end){
        Move move = new Move(piece,start,end);
        return move;
    }

}