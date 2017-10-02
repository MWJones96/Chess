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

    public ArrayList<Move> getMoves()
    {
        return null;
    }

    public void move(int x, int y)
    {}
}
