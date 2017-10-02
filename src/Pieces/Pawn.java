package Pieces;

import Game.Game;
import Game.Move;

import java.awt.*;
import java.util.ArrayList;

public class Pawn extends Piece
{
    public Pawn(Color color, int[] pos, Type type)
    {
        super(color, pos, type, 'P');
    }

    public ArrayList<Move> getMoves()
    {
        ArrayList<Move> moves = new ArrayList<Move>();
        Game g = Game.getInstance();

        int x = m_pos[0];
        int y = m_pos[1];

        if(m_color == Color.WHITE)
        {
            if(g.m_board.m_pieces[x][y - 1] == null)
            {
                moves.add(new Move(x, y - 1));
                if(y == g.m_board.m_size - 2 && g.m_board.m_pieces[x][y - 2] == null)
                {
                    moves.add(new Move(x, y - 2));
                }
            }

            //Capturing left
            if(x > 0 && y > 0 && g.m_board.m_pieces[x - 1][y - 1] != null)
            {
                if (g.m_board.m_pieces[x - 1][y - 1].m_color == Color.BLACK) {
                    moves.add(new Move(x - 1, y - 1));
                }
            }

            //Capturing right
            if(x < g.m_board.m_size - 1 && y > 0 && g.m_board.m_pieces[x + 1][y - 1] != null)
            {
                if (g.m_board.m_pieces[x + 1][y - 1].m_color == Color.BLACK) {
                    moves.add(new Move(x + 1, y - 1));
                }
            }

        }
        else if(m_color == Color.BLACK)
        {
            if(g.m_board.m_pieces[x][y + 1] == null)
            {
                moves.add(new Move(x, y + 1));
                if(y == 1 && g.m_board.m_pieces[x][y + 2] == null)
                {
                    moves.add(new Move(x, y + 2));
                }
            }

            //Capturing left
            if(x > 0 && y < g.m_board.m_size - 1 && g.m_board.m_pieces[x - 1][y + 1] != null)
            {
                if (g.m_board.m_pieces[x - 1][y + 1].m_color == Color.WHITE) {
                    moves.add(new Move(x - 1, y + 1));
                }
            }

            //Capturing right
            if(x < g.m_board.m_size - 1 && y < g.m_board.m_size - 1 && g.m_board.m_pieces[x + 1][y + 1] != null)
            {
                if (g.m_board.m_pieces[x + 1][y + 1].m_color == Color.WHITE) {
                    moves.add(new Move(x + 1, y + 1));
                }
            }
        }

        //TODO: en Passant

        return moves;
    }

    public void move(int x, int y)
    {
        Game g = Game.getInstance();
        if(m_color == Color.WHITE)
        {
            g.m_board.putPiece(x, y, this);

            if(y == 0)
            {
                g.m_board.putPiece(x, y, new Queen(Color.WHITE, new int[]{x, y}, Type.QUEEN));
            }
        }
        else if(m_color == Color.BLACK)
        {
            g.m_board.putPiece(x, y, this);

            if(y == g.m_board.m_size - 1)
            {
                g.m_board.putPiece(x, y, new Queen(Color.BLACK, new int[]{x, y}, Type.QUEEN));
            }
        }
    }
}