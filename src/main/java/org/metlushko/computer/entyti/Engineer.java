package org.metlushko.computer.entyti;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Engineer {

    private UUID id;
    private String name;
    private LocalDateTime dateBirthday;
    private List<Computer> computers;

}
