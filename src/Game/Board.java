package Game;

import Pieces.Piece;

public class Board
{
    public int m_size;
    public Piece[][] m_pieces;

    public Board(int size)
    {
        m_size = size;
        m_pieces = new Piece[size][size];
    }

    public void putPiece(int x, int y, Piece p)
    {
        m_pieces[x][y] = p;
    }
}
