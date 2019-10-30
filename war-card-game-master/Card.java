/**
 * Representation of a card.  
 * 
 * We could represent rank and suit as simple ints or enum types, but since 
 * different card games have different semantics, we can represent these fields
 * as interfaces with per-game implementations.
 *
 * We will figure out the details of what these interfaces need to do as we
 * solve this problem.  (Ideally you can decide the interface up front, but
 * I'll assume that I'm working on this in a non-shared/released branch for
 * now.)
 */
public class Card {
    private Rank rank;
    private Suit suit;

    /**
     * Constructor, a Card takes a suit and a rank.
     * @param rank The rank of a card.
     * @param suit The suit of a card.  
     */
    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    /**
     * We define two cards as equal if their rank and suit are equal.  
     * @param other An object we are testing equality with.
     * @return true if other is equal to this, false otherwise.  
     */
    public boolean equals(Object other) {
        if (!(other instanceof Card)) {
            return false;
        }
        Card otherCard = (Card) other;
        return this.rank.equals(otherCard.rank) && this.suit.equals(otherCard.suit);
    }

    /**
     * Prints a simple string representation of this card.
     * @return A string representation of this card.  
     */
    public String toString() {
        return "Card<" + rank.toString() + " of " + suit.toString() + ">";
    }

    /**
     * Compares this card to another card.  
     * @param otherCard The card being compared to.  
     * @return -1 if this card is weaker than otherCard,
     *          0 if this card is equal strength to otherCard,
     *          1 if this card is stronger than otherCard. 
     *
     * NOTE: If I were to continue working on this, I'd consider moving 
     * comparison out of the Card object, since a Card representation is
     * fairly independent of the game being played, but the comparison
     * function is definitely dependent on the game being played.  
     */
    public int compareTo(Card otherCard) {
        return this.rank.compareTo(otherCard.rank);
    }
}
