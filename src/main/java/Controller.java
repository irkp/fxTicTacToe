import game.GameState;
import game.GameStateImpl;
import game.Player;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Controller {
    private GameState gameState;
    private static Controller ourInstance = new Controller();

    public Controller() {
        this.gameState = new GameStateImpl();
    }

    @FXML
    public void pressField(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        Integer fieldId = Integer.parseInt(button.getId());
        gameState.changeState(fieldId, Boolean.TRUE);
        button.setText("X");
        button.setDisable(Boolean.TRUE);
        if (gameState.isEndOfGame()) {
            endGame();
        } else {
            Integer chosenField = gameState.computersMove();
            Parent parent = button.getParent();
            Button chosenButton = (Button) ((GridPane) parent).getChildren().get(chosenField);
            chosenButton.setText("O");
            chosenButton.setDisable(Boolean.TRUE);
            if (gameState.isEndOfGame()) {
                endGame();
            }
        }
    }

    private void endGame() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("THE END!");
        alert.setHeaderText(null);
        String winner = Player.HUMAN.equals(gameState.findWinner()) ? "You" : "Computer";
        alert.setContentText("The Winner is: " + winner + "!");
        alert.showAndWait();
    }
}
