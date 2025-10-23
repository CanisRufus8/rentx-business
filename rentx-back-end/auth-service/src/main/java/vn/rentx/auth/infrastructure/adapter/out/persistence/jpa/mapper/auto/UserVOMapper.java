package vn.rentx.auth.infrastructure.adapter.out.persistence.jpa.mapper.auto;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import vn.rentx.auth.domain.user.vo.Email;
import vn.rentx.auth.domain.user.vo.Password;
import vn.rentx.auth.domain.user.vo.ProfileImage;
import vn.rentx.auth.domain.user.vo.UserName;

@Mapper(componentModel = "spring")
public interface UserVOMapper {

    /* ===== DOMAIN → ENTITY (VO → String) ===== */
    @Named("mapUsername")
    default String mapUsername(UserName username) {
        return username.getValue();
    }

    @Named("mapEmail")
    default String mapEmail(Email email) {
        return email != null ? email.getValue() : null;
    }

    @Named("mapPassword")
    default String mapPassword(Password password) {
        return password.getHashedValue();
    }

    @Named("mapProfileImage")
    default String mapProfileImage(ProfileImage profileImg) {
        return profileImg != null ? profileImg.getUrl() : null;
    }

    /* ===== ENTITY → DOMAIN (String → VO) ===== */
    @Named("toUserName")
    default UserName toUserName(String username) {
        return new UserName(username);
    }

    @Named("toEmail")
    default Email toEmail(String email) {
        return email != null ? new Email(email) : null;
    }

    @Named("toPassword")
    default Password toPassword(String passwordHash) {
        return new Password(passwordHash);
    }

    @Named("toProfileImage")
    default ProfileImage toProfileImage(String url) {
        return url != null ? new ProfileImage(url) : null;
    }
}
