package Pieces;

import Game.Game;
import Game.Move;

import java.awt.*;
import java.util.ArrayList;

public class Knight extends Piece
{
    public Knight(Color color, int x, int y, Type type)
    {
        super(color, x, y, type, 'N');
    }

    @Override
    public ArrayList<Move> getMoves()
    {
        ArrayList<Move> moves = new ArrayList<>();
        Game g = Game.getInstance();

        Move[] vectors = new Move[]{new Move(1, -2),
                new Move(-1, -2),
                new Move(2, -1),
                new Move(2, 1),
                new Move(-1, 2),
                new Move(1, 2),
                new Move(-2, -1),
                new Move(-2, 1)
        };

        for(Move m : vectors)
        {
            if(this.m_x + m.m_x < 0|| this.m_x + m.m_x > g.m_board.m_size - 1
                    || this.m_y + m.m_y < 0 || this.m_y + m.m_y > g.m_board.m_size - 1)
            {
                //Out of range, just continue
                continue;
            }

            if(g.m_board.m_pieces[this.m_x + m.m_x][this.m_y + m.m_y] == null)
            {
                moves.add(new Move(this.m_x + m.m_x, this.m_y + m.m_y));
            }
            else if(g.m_board.m_pieces[this.m_x + m.m_x][this.m_y + m.m_y].m_color != this.m_color)
            {
                moves.add(new Move(this.m_x + m.m_x, this.m_y + m.m_y));
            }
        }

        return moves;
    }

    @Override
    public void move(int x, int y)
    {
        Game g = Game.getInstance();
        g.m_board.movePiece(this.m_x, this.m_y, x, y);
    }
}