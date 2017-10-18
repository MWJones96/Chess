package Game;

import javax.swing.*;
import java.awt.*;

public class Main
{
    public static void main(String[] args)
    {
        //Game g = Game.getInstance();
        //g.initBoard();
        //g.play();

        int rows = 8;
        int columns = 8;

        JFrame frame = new JFrame("Chess");
        JPanel panel = new JPanel();

        JButton[] buttons = new JButton[8 * 8];

        for(int i = 0; i < 8*8; i++)
        {
            buttons[i] = new JButton(Integer.toString(i));
        }

        panel.setLayout(new GridLayout(8, 8));

        frame.setSize(640, 640);
        frame.setMinimumSize(new Dimension(640, 640));
        frame.setMaximumSize(new Dimension(640, 640));
        frame.setPreferredSize(new Dimension(640, 640));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }
}