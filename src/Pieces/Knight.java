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

    @Override
    public ArrayList<Move> getMoves()
    {
        ArrayList<Move> moves = new ArrayList<Move>();

        Game g = Game.getInstance();

        int x = m_pos[0];
        int y = m_pos[1];

        Move[] m = new Move[]{new Move(2, 1),
                              new Move(2, -1),
                              new Move(1, -2),
                              new Move(-1, -2),
                              new Move(-2, 1),
                              new Move(-2, -1),
                              new Move(1, 2),
                              new Move(-1, 2)};

        if(m_color == Color.WHITE) {
            for (int i = 0; i < m.length; i++) {
                if (x + m[i].m_x < 0 || x + m[i].m_x > g.m_board.m_size - 1 ||
                        y + m[i].m_y < 0 || y + m[i].m_y > g.m_board.m_size - 1) {
                    continue;
                }

                if (g.m_board.m_pieces[x + m[i].m_x][y + m[i].m_y] != null) {
                    if (g.m_board.m_pieces[x + m[i].m_x][y + m[i].m_y].m_color == Color.BLACK) {
                        moves.add(new Move(x + m[i].m_x, y + m[i].m_y));
                        continue;
                    }
                } else {
                    moves.add(new Move(x + m[i].m_x, y + m[i].m_y));
                }
            }
        }
        else if(m_color == Color.BLACK)
        {
            for (int i = 0; i < m.length; i++) {
                if (x + m[i].m_x < 0 || x + m[i].m_x > g.m_board.m_size - 1 ||
                        y + m[i].m_y < 0 || y + m[i].m_y > g.m_board.m_size - 1) {
                    continue;
                }

                if (g.m_board.m_pieces[x + m[i].m_x][y + m[i].m_y] != null) {
                    if (g.m_board.m_pieces[x + m[i].m_x][y + m[i].m_y].m_color == Color.WHITE) {
                        moves.add(new Move(x + m[i].m_x, y + m[i].m_y));
                        continue;
                    }
                } else {
                    moves.add(new Move(x + m[i].m_x, y + m[i].m_y));
                }
            }
        }

        return moves;
    }

    @Override
    public void move(int x, int y)
    {
        Game g = Game.getInstance();
        g.m_board.movePiece(this.m_pos[0], this.m_pos[1], x, y);
    }
}