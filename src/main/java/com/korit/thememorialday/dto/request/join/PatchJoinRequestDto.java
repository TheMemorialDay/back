package com.korit.thememorialday.dto.request.join;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchJoinRequestDto {
    private String permission;
    private String businessNumber;
    private String businessOpendate;
}
