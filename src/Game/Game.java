package Game;

import Pieces.*;
import Game.Move;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**Represents the state of the game. Implemented as Singleton Pattern as there should only be ONE at once
 *
 */
public class Game
{
    //Singleton game instance
    private static Game instance = null;

    public GUI gui;

    //White == true, black == false
    boolean turn = true;
    //Whether the game is ongoing
    boolean playing = true;

    public Player[] m_players;
    //Gameboard
    public Board m_board;

    volatile int x1 = -1;
    volatile int y1 = -1;
    volatile int x2 = -1;
    volatile int y2 = -1;

    boolean origOrDest = false;

    private Game()
    {
        turn = true;
        m_players = new Player[2];
        m_board = new Board(8);
        gui = new GUI();
    }

    /**Singleton pattern for game instance
     *
     * @return - The game instance
     */
    public static Game getInstance()
    {
        if(instance == null)
        {
            instance = new Game();
        }

        return instance;
    }

    /**Deals with all activities involved in a game of Chess
     *
     */
    public void play()
    {
        while(playing) {
            origOrDest = false;
            x1 = -1;
            x2 = -1;
            y1 = -1;
            y2 = -1;


            String c = (turn) ? "White" : "Black";
            Color color = (turn) ? Color.WHITE : Color.BLACK;

            System.out.println(c + "'s turn");
            printBoard();
            System.out.println("Please select a location: ");

            while(x1 == -1 || y1 == -1)
            {}

            //Validates initial selection
            if(x1 < 0 || y1 < 0 || x1 > m_board.m_size - 1 || y1 > m_board.m_size - 1)
            {
                System.out.println("Index out of bounds");
                continue;
            }

            if(m_board.m_pieces[x1][y1] == null)
            {
                System.out.println("Empty square selected");
                continue;
            }

            if(m_board.m_pieces[x1][y1].m_color != color)
            {
                System.out.println("Selected wrong colour piece");
                continue;
            }

            origOrDest = true;

            Piece p = m_board.m_pieces[x1][y1];
            ArrayList<Move> moves = p.getMoves();

            for(Move legals : moves)
            {
                Color dest = (legals.m_x + legals.m_y) % 2 != 0 ? Color.decode("#228B22"): Color.decode("#ADFF2F");
                gui.buttons[legals.m_x][legals.m_y].setBackground(dest);
            }

            String m = "";

            for(Move move : moves)
            {
                m = m.concat(move.toString());
            }

            System.out.println("Available moves: " + m);
            System.out.println("Please select a destination: ");

            while(x2 == -1 || y2 == -1)
            {}

            //Validates destination
            if(!containsMove(new Move(x2, y2), moves))
            {
                for(Move o : moves)
                {
                    Color cancel = (o.m_x + o.m_y) % 2 != 0 ? Color.BLACK : Color.WHITE;
                    gui.buttons[o.m_x][o.m_y].setBackground(cancel);
                }

                System.out.println("Not a valid move");
                continue;
            }

            m_board.movePiece(x1, y1, x2, y2);
            ImageIcon i = (ImageIcon)gui.buttons[x1][y1].getIcon();
            gui.buttons[x1][y1].setIcon(null);
            gui.buttons[x2][y2].setIcon(i);

            for(Move o : moves)
            {
                Color orig = (o.m_x + o.m_y) % 2 != 0 ? Color.BLACK : Color.WHITE;
                gui.buttons[o.m_x][o.m_y].setBackground(orig);
            }

            turn = !turn;
        }
    }

    public void setOrigin(int x, int y)
    {
        x1 = x;
        y1 = y;
    }

    public void setDest(int x, int y)
    {
        x2 = x;
        y2 = y;
    }

