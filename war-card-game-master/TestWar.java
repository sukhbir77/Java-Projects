import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class TestWar {
    @Test
    public void testDivideCards() {
        War game = new War();
        Deck deck = new WarDeck();
        deck.create(4, 13);
        WarPlayer[] players = game.divideCards(deck, 2);
        assertEquals(players.length, 2);
        assertNotNull(players[0]);
        assertEquals(players[0].handSize(), 26);
        assertNotNull(players[1]);
        assertEquals(players[1].handSize(), 26);
    }

    @Test
    public void testDivideCardsThreeWays() {
        War game = new War();
        Deck deck = new WarDeck();
        deck.create(4, 13);
        WarPlayer[] players = game.divideCards(deck, 3);
        assertEquals(players.length, 3);
        assertNotNull(players[0]);
        assertEquals(17, players[0].handSize());
        assertNotNull(players[1]);
        assertEquals(17, players[1].handSize());
        assertNotNull(players[2]);
        assertEquals(17, players[2].handSize());

    }

    @Test
    public void testOneSimpleRound() {
        WarPlayer first = new WarPlayer();
        first.addCard(new Card(new WarRank(3), new WarSuit("spades")));
        WarPlayer second = new WarPlayer();
        second.addCard(new Card(new WarRank(7), new WarSuit("hearts")));
        assertEquals(1, first.handSize());
        assertEquals(1, second.handSize());

        War game = new War();
        game.playOneRound(new WarPlayer[] { first, second });
        assertEquals(0, first.handSize());
        assertEquals(2, second.handSize());
    }

    @Test
    public void testOneRoundWithAnEmptyPlayer() {
        WarPlayer first = new WarPlayer();
        WarPlayer second = new WarPlayer();
        second.addCard(new Card(new WarRank(3), new WarSuit("spades")));
        WarPlayer third = new WarPlayer();
        third.addCard(new Card(new WarRank(7), new WarSuit("hearts")));
        assertEquals(0, first.handSize());
        assertEquals(1, second.handSize());
        assertEquals(1, third.handSize());

        War game = new War();
        game.playOneRound(new WarPlayer[] { first, second, third });
        assertEquals(0, first.handSize());
        assertEquals(0, second.handSize());
        assertEquals(2, third.handSize());
    }

    @Test
    public void testOneRoundInvolvingATie() {
        WarPlayer first = new WarPlayer();
        WarPlayer second = new WarPlayer();
        
        // first round tie causes a WAR
        first.addCard(new Card(new WarRank(7), new WarSuit("spades")));
        second.addCard(new Card(new WarRank(7), new WarSuit("hearts")));

        // face down for subround 2 of round 1
        first.addCard(new Card(new WarRank(5), new WarSuit("spades")));
        second.addCard(new Card(new WarRank(9), new WarSuit("hearts")));

        // face up for subround 2 of round 1
        first.addCard(new Card(new WarRank(5), new WarSuit("spades")));
        second.addCard(new Card(new WarRank(8), new WarSuit("hearts")));
        assertEquals(3, first.handSize());
        assertEquals(3, second.handSize());
        War game = new War();
        game.playOneRound(new WarPlayer[] { first, second });
        assertEquals(0, first.handSize());
        assertEquals(6, second.handSize());
    }

    /**
     * According to https://www.pagat.com/war/war.html, "Note that all players 
     * take part in a war, not only the ones who had the highest cards." 
     */
    @Test
    public void testOneRoundInvolvingATieThreePlayers() {
        WarPlayer first = new WarPlayer();
        WarPlayer second = new WarPlayer();
        WarPlayer third = new WarPlayer();

        // this hand will result in a WAR (first and second player tie)
        first.addCard(new Card(new WarRank(7), new WarSuit("spades")));
        second.addCard(new Card(new WarRank(7), new WarSuit("hearts")));
        third.addCard(new Card(new WarRank(1), new WarSuit("hearts")));
        
        // this 2nd subround of round 1 is a throwaway ('dealt face down')
        first.addCard(new Card(new WarRank(1), new WarSuit("spades")));
        second.addCard(new Card(new WarRank(2), new WarSuit("hearts")));
        third.addCard(new Card(new WarRank(3), new WarSuit("hearts")));

        // this 2nd subround of round 1 is the hand played ('dealt face up')
        // obviously second player wins
        first.addCard(new Card(new WarRank(4), new WarSuit("spades")));
        second.addCard(new Card(new WarRank(5), new WarSuit("hearts")));
        third.addCard(new Card(new WarRank(1), new WarSuit("hearts")));

        // we won't hit this layer of cards so losers should still have 1 card
        first.addCard(new Card(new WarRank(11), new WarSuit("spades")));
        second.addCard(new Card(new WarRank(2), new WarSuit("hearts")));
        third.addCard(new Card(new WarRank(4), new WarSuit("hearts")));

        assertEquals(4, first.handSize());
        assertEquals(4, second.handSize());
        assertEquals(4, third.handSize());

        War game = new War();
        game.playOneRound(new WarPlayer[] {first,second,third});

        assertEquals(1, first.handSize());
        assertEquals(10, second.handSize());
        assertEquals(1, third.handSize());

    }

    @Test
    public void realDrawTestCase() {
        WarPlayer first = new WarPlayer();
        WarPlayer second = new WarPlayer();

        // this hand will result in a WAR
        first.addCard(new Card(new WarRank(7), new WarSuit("spades")));
        second.addCard(new Card(new WarRank(7), new WarSuit("hearts")));
        
        // this 2nd subround of round 1 is a throwaway ('dealt face down')
        first.addCard(new Card(new WarRank(1), new WarSuit("spades")));
        second.addCard(new Card(new WarRank(2), new WarSuit("hearts")));

        // this 2nd subround of round 1 is the hand played ('dealt face up')
        // obviously second player wins
        first.addCard(new Card(new WarRank(4), new WarSuit("spades")));
        second.addCard(new Card(new WarRank(4), new WarSuit("hearts")));

        // we hit this layer of cards for the second 'face down'
        first.addCard(new Card(new WarRank(11), new WarSuit("spades")));
        second.addCard(new Card(new WarRank(2), new WarSuit("hearts")));

        assertEquals(4, first.handSize());
        assertEquals(4, second.handSize());

        try {
            War game = new War();
            game.playOneRound(new WarPlayer[] {first,second});
            assertNotNull(null); // this line gets run and fails if 
            // DrawGameException is not thrown by .playOneRound() above
        } catch (DrawGameException e) {
        }
    }

    public static void main(String[] args) {
        org.junit.runner.JUnitCore.main("TestWar");
    }
}