package Pieces;

import Game.Game;
import Game.Move;

import java.awt.*;
import java.util.ArrayList;

public class Pawn extends Piece
{
    public Pawn(Color color, int x, int y, Type type)
    {
        super(color, x, y, type, 'P');
    }

    @Override
    public ArrayList<Move> getMoves()
    {
        ArrayList<Move> moves = new ArrayList<>();
        Game g = Game.getInstance();

        //Starting position of pawn determined by colour
        int startingPos = this.m_color == Color.WHITE ? g.m_board.m_size - 2 : 1;
        //Direction pawn moves up or down the board
        int dir = this.m_color == Color.WHITE ? -1 : 1;

        if(g.m_board.m_pieces[this.m_x][this.m_y + dir] == null)
            moves.add(new Move(this.m_x, this.m_y + dir));

        //Determines if the pawn double move is available
        if(this.m_y == startingPos && g.m_board.m_pieces[this.m_x][this.m_y + dir] == null &&
                g.m_board.m_pieces[this.m_x][this.m_y + 2*dir] == null)
            moves.add(new Move(this.m_x, this.m_y + 2*dir));

        //Capture right
        if(this.m_x + 1 < g.m_board.m_size)
        {
            if(g.m_board.m_pieces[this.m_x + 1][this.m_y + dir] != null)
            {
                if(g.m_board.m_pieces[this.m_x + 1][this.m_y + dir].m_color != this.m_color)
                    moves.add(new Move(this.m_x + 1, this.m_y + dir));
            }
        }

        //Capture left
        if(this.m_x - 1 >= 0)
            if(g.m_board.m_pieces[this.m_x - 1][this.m_y + dir] != null)
            {
                if(g.m_board.m_pieces[this.m_x - 1][this.m_y + dir].m_color != this.m_color)
                    moves.add(new Move(this.m_x - 1, this.m_y + dir));
            }

        return moves;
    }

    @Override
    public void move(int x, int y)
    {
        Game g = Game.getInstance();
        g.m_board.movePiece(this.m_x, this.m_y, x, y);
    }
}