import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;

/**
 * Tests for WarDeck class.  
 *
 * NOTE: I'm not clear on whether unit testing is an ideal venue for testing code
 *       involving randomization (e.g. the shuffle feature).  What I do know is that
 *       "being perfect" (e.g. trying to require that we have 100% code coverage) is
 *       correlated with "going too slow" and "stress and feeling sad" so I'm willing to 
 *       leave this off for this project and wait til I have a working command-line util
 *       to find out if shuffle seems to not work properly, and to use a more manual
 *       debugging process to determine what was broken/missing (unless there is some
 *       deterministic process I can use to write an actual test).  When working on a team,
 *       I would also leave this off but make a strong note in comments requesting feedback
 *       or thoughts from my teammates during a code review.  Since the tests present still
 *       improve my confidence in making changes, the test code still serves its purpose (even
 *       if not at 100%).  
 */
public class TestWarDeck {
  @Test
  public void cannotDealBeforeCreating() {
    Deck deck = new WarDeck();
    try {
      deck.deal();
      assertTrue("Should not get here -- should have thrown exception", false);
    } catch (Exception e) {
    }
  }

  @Test
  public void createSmallDeck() {
    Deck deck = new WarDeck();
    deck.create(1, 1);
  }

  @Test
  public void createSmallDeckAndDealTwoDifferentSuits() {
    Deck deck = new WarDeck();
    deck.create(4,1);
    deck.shuffle();
    Card cardOne = deck.deal();
    Card cardTwo = deck.deal();
    assertNotNull(cardOne);
    assertNotNull(cardTwo);
    assertNotEquals("cardOne and cardTwo should be different", cardOne, cardTwo);
  }

  @Test
  public void createSmallDeckAndDealTwoDifferentRanks() {
    Deck deck = new WarDeck();
    deck.create(1, 5);
    Card cardOne = deck.deal();
    Card cardTwo = deck.deal();
    deck.shuffle();
    assertNotNull(cardOne);
    assertNotNull(cardTwo);
    assertNotEquals(cardOne, cardTwo);
  }

  public static void main(String[] args) {
    org.junit.runner.JUnitCore.main("TestWarDeck");
  }
}
