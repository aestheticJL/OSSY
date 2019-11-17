package com.mmt.ossy.demo_2.dto;

import lombok.Data;

@Data
public class BankDTO {
    private Integer processId;
    private Integer maxA;
    private Integer maxB;
    private Integer maxC;
    private Integer allocationA;
    private Integer allocationB;
    private Integer allocationC;
    private Integer availableA;
    private Integer availableB;
    private Integer availableC;
}
