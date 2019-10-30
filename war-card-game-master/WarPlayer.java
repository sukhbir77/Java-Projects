import java.util.LinkedList;
import java.util.Set;

/**
 * Class that represents the cards a player has.  
 */
class WarPlayer {
    /**
     * The list of all cards a player has.  'hand' may not have been the best
     * variable name meaning-wise, but it works.  
     */
    private LinkedList<Card> hand = new LinkedList<Card>();

    /**
     * Add a card to this player.
     * @param card The card to add to this player's stash.
     */
    public void addCard(Card card) {
        hand.add(card);
    }

    /**
     * Add a set of cards to this player.
     * @param cards The set of cards to add to this player's stash.
     */
    public void addCards(Set<Card> cards) {
        hand.addAll(cards);
    }

    /**
     * Take a single card from this player's stash.
     * @return The card to take from this player's stash.
     */
    public Card takeCard() {
        return hand.removeFirst();
    }

    /**
     * Number of cards this player has.
     * @return The number of cards this player has.
     */
    public int handSize() {
        return hand.size();
    }

    /**
     * Does this player have at least 1 card?
     * @return true if this player has any cards,
     *         false if this player has no cards.
     */
    public boolean hasCards() {
        return hand.size() > 0;
    }

    /**
     * Returns a string representation of this player's cards.
     * @return A string representation of this player's cards.
     */
    public String toString() {
        String rv = hand.get(0).toString();
        for (int i=1; i<hand.size(); i++) {
            rv += "," + hand.get(i).toString();
        }
        return rv;
    }
}