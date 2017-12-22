package Pieces;

import Game.Game;
import Game.Move;

import java.awt.*;
import java.util.ArrayList;

public class King extends Piece
{
    public King(Color color, int x, int y, Type type)
    {
        super(color, x, y, type, 'K');
    }

    @Override
    public ArrayList<Move> getMoves()
    {
        ArrayList<Move> moves = new ArrayList<>();
        Game g = Game.getInstance();

        Move[] vectors = new Move[]{new Move(0, -1),
                new Move(1, -1),
                new Move(1, 0),
                new Move(1, 1),
                new Move(0, 1),
                new Move(-1, 1),
                new Move(-1, 0),
                new Move(-1, -1)
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

        //Updates reference to the King
        if(this.m_color == Color.WHITE){g.wk = this;}else{g.bk = this;}
    }

    /**Returns whether or not this King is in Check
     *
     * @return - Whether the King is in Check
     */
    public boolean isCheck()
    {
        Game g = Game.getInstance();

        for(Piece[] p1 : g.m_board.m_pieces)
        {
            for(Piece p2 : p1)
            {
                if(p2 == null){ continue; }
                if(g.containsMove(new Move(m_x, m_y), p2.getMoves()))
                    return true;
            }
        }

        return false;
    }

    /**Checks if the King is in Checkmate
     *
     * @return - Whether the King is in Checkmate
     */
    public boolean isCheckmate()
    {
       Game g = Game.getInstance();

       int sum = 0;

       for(Piece[] p1 : g.m_board.m_pieces)
       {
           for(Piece p2 : p1)
           {
               if(p2 != null)
               {
                   if(p2.m_color == this.m_color)
                   {
                       sum += p2.getLegalMoves().size();
                   }
               }
           }
       }

       if(sum == 0){ return true; } else { return false; }
    }
}