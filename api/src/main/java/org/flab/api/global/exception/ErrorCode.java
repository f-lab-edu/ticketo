package org.flab.api.global.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // common
    INVALID_INPUT_VALUE("COMMON-0001", "유효하지 않은 입력값입니다")
    , METHOD_NOT_ALLOWED("COMMON-0002", "허용되지 않은 Method 요청입니다")

    // category
    , TOP_CATEGORY_NOT_FOUND("CATEGORY-0001", "상위 카테고리가 존재하지 않습니다.")

    // event
    , EVENT_NOT_FOUND("EVENT-0001", "존재하지 않는 이벤트입니다.")
    , EVENT_TYPE_NOT_FOUND("EVENT-0002", "유효하지 않은 이벤트 타입입니다.")
    ;

    private final String message;
    private final String code;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ErrorCode valueOfOrNull(String name) {
        try {
            return valueOf(name);
        } catch (Exception e) {
            return null;
        }
    }
}
