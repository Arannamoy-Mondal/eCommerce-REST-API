package com.aranna.backend.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    // @NotBlank
    // @Size(min=5)
    // private String street;


    // @NotBlank
    // @Size(min=5)
    // private String buildingName;

    // @NotBlank
    // @Size(min=5)
    // private String city;


    // @NotBlank
    // @Size(min=5)
    // private String state;

    // @NotBlank
    // @Size(min=5)
    // private String country;



    // @NotBlank
    // @Size(min=5)
    // private String pinCode;

    // @OneToMany(mappedBy = "address")
    // private List<User> users=new ArrayList<>();

}
