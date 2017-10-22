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

    @Override
    public ArrayList<Move> getMoves()
    {
        ArrayList<Move> moves = new ArrayList<Move>();

        Game g = Game.getInstance();

        Move[] grads = new Move[]{new Move(1, -1),
                                  new Move(1, 1),
                                  new Move(-1, 1),
                                  new Move(-1, -1)};

        if(m_color == Color.WHITE) {
            for (int i = 0; i < 4; i++) {
                int x = m_pos[0];
                int y = m_pos[1];

                while (true) {
                    x += grads[i].m_x;
                    y += grads[i].m_y;

                    if(x < 0 || x > g.m_board.m_size - 1 || y < 0 || y > g.m_board.m_size - 1)
                        break;

                    if (g.m_board.m_pieces[x][y] == null) {
                        moves.add(new Move(x, y));
                    } else {
                        if (g.m_board.m_pieces[x][y].m_color == Color.BLACK)
                        {
                            moves.add(new Move(x, y));
                            break;
                        }
                        else if(g.m_board.m_pieces[x][y].m_color == Color.WHITE)
                        {
                            break;
                        }
                    }

                }
            }
        }
        else if(m_color == Color.BLACK)
        {
            for (int i = 0; i < 4; i++) {
                int x = m_pos[0];
                int y = m_pos[1];

                while (true) {
                    x += grads[i].m_x;
                    y += grads[i].m_y;

                    if(x < 0 || x > g.m_board.m_size - 1 || y < 0 || y > g.m_board.m_size - 1)
                        break;

                    if (g.m_board.m_pieces[x][y] == null) {
                        moves.add(new Move(x, y));
                    } else {
                        if (g.m_board.m_pieces[x][y].m_color == Color.WHITE)
                        {
                            moves.add(new Move(x, y));
                            break;
                        }
                        else if(g.m_board.m_pieces[x][y].m_color == Color.BLACK)
                        {
                            break;
                        }
                    }

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