package org.scamlet.mvc.mvcstock.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product name is required")
    @Size(min = 4, max = 50, message = "Product name must be 4-50 character")
    @Column(name="name")
    private String name;

    @NotBlank(message = "Description is required")
    @Column(name="description", columnDefinition = "TEXT")
    private String description;

    @Column(name="quantity")
    private Long quantity;

    @Column(name="price")
    private Double price;


}
