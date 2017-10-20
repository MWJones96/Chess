package Pieces;

import Game.Game;
import Game.Move;

import java.awt.*;
import java.util.ArrayList;

public class King extends Piece
{
    public King(Color color, int[] pos, Type type)
    {
        super(color, pos, type, 'K');
    }

    @Override
    public ArrayList<Move> getMoves()
    {
        ArrayList<Move> moves = new ArrayList<Move>();
        Game g = Game.getInstance();

        Move[] m = new Move[]{new Move(1, 0),
                              new Move(1, 1),
                              new Move(0, 1),
                              new Move(-1, 1),
                              new Move(-1, 0),
                              new Move(-1, -1),
                              new Move(0, -1),
                              new Move(1, -1)
        };

        for(Move m1 : m)
        {
            if(m_pos[0] + m1.m_x < 0 || m_pos[0] + m1.m_x > g.m_board.m_size - 1
                    || m_pos[1] + m1.m_y < 0 || m_pos[1] + m1.m_y > g.m_board.m_size - 1)
            {
                continue;
            }

            if(g.m_board.m_pieces[m_pos[0] + m1.m_x][m_pos[1] + m1.m_y] != null)
            {
                if (g.m_board.m_pieces[m_pos[0] + m1.m_x][m_pos[1] + m1.m_y].m_color == m_color)
                {
                    continue;
                }
                else
                {
                    if(!isCheck(m_pos[0] + m1.m_x, m_pos[1] + m1.m_y))
                    {
                        moves.add(new Move(m_pos[0] + m1.m_x, m_pos[1] + m1.m_y));
                    }
                }
            }
            else
            {
                if(!isCheck(m_pos[0] + m1.m_x, m_pos[1] + m1.m_y))
                {
                    moves.add(new Move(m_pos[0] + m1.m_x, m_pos[1] + m1.m_y));
                }
            }

        }

        return moves;
    }

    /**Gets the list of squares that the King can attack. Used to determine if the enemy King is in Check
     *
     * @return - List of attackable squares
     */
    public ArrayList<Move> getAttackableMoves()
    {
        Game g = Game.getInstance();
        ArrayList<Move> moves = new ArrayList<Move>();

        Move[] m = new Move[]{new Move(1, 0),
                new Move(1, 1),
                new Move(0, 1),
                new Move(-1, 1),
                new Move(-1, 0),
                new Move(-1, -1),
                new Move(0, -1),
                new Move(1, -1)
        };

        for(Move m1 : m)
        {
            if(m_pos[0] + m1.m_x < 0 || m_pos[0] + m1.m_x > g.m_board.m_size - 1
                    || m_pos[1] + m1.m_y < 0 || m_pos[1] + m1.m_y > g.m_board.m_size - 1)
            {
                continue;
            }

            moves.add(new Move(m_pos[0] + m1.m_x, m_pos[1] + m1.m_y));
        }

        return moves;
    }

    @Override
    public void move(int x, int y)
    {
        Game g = Game.getInstance();
        g.m_board.putPiece(x, y, this);
    }

    /**Checks if square (x, y) will put the King in Check
     *
     * @param x - x coordinate of square
     * @param y - y coordinate of square
     * @return - Whether the square (x, y) will put the King in Check
     */
    public boolean isCheck(int x, int y)
    {
        Game g = Game.getInstance();

        for(Piece[] p1 : g.m_board.m_pieces)
        {
            for(Piece p : p1)
            {
                if(p == null)
                {
                    continue;
                }
                if(p.m_color == m_color)
                {
                    continue;
                }
                if(p.m_type == Type.PAWN)
                {
                    if(g.containsMove(new Move(x, y), ((Pawn)p).getAttackableMoves()))
                    {
                        return true;
                    }
                }
                else if(p.m_type == Type.KING)
                {
                    if(g.containsMove(new Move(x, y), ((King)p).getAttackableMoves()))
                    {
                        return true;
                    }
                }
                else
                {
                    if(g.containsMove(new Move(x, y), p.getMoves()))
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**Checks if the King is in Stalemate
     *
     * @return - Whether the King is in Stalemate
     */
    public boolean isStalemate()
    {
        return false;
    }

    /**Checks if the King is in Checkmate
     *
     * @return - Whether the King is in Checkmate
     */
    public boolean isCheckmate()
    {
        return false;
    }
}