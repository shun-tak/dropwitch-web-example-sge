package com.github.shuntak.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResponseCommonBody {
    private final boolean result;
    private final List<Object> data;

    @JsonCreator
    public ResponseCommonBody() {
        this.result = false;
        this.data = null;
    }

    @JsonCreator
    public ResponseCommonBody(@JsonProperty List<Object> data) {
        this.result = true;
        this.data = data;
    }

    @JsonProperty
    public boolean isResult() {
        return this.result;
    }

    @JsonProperty
    public List<Object> getData() {
        return this.data;
    }
}
