package com.ahn.game_homework.domain.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * 회원가입 요청 DTO.
 * record: 불변 데이터 클래스. 필드 접근은 email() 형태 (getEmail() 아님).
 */
public record SignupRequest(

        @Email(message = "올바른 이메일 형식이 아닙니다.")
        @NotBlank(message = "이메일은 필수입니다.") // @NotNull과 달리 빈 문자열/공백도 거부
        String email,

        @NotBlank(message = "비밀번호는 필수입니다.")
        @Size(min = 8, max = 100, message = "비밀번호는 8자 이상 100자 이하로 입력해주세요")
        String password,

        @NotBlank(message = "닉네임은 필수입니다.")
        @Size(max = 20, message = "닉네임은 20자 이하로 입력해주세요")
        String nickname
) {
}
