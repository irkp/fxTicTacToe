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
        IntStream.range(0,8).forEach(i -> states.put(i, FieldValue.EMPTY));
    }

    @Override
    public Boolean isEndOfGame() {
        if (treeTheSameInHorizontalLine() || threeTheSameInVerticalLine() || threeTheSameDiagonally()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public void changeState(Integer fieldNumber) {
        if (FieldValue.EMPTY == states.get(fieldNumber)) {
            // player is always X
            states.put(fieldNumber, FieldValue.X);
        } else {
            throw new FieldIsAlreadyUsedException();
        }
    }

    @Override
    public Integer computersMove() {
        Integer chosenField = getFreeRandomField();
        states.put(chosenField, FieldValue.O);
        return chosenField;
    }

    private Integer getFreeRandomField() {
        Optional<Integer> anyFreeField = states.entrySet().stream().filter(i -> FieldValue.EMPTY == i.getValue()).map(Map.Entry::getKey).findAny();
        if(anyFreeField.isPresent()){
            return anyFreeField.get();
        }
        throw new NoFreeFieldsException();
    }

    private Boolean threeTheSameDiagonally() {

        return Boolean.FALSE;
//        for(int i =0; i<2; i++){
//            if(states[]
//        }

    }

    private Boolean threeTheSameInVerticalLine() {
        return Boolean.FALSE;
    }

    private Boolean treeTheSameInHorizontalLine() {
        for(int i =0; i<7; i+=3){
            if(!states.get(i).equals(FieldValue.EMPTY)
                    && states.get(i).equals(states.get(i+1))
                    && states.get(i).equals(states.get(i+2))){
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }
}
