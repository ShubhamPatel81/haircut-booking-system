package com.hair.User_Service.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fullName;

    @NotBlank(message = "UserName is mendatory")
    @Column(unique = true)
    private String userName;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email  Should be valid ")
    @Column(unique = true)
    private String email;

    private String phone;

    private String role;
    @CreationTimestamp
    private LocalDateTime cratedAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @NotBlank(message = "Password is mandatory")
    private String password;

 

}
