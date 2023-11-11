package org.metlushko.computer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.metlushko.computer.entyti.Computer;
import org.metlushko.computer.entyti.Engineer;
import org.metlushko.computer.entyti.Order;
import org.metlushko.computer.util.JacksonStatham;
import org.metlushko.computer.util.TestData;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeserializationTest {

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void toInstance() {
        //given
        Computer computer1 = TestData.builder()
                .withId(UUID.fromString("50fb357b-8833-49a2-b389-a5f88e222694"))
                .build().buildComputer();

        Computer computer2 = TestData.builder()
                .withId(UUID.fromString("3162b1ac-81e7-413c-ac94-915ded78fde0"))
                .build().buildComputer();

        Engineer engineer1 = TestData.builder()
                .withId(UUID.fromString("1d0d89b7-01b6-4465-b750-ddbe1b8aaa5a"))
                .withComputers(List.of(computer1, computer2))
                .build().buildEngineer();

        Engineer engineer2 = TestData.builder()
                .withComputers(List.of(computer1, computer2))
                .build().buildEngineer();

        Order order = TestData.builder()
                .withEngineers(List.of(engineer1, engineer2))
                .build().buildOrder();



        String json = JacksonStatham.toJson(order);
        Object actual = JacksonStatham.toObject(json, Order.class);


        //when
        Order expected = Deserialization.toInstance(json, Order.class);
        System.out.println();
        //then
        assertEquals(expected,actual);
    }
}
