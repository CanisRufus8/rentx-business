package vn.rentx.auth.domain.user.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.rentx.auth.domain.user.shared.ValueObject;

@Getter
@Setter
@ToString
public final class ProfileImage extends ValueObject<ProfileImage> {

    private final String url;

    public ProfileImage(String url) {
        if (url == null || url.isBlank()) {
            throw new IllegalArgumentException("Profile image URL must not be blank");
        }
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            throw new IllegalArgumentException("Profile image URL must be valid");
        }
        this.url = url;
    }

    @Override
    protected boolean equalsCore(ProfileImage other) {
        return this.url.equals(other.url);
    }

    @Override
    protected int hashCodeCore() {
        return 0;
    }

}
