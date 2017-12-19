package Pieces;

import Game.Move;

import java.awt.*;
import java.util.ArrayList;

/**Class representing a generic piece. Cannot be instantiated on its own, only subclasses
 *
 */
public abstract class Piece
{
    public Color m_color;   //Color of the piece

    public int m_x;   //X coord of Piece
    public int m_y;   //Y coord of piece

    public Type m_type; //Type of the piece
    public char m_letterCode;   //Letter code of the piece to print

    Piece(Color color, int x, int y, Type type, char letterCode)
    {
        m_color = color;
        m_x = x;
        m_y = y;
        m_type = type;
        m_letterCode = letterCode;
    }

    /**Gets a list of theoretical moves that a piece could move to
     *
     * @return - List of theoretical moves
     */
    public abstract ArrayList<Move> getMoves();

    /**Filters the getMoves() to rule out any move that can put the
     * Piece's King in Check
     *
     * @return - List of legal moves
     */
    public abstract ArrayList<Move> getLegalMoves();

    /**Updates the game board to simulate a Piece moving to (x,y)
     *
     * @param x - x coordinate of destination
     * @param y - y coordinate of destination
     */
    public abstract void move(int x, int y);
}
