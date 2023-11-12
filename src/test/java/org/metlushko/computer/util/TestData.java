package org.metlushko.computer.util;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.metlushko.computer.entyti.Computer;
import org.metlushko.computer.entyti.Engineer;
import org.metlushko.computer.entyti.Order;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.UUID;

@Data
@Accessors(chain = true)
@Builder(setterPrefix = "with", toBuilder = true)
public class TestData {

    @Builder.Default
    private UUID id = UUID.fromString("bb22f153-5ca3-4af9-847d-527549641e02");

    @Builder.Default
    private String name = "FreddyMercury";

    @Builder.Default
    private LocalDate date = LocalDate.of(1961, Month.FEBRUARY, 1);

    @Builder.Default
    private String brand = "Nokia";

    @Builder.Default
    private String model = "X100";

    @Builder.Default
    private Double price = 999.99;


    private List<Computer> computers ;


    private List<Engineer> engineers ;

    public Computer buildComputer(){
       return new Computer(id,name,brand,price, date);
    }

    public Engineer buildEngineer(){
        return new Engineer(id,name,date,computers);
    }

    public Order buildOrder(){
        return new Order(id,name,date,engineers);
    }









}
