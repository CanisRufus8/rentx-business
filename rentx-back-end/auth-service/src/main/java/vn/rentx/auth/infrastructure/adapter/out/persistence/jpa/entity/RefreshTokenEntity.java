package vn.rentx.auth.infrastructure.adapter.out.persistence.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import vn.rentx.auth.domain.auth.aggregate.RefreshToken;
import vn.rentx.auth.domain.auth.enums.TokenStatus;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "REFRESH_TOKEN", uniqueConstraints = {@UniqueConstraint(name = "uk_token_hash", columnNames = "token_hash")})
public class RefreshTokenEntity {

    @Id
    @Column(name = "id")
    @UuidGenerator
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "family_id", nullable = false)
    private UUID familyId;

    @Lob
    @Column(name = "token_hash", nullable = false)
    private byte[] tokenHash;

    @Column(length = 200)
    private String fingerprint;

    @Column(name = "issued_at", nullable = false)
    private Instant issuedAt;

    @Column(name = "expires_at", nullable = false)
    private Instant expiresAt;

    @Column(name = "use_at")
    private Instant usedAt;

    @Column(name = "revoked_at")
    private Instant revokedAt;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TokenStatus status;

    public RefreshTokenEntity toEntity(RefreshToken d) {
        RefreshTokenEntity e = new RefreshTokenEntity();
        e.setId(d.getId());
        e.setUserId(d.getUserId());
        e.setFamilyId(d.getFamilyId());
        e.setTokenHash(d.getTokenHash());
        e.setFingerprint(d.getFingerprint());
        e.setIssuedAt(d.getIssuedAt());
        e.setExpiresAt(d.getExpiresAt());
        e.setUsedAt(d.getUsedAt());
        e.setRevokedAt(d.getRevokedAt());
        e.setStatus(d.getStatus());
        return e;
    }

    public RefreshToken toDomain() {
        return new RefreshToken(this.getId(),
                this.getUserId(),
                this.getFamilyId(),
                this.getTokenHash(),
                this.getFingerprint(),
                this.getIssuedAt(),
                this.getExpiresAt(),
                this.getUsedAt(),
                this.getRevokedAt(),
                this.getStatus());
    }

}
