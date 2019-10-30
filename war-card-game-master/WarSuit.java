/**
 * Implementation of Suit interface suitable for the game of war.
 */
class WarSuit implements Suit {
    /**
     * Name of the suit.  
     */
    private String suitName;

    /**
     * Constructor. 
     * @param name The name of this suit.  
     */
    WarSuit(String name) {
        this.suitName = name;
    }

    /**
     * Returns the name of this suit.
     * @return The name of this suit. 
     */
    public String getName() {
        return suitName;
    }

    /**
     * Tests equality of this suit with another object.
     * @param o The other object to test equality against.
     * @return true if o is a Suit and its name equals this suit's name.
     */
    public boolean equals(Object o) {
        if (!(o instanceof Suit)) {
            return false;
        }
        Suit otherSuit = (Suit) o;
        return suitName.equals(otherSuit.getName());
    }

    /**
     * Returns a string representation of this suit.
     * @return A string representation of this suit.
     */
    public String toString() {
        return suitName;
    }
}