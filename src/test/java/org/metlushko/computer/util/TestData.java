package org.metlushko.computer.util;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.metlushko.computer.entyti.Computer;

import java.time.LocalDateTime;
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
    private LocalDateTime dateBirthday = LocalDateTime.of(1961, 11, 14, 10, 45);

    @Builder.Default
    private String brand = "Nokia";

    @Builder.Default
    private String model = "X100";

    @Builder.Default
    private String jsonComputer = "{\n" +
            "  \"id\": \"bb22f153-5ca3-4af9-847d-527549641e02\",\n" +
            "  \"brand\": \"Nokia\",\n" +
            "  \"model\": \"X100\",\n" +
            "  \"price\": \"999.99\",\n" +
            "  \"dateTime\": \"1961-11-14T10:45\"\n" +
            "}";

    @Builder.Default
    private Double price = 999.99;

    public Computer buildComputer(){
       return new Computer(id,brand,model,price,dateBirthday);
    }

    public String buildJsonForComputer(){
        return jsonComputer;
    }







}
