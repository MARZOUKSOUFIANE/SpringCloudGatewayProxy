package com.sid;

import com.sid.dao.ProductRepository;
import com.sid.entities.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProductRepository productRepository){
    return args ->{
            productRepository .save( new Product( null ,"Computer Desk Top", 900));
            productRepository .save (new Product( null ,"Printer",800));
            productRepository .save (new Product( null ,"MacBook Pro Lap", 1800));
            productRepository .findAll().forEach(System.out::println);

};
}
}
