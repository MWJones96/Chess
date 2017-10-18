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
    public int[] m_pos = new int[2];    //Position of the piece
    public Type m_type; //Type of the piece
    public char m_letterCode;   //Letter code of the piece to print

    Piece(Color color, int[] pos, Type type, char letterCode)
    {
        m_color = color;
        m_pos = pos;
        m_type = type;
        m_letterCode = letterCode;
    }

    /**Gets a list of legal moves that the piece can move to
     *
     * @return - List of legal moves
     */
    public abstract ArrayList<Move> getMoves();

    /**Moves the piece to point (x, y)
     *
     * @param x - x coordinate of destination
     * @param y - y coordinate of destination
     */
    public abstract void move(int x, int y);
}
