package Pieces;

import Game.Game;
import Game.Move;

import java.awt.*;
import java.util.ArrayList;

public class Bishop extends Piece
{
    public Bishop(Color color, int x, int y, Type type)
    {
        super(color, x, y, type, 'B');
    }

    @Override
    public ArrayList<Move> getMoves()
    {
        ArrayList<Move> moves = new ArrayList<>();
        Game g = Game.getInstance();

        Move[] vectors = new Move[]{new Move(1, 1),
                                    new Move(-1, -1),
                                    new Move(1, -1),
                                    new Move(-1, 1)
        };

        for(Move m : vectors)
        {
            int x = this.m_x;
            int y = this.m_y;
            while(true)
            {
                x += m.m_x;
                y += m.m_y;

                if(x < 0 || x > g.m_board.m_size - 1 || y < 0 || y > g.m_board.m_size - 1)
                    break;

                if (g.m_board.m_pieces[x][y] == null)
                    moves.add(new Move(x, y));
                else if(g.m_board.m_pieces[x][y].m_color == this.m_color)
                    break;
                else
                    {
                    moves.add(new Move(x, y));
                    break;
                }

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