/**
 * Interface to represent the rank of a card.  
 */
public interface Rank {
    /**
     * Get the integer value of this rank.
     * @return The integer value of this rank.  
     */
    public int getValue();

    /**
     * Compare this rank to another rank.  
     * @return -1 if this rank is less than other,
     *          0 if this rank is equal to other,
     *          1 if this rank is greater than other.  
     */
    public int compareTo(Rank other);
}
