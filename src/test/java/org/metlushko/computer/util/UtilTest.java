package org.metlushko.computer.util;

import org.junit.jupiter.api.Test;
import org.metlushko.computer.entyti.Computer;

import java.lang.reflect.Type;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {



    @Test
    void toInstance() {
        //given
        String jsonForComputer = TestData.builder().build().buildJsonForComputer();
        Computer expected = TestData.builder().build().buildComputer();

        //when
        Computer actual = Util.toInstance(jsonForComputer, Computer.class);

        //then
        assertEquals(expected,actual);
    }


    @Test

    void toJson() throws ClassNotFoundException {
        //given
        Computer computer = TestData.builder().build().buildComputer();
        String expected = TestData.builder().build().buildJsonForComputer();
        Class cast = Class.class.cast(computer);
        Class<?> clazz = Class.forName("org.metlushko.computer.entyti.Computer");
        //when
        String actual = Util.toJson(cast, expected);

        //then
        assertEquals(expected,actual);


    }
}
