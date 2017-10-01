package Game;

import Pieces.*;

import java.awt.*;
import java.util.Scanner;

public class Game
{
    //Singleton game instance
    private static Game instance = null;

    //White == true, black == false
    boolean turn = true;
    boolean playing = true;

    public Player[] m_players;
    public Board m_board;

    private Game()
    {
        turn = true;
        m_players = new Player[2];
        m_board = new Board(8);
    }

    public static Game getInstance()
    {
        if(instance == null)
        {
            instance = new Game();
        }

        return instance;
    }

    public void play()
    {
        Scanner s = new Scanner(System.in);
        while(playing)
        {
            printBoard();
            String t = (turn) ? "White" : "Black";
            System.out.println(t + "'s turn. Select an index: ");

            int x = s.nextInt();
            int y = s.nextInt();

            Color c = (turn) ? Color.WHITE : Color.BLACK;

            if(m_board.m_pieces[x][y] == null || m_board.m_pieces[x][y].m_color != c || x >= m_board.m_size || y >= m_board.m_size)
            {
                System.out.println("Invalid selection");
                continue;
            }

            System.out.println("Select a destination: ");

            int x2 = s.nextInt();
            int y2 = s.nextInt();

            Piece p = m_board.m_pieces[x][y];
            m_board.m_pieces[x][y] = null;

            m_board.m_pieces[x2][y2] = p;

            turn = !turn;
        }
    }

    public void initBoard()
    {
        Pawn p = new Pawn(Color.WHITE, new int[]{0, 6}, Type.PAWN);
        m_board.putPiece(p.m_pos[0], p.m_pos[1], p);

        p = new Pawn(Color.WHITE, new int[]{1, 6}, Type.PAWN);
        m_board.putPiece(p.m_pos[0], p.m_pos[1], p);

        p = new Pawn(Color.WHITE, new int[]{2, 6}, Type.PAWN);
        m_board.putPiece(p.m_pos[0], p.m_pos[1], p);

        p = new Pawn(Color.WHITE, new int[]{3, 6}, Type.PAWN);
        m_board.putPiece(p.m_pos[0], p.m_pos[1], p);

        p = new Pawn(Color.WHITE, new int[]{4, 6}, Type.PAWN);
        m_board.putPiece(p.m_pos[0], p.m_pos[1], p);

        p = new Pawn(Color.WHITE, new int[]{5, 6}, Type.PAWN);
        m_board.putPiece(p.m_pos[0], p.m_pos[1], p);

        p = new Pawn(Color.WHITE, new int[]{6, 6}, Type.PAWN);
        m_board.putPiece(p.m_pos[0], p.m_pos[1], p);

        p = new Pawn(Color.WHITE, new int[]{7, 6}, Type.PAWN);
        m_board.putPiece(p.m_pos[0], p.m_pos[1], p);

        Rook r = new Rook(Color.WHITE, new int[]{0, 7}, Type.ROOK);
        m_board.putPiece(r.m_pos[0], r.m_pos[1], r);

        r = new Rook(Color.WHITE, new int[]{7, 7}, Type.ROOK);
        m_board.putPiece(r.m_pos[0], r.m_pos[1], r);

        Knight n = new Knight(Color.WHITE, new int[]{1, 7}, Type.KNIGHT);
        m_board.putPiece(n.m_pos[0], n.m_pos[1], n);

        n = new Knight(Color.WHITE, new int[]{6, 7}, Type.KNIGHT);
        m_board.putPiece(n.m_pos[0], n.m_pos[1], n);

        Bishop b = new Bishop(Color.WHITE, new int[]{2, 7}, Type.BISHOP);
        m_board.putPiece(b.m_pos[0], b.m_pos[1], b);

        b = new Bishop(Color.WHITE, new int[]{5, 7}, Type.BISHOP);
        m_board.putPiece(b.m_pos[0], b.m_pos[1], b);

        Queen q = new Queen(Color.WHITE, new int[]{3, 7}, Type.QUEEN);
        m_board.putPiece(q.m_pos[0], q.m_pos[1], q);

        King k = new King(Color.WHITE, new int[]{4, 7}, Type.KING);
        m_board.putPiece(k.m_pos[0], k.m_pos[1], k);

        p = new Pawn(Color.BLACK, new int[]{0, 1}, Type.PAWN);
        m_board.putPiece(p.m_pos[0], p.m_pos[1], p);

        p = new Pawn(Color.BLACK, new int[]{1, 1}, Type.PAWN);
        m_board.putPiece(p.m_pos[0], p.m_pos[1], p);

        p = new Pawn(Color.BLACK, new int[]{2, 1}, Type.PAWN);
        m_board.putPiece(p.m_pos[0], p.m_pos[1], p);

        p = new Pawn(Color.BLACK, new int[]{3, 1}, Type.PAWN);
        m_board.putPiece(p.m_pos[0], p.m_pos[1], p);

        p = new Pawn(Color.BLACK, new int[]{4, 1}, Type.PAWN);
        m_board.putPiece(p.m_pos[0], p.m_pos[1], p);

        p = new Pawn(Color.BLACK, new int[]{5, 1}, Type.PAWN);
        m_board.putPiece(p.m_pos[0], p.m_pos[1], p);

        p = new Pawn(Color.BLACK, new int[]{6, 1}, Type.PAWN);
        m_board.putPiece(p.m_pos[0], p.m_pos[1], p);

        p = new Pawn(Color.BLACK, new int[]{7, 1}, Type.PAWN);
        m_board.putPiece(p.m_pos[0], p.m_pos[1], p);

        r = new Rook(Color.BLACK, new int[]{0, 0}, Type.ROOK);
        m_board.putPiece(r.m_pos[0], r.m_pos[1], r);

        r = new Rook(Color.BLACK, new int[]{7, 0}, Type.ROOK);
        m_board.putPiece(r.m_pos[0], r.m_pos[1], r);

        n = new Knight(Color.BLACK, new int[]{1, 0}, Type.KNIGHT);
        m_board.putPiece(n.m_pos[0], n.m_pos[1], n);

        n = new Knight(Color.BLACK, new int[]{6, 0}, Type.KNIGHT);
        m_board.putPiece(n.m_pos[0], n.m_pos[1], n);

        b = new Bishop(Color.BLACK, new int[]{2, 0}, Type.BISHOP);
        m_board.putPiece(b.m_pos[0], b.m_pos[1], b);

        b = new Bishop(Color.BLACK, new int[]{5, 0}, Type.BISHOP);
        m_board.putPiece(b.m_pos[0], b.m_pos[1], b);

        q = new Queen(Color.BLACK, new int[]{3, 0}, Type.QUEEN);
        m_board.putPiece(q.m_pos[0], q.m_pos[1], q);

        k = new King(Color.BLACK, new int[]{4, 0}, Type.KING);
        m_board.putPiece(k.m_pos[0], k.m_pos[1], k);
    }

    public void printBoard()
    {
        for(int i = 0; i < m_board.m_size; i++)
        {
            for(int j = 0; j < m_board.m_size; j++)
            {
                if(m_board.m_pieces[j][i] == null)
                    System.out.print("--");
                else
                {
                    char col = (m_board.m_pieces[j][i].m_color == Color.WHITE) ? 'W' : 'B';
                    System.out.print("" + col + m_board.m_pieces[j][i].m_letterCode);
                }
                System.out.print("|");
            }
            System.out.print("\n");
        }
    }
}
