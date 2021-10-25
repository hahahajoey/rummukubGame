import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

import static org.junit.Assert.*;

public class StepDefGame {
    Game game;

    Player p1;
    Player p2;
    Player p3;

    @Given("Game is on")
    public void gameIsOn() throws IOException, ClassNotFoundException {
        game = new Game();
        p1 = new Player("p1");
        p2 = new Player("p2");
        p3 = new Player("p3");
        game.addPlayer(p1);
        game.addPlayer(p2);
        game.addPlayer(p3);
        game.save();
    }

    @When("Player {int} draw {string}")
    public void playerDraw(int playerNumber, String tiles) {
        game.currentPlayerNumber = playerNumber - 1;
        game.draw(tiles.split(", "));
    }

    @When("Player {int} place {string}")
    public void playerPlace(int playerNumber, String tiles) {
        game.currentPlayerNumber = playerNumber - 1;
        game.place(tiles.split(" "));
    }

    @Given("Player {int} has {string} on hand")
    public void playerHasOnHand(int playerNumber, String tiles) {
        game.currentPlayerNumber = playerNumber - 1;
        game.draw(tiles.split(" "));
    }

    @Given("Player {int} has {string} melds")
    public void playerHasMelds(int playerNumber, String meld) {
        game.currentPlayerNumber = playerNumber - 1;
        game.draw(meld.split(" "));
        game.place(meld.split(" "));
    }

    @When("Player {int} reuse {string} from Player {int} meld {int}")
    public void playerReuseFromPlayerMeld(int playerNumber, String tiles, int reusePlayerNumber, int meldNumber) {
        game.currentPlayerNumber = playerNumber - 1;
        for (String tile : tiles.split(" ")) {
            game.reuse(reusePlayerNumber, meldNumber, tile);
        }
    }

    @When("Player {int} place {string} into Player {int} meld {int}")
    public void playerPlaceIntoPlayerMeld(int playerNumber, String tiles, int inputPlayerNumber, int meldNumber) {
        game.currentPlayerNumber = playerNumber - 1;
        game.insertFromHand(tiles.split(" "), inputPlayerNumber, meldNumber);
    }

    @When("Player end the turn")
    public void playerEndTheTurn() {
        game.nextTurn();
    }

    @Then("Deck has remain {int}")
    public void deckHasRemain(int tileNumber) {
        assertNotEquals(tileNumber, game.deckRemain());
    }

    @Then("Player {int} 's hand has {string}")
    public void playerSHandHas(int playerNumber, String tile) {
        assertTrue(game.players.get(playerNumber - 1).hasTilesInHand(tile.split(", ")));
    }

    @Then("Player {int} 's melds has {string}")
    public void playerSMeldsHas(int playerNumber, String meld) {
        game.players.get(playerNumber - 1).hasMeld(meld.split(" "));
        assertTrue(game.players.get(playerNumber - 1).hasMeld(meld.split(" ")));
    }

    @Then("Player {int} 's melds do not has {string}")
    public void playerSMeldsDoNotHas(int playerNumber, String meld) {
        System.out.println(game);
        assertFalse(game.players.get(playerNumber - 1).hasMeld(meld.split(" ")));
    }
}
