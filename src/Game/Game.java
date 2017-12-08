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

import static sun.misc.Version.print;

/**Represents the state of the game. Implemented as Singleton Pattern as there should only be ONE at once
 *
 */
public class Game
{
    //Singleton game instance
    private static Game instance = null;

    public GUI gui;

    //The current turn (starts white)
    Color turn = Color.WHITE;

    //True = select piece, false = select destination
    boolean pickup = true;

    //Whether the game is ongoing
    boolean playing = true;

    //Gameboard
    public Board m_board;

    public Piece selectedPiece = null;

    private Game()
    {
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
    public void processButtonPress(int x, int y)
    {
        if(turn == Color.WHITE)    //White
        {
            if(pickup)
            {
                if(m_board.m_pieces[x][y] != null)
                {
                    if(m_board.m_pieces[x][y].m_color != turn)
                    {
                        System.out.println("Invalid piece selection");
                        return;
                    }
                    else
                    {
                        //Valid selection
                        if(m_board.m_pieces[x][y].getMoves().size() == 0)
                        {
                            System.out.println("Piece cannot move");
                            return;
                        }

                        System.out.println("Available moves: " + m_board.m_pieces[x][y].getMoves());
                        selectedPiece = m_board.m_pieces[x][y];

                        //Paints legal moves on the board
                        for(Move m : selectedPiece.getMoves())
                        {
                            Color s = (m.m_x + m.m_y) % 2 != 0 ? Color.decode("#228B22"): Color.decode("#ADFF2F");
                            gui.buttons[m.m_x][m.m_y].setBackground(s);
                        }

                        pickup = !pickup;
                        return;
                    }
                }
                else
                {
                    System.out.println("Invalid selection");
                    return;
                }
            }
            else
            {
                //Put down
                if(!containsMove(new Move(x, y), selectedPiece.getMoves()))
                {
                    System.out.println("Cannot move there");

                    for(Move m : selectedPiece.getMoves())
                    {
                        Color s = (m.m_x + m.m_y) % 2 != 0 ? Color.BLACK: Color.WHITE;
                        gui.buttons[m.m_x][m.m_y].setBackground(s);
                    }

                    selectedPiece = null;

                    pickup = !pickup;
                    return;
                }
                else
                {
                    for(Move m : selectedPiece.getMoves())
                    {
                        Color s = (m.m_x + m.m_y) % 2 != 0 ? Color.BLACK: Color.WHITE;
                        gui.buttons[m.m_x][m.m_y].setBackground(s);
                    }

                    m_board.movePiece(selectedPiece.m_pos[0], selectedPiece.m_pos[1], x, y);
                    pickup = !pickup;

                    selectedPiece = null;
                    turn = Color.BLACK;
                    return;
                }
            }
        }
        else
        {
            if(pickup)
            {
                if(m_board.m_pieces[x][y] != null)
                {
                    if(m_board.m_pieces[x][y].m_color != turn)
                    {
                        System.out.println("Invalid piece selection");
                        return;
                    }
                    else
                    {
                        //Valid selection

                        if(m_board.m_pieces[x][y].getMoves().size() == 0)
                        {
                            System.out.println("Piece cannot move");
                            return;
                        }

                        System.out.println("Available moves: " + m_board.m_pieces[x][y].getMoves());
                        selectedPiece = m_board.m_pieces[x][y];

                        //Paints legal moves on the board
                        for(Move m : selectedPiece.getMoves())
                        {
                            Color s = (m.m_x + m.m_y) % 2 != 0 ? Color.decode("#228B22"): Color.decode("#ADFF2F");
                            gui.buttons[m.m_x][m.m_y].setBackground(s);
                        }

                        pickup = !pickup;
                        return;
                    }
                }
                else
                {
                    System.out.println("Invalid selection");
                    return;
                }
            }
            else
            {
                //Put down
                if(!containsMove(new Move(x, y), selectedPiece.getMoves()))
                {
                    System.out.println("Cannot move there");

                    for(Move m : selectedPiece.getMoves())
                    {
                        Color s = (m.m_x + m.m_y) % 2 != 0 ? Color.BLACK: Color.WHITE;
                        gui.buttons[m.m_x][m.m_y].setBackground(s);
                    }

                    selectedPiece = null;
                    pickup = !pickup;
                    return;
                }
                else
                {
                    for(Move m : selectedPiece.getMoves())
                    {
                        Color s = (m.m_x + m.m_y) % 2 != 0 ? Color.BLACK: Color.WHITE;
                        gui.buttons[m.m_x][m.m_y].setBackground(s);
                    }

                    m_board.movePiece(selectedPiece.m_pos[0], selectedPiece.m_pos[1], x, y);
                    pickup = !pickup;
                    selectedPiece = null;
                    turn = Color.WHITE;
                    return;
                }
            }
        }
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
