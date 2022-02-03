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

        squares = new Boards(square, false, true, true, true, true, new ArrayList<Move>());

        // starting position:  "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"
        FENReader("rnbq1k1r/pp1Pbppp/2p5/8/2B5/8/PPP1NnPP/RNBQK2R w KQ - 1 8 ");

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

        for (int i = 1; i < 6; i++){
            System.out.println(i);
            long startTime = System.currentTimeMillis();
            squares.moveGenerationTest(i, i);
            long endTime = System.currentTimeMillis();
            System.out.println(endTime-startTime);
            System.out.println();
        }

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
        Move thisMove = new Move(piece, start, end);
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
        Move thisMove = new Move(piece, start, end);
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
        Color brown = new Color(80, 44, 6, 255);
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

    public void FENReader(String fen){
        // rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1
        ArrayList<String> pieces = new ArrayList<>();
        while (fen.contains("/")){
            int end = fen.indexOf("/");
            pieces.add(fen.substring(0,end));
            fen = fen.substring(end+1);
        }

        pieces.add(fen.substring(0, fen.indexOf(" ")));
        fen = fen.substring(fen.indexOf(" ") + 1);

        while (fen.contains(" ")){
            int end = fen.indexOf(" ");
            pieces.add(fen.substring(0,end));
            fen = fen.substring(end+1);
        }

        for (int i = 0; i < 8; i++) {
            String fill = pieces.get(i);
            for (int j = 0; j < 8; j++) {
                int b = fill.charAt(0);
                if (b < 62) {
                    j += (b - 48) - 1;
                }
                else if (b < 95) {
                    //if statements for b, k, n, p, q, r
                    if (b == 66) {
                        square[j * 8 + i] = Piece.white | Piece.bishop;
                    } else if (b == 75) {
                        square[j * 8 + i] = Piece.white | Piece.king;

                    } else if (b == 78) {
                        square[j * 8 + i] = Piece.white | Piece.knight;

                    } else if (b == 80) {
                        square[j * 8 + i] = Piece.white | Piece.pawn;

                    } else if (b == 81) {
                        square[j * 8 + i] = Piece.white | Piece.queen;

                    } else if (b == 82) {
                        square[j * 8 + i] = Piece.white | Piece.rook;
                    }
                }
                else {
                    //if statements for b, k, n, p, q, r
                    if (b == 98) {
                        square[j * 8 + i] = Piece.black | Piece.bishop;
                    } else if (b == 107) {
                        square[j * 8 + i] = Piece.black | Piece.king;

                    } else if (b == 110) {
                        square[j * 8 + i] = Piece.black | Piece.knight;

                    } else if (b == 112) {
                        square[j * 8 + i] = Piece.black | Piece.pawn;

                    } else if (b == 113) {
                        square[j * 8 + i] = Piece.black | Piece.queen;

                    } else if (b == 114) {
                        square[j * 8 + i] = Piece.black | Piece.rook;
                    }
                }
                fill = fill.substring(1);
            }
        }
        boolean whiteTurn = true;
        if (pieces.get(8).charAt(0) == 98){
            squares.switchMove();
            whiteTurn = false;
        }

        String castling = pieces.get(9);
        if (!castling.contains("K")){
            squares.setWhiteCanSC(false);
        }
        if (!castling.contains("Q")){
            squares.setWhiteCanLC(false);
        }
        if (!castling.contains("k")){
            squares.setBlackCanSC(false);
        }
        if (!castling.contains("q")){
            squares.setBlackCanLC(false);
        }

        String enPassant = pieces.get(10);
        if (enPassant.equals("-")){}
        else{
            int r = enPassant.charAt(0) - 97;
            int f = enPassant.charAt(1) - 48;
            int square = r*8 + f;
            if (whiteTurn){squares.addMoveToGame(new Move(17, square-1, square+1));}
            else{squares.addMoveToGame(new Move(9, square+1, square-1));}
        }

        //next 2 are repeated moves and number of moves
    }

}