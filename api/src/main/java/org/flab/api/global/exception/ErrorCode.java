package org.flab.api.global.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // common
    INVALID_INPUT_VALUE("COMMON-0001", "유효하지 않은 입력값입니다")
    , METHOD_NOT_ALLOWED("COMMON-0002", "허용되지 않은 Method 요청입니다")

    // event
    , EVENT_NOT_FOUND("EVENT-0001", "존재하지 않는 이벤트입니다.")
    , INVALID_EVENT_TYPE("EVENT-0002", "유효하지 않은 이벤트 타입입니다.")
    , EVENT_HAS_NO_SHOW("EVENT-0003", "이벤트의 회차가 존재하지 않습니다.")
    , INVALID_EVENT_SHOW("EVENT-0004", "이벤트와 회차 정보가 일치하지 않습니다.")
    , NULL_PERIOD("EVENT-0005", "시작일시 또는 종료일시가 null 입니다.")
    , NULL_IMAGE("EVENT-0006", "공연의 이미지 데이터가 null 입니다. ")

    // show
    , SHOW_NOT_FOUND("SHOW-0001", "존재하지 않는 회차입니다.")
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
