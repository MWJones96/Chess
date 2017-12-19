package Game;

import Pieces.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**Represents the state of the game. Implemented as Singleton Pattern as there should only be ONE at once
 *
 */
public class Game {
    //Singleton game instance
    private static Game instance = null;

    public GUI gui;

    //The current turn (starts white)
    public Color turn = Color.WHITE;

    //True = select piece, false = select destination
    boolean pickup = true;

    //Whether the game is ongoing
    boolean playing = true;

    //Gameboard
    public Board m_board;

    public Piece selectedPiece = null;

    public King wk; //Reference to White King
    public King bk; //Reference to Black King

    private Game() {
        m_board = new Board(8);
        gui = new GUI();
    }

    /**
     * Singleton pattern for game instance
     *
     * @return - The game instance
     */
    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }

        return instance;
    }

    /**
     * Advances the state of the game when a button on the GUI is pressed
     *
     * x - X component of button press
     * y - Y component of button press
     */
    public void processButtonPress(int x, int y)
    {
        if(pickup)
        {
            if(m_board.m_pieces[x][y] == null)
            {
                System.out.println("Empty square clicked: invalid selection!");
                return;
            }
            if(m_board.m_pieces[x][y].m_color != turn)
            {
                System.out.println("Enemy piece clicked: invalid selection!");
                return;
            }

            ArrayList<Move> moves = m_board.m_pieces[x][y].getLegalMoves();
            if(moves.size() == 0)
            {
                System.out.println("Selected piece is unable to move!");
                return;
            }
            //Selection is OK!

            selectedPiece = m_board.m_pieces[x][y];

            //Paints moves on GUI
            for(Move m : selectedPiece.getLegalMoves())
            {
                Color s = (m.m_x + m.m_y) % 2 != 0 ? Color.decode("#228B22"): Color.decode("#ADFF2F");
                gui.buttons[m.m_x][m.m_y].setBackground(s);
            }

            pickup = !pickup;
        }
        else
        {
            if(!containsMove(new Move(x, y), selectedPiece.getLegalMoves()))
            {
                System.out.println("Not a valid move!");

                for(Move m : selectedPiece.getLegalMoves())
                {
                    Color s = (m.m_x + m.m_y) % 2 != 0 ? Color.BLACK: Color.WHITE;
                    gui.buttons[m.m_x][m.m_y].setBackground(s);
                }

                selectedPiece = null;

                pickup = !pickup;
                return;

            }

            for(Move m : selectedPiece.getLegalMoves())
            {
                Color s = (m.m_x + m.m_y) % 2 != 0 ? Color.BLACK: Color.WHITE;
                gui.buttons[m.m_x][m.m_y].setBackground(s);
            }

            gui.buttons[x][y].setIcon(gui.buttons[selectedPiece.m_x][selectedPiece.m_y].getIcon());
            gui.buttons[selectedPiece.m_x][selectedPiece.m_y].setIcon(null);

            selectedPiece.move(x, y);

            //Checking for pawn promotion
            if(selectedPiece.m_type == Type.PAWN)
            {
                int endPos = (selectedPiece.m_color == Color.WHITE) ? 0 : m_board.m_size - 1;
                if(selectedPiece.m_y == endPos)
                {

                    ImageIcon ic = (selectedPiece.m_color == Color.WHITE) ? gui.white_queen : gui.black_queen;
                    gui.buttons[selectedPiece.m_x][endPos].setIcon(ic);

                    m_board.putPiece(selectedPiece.m_x, endPos, new Queen(selectedPiece.m_color, selectedPiece.m_x, endPos, Type.QUEEN));
                }
            }

            System.out.println(selectedPiece.m_x);
            System.out.println(selectedPiece.m_y);

            pickup = !pickup;

            selectedPiece = null;

            //Switches turn
            turn = (turn == Color.WHITE) ? Color.BLACK : Color.WHITE;

            printBoard();
        }
    }

    /**Checks if a given move is valid for the piece selected
     *
     * @param m - The move to be checked
     * @param moves - The possible moves the current selection can move to
     * @return - Whether the move is in the list
     */
    public boolean containsMove(Move m, ArrayList<Move> moves)
    {
        for(int i = 0; i < moves.size(); i++)
        {
            if(moves.get(i).equals(m))
            {
                return true;
            }
        }

        return false;
    }

    public void initTestBoard()
    {
        Bishop b = new Bishop(Color.WHITE, 3, 3, Type.BISHOP);
        m_board.putPiece(3, 3, b);
        gui.buttons[3][3].setIcon(gui.white_bishop);

        Pawn p = new Pawn(Color.BLACK, 1, 1, Type.PAWN);
        m_board.putPiece(1, 1, p);
        gui.buttons[1][1].setIcon(gui.black_pawn);

        King k = new King(Color.WHITE, 0, 0, Type.KING);
        m_board.putPiece(0, 0, k);
        gui.buttons[0][0].setIcon(gui.white_king);

        wk = k;

        King bkf = new King(Color.BLACK, 1, 0, Type.KING);
        m_board.putPiece(1, 0, bkf);
        gui.buttons[1][0].setIcon(gui.black_king);

        bk = bkf;
    }

    /**Initialises the board to the state of a standard game of chess
     *
     */
    public void initBoard()
    {
        Pawn p = new Pawn(Color.WHITE, 0, 6, Type.PAWN);
        m_board.putPiece(p.m_x, p.m_y, p);
        gui.buttons[0][6].setIcon(gui.white_pawn);

        p = new Pawn(Color.WHITE, 1, 6, Type.PAWN);
        m_board.putPiece(p.m_x, p.m_y, p);
        gui.buttons[1][6].setIcon(gui.white_pawn);

        p = new Pawn(Color.WHITE, 2, 6, Type.PAWN);
        m_board.putPiece(p.m_x, p.m_y, p);
        gui.buttons[2][6].setIcon(gui.white_pawn);

        p = new Pawn(Color.WHITE, 3, 6, Type.PAWN);
        m_board.putPiece(p.m_x, p.m_y, p);
        gui.buttons[3][6].setIcon(gui.white_pawn);

        p = new Pawn(Color.WHITE, 4, 6, Type.PAWN);
        m_board.putPiece(p.m_x, p.m_y, p);
        gui.buttons[4][6].setIcon(gui.white_pawn);

        p = new Pawn(Color.WHITE, 5, 6, Type.PAWN);
        m_board.putPiece(p.m_x, p.m_y, p);
        gui.buttons[5][6].setIcon(gui.white_pawn);

        p = new Pawn(Color.WHITE, 6, 6, Type.PAWN);
        m_board.putPiece(p.m_x, p.m_y, p);
        gui.buttons[6][6].setIcon(gui.white_pawn);

        p = new Pawn(Color.WHITE, 7, 6, Type.PAWN);
        m_board.putPiece(p.m_x, p.m_y, p);
        gui.buttons[7][6].setIcon(gui.white_pawn);

        Rook r = new Rook(Color.WHITE, 0, 7, Type.ROOK);
        m_board.putPiece(r.m_x, r.m_y, r);
        gui.buttons[0][7].setIcon(gui.white_rook);

        r = new Rook(Color.WHITE, 7, 7, Type.ROOK);
        m_board.putPiece(r.m_x, r.m_y, r);
        gui.buttons[7][7].setIcon(gui.white_rook);

        Knight n = new Knight(Color.WHITE, 1, 7, Type.KNIGHT);
        m_board.putPiece(n.m_x, n.m_y, n);
        gui.buttons[1][7].setIcon(gui.white_knight);

        n = new Knight(Color.WHITE, 6, 7, Type.KNIGHT);
        m_board.putPiece(n.m_x, n.m_y, n);
        gui.buttons[6][7].setIcon(gui.white_knight);

        Bishop b = new Bishop(Color.WHITE, 2, 7, Type.BISHOP);
        m_board.putPiece(b.m_x, b.m_y, b);
        gui.buttons[2][7].setIcon(gui.white_bishop);

        b = new Bishop(Color.WHITE, 5, 7, Type.BISHOP);
        m_board.putPiece(b.m_x, b.m_y, b);
        gui.buttons[5][7].setIcon(gui.white_bishop);

        Queen q = new Queen(Color.WHITE, 3, 7, Type.QUEEN);
        m_board.putPiece(q.m_x, q.m_y, q);
        gui.buttons[3][7].setIcon(gui.white_queen);

        King k = new King(Color.WHITE, 4, 7, Type.KING);
        m_board.putPiece(k.m_x, k.m_y, k);
        gui.buttons[4][7].setIcon(gui.white_king);
        wk = k; //Sets reference to White King

        p = new Pawn(Color.BLACK, 0, 1, Type.PAWN);
        m_board.putPiece(p.m_x, p.m_y, p);
        gui.buttons[0][1].setIcon(gui.black_pawn);

        p = new Pawn(Color.BLACK, 1, 1, Type.PAWN);
        m_board.putPiece(p.m_x, p.m_y, p);
        gui.buttons[1][1].setIcon(gui.black_pawn);

        p = new Pawn(Color.BLACK, 2, 1, Type.PAWN);
        m_board.putPiece(p.m_x, p.m_y, p);
        gui.buttons[2][1].setIcon(gui.black_pawn);

        p = new Pawn(Color.BLACK, 3, 1, Type.PAWN);
        m_board.putPiece(p.m_x, p.m_y, p);
        gui.buttons[3][1].setIcon(gui.black_pawn);

        p = new Pawn(Color.BLACK, 4, 1, Type.PAWN);
        m_board.putPiece(p.m_x, p.m_y, p);
        gui.buttons[4][1].setIcon(gui.black_pawn);

        p = new Pawn(Color.BLACK, 5, 1, Type.PAWN);
        m_board.putPiece(p.m_x, p.m_y, p);
        gui.buttons[5][1].setIcon(gui.black_pawn);

        p = new Pawn(Color.BLACK, 6, 1, Type.PAWN);
        m_board.putPiece(p.m_x, p.m_y, p);
        gui.buttons[6][1].setIcon(gui.black_pawn);

        p = new Pawn(Color.BLACK, 7, 1, Type.PAWN);
        m_board.putPiece(p.m_x, p.m_y, p);
        gui.buttons[7][1].setIcon(gui.black_pawn);

        r = new Rook(Color.BLACK, 0, 0, Type.ROOK);
        m_board.putPiece(r.m_x, r.m_y, r);
        gui.buttons[0][0].setIcon(gui.black_rook);

        r = new Rook(Color.BLACK, 7, 0, Type.ROOK);
        m_board.putPiece(r.m_x, r.m_y, r);
        gui.buttons[7][0].setIcon(gui.black_rook);

        n = new Knight(Color.BLACK, 1, 0, Type.KNIGHT);
        m_board.putPiece(n.m_x, n.m_y, n);
        gui.buttons[1][0].setIcon(gui.black_knight);

        n = new Knight(Color.BLACK, 6, 0, Type.KNIGHT);
        m_board.putPiece(n.m_x, n.m_y, n);
        gui.buttons[6][0].setIcon(gui.black_knight);

        b = new Bishop(Color.BLACK, 2, 0, Type.BISHOP);
        m_board.putPiece(b.m_x, b.m_y, b);
        gui.buttons[2][0].setIcon(gui.black_bishop);

        b = new Bishop(Color.BLACK, 5, 0, Type.BISHOP);
        m_board.putPiece(b.m_x, b.m_y, b);
        gui.buttons[5][0].setIcon(gui.black_bishop);

        q = new Queen(Color.BLACK, 3, 0, Type.QUEEN);
        m_board.putPiece(q.m_x, q.m_y, q);
        gui.buttons[3][0].setIcon(gui.black_queen);

        k = new King(Color.BLACK, 4, 0, Type.KING);
        m_board.putPiece(k.m_x, k.m_y, k);
        gui.buttons[4][0].setIcon(gui.black_king);
        bk = k; //Sets reference to Black King
    }

    /**Prints out the current state of the board
     */
    public void printBoard()
    {
        for(int i = 0; i < m_board.m_size; i++)
        {
            for(int j = 0; j < m_board.m_size; j++)
            {
                if(m_board.m_pieces[j][i] == null)
                    System.out.print("--");
                else
                {
                    char col = (m_board.m_pieces[j][i].m_color == Color.WHITE) ? 'W' : 'B';
                    System.out.print("" + col + m_board.m_pieces[j][i].m_letterCode);
                }
                System.out.print("|");
            }
            System.out.print("\n");
        }
    }
}
