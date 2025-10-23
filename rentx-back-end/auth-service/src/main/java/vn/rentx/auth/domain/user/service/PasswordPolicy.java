package vn.rentx.auth.domain.user.service;

public interface PasswordPolicy {

    public boolean isSatisfiedBy(String password);

    boolean isStrong(String password);
}
