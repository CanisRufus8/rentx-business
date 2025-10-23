package vn.rentx.auth.infrastructure.adapter.out.persistence.jpa.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "PERMISSION")
public class Permission {

    @Id
    @UuidGenerator
    @Column(name="permission_id")
    private UUID id;

    @Column(unique = true, nullable = false)
    private String name; // eg: CAN_VIEW_USER, CAN_EDIT_USER

    @Column(length = 255)
    private String description;

    @ManyToMany(mappedBy = "permissions")
    private Set<RoleEntity> roles = new HashSet<>();

}
