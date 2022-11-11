package org.example.delegates.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Client implements Serializable {

    private int passport;

    private int age;
    private int income;
    private boolean hasCreditStory;
    private int years;
    private int wantedMoney;

}
