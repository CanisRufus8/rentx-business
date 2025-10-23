package vn.rentx.auth.application.service.auth.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.rentx.auth.application.port.in.auth.command.RefreshTokenUseCase;
import vn.rentx.auth.application.port.out.TokenRepositoryPort;
import vn.rentx.auth.domain.auth.aggregate.RefreshToken;
import vn.rentx.auth.domain.auth.enums.TokenStatus;
import vn.rentx.auth.shared.constant.RentXCommonConst;
import vn.rentx.auth.shared.exception.instance.GlobalServerException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService implements RefreshTokenUseCase {

    /*────────── DI ──────────*/
    private final TokenRepositoryPort tokenRepositoryPort;


    /*────────── PROPERTIES ──────────*/
    private final Instant CURRENT_TIME = Instant.now();

    @Override
    public String createRefreshToken(UUID userId, String rawFingerprint) {

        try {
            UUID familyId = UUID.randomUUID(); // new family for new login
            Instant expiresAt = CURRENT_TIME.plus(Duration.ofDays(30)); // Refresh token will expire in 30 days
            String fingerprint = Base64.getEncoder().encodeToString(
                    MessageDigest.getInstance("SHA-256")
                            .digest(rawFingerprint.getBytes(StandardCharsets.UTF_8)));

            // Create plain token (return to client)
            String refreshPlain = UUID.randomUUID() + RentXCommonConst.DOT + UUID.randomUUID();

            // Hash for saving in DB
            byte[] hash = hashToken(refreshPlain);

            RefreshToken domain = RefreshToken.builder()
                    .userId(userId)
                    .familyId(familyId)
                    .tokenHash(hash)
                    .fingerprint(fingerprint)
                    .issuedAt(CURRENT_TIME)
                    .expiresAt(expiresAt)
                    .status(TokenStatus.ACTIVE)
                    .build();

            // Save in database
            tokenRepositoryPort.saveRefreshToken(domain);

            return refreshPlain;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new GlobalServerException("Error creating refresh token");
        }
    }

    // ✅ Hàm rotate token
//    public String rotate(String refreshPlain, String fingerprint) {
//        byte[] hash = hashToken(refreshPlain);
//        RefreshToken domain = tokenRepositoryPort.findRefreshTokenByTokenHash(hash);
//
//        // Check expired
//        if (domain.isExpired(CURRENT_TIME)) {
//            throw new RuntimeException("Refresh token expired");
//        }
//
//        // Check reuse
//        if (domain.getStatus() == TokenStatus.USED || domain.getStatus() == TokenStatus.REVOKED) {
//            // -> revoke toàn bộ family
//            repository.findAllByFamilyId(token.getFamilyId())
//                    .forEach(t -> {
//                        t.setStatus(TokenStatus.REVOKED);
//                        t.setRevokedAt(Instant.now());
//                        repository.save(t);
//                    });
//            throw new RuntimeException("Refresh token reuse detected, family revoked");
//        }
//
//        // Mark old token as USED
//        token.markUsed();
//        entity = entity.toEntity(token);
//        entity.setUsedAt(Instant.now());
//        repository.save(entity);
//
//        // Tạo token mới trong cùng family
//        String newPlain = UUID.randomUUID() + "." + UUID.randomUUID();
//        byte[] newHash = hashToken(newPlain);
//
//        RefreshToken newToken = RefreshToken.builder()
//                .id(UUID.randomUUID())
//                .userId(token.getUserId())
//                .familyId(token.getFamilyId())
//                .tokenHash(newHash)
//                .fingerprint(fingerprint)
//                .issuedAt(Instant.now())
//                .expiresAt(Instant.now().plus(Duration.ofDays(30)))
//                .status(TokenStatus.ACTIVE)
//                .build();
//
//        repository.save(new RefreshTokenEntity().toEntity(newToken));
//
//        return newPlain;
//    }

//    // ✅ Hàm revoke tất cả family (dùng khi logout)
//    public void revokeFamily(UUID familyId) {
//        List<RefreshTokenEntity> tokens = repository.findAllByFamilyId(familyId);
//        tokens.forEach(t -> {
//            t.setStatus(TokenStatus.REVOKED);
//            t.setRevokedAt(Instant.now());
//            tokenRepositoryPort.saveRefreshToken(t);
//        });
//    }

    // Hash token
    private byte[] hashToken(String refreshPlain) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(refreshPlain.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