    /**Checks if a given move is valdi for the piece selected
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

    /**Initialises the board to the state of a standard game of chess
     *
     */
    public void initBoard()
    {
        Pawn p = new Pawn(Color.WHITE, new int[]{0, 6}, Type.PAWN);
        m_board.putPiece(p.m_pos[0], p.m_pos[1], p);

        p = new Pawn(Color.WHITE, new int[]{1, 6}, Type.PAWN);
        m_board.putPiece(p.m_pos[0], p.m_pos[1], p);

        p = new Pawn(Color.WHITE, new int[]{2, 6}, Type.PAWN);
        m_board.putPiece(p.m_pos[0], p.m_pos[1], p);

        p = new Pawn(Color.WHITE, new int[]{3, 6}, Type.PAWN);
        m_board.putPiece(p.m_pos[0], p.m_pos[1], p);

        p = new Pawn(Color.WHITE, new int[]{4, 6}, Type.PAWN);
        m_board.putPiece(p.m_pos[0], p.m_pos[1], p);

        p = new Pawn(Color.WHITE, new int[]{5, 6}, Type.PAWN);
        m_board.putPiece(p.m_pos[0], p.m_pos[1], p);

        p = new Pawn(Color.WHITE, new int[]{6, 6}, Type.PAWN);
        m_board.putPiece(p.m_pos[0], p.m_pos[1], p);

        p = new Pawn(Color.WHITE, new int[]{7, 6}, Type.PAWN);
        m_board.putPiece(p.m_pos[0], p.m_pos[1], p);

        Rook r = new Rook(Color.WHITE, new int[]{0, 7}, Type.ROOK);
        m_board.putPiece(r.m_pos[0], r.m_pos[1], r);

        r = new Rook(Color.WHITE, new int[]{7, 7}, Type.ROOK);
        m_board.putPiece(r.m_pos[0], r.m_pos[1], r);

        Knight n = new Knight(Color.WHITE, new int[]{1, 7}, Type.KNIGHT);
        m_board.putPiece(n.m_pos[0], n.m_pos[1], n);

        n = new Knight(Color.WHITE, new int[]{6, 7}, Type.KNIGHT);
        m_board.putPiece(n.m_pos[0], n.m_pos[1], n);

        Bishop b = new Bishop(Color.WHITE, new int[]{2, 7}, Type.BISHOP);
        m_board.putPiece(b.m_pos[0], b.m_pos[1], b);

        b = new Bishop(Color.WHITE, new int[]{5, 7}, Type.BISHOP);
        m_board.putPiece(b.m_pos[0], b.m_pos[1], b);

        Queen q = new Queen(Color.WHITE, new int[]{3, 7}, Type.QUEEN);
        m_board.putPiece(q.m_pos[0], q.m_pos[1], q);

        King k = new King(Color.WHITE, new int[]{4, 7}, Type.KING);
        m_board.putPiece(k.m_pos[0], k.m_pos[1], k);

        p = new Pawn(Color.BLACK, new int[]{0, 1}, Type.PAWN);
        m_board.putPiece(p.m_pos[0], p.m_pos[1], p);

        p = new Pawn(Color.BLACK, new int[]{1, 1}, Type.PAWN);
        m_board.putPiece(p.m_pos[0], p.m_pos[1], p);

        p = new Pawn(Color.BLACK, new int[]{2, 1}, Type.PAWN);
        m_board.putPiece(p.m_pos[0], p.m_pos[1], p);

        p = new Pawn(Color.BLACK, new int[]{3, 1}, Type.PAWN);
        m_board.putPiece(p.m_pos[0], p.m_pos[1], p);

        p = new Pawn(Color.BLACK, new int[]{4, 1}, Type.PAWN);
        m_board.putPiece(p.m_pos[0], p.m_pos[1], p);

        p = new Pawn(Color.BLACK, new int[]{5, 1}, Type.PAWN);
        m_board.putPiece(p.m_pos[0], p.m_pos[1], p);

        p = new Pawn(Color.BLACK, new int[]{6, 1}, Type.PAWN);
        m_board.putPiece(p.m_pos[0], p.m_pos[1], p);

        p = new Pawn(Color.BLACK, new int[]{7, 1}, Type.PAWN);
        m_board.putPiece(p.m_pos[0], p.m_pos[1], p);

        r = new Rook(Color.BLACK, new int[]{0, 0}, Type.ROOK);
        m_board.putPiece(r.m_pos[0], r.m_pos[1], r);

        r = new Rook(Color.BLACK, new int[]{7, 0}, Type.ROOK);
        m_board.putPiece(r.m_pos[0], r.m_pos[1], r);

        n = new Knight(Color.BLACK, new int[]{1, 0}, Type.KNIGHT);
        m_board.putPiece(n.m_pos[0], n.m_pos[1], n);

        n = new Knight(Color.BLACK, new int[]{6, 0}, Type.KNIGHT);
        m_board.putPiece(n.m_pos[0], n.m_pos[1], n);

        b = new Bishop(Color.BLACK, new int[]{2, 0}, Type.BISHOP);
        m_board.putPiece(b.m_pos[0], b.m_pos[1], b);

        b = new Bishop(Color.BLACK, new int[]{5, 0}, Type.BISHOP);
        m_board.putPiece(b.m_pos[0], b.m_pos[1], b);

        q = new Queen(Color.BLACK, new int[]{3, 0}, Type.QUEEN);
        m_board.putPiece(q.m_pos[0], q.m_pos[1], q);

        k = new King(Color.BLACK, new int[]{4, 0}, Type.KING);
        m_board.putPiece(k.m_pos[0], k.m_pos[1], k);
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
