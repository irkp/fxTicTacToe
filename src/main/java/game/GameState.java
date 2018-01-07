package game;

public interface GameState {
    Boolean isEndOfGame();

    void changeState(Integer fieldNumber, Boolean humanMove);

    Integer computersMove();

    Player findWinner();
}
