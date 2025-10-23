package vn.rentx.auth.domain.user.aggregate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.rentx.auth.domain.user.enums.AccountStatus;
import vn.rentx.auth.domain.user.enums.Role;
import vn.rentx.auth.domain.user.vo.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppUser {

    private UUID id;

    private String fullName;

    private UserName username;

    private Email email;

    private Password password;

    private ProfileImage profileImg;

    private Instant createdDate;

    private Instant lastUpdate;

    private Instant lastLogin;

    private Set<UserRole> userRoles = new HashSet<>();

    private AccountStatus accountStatus;


    /*────────── FACTORY ──────────*/

    public static AppUser register(
            String fullName,
            UserName userName,
            Email email,
            Password hashedPassword,
//                                   Set<UserRole> userRoles,
            Instant nowUtc) {


        return new AppUser(
                fullName,
                userName,
                email,
                hashedPassword,
                nowUtc != null ? nowUtc : Instant.now(),
                nowUtc != null ? nowUtc : Instant.now(),
//                userRoles,
                AccountStatus.ACTIVE
        );
    }


    public void assignRole(UserRole role) {
        Role roleEnum = Role.valueOf(role.getValue());
        if (roleEnum == Role.ADMIN || roleEnum == Role.SUPER_ADMIN) {
            throw new IllegalArgumentException("Cannot register with this role.");
        }
        this.userRoles.add(role);
    }


    /*────────── CONSTRUCTOR ──────────*/

    public AppUser(UUID id,
                   String fullName,
                   UserName username,
                   Email email,
                   Password password,
                   Instant createdDate,
                   Instant lastUpdate,
                   Set<UserRole> userRoles,
                   AccountStatus accountStatus) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.createdDate = createdDate;
        this.lastUpdate = lastUpdate;
        this.userRoles = userRoles;
        this.accountStatus = accountStatus;
    }

    public AppUser(UUID id,
                   String fullName,
                   UserName username,
                   Email email,
                   Password password,
                   Instant createdDate,
                   Instant lastUpdate,
                   AccountStatus accountStatus) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.createdDate = createdDate;
        this.lastUpdate = lastUpdate;
        this.accountStatus = accountStatus;
    }

    public AppUser(String fullName,
                   UserName username,
                   Email email,
                   Password password,
                   Instant createdDate,
                   Instant lastUpdate,
                   AccountStatus accountStatus) {
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.createdDate = createdDate;
        this.lastUpdate = lastUpdate;
        this.accountStatus = accountStatus;
    }

    /*────────── BUSINESS BEHAVIOURS ──────────*/

    public void changeEmail(Email newEmail) {
        this.email = Objects.requireNonNull(newEmail);
        touch();
    }

    public void changePassword(Password newHashedPassword) {
        this.password = Objects.requireNonNull(newHashedPassword);
        touch();
    }

    public void updateProfileImage(ProfileImage newImage) {
        this.profileImg = newImage;
        touch();
    }

    public void addRole(UserRole userRole) {
        this.userRoles.add(Objects.requireNonNull(userRole));
        touch();
    }

    public void removeRole(UserRole userRole) {
        this.userRoles.remove(userRole);
        touch();
    }

    public void recordLoginSuccess(Instant when) {
        this.lastLogin = when != null ? when : Instant.now();
    }

    public void deactivate() {
        this.accountStatus = AccountStatus.DELETED;
        touch();
    }


    /*────────── INTERNAL ──────────*/

    private void touch() {
        this.lastUpdate = Instant.now();
    }

}