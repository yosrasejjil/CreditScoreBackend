package com.yosra.jwt.backend.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "app_user")
public class User {

    @Id
    private Long id;

    @Column(name = "first_name", nullable = false)
    @Size(max = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @Size(max = 100)
    private String lastName;

    @Column(nullable = false)
    @Size(max = 100)
    private String login;

    @Column(nullable = false)
    @Size(max = 100)
    private String password;
    @OneToMany(mappedBy = "user")
    private Set<FinancialData> financialData;
//    @Column(nullable = false)
//    @Size(max = 100)
//    private  String ticker ;
//    @Column(nullable = false)
//    @Size(max = 100)
//    private String companyName ;
//    @Column(nullable = false)
//    @Size(max = 100)
//    private Long cik ;

}
