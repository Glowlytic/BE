package com.spss.glowlytic.entity;

import com.spss.glowlytic.enums.RoleName;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", length = 20)
    private RoleName name;

    @Lob
    private String description;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> users;
}
