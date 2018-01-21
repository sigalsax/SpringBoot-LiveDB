package com.sigal;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //recognize annotation and config
public class Main {
    public static void main (String [] args){
        // args= commandline arguments
        SpringApplication.run(Main.class, args);
    }
}
