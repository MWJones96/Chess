package Game;

import Pieces.Piece;

import javax.swing.*;

/**Represents the board of the game. Stores current state of board
 */
public class Board
{
    public int m_size;  //Size of board e.g. 8x8
    public Piece[][] m_pieces;  //Array of pieces (null for empty square)

    /**Initialises board of given size
     *
     * @param size - size of board
     */
    public Board(int size)
    {
        m_size = size;
        m_pieces = new Piece[size][size];
    }

    /**Moves the piece at (x1,y1) to (x2,y2)
     *
     * @param x1 - x co-ord of piece to move
     * @param y1 - y co-ord of piece to move
     * @param x2 - x co-ord of destination
     * @param y2 - y co-ord of destination
     */
    public void movePiece(int x1, int y1, int x2, int y2)
    {
        Game g = Game.getInstance();
        Piece p = m_pieces[x1][y1];

        m_pieces[x1][y1] = null;
        p.m_pos = new int[]{x2, y2};
        m_pieces[x2][y2] = p;

        g.gui.buttons[x2][y2].setIcon(g.gui.buttons[x1][y1].getIcon());
        g.gui.buttons[x1][y1].setIcon(null);
    }

    /**Puts a piece on the board. Called during game setup.
     *
     * @param x
     * @param y
     * @param p
     */
    public void putPiece(int x, int y, Piece p)
    {
        Game g = Game.getInstance();
        p.m_pos = new int[]{x, y};
        m_pieces[x][y] = p;

        g.gui.buttons[x][y].setIcon(g.gui.buttons[x][y].getIcon());
    }

    /**
     *
     * @param x
     * @param y
     */
    public void removePiece(int x, int y)
    {
        Game g = Game.getInstance();
        m_pieces[x][y] = null;
        g.gui.buttons[x][y].setIcon(null);
    }
}
