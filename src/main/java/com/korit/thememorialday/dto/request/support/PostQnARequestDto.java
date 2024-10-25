package com.korit.thememorialday.dto.request.support;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PostQnARequestDto {
    
    @NotNull
    private String questionTitle;

    @NotNull
    private String questionContents;

    @NotBlank
    private String userId;

    @NotNull
    private Date questionDay;

    @NotBlank
    private String questionStatus;

    private String answerContents;
}
