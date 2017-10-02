package Pieces;

import Game.Game;
import Game.Move;

import java.awt.*;
import java.util.ArrayList;

public class Rook extends Piece
{
    public Rook(Color color, int[] pos, Type type)
    {
        super(color, pos, type, 'R');
    }

    public ArrayList<Move> getMoves()
    {
        return null;
    }

    public void move(int x, int y)
    {

    }
}