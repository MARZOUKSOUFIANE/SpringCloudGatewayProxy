package com.sid;

import com.sid.dao.BillRepository;
import com.sid.dao.ProductItemRepository;
import com.sid.entitites.Bill;
import com.sid.entitites.Customer;
import com.sid.entitites.ProductItem;
import com.sid.web.CustomerServiceClient;
import com.sid.web.InventoryServiceClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
@EnableFeignClients
public class BilingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BilingServiceApplication.class, args);
    }


    @Bean
    CommandLineRunner start(BillRepository billRepository, ProductItemRepository productItemRepository,
                            InventoryServiceClient inventoryServiceClient, CustomerServiceClient customerServiceClient) {
        return args -> {
            Bill bill = new Bill();
            bill.setBillingDate(new Date());
            Customer customer = customerServiceClient.findCustomerById(1L);
            System.out.println(customer);
            bill.setCustomerID(customer.getId());
            billRepository.save(bill);
            System.out.println(bill);
            System.out.println(inventoryServiceClient.findAll());
            System.out.println("----------------------------------------------");
            inventoryServiceClient.findAll().getContent().forEach(p -> {
                System.out.println(p);
                productItemRepository.save(new ProductItem(null, null, p.getId(), p.getPrice(), (int) (1 + Math.random() * 1000), bill));
            });
        };
    }

}