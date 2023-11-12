package org.metlushko.computer;

import org.junit.jupiter.api.Test;
import org.metlushko.computer.entyti.Computer;
import org.metlushko.computer.entyti.Engineer;
import org.metlushko.computer.entyti.Order;
import org.metlushko.computer.util.JacksonStatham;
import org.metlushko.computer.util.TestData;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomObjectMapperTest {

    private Computer computer1 = TestData.builder()
            .withId(UUID.fromString("50fb357b-8833-49a2-b389-a5f88e222694"))
            .build().buildComputer();

    private Computer computer2 = TestData.builder()
            .withId(UUID.fromString("3162b1ac-81e7-413c-ac94-915ded78fde0"))
            .build().buildComputer();

    private Engineer engineer1 = TestData.builder()
            .withId(UUID.fromString("1d0d89b7-01b6-4465-b750-ddbe1b8aaa5a"))
            .withComputers(List.of(computer1, computer2, computer1, computer2))
            .build().buildEngineer();

    private Engineer engineer2 = TestData.builder()
            .withComputers(List.of(computer1, computer2, computer1, computer2))
            .build().buildEngineer();

    private Order order = TestData.builder()
            .withEngineers(List.of(engineer1, engineer2, engineer1, engineer2))
            .build().buildOrder();


    @Test
    void toObject() {

        //given
        String json = JacksonStatham.toJson(order);
        Object actual = JacksonStatham.toObject(json, Order.class);

        //when
        Object expected = CustomObjectMapper.toObject(json, Order.class);

        //then
        assertEquals(expected, actual);

    }

    @Test
    void toJson() {
        //given
        String actual = JacksonStatham.toJson(order);

        //when
        String expected = CustomObjectMapper.toJson(order);

        //then
        assertEquals(expected, actual);
    }
}
