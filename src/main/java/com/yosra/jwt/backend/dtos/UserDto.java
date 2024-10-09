package com.yosra.jwt.backend.dtos;


import com.yosra.jwt.backend.entites.FinancialData;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String login;
    private String token;


//    private  String ticker ;
//    private String companyName ;
//    private Long cik ;




}
