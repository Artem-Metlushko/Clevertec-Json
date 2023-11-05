package org.metlushko.computer;

import org.metlushko.computer.entyti.Computer;

import static org.metlushko.computer.util.Util.convertJsonToInstance;


public class Main {
    public static void main(String[] args) {
        String jsonComputer = "{\n" +
                "  \"id\": \"bb22f153-5ca3-4af9-847d-527549641e02\",\n" +
                "  \"brand\": \"Samsung\",\n" +
                "  \"model\": \"xxx\",\n" +
                "  \"price\": \"100.0\"\n" +
                "}";

        Computer computer = convertJsonToInstance(jsonComputer, Computer.class);
        System.out.println(computer);

    }



}
