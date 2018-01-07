package game;

import exceptions.FieldIsAlreadyUsedException;
import exceptions.NoFreeFieldsException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

public class GameStateImpl implements GameState {

    private Map<Integer, FieldValue> states;

    public GameStateImpl() {
        super();
        this.states = new HashMap<>();
        IntStream.range(0, 9).forEach(i -> states.put(i, FieldValue.EMPTY));
    }

    @Override
    public Boolean isEndOfGame() {
        if (treeTheSameInRow(FieldValue.EMPTY, Boolean.FALSE)
                || threeTheSameInColumn(FieldValue.EMPTY, Boolean.FALSE)
                || threeTheSameDiagonally()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public void changeState(Integer fieldNumber, Boolean humanMove) {
        if (FieldValue.EMPTY == states.get(fieldNumber)) {
            // player is always X
            FieldValue fieldValue = humanMove ? FieldValue.X : FieldValue.O;
            states.put(fieldNumber, fieldValue);
        } else {
            throw new FieldIsAlreadyUsedException();
        }
    }

    @Override
    public Integer computersMove() {
        Integer chosenField = getFreeRandomField();
        // computer is always O
        states.put(chosenField, FieldValue.O);
        return chosenField;
    }

    @Override
    public Player findWinner() {
        if(isComputerWinner()){
            return Player.COMPUTER;
        } else {
            return Player.HUMAN;
        }
    }

    private boolean isComputerWinner() {
       return computerWinDiagonally() || computerWinInRow() || computerWinInColumn();
    }

    private boolean computerWinInColumn() {
        return threeTheSameInColumn(FieldValue.O, Boolean.TRUE);
    }

    private boolean computerWinInRow() {
        return treeTheSameInRow(FieldValue.O, Boolean.TRUE);
    }

    private boolean computerWinDiagonally() {
        return states.get(4).equals(FieldValue.O) && sameStateDiagonally();
    }

    private Integer getFreeRandomField() {
        Optional<Integer> anyFreeField = states.entrySet().stream().filter(i -> FieldValue.EMPTY == i.getValue()).map(Map.Entry::getKey).findAny();
        if (anyFreeField.isPresent()) {
            return anyFreeField.get();
        }
        throw new NoFreeFieldsException();
    }

    private Boolean threeTheSameDiagonally() {
        if(!states.get(4).equals(FieldValue.EMPTY) && sameStateDiagonally()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private boolean sameStateDiagonally() {
        return ( (states.get(0).equals(states.get(4)) && states.get(4).equals(states.get(8)))
                || (( states.get(2).equals(states.get(4)) && states.get(4).equals(states.get(6)))));
    }

    private Boolean threeTheSameInColumn(FieldValue fieldValue, Boolean equalToFieldFromParam) {
        for (int i = 0; i < 3; i++) {
            if ( (equalToFieldFromParam == states.get(i).equals(fieldValue)) && sameStateInColumn(i)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    private boolean sameStateInColumn(int column) {
        return states.get(column).equals(states.get(column + 3))
                && states.get(column).equals(states.get(column + 6));
    }

    private Boolean treeTheSameInRow(FieldValue fieldValue, Boolean equalToFieldFromParam) {
        for (int i = 0; i < 7; i += 3) {
            if ( (equalToFieldFromParam == states.get(i).equals(fieldValue)) && sameStateInRow(i)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    private boolean sameStateInRow(int i) {
        return states.get(i).equals(states.get(i + 1)) && states.get(i).equals(states.get(i + 2));
    }
}
