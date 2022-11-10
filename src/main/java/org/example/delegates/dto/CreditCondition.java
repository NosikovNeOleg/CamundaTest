package org.example.delegates.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class CreditCondition implements Serializable {

    private final int money;
    private final int years;
}
