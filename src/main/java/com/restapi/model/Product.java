package com.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @Column(nullable = false, length = 200)
    private String equipmentName;
    @Column(nullable = false, length = 200)
    private String description;
    @Column(nullable = false, length = 200)
    private String specifications;
    @Column(nullable = false, length = 200)
    private double price;
    @Lob
    @Column(name = "photo", columnDefinition="BLOB")
    private byte[] photo;

    @Transient
    private Integer count;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<Cart> carts = new ArrayList<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
