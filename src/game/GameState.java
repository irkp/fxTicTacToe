package game;

public interface GameState {
    Boolean isEndOfGame();

    void changeState(Integer fieldNumber);

    Integer computersMove();
}
