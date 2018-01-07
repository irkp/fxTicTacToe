import game.GameState;
import game.GameStateImpl;
import game.Player;
import org.junit.Assert;
import org.junit.Test;

public class GameStateTest {

    @Test
    public void shouldFindComputerAsAWinner(){
        GameState gs = new GameStateImpl();
        /*
         * OOO
         * ---
         * ---
         */
        gs.changeState(0,false);
        gs.changeState(1,false);
        gs.changeState(2,false);
        Player winner = gs.findWinner();
        Assert.assertNotNull(winner);
        Assert.assertEquals(winner, Player.COMPUTER);
    }
    @Test
    public void shouldFindComputerAsAWinner2(){
        GameState gs = new GameStateImpl();
        /*
         * O--
         * O--
         * O--
         */
        gs.changeState(0,false);
        gs.changeState(3,false);
        gs.changeState(6,false);
        Player winner = gs.findWinner();
        Assert.assertNotNull(winner);
        Assert.assertEquals(winner, Player.COMPUTER);
    }

    @Test
    public void shouldFindComputerAsAWinner3(){
        GameState gs = new GameStateImpl();
        /*
         * O--
         * -O-
         * --O
         */
        gs.changeState(0,false);
        gs.changeState(4,false);
        gs.changeState(8,false);
        Player winner = gs.findWinner();
        Assert.assertNotNull(winner);
        Assert.assertEquals(winner, Player.COMPUTER);
    }

    @Test
    public void shouldFindComputerAsAWinner4(){
        GameState gs = new GameStateImpl();
        /*
         * --O
         * -O-
         * O--
         */
        gs.changeState(2,false);
        gs.changeState(4,false);
        gs.changeState(6,false);
        Player winner = gs.findWinner();
        Assert.assertNotNull(winner);
        Assert.assertEquals(winner, Player.COMPUTER);
    }

    @Test
    public void shouldFindHumanAsAWinner(){
        GameState gs = new GameStateImpl();
        /*
         * XOO
         * XOO
         * X--
         */
        gs.changeState(0,true);
        gs.changeState(3,true);
        gs.changeState(6,true);
        gs.changeState(1,false);
        gs.changeState(2,false);
        gs.changeState(4,false);
        gs.changeState(5,false);
        Player winner = gs.findWinner();
        Assert.assertNotNull(winner);
        Assert.assertEquals(winner, Player.HUMAN);
    }

    @Test
    public void shouldFindHumanAsAWinner2(){
        GameState gs = new GameStateImpl();
        /*
         * XOO
         * OXO
         * OOX
         */
        gs.changeState(0,true);
        gs.changeState(4,true);
        gs.changeState(8,true);
        gs.changeState(1,false);
        gs.changeState(2,false);
        gs.changeState(3,false);
        gs.changeState(5,false);
        gs.changeState(6,false);
        gs.changeState(7,false);
        Player winner = gs.findWinner();
        Assert.assertNotNull(winner);
        Assert.assertEquals(winner, Player.HUMAN);
    }
}
