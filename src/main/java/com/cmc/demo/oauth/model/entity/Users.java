package com.cmc.demo.oauth.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})
public class Users extends Audit<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "is_active")
    private boolean isActive;
    @Column(name = "description")
    private String description;
    @Column(name = "remember_token")
    private String rememberToken;

//    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
//    @JsonIgnore
//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
//    private Set<ResourceAssign> resourceAssignSet;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Role> roleSet;
}
