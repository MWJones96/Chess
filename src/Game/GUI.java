package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;

public class GUI
{
    public JFrame frame;

    public ChessButton[][] buttons;

    public GUI()
    {
        frame = new JFrame("Chess");

        buttons = new ChessButton[8][8];

        frame.setLayout(new GridLayout(8, 8));

        frame.setSize(640, 640);
        frame.setMinimumSize(new Dimension(640, 640));
        frame.setMaximumSize(new Dimension(640, 640));
        frame.setPreferredSize(new Dimension(640, 640));
        frame.setResizable(false);

        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                buttons[j][i] = new ChessButton(j, i);
                if((i + j) % 2 != 0)
                    buttons[j][i].setBackground(Color.BLACK);
                else
                    buttons[j][i].setBackground(Color.WHITE);
                buttons[j][i].setOpaque(true);
                buttons[j][i].setBorderPainted(false);
                buttons[j][i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        ChessButton b = (ChessButton) e.getSource();
                        System.out.println("Button pressed: " + Integer.toString(b.m_x) + ", " + Integer.toString(b.m_y));
                        Game g = Game.getInstance();
                        if(!g.origOrDest)
                            g.setOrigin(b.m_x, b.m_y);
                        else
                            g.setDest(b.m_x, b.m_y);
                    }
                });
                frame.add(buttons[j][i]);
            }

        }

        ImageIcon black_rook = new ImageIcon("res/black_rook.png");
        ImageIcon black_knight = new ImageIcon("res/black_knight.png");
        ImageIcon black_bishop = new ImageIcon("res/black_bishop.png");
        ImageIcon black_queen = new ImageIcon("res/black_queen.png");
        ImageIcon black_king = new ImageIcon("res/black_king.png");
        ImageIcon black_pawn = new ImageIcon("res/black_pawn.png");

        ImageIcon white_rook = new ImageIcon("res/white_rook.png");
        ImageIcon white_knight = new ImageIcon("res/white_knight.png");
        ImageIcon white_bishop = new ImageIcon("res/white_bishop.png");
        ImageIcon white_queen = new ImageIcon("res/white_queen.png");
        ImageIcon white_king = new ImageIcon("res/white_king.png");
        ImageIcon white_pawn = new ImageIcon("res/white_pawn.png");

        buttons[0][0].setIcon(black_rook);
        buttons[1][0].setIcon(black_knight);
        buttons[2][0].setIcon(black_bishop);
        buttons[3][0].setIcon(black_queen);
        buttons[4][0].setIcon(black_king);
        buttons[5][0].setIcon(black_bishop);
        buttons[6][0].setIcon(black_knight);
        buttons[7][0].setIcon(black_rook);

        buttons[0][1].setIcon(black_pawn);
        buttons[1][1].setIcon(black_pawn);
        buttons[2][1].setIcon(black_pawn);
        buttons[3][1].setIcon(black_pawn);
        buttons[4][1].setIcon(black_pawn);
        buttons[5][1].setIcon(black_pawn);
        buttons[6][1].setIcon(black_pawn);
        buttons[7][1].setIcon(black_pawn);

        buttons[0][7].setIcon(white_rook);
        buttons[1][7].setIcon(white_knight);
        buttons[2][7].setIcon(white_bishop);
        buttons[3][7].setIcon(white_queen);
        buttons[4][7].setIcon(white_king);
        buttons[5][7].setIcon(white_bishop);
        buttons[6][7].setIcon(white_knight);
        buttons[7][7].setIcon(white_rook);

        buttons[0][6].setIcon(white_pawn);
        buttons[1][6].setIcon(white_pawn);
        buttons[2][6].setIcon(white_pawn);
        buttons[3][6].setIcon(white_pawn);
        buttons[4][6].setIcon(white_pawn);
        buttons[5][6].setIcon(white_pawn);
        buttons[6][6].setIcon(white_pawn);
        buttons[7][6].setIcon(white_pawn);

        frame.setLocationRelativeTo(null);
        frame.setIconImage(new ImageIcon("/res/white_king.png").getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
