package com.example.android.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    USER("1"),
    ADMIN("0");

    private String value;
}
