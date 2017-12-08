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
        return null;
    }

    @Override
    public void move(int x, int y)
    {
        Game g = Game.getInstance();
        g.m_board.movePiece(this.m_pos[0], this.m_pos[1], x, y);
    }

    /**Checks if square (x, y) will put the King in Check
     *
     * @param x - x coordinate of square
     * @param y - y coordinate of square
     * @return - Whether the square (x, y) will put the King in Check
     */
    public boolean isCheck(int x, int y)
    {
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