package com.microservice.inventoryservice.model;

import lombok.*;

import javax.persistence.*;

@Data
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_inventory")
@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String skuCode;
    private Integer quantity;


}
