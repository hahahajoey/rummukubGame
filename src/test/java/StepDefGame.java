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
    }

    @When("Player {int} draw {string}")
    public void playerDraw(int playerNumber, String tiles) {
        game.currentPlayerNumber = playerNumber;
        game.draw(tiles.split(", "));
    }

    @Then("Deck has remain {int}")
    public void deckHasRemain(int tileNumber) {
        assertNotEquals(tileNumber, game.deckRemain());
    }

    @When("Player {int} place {string}")
    public void playerPlace(int playerNumber, String tiles) {
        game.currentPlayerNumber = playerNumber;
        game.place(tiles.split(" "));
    }

    @Then("Player {int} 's melds has {string}")
    public void playerSMeldsHas(int playerNumber, String meld) {
        assertTrue(game.players.get(playerNumber).hasMeld(meld.split(" ")));
    }

    @Then("Player {int} 's hand has {string}")
    public void playerSHandHas(int playerNumber, String tiles) {
        assertTrue(game.players.get(playerNumber).hasTilesInHand(tiles.split(", ")));
    }

    @Given("Player {int} has {string} on hand")
    public void playerHasOnHand(int playerNumber, String tiles) {
        game.currentPlayerNumber = playerNumber;
        game.draw(tiles.split(" "));
    }
}