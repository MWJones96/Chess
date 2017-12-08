package Pieces;

import Game.Game;
import Game.Move;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class King extends Piece
{
    public King(Color color, int[] pos, Type type)
    {
        super(color, pos, type, 'K');
    }

    @Override
    public ArrayList<Move> getMoves()
    {
        return new ArrayList<Move>();
    }

    @Override
    public void move(int x, int y)
    {
        Game g = Game.getInstance();
        g.m_board.movePiece(this.m_pos[0], this.m_pos[1], x, y);
    }

    /**Returns whether or not this King is in Check
     *
     * @return - Whether the King is in Check
     */
    public boolean isCheck()
    {
        Game g = Game.getInstance();
        Piece[][] allPieces = g.m_board.m_pieces;
        ArrayList<Piece> enemyPieces = new ArrayList<>();

        for(Piece[] p1 : allPieces)
        {
            for(Piece p2 : p1)
            {
                if(p2 != null)
                {
                    if (p2.m_color != m_color)
                    {
                        enemyPieces.add(p2);
                    }
                }
            }
        }

        for(Piece enemy : enemyPieces)
        {
            if(g.containsMove(new Move(this.m_pos[0], this.m_pos[1]), enemy.getMoves()))
            {
                return true;
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