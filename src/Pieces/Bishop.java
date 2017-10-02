package Pieces;

import Game.Game;
import Game.Move;

import java.awt.*;
import java.util.ArrayList;

public class Bishop extends Piece
{
    public Bishop(Color color, int[] pos, Type type)
    {
        super(color, pos, type, 'B');
    }

    public ArrayList<Move> getMoves()
    {
        return null;
    }

    public void move(int x, int y)
    {}
}