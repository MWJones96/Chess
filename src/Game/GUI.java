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

    public ImageIcon black_rook = new ImageIcon("res/black_rook.png");
    public ImageIcon black_knight = new ImageIcon("res/black_knight.png");
    public ImageIcon black_bishop = new ImageIcon("res/black_bishop.png");
    public ImageIcon black_queen = new ImageIcon("res/black_queen.png");
    public ImageIcon black_king = new ImageIcon("res/black_king.png");
    public ImageIcon black_pawn = new ImageIcon("res/black_pawn.png");

    public ImageIcon white_rook = new ImageIcon("res/white_rook.png");
    public ImageIcon white_knight = new ImageIcon("res/white_knight.png");
    public ImageIcon white_bishop = new ImageIcon("res/white_bishop.png");
    public ImageIcon white_queen = new ImageIcon("res/white_queen.png");
    public ImageIcon white_king = new ImageIcon("res/white_king.png");
    public ImageIcon white_pawn = new ImageIcon("res/white_pawn.png");

    public GUI()
    {
        frame = new JFrame("Chess - White's turn");

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
                buttons[j][i].setFocusPainted(false);
                buttons[j][i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        ChessButton b = (ChessButton) e.getSource();
                        Game g = Game.getInstance();
                        g.processButtonPress(b.m_x, b.m_y);
                    }
                });
                frame.add(buttons[j][i]);
            }

        }

        frame.setLocationRelativeTo(null);
        frame.setIconImage(new ImageIcon("/res/white_king.png").getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(white_king.getImage());
        frame.pack();
        frame.setVisible(true);
    }
}
