import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Console;
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


//        square[40] = Piece.black | Piece.king;
//        square[0] = Piece.black | Piece.rook;
//        square[8] = Piece.black | Piece.knight;
//        square[16] = Piece.black | Piece.bishop;
//        square[24] = Piece.black | Piece.queen;
//        square[56] = Piece.black | Piece.rook;
//        square[1] = Piece.black | Piece.pawn;
//        square[9] = Piece.black | Piece.pawn;
//        square[18] = Piece.black | Piece.pawn;
//        square[25] = Piece.white | Piece.pawn;
//        square[33] = Piece.black | Piece.bishop;
//        square[41] = Piece.black | Piece.pawn;
//        square[49] = Piece.black | Piece.pawn;
//        square[57] = Piece.black | Piece.pawn;
//
//        square[7] = Piece.white | Piece.rook;
//        square[15] = Piece.white | Piece.knight;
//        square[23] = Piece.white | Piece.bishop;
//        square[31] = Piece.white | Piece.queen;
//        square[39] = Piece.white | Piece.king;
//        square[20] = Piece.white | Piece.bishop;
//        square[38] = Piece.white | Piece.knight;
//        square[63] = Piece.white | Piece.rook;
//        square[6] = Piece.white | Piece.pawn;
//        square[14] = Piece.white | Piece.pawn;
//        square[22] = Piece.white | Piece.pawn;
//        square[46] = Piece.black | Piece.knight;
//        square[54] = Piece.white | Piece.pawn;
//        square[62] = Piece.white | Piece.pawn;

        squares = new Boards(square, false, true, true, true, true, new ArrayList<Move>());

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

        start();

    }

    public void start(){
        squares.switchMove();
        squares.moveGenerationTest(4);
//        setupMouseListener();
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
                else if (!squares.getMove() && pressedPiece > 16){
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

                turn(selectedPiece, startSquare, drop);

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

    public void turn(int piece, int start, int end){
//        compGame(piece, start, end);
        playerGame(piece,start,end);
    }

    public void playerGame(int piece, int start, int end){
        boolean legal = false;
        ArrayList<Move> moves = squares.movesNoCheck();
        Move thisMove = buildMove(piece, start, end);
        if ((piece == 9 && end % 8 == 0) || (piece == 17 && end % 8 == 7)){
            legal = true;
            JOptionPane promotion = new JOptionPane();
            thisMove = new Move(promotion.getPiece()+8, start, end);
        }
        for (Move moo: moves){
            if (thisMove.equalsMove(moo)){
                legal = true;
                thisMove = moo;
                break;
            }
        }

        if (legal) {
            squares.makeMove(thisMove);
            repaint();
        } else {
            squares.getSquares()[start] = piece;
        }
    }

    public void compGame(int piece, int start, int end){
        boolean legal = false;
        ArrayList<Move> moves = squares.movesNoCheck();
        Move thisMove = buildMove(piece, start, end);
        if ((piece == 9 && end % 8 == 0) || (piece == 17 && end % 8 == 7)){
            legal = true;
            JOptionPane promotion = new JOptionPane();
            thisMove = new Move(promotion.getPiece()+8, start, end);
        }
        for (Move moo: moves){
            if (thisMove.equalsMove(moo)){
                legal = true;
                thisMove = moo;
                break;
            }
        }

        if (legal) {
            squares.makeMove(thisMove);
            repaint();
            ArrayList<Move> compMoves = squares.movesNoCheck();
            Move move = compMoves.get((int)(Math.random() * compMoves.size()));
//                    Move move = squares.bestMove(compMoves, squares);
            squares.makeMove(move);
        } else {
            squares.getSquares()[start] = piece;
        }
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

    public Move buildMove(int piece, int start, int end){
        Move move = new Move(piece,start,end);
        return move;
    }

}