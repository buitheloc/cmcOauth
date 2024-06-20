package com.cmc.demo.oauth.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "resource_assign")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})
public class ResourceAssign extends Audit<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resource_assign_id")
    private Long resourceAssignId;

    @Column(name = "resource_assign_type")
    private String resourceAssignType;

    @Column(name="resource_id")
    private String resourceId;

    @JoinColumn(name = "role_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Role role;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Users users;
}
