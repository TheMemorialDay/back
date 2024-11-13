package com.korit.thememorialday.entity.pk;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ThemaPk implements Serializable {
    
    private String thema;
    private Integer productNumber;

}
