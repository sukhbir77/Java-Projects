/**
 * Implementation of Rank interface suitable for the game of war.
 */
class WarRank implements Rank {
    private int rankValue;

    /**
     * Constructor.
     * @param rank The rank for this WarRank.
     */
    WarRank(int rank) {
        this.rankValue = rank;
    }

    /**
     * Returns the integer value of this rank.
     * @return The integer value of this rank.
     */
    public int getValue() {
        return rankValue;
    }

    /**
     * Tests equality with another object.
     * @param other The other object with which to test equality.
     * @return true if the other object is a Rank and has equal value to this,
     *         false otherwise.
     */
    public boolean equals(Object other) {
        if (!(other instanceof Rank)) {
            return false;
        }
        Rank otherRank = (Rank) other;
        return rankValue == otherRank.getValue();
    }

    /**
     * Returns a string representation of this rank.
     * @return A string representation of this rank.
     */
    public String toString() {
        return "" + rankValue;
    }

    /**
     * Compare this rank to another rank in terms of the game of war.  
     * 
     * Since we allow any integer number of ranks, it doesn't necessarily make
     * sense to use the "Ace is high" semantics since, what if we have 3 rank
     * values?  Or 25 rank values?  Granted, we could start at 1 and always
     * say 1 beats all, but since its not that interesting, I'll just allow the
     * deck to start with 0 and have no special meaning.  
     */
    public int compareTo(Rank other) {
        if (rankValue == other.getValue()) {
            return 0;
        }
        if (rankValue < other.getValue()) {
            return -1;
        }
        return 1;
    }
}