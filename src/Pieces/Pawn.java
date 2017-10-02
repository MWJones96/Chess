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
        }

        //TODO: Promotion, en Passant

        return moves;
    }
}