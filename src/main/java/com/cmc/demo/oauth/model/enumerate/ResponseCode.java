package com.cmc.demo.oauth.model.enumerate;

import org.springframework.lang.Nullable;

public enum ResponseCode {

	OK(2000000, Series.SUCCESSFUL),
    CREATED(2010000, Series.SUCCESSFUL),
    NO_CONTENT(2040000, Series.INFORMATIONAL),
    UNAUTHORIZED(4010000, Series.CLIENT_ERROR),
    LOGIN_FAILED(4010001, Series.CLIENT_ERROR),
    INVALID_TOKEN(4010002, Series.CLIENT_ERROR),
    ETOKEN_IS_UNACTIVATED(4010003, Series.CLIENT_ERROR),
    ETOKEN_IS_ACTIVATED_ON_ANOTHER_DEVICE(4010004, Series.CLIENT_ERROR),
    NOT_FOUND(4040000, Series.CLIENT_ERROR),
    USER_NAME_OR_PASSWORD_NOT_CORRECT(4040001, Series.CLIENT_ERROR),
    REGISTER_DEVICE_FIRST_TIME_OTP_INCORRECT(4040002, Series.CLIENT_ERROR),
    USER_IDENTITY_NOT_FOUND(4040003, Series.CLIENT_ERROR),
    FORGOT_PASSWORD_SESSION_NOT_FOUND(4040004, Series.CLIENT_ERROR),
    ETOKEN_NOT_FOUND(4040005, Series.CLIENT_ERROR),
    PASSWORD_IS_INCORRECT(4040006, Series.CLIENT_ERROR),
    USER_DOES_NOT_EXIST(4040007, Series.CLIENT_ERROR),
    IDENTITY_PAPERS_NOT_FOUND(4040008, Series.CLIENT_ERROR),
    REFERRAL_CODE_NOT_FOUND(4040009, Series.CLIENT_ERROR),
    UPDATE_USER_INFO_SESSION_NOT_FOUND(4040010, Series.CLIENT_ERROR),
    CONFLICT(4090000, Series.CLIENT_ERROR),
    PASSWORD_NOT_MATCH(4090001, Series.CLIENT_ERROR),
    USER_NAME_EXISTED(4090002, Series.CLIENT_ERROR),
    ID_CARD_EXISTED(4090003, Series.CLIENT_ERROR),
    PHONE_NUMBER_EXISTED(4090004, Series.CLIENT_ERROR),
    PHONE_NUMBER_USED_BY_ANOTHER(4090005, Series.CLIENT_ERROR),
    EMAIL_EXISTED(4090006, Series.CLIENT_ERROR),
    EMAIL_USED_BY_ANOTHER(4090007, Series.CLIENT_ERROR),
    BAD_REQUEST(4000000, Series.CLIENT_ERROR),
    INVALID_OTP(4000001, Series.CLIENT_ERROR),
    RECOVERY_IDENTITY_NOT_SUPPORT(4000002, Series.CLIENT_ERROR),
    INVALID_REFRESH_TOKEN(4000003, Series.CLIENT_ERROR),
    MUST_VERIFY_OTP_FIRST(4000004, Series.CLIENT_ERROR),
    INVALID_ID_CARD(4000005, Series.CLIENT_ERROR),
    INVALID_USER_NAME(4000006, Series.CLIENT_ERROR),
    INVALID_USER_AGE(4000007, Series.CLIENT_ERROR),
    CANNOT_EMPTY( 10001, Series.CLIENT_ERROR),
    FEE_RULE_CODE_DUPLICATE(4000008, Series.CLIENT_ERROR);


	
    private final int value;
    private final Series series;

    public static ResponseCode valueOf(int value) {
        ResponseCode[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            ResponseCode responseCode = var1[var3];
            if (responseCode.getValue() == value) {
                return responseCode;
            }
        }

        return null;
    }

    public static boolean equal(Integer code, ResponseCode responseCode) {
        if (code != null && responseCode != null) {
            ResponseCode responseCode1 = valueOf(code);
            return responseCode1 == responseCode;
        } else {
            return true;
        }
    }

    private ResponseCode(int value, Series series) {
        this.value = value;
        this.series = series;
    }

    public int getValue() {
        return this.value;
    }

    public Series getSeries() {
        return this.series;
    }

    public static enum Series {
        SUCCESSFUL(0),
        INFORMATIONAL(1),
        REDIRECTION(2),
        CLIENT_ERROR(3),
        SERVER_ERROR(4),
        CORE_BANK_ERROR(5),
        SMART_BANK_ERROR(6);

        private final int value;

        private Series(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }

        @Nullable
        public static Series resolve(int statusCode) {
            int seriesCode = statusCode / 100;
            Series[] var2 = values();
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                Series series = var2[var4];
                if (series.value == seriesCode) {
                    return series;
                }
            }

            return null;
        }
    }
}