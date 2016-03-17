package com.github.shuntak.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseMasterBody {
    private final boolean result;
    private final Object data;

    @JsonCreator
    public ResponseMasterBody() {
        this.result = false;
        this.data = null;
    }

    @JsonCreator
    public ResponseMasterBody(@JsonProperty Object data) {
        this.result = true;
        this.data = data;
    }

    @JsonProperty
    public boolean isResult() {
        return this.result;
    }

    @JsonProperty
    public Object getData() {
        return this.data;
    }
}
