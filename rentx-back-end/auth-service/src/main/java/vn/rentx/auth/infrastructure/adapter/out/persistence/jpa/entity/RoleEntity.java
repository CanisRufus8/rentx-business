package vn.rentx.auth.infrastructure.adapter.out.persistence.jpa.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import vn.rentx.auth.domain.user.vo.UserRole;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ROLE")
public class RoleEntity {
    @Id
    @UuidGenerator
    @Column(name="id")
    private UUID id;

    @Column(unique = true, nullable = false)
    private String name; // eg: ADMIN, USER, MODERATOR

    @Column(length = 500)
    private String description;

    @ManyToMany(mappedBy = "roles")
    private Set<UserEntity> users = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "ROLE_PERMISSION",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<Permission> permissions = new HashSet<>();

    public UserRole toDomain() {
        return new UserRole(String.valueOf(this.name));
    }

}
