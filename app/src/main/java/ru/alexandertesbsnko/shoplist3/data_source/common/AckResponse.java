package ru.alexandertesbsnko.shoplist3.data_source.common;

/**
 * Created by avtseben on 27.10.17.
 */

public class AckResponse {

    private enum State {
        OK,
        ERROR
    }

    private State state = State.OK;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
