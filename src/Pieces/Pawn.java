package Pieces;

import Game.Game;
import Game.Move;

import java.awt.*;
import java.util.ArrayList;

public class Pawn extends Piece
{
    public Pawn(Color color, int[] pos, Type type)
    {
        super(color, pos, type, 'P');
    }

    public ArrayList<Move> getMoves()
    {
        ArrayList<Move> moves = new ArrayList<Move>();
        Game g = Game.getInstance();

        if(m_color == Color.WHITE)
        {
            if (g.m_board.m_pieces[this.m_pos[0]][this.m_pos[1] - 1] == null)
            {
                moves.add(new Move(this.m_pos[0], this.m_pos[1] - 1));
            }

            if(this.m_pos[1] == 6)
            {
                if (g.m_board.m_pieces[this.m_pos[0]][this.m_pos[1] - 1] == null &&
                        g.m_board.m_pieces[this.m_pos[0]][this.m_pos[1] - 2] == null) {
                    moves.add(new Move(this.m_pos[0], this.m_pos[1] - 2));
                }
            }

            if(this.m_pos[0] > 0)
            {
                if (g.m_board.m_pieces[this.m_pos[0] - 1][this.m_pos[1] - 1].m_color == Color.BLACK) {
                    moves.add(new Move(this.m_pos[0] - 1, this.m_pos[1] - 1));
                }
            }

            if(this.m_pos[0] < g.m_board.m_size - 1)

                if (g.m_board.m_pieces[this.m_pos[0] + 1][this.m_pos[1] - 1].m_color == Color.BLACK) {
                    moves.add(new Move(this.m_pos[0] + 1, this.m_pos[1] - 1));
                }
        }
        else if(m_color == Color.BLACK)
        {

        }

        //TODO: Promotion, en Passant

        return moves;
    }
}