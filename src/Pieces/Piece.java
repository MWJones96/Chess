package Pieces;

import Game.Move;

import java.awt.*;
import java.util.ArrayList;

public abstract class Piece
{
    public Color m_color;
    public int[] m_pos = new int[2];
    public Type m_type;
    public char m_letterCode;

    Piece(Color color, int[] pos, Type type, char letterCode)
    {
        m_color = color;
        m_pos = pos;
        m_type = type;
        m_letterCode = letterCode;
    }

    public abstract ArrayList<Move> getMoves();
}
