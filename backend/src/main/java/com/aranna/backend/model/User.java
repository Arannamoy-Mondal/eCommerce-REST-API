package com.aranna.backend.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "\"users\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @NotBlank
    @Size(max = 50,min = 3)
    @Column(unique = true)
    private String userName;
    @NotBlank
    @Email
    @Column(unique = true)
    private String userEmail;
    @NotBlank
    @Size(max = 30)
    private String password;

    // @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE},
    //     fetch = FetchType.LAZY
    // )
    // @JoinTable(name = "user_role",
    //     joinColumns = @JoinColumn(name="user_id"),
    //     inverseJoinColumns = @JoinColumn(name="role_id")
    // )
    // private Set<Role> roles=new HashSet<>();

    // @OneToMany(mappedBy = "users",orphanRemoval = true,cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    // private List<Product> products=new ArrayList<>();



    // @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    // @JoinColumn(name = "address_id")
    // private Address address;
}
