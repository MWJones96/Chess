package Game;

import java.util.Objects;

/**Simply a 2D vector that contains some x and y position
 */

public class Move
{
    public int m_x; //x component of move
    public int m_y; //y component of move

    public Move(int x, int y)
    {
        m_x = x;
        m_y = y;
    }

    /**Converts the data to a string tuple of the coordinates
     *
     * @return The string of the tuple of coordinates
     */
    public String toString()
    {
        return "(" + Integer.toString(m_x) + ", " + Integer.toString(m_y) + ") ";
    }

    /**Checks if Move object is equal to object obj
     *
     * @param obj - The object to compare
     * @return - Whether or not the two are equal
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        // null check
        if (obj == null)
            return false;
        // type check and cast
        if (getClass() != obj.getClass())
            return false;
        Move move = (Move) obj;
        // field comparison
        return Objects.equals(m_x, move.m_x)
                && Objects.equals(m_y, move.m_y);
    }
}
