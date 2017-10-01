package Game;

public class Move
{
    public int m_x;
    public int m_y;

    public Move(int x, int y)
    {
        m_x = x;
        m_y = y;
    }

    public String toString()
    {
        return "(" + Integer.toString(m_x) + ", " + Integer.toString(m_y) + ") ";
    }
}
