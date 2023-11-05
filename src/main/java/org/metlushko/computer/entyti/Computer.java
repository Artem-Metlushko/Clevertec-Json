package org.metlushko.computer.entyti;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Computer {

    private UUID id;
    private String brand;
    private String model;
    private Double price;

}
