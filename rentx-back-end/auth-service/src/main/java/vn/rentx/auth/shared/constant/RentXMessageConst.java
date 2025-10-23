package vn.rentx.auth.shared.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RentXMessageConst {

    /*────────── EXCEPTION MESSAGES ──────────*/

    public static final String SERVER_ERROR = "Server error, please try again.";
    public static final String ENTITY_NOT_FOUND = "Entity not found.";
    public static final String PRODUCT_NOT_FOUND = "Product not found.";
    public static final String PRODUCT_IMAGE_NOT_FOUND = "Product Image not found.";
    public static final String CATEGORY_NOT_FOUND = "Product not found.";
    public static final String PRODUCT_TYPE_NOT_FOUND = "Product type not found.";
    public static final String USER_NOT_FOUND = "User with username %s not found.";
    public static final String INVALID_EMAIL = "Email %s is invalid.";
    public static final String EMPLOYEE_NOT_FOUND = "Employee not found.";
    public static final String CUSTOMER_NOT_FOUND = "Customer not found.";
    public static final String SENDING_MAIL_FAILED = "Sending mail failed.";
    public static final String INVALID_TOKEN = "Invalid token.";
    public static final String BLACK_LIST_TOKEN = "Token is revoked.";
    public static final String ACCESS_TOKEN_EXPIRED = "Access token expired.";

    /*────────── ACTION RESULT MESSAGES ──────────*/

    public static final String CREATE_USER_SUCCESS = "Success: User has been created.";

}
