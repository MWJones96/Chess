package Pieces;

import Game.Game;
import Game.Move;

import java.awt.*;
import java.util.ArrayList;

public class Queen extends Piece
{
    public Queen(Color color, int[] pos, Type type)
    {
        super(color, pos, type, 'Q');
    }

    public ArrayList<Move> getMoves()
    {
        return null;
    }
}