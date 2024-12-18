package teamyc.recordpet.domain.user.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {
    DOCTOR("DOCTOR", "수의사"),
    MEMBER("MEMBER", "회원"),
    MANAGER("MANAGER", "관리자");

    private final String authority;
    private final String value;
}
