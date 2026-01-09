package dev.TrueFood.enums;

import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor
public enum OrderType implements Serializable {
    ADVERTISEMENT("ADVERTISEMENT"),
    TASK("TASK");

    private final String value;

}
