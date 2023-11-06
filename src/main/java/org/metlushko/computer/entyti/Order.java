package org.metlushko.computer.entyti;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class Order {

    private UUID id;
    private List<Engineer> engineers;
    private LocalDateTime created;

}
