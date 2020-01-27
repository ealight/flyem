package com.ddm.flyem.dao;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    @Column(name = "email_approved")
    private Boolean emailApproved;

    @PrePersist
    private void prePersist(){
        if(emailApproved == null) {
            emailApproved = false;
        }
    }
}
