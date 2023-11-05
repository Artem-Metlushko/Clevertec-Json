package org.metlushko.computer.entyti;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class Customer {

    private UUID id;
    private String name;
    private LocalDateTime dateBirthday;
    private List<Order> orders;

}
