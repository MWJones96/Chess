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
        ArrayList<Move> moves = new ArrayList<Move>();

        Move[] m = new Move[]{new Move(2, 1),
                              new Move(2, -1),
                              new Move(1, -2),
                              new Move(-1, -2),
                              new Move(-2, 1),
                              new Move(-2, -1),
                              new Move(1, 2),
                              new Move(-1, 2)};


        return moves;
    }

    public void move(int x, int y)
    {

    }
}