package ru.alexandertesbsnko.shoplist3.data_source.common;

import ru.alexandertesbsnko.shoplist3.data_source.net.model.response.AbstractResponse;

public class AckResponse extends AbstractResponse {

    public enum State {
        OK,
        ERROR
    }
    private BasicInfoCode error;

    public BasicInfoCode getError() {
        return error;
    }

    public void setError(BasicInfoCode error) {
        this.error = error;
    }

    private State state = State.OK;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
