package org.metlushko.computer.util;

import org.junit.jupiter.api.Test;
import org.metlushko.computer.entyti.Computer;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {

    @Test
    void convertJsonToInstance() {
        //given
        String jsonForComputer = TestData.builder().build().buildJsonForComputer();
        Computer expected = TestData.builder().build().buildComputer();

        //when
        Computer actual = Util.toInstance(jsonForComputer, Computer.class);

        //then
        assertEquals(expected,actual);
    }
}
