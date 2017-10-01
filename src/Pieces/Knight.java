package Pieces;

import Game.Game;
import Game.Move;

import java.awt.*;
import java.util.ArrayList;

public class Knight extends Piece
{
    public Knight(Color color, int[] pos, Type type)
    {
        super(color, pos, type, 'N');
    }

    public ArrayList<Move> getMoves()
    {
        return null;
    }
}