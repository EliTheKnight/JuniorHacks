import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.ImageObserver;
import java.io.File;
import java.util.ArrayList;

public class ChessBoard extends JPanel {

    public static int[] square;
    public static ArrayList<Image> sprites;
    public static Image blackbishop, blackking, blackknight, blackpawn, blackqueen, blackrook, whitebishop, whiteking, whiteknight, whitepawn, whitequeen, whiterook, blank;
    public ImageObserver observer;

    public int selectedPiece, startSquare;
    public int mouseX, mouseY;
    public boolean whiteMove;

    public ChessBoard(int width, int height) {
        whiteMove = true;
        setSize(width, height);
        selectedPiece = 0;
        startSquare = 0;
        mouseX = 0;
        mouseY = 0;
        square = new int[64];
        sprites = new ArrayList<>();
        square[0] = Piece.black | Piece.rook;
        square[8] = Piece.black | Piece.knight;
        square[16] = Piece.black | Piece.bishop;
        square[24] = Piece.black | Piece.queen;
        square[32] = Piece.black | Piece.king;
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

        observer = (img, infoflags, x, y, width1, height1) -> false;
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
                repaint();

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
                int r = e.getX() / 96;
                int f = e.getY() / 96;
                int pressedPiece = square[r*8+f];
                if (whiteMove && pressedPiece < 16 && pressedPiece != 0)
                selectedPiece = pressedPiece;
                startSquare = r * 8 + f;
                square[r * 8 + f] = 0;
                setupMouseMotionListener();
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int r = e.getX() / 96;
                int f = e.getY() / 96;
                int drop = r * 8 + f;

//                if (selectedPiece == 0){return;}
//                else if (selectedPiece>16){
//                    if (selectedPiece % 8 == 1){
//                        boolean legal = legalPawnMove(drop);
//                        if (legal){
//                            square[drop] = selectedPiece;
//                        }
//                        else{
//                            square[startSquare] = selectedPiece;
//                        }
//                        selectedPiece = 0;
//                        repaint();
//                        return;
//
//                    }
//                    if (selectedPiece % 8 == 2){
//                        boolean legal = legalBishopMove(drop);
//                        if (legal){
//                            square[drop] = selectedPiece;
//                        }
//                        else{
//                            square[startSquare] = selectedPiece;
//                        }
//                        selectedPiece = 0;
//                        repaint();
//                        return;
//                    }
//                    if (selectedPiece % 8 == 3){
//                        boolean legal = legalKnightMove(drop);
//                        if (legal){
//                            square[drop] = selectedPiece;
//                        }
//                        else{
//                            square[startSquare] = selectedPiece;
//                        }
//                        selectedPiece = 0;
//                        repaint();
//                        return;
//                    }
//                    if (selectedPiece % 8 == 4){
//                        boolean legal = legalRookMove(drop, r, f);
//                        if (legal){
//                            square[drop] = selectedPiece;
//                        }
//                        else{
//                            square[startSquare] = selectedPiece;
//                        }
//                        selectedPiece = 0;
//                        repaint();
//                        return;
//                    }
//                    if (selectedPiece % 8 == 5){
//                        boolean legal = legalQueenMove(drop, r, f);
//                        if (legal){
//                            square[drop] = selectedPiece;
//                        }
//                        else{
//                            square[startSquare] = selectedPiece;
//                        }
//                        selectedPiece = 0;
//                        repaint();
//                        return;
//                    }
//                    if (selectedPiece % 8 == 6){
//                        boolean legal = legalKingMove(drop);
//                        if (legal){
//                            square[drop] = selectedPiece;
//                        }
//                        else{
//                            square[startSquare] = selectedPiece;
//                        }
//                        selectedPiece = 0;
//                        repaint();
//                    }
//                }
//                else{
//                    if (selectedPiece % 8 == 1){
//                        boolean legal = legalPawnMove(drop);
//                        if (legal){
//                            square[drop] = selectedPiece;
//                            selectedPiece = 0;
//                            repaint();
//                            return;}
//                        else{
//                            square[startSquare] = selectedPiece;
//                            selectedPiece = 0;
//                            repaint();
//                            return;
//                        }
//                    }
//                    if (selectedPiece % 8 == 2){
//                        boolean legal = legalBishopMove(drop);
//                        if (legal){
//                            square[drop] = selectedPiece;
//                        }
//                        else{
//                            square[startSquare] = selectedPiece;
//                        }
//                        selectedPiece = 0;
//                        repaint();
//                        return;
//                    }
//                    if (selectedPiece % 8 == 3){
//                        boolean legal = legalKnightMove(drop);
//                        if (legal){
//                            square[drop] = selectedPiece;
//                        }
//                        else{
//                            square[startSquare] = selectedPiece;
//                        }
//                        selectedPiece = 0;
//                        repaint();
//                        return;
//                    }
//                    if (selectedPiece % 8 == 4){
//                        boolean legal = legalRookMove(drop, r, f);
//                        if (legal){
//                            square[drop] = selectedPiece;
//                        }
//                        else{
//                            square[startSquare] = selectedPiece;
//                        }
//                        selectedPiece = 0;
//                        repaint();
//                        return;
//                    }
//                    if (selectedPiece % 8 == 5){
//                        boolean legal = legalQueenMove(drop, r, f);
//                        if (legal){
//                            square[drop] = selectedPiece;
//                        }
//                        else{
//                            square[startSquare] = selectedPiece;
//                        }
//                        selectedPiece = 0;
//                        repaint();
//                        return;
//                    }
//                    if (selectedPiece % 8 == 6){
//                        boolean legal = legalKingMove(drop);
//                        if (legal){
//                            square[drop] = selectedPiece;
//                        }
//                        else{
//                            square[startSquare] = selectedPiece;
//                        }
//                        selectedPiece = 0;
//                        repaint();
//                    }
//                }

                boolean legal = isLegalMove(drop, r, f);

                if (legal) {
                    square[drop] = selectedPiece;
                    selectedPiece = 0;
                    repaint();
                } else {
                    square[startSquare] = selectedPiece;
                    selectedPiece = 0;
                    repaint();
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    public boolean isLegalMove(int drop, int r, int f) {
        if (selectedPiece == 0) {
            return false;
        }
        if (r > 7 || f > 7 || r < 0 || f < 0) {
            return false;
        }
        if (selectedPiece % 8 == 1) {
            return legalPawnMove(drop);
        }
        if (selectedPiece % 8 == 2) {
            return legalBishopMove(drop);
        }
        if (selectedPiece % 8 == 3) {
            return legalKnightMove(drop);
        }
        if (selectedPiece % 8 == 4) {
            return legalRookMove(drop, r, f);
        }
        if (selectedPiece % 8 == 5) {
            return legalQueenMove(drop, r, f);
        }
        if (selectedPiece % 8 == 6) {
            return legalKingMove(drop);
        }
        return false;
    }

    public boolean legalPawnMove(int drop) {
        if (selectedPiece > 16) {
            if (drop == startSquare + 2) {
                if (startSquare % 8 == 1 && square[startSquare + 1] == 0 && square[drop] == 0) {
                    return true;
                }
            }
            if (drop == startSquare + 1) {
                if (square[drop] == 0) {
                    return true;
                }
            }
            if (drop == startSquare - 7 || drop == startSquare + 9) {
                return square[drop] > 0 && square[drop] < 16;
            }
        } else {
            if (drop == startSquare - 2 && square[startSquare - 1] == 0 && square[drop] == 0) {
                if (startSquare % 8 == 6) {
                    return true;
                }
            }
            if (drop == startSquare - 1) {
                if (square[drop] == 0) {
                    return true;
                }
            }
            if (drop == startSquare + 7 || drop == startSquare - 9) {
                return square[drop] > 16;
            }
        }
        return false;
    }

    public boolean legalBishopMove(int drop) {
        if (selectedPiece > 16) {
            if (square[drop] > 16) {
                return false;
            }
            if (drop % 7 == startSquare % 7) {
                if (drop > startSquare) {
                    for (int i = startSquare + 7; i < drop; i += 7) {
                        if (square[i] != 0) {
                            return false;
                        }
                    }
                }
                if (drop < startSquare) {
                    for (int i = startSquare - 7; i > drop; i -= 7) {
                        if (square[i] != 0) {
                            return false;
                        }
                    }
                }
            } else if (drop % 9 == startSquare % 9) {
                if (drop > startSquare) {
                    for (int i = startSquare + 9; i < drop; i += 9) {
                        if (square[i] != 0) {
                            return false;
                        }
                    }
                }
                if (drop < startSquare) {
                    for (int i = startSquare - 9; i > drop; i -= 9) {
                        if (square[i] != 0) {
                            return false;
                        }
                    }
                }
            } else {
                return false;
            }
        } else {
            if (square[drop] < 16 && square[drop] > 0) {
                return false;
            }
            if (drop % 7 == startSquare % 7) {
                if (drop > startSquare) {
                    for (int i = startSquare + 7; i < drop; i += 7) {
                        if (square[i] != 0) {
                            return false;
                        }
                    }
                }
                if (drop < startSquare) {
                    for (int i = startSquare - 7; i > drop; i -= 7) {
                        if (square[i] != 0) {
                            return false;
                        }
                    }
                }
            } else if (drop % 9 == startSquare % 9) {
                if (drop > startSquare) {
                    for (int i = startSquare + 9; i < drop; i += 9) {
                        if (square[i] != 0) {
                            return false;
                        }
                    }
                }
                if (drop < startSquare) {
                    for (int i = startSquare - 9; i > drop; i -= 9) {
                        if (square[i] != 0) {
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

    public boolean legalKnightMove(int drop) {
        if (selectedPiece > 16) {
            if (square[drop] > 16) {
                return false;
            }
        } else {
            if (square[drop] < 16 && square[drop] > 0) {
                return false;
            }
        }
        return drop == startSquare + 6 || drop == startSquare - 6 || drop == startSquare + 10 || drop == startSquare - 10 || drop == startSquare + 15 || drop == startSquare - 15 || drop == startSquare + 17 || drop == startSquare - 17;
    }

    public boolean legalRookMove(int drop, int r, int f) {
        int rank = startSquare / 8;
        int file = startSquare % 8;
        if (selectedPiece > 16) {
            if (square[drop] > 16) {
                return false;
            }
        } else {
            if (square[drop] < 16 && square[drop] > 0) {
                return false;
            }
        }
        if (r != rank && f != file) {
            return false;
        }
        if (rank == r) {
            if (file > f) {
                for (int i = f; i < file; i++) {
                    if (square[r * 8 + i] != 0 && r * 8 + i != drop) {
                        return false;
                    }
                }
            }
            if (file < f) {
                for (int i = file; i < f; i++) {
                    if (square[r * 8 + i] != 0 && r * 8 + i != drop) {
                        return false;
                    }
                }
            }
        }
        if (file == f) {
            if (rank > r) {
                for (int i = r; i < rank; i++) {
                    if (square[i * 8 + f] != 0 && i * 8 + f != drop) {
                        return false;
                    }
                }
            }
            if (rank < r) {
                for (int i = rank; i < r; i++) {
                    if (square[i * 8 + f] != 0 && i * 8 + f != drop) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean legalQueenMove(int drop, int r, int f) {
        return legalRookMove(drop, r, f) || legalBishopMove(drop);
    }

    public boolean legalKingMove(int drop) {
        if (selectedPiece > 16) {
            if (square[drop] > 16) {
                return false;
            }
        } else {
            if (square[drop] < 16 && square[drop] > 0) {
                return false;
            }
        }
        return drop == startSquare + 1 || drop == startSquare - 1 || drop == startSquare + 7 || drop == startSquare - 7 || drop == startSquare + 8 || drop == startSquare - 8 || drop == startSquare + 9 || drop == startSquare - 9;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Color brown = new Color(59, 32, 3, 255);
        int l = 96;
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

        for (int r = 0; r < 8; r++) {
            for (int f = 0; f < 8; f++) {
                if (square[(r * 8 + f)] == 0) {
                    continue;
                }

                g2.drawImage(sprites.get(square[r * 8 + f]), r * l, f * l, l, l, observer);
            }
        }

        if (selectedPiece != 0)
            g2.drawImage(sprites.get(selectedPiece), mouseX - l / 2, mouseY - l / 2, l, l, observer);
    }

}