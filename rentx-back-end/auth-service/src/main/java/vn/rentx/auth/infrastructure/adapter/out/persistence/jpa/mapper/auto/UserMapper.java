package vn.rentx.auth.infrastructure.adapter.out.persistence.jpa.mapper.auto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.rentx.auth.domain.user.aggregate.AppUser;
import vn.rentx.auth.infrastructure.adapter.out.persistence.jpa.entity.UserEntity;

@Mapper(
        componentModel = "spring",
        uses = UserVOMapper.class
)
public interface UserMapper {

    /*────────────── ENTITY → DOMAIN ──────────────*/
    @Mapping(target = "username", source = "username", qualifiedByName = "toUserName")
    @Mapping(target = "email",    source = "email",    qualifiedByName = "toEmail")
    @Mapping(target = "password", source = "password", qualifiedByName = "toPassword")
    @Mapping(target = "profileImg", source = "profileImg", qualifiedByName = "toProfileImage")
    @Mapping(target = "createdDate", source = "createdDate")
    @Mapping(target = "lastUpdate", source = "lastUpdate")
    @Mapping(target = "lastLogin", source = "lastLogin")
    @Mapping(target = "accountStatus", source = "accountStatus")
    AppUser toDomain(UserEntity userEntity);

    /*────────────── DOMAIN → ENTITY ──────────────*/
    @Mapping(target = "username", source = "username", qualifiedByName = "mapUsername")
    @Mapping(target = "email",    source = "email",    qualifiedByName = "mapEmail")
    @Mapping(target = "password", source = "password", qualifiedByName = "mapPassword")
    @Mapping(target = "profileImg", source = "profileImg", qualifiedByName = "mapProfileImage")
    @Mapping(target = "createdDate", source = "createdDate")
    @Mapping(target = "lastUpdate", source = "lastUpdate")
    @Mapping(target = "lastLogin", source = "lastLogin")
    @Mapping(target = "accountStatus", source = "accountStatus")
    UserEntity toEntity(AppUser appUser);

}