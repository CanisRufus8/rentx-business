package vn.rentx.auth.shared.utils;

import jakarta.servlet.http.HttpServletRequest;
import vn.rentx.auth.shared.constant.RentXCommonConst;

public class HttpRequestUtils {

    public static String extractClientIP(HttpServletRequest request) {
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        if (xForwardedFor != null && !xForwardedFor.isEmpty()) {
            return xForwardedFor.split(",")[0].trim();
        }
        return request.getRemoteAddr();
    }

    public static String getRawFingerprint(HttpServletRequest request) {
        return request.getHeader(RentXCommonConst.USER_AGENT) + RentXCommonConst.HYPHEN + request.getRemoteAddr();
    }
}
