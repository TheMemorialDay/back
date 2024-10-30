package com.korit.thememorialday.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.korit.thememorialday.entity.pk.ThemaPk;

import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "thema")
@IdClass(ThemaPk.class)
public class ThemaEntity {
    
    @Id
    private String thema;
    @Id
    private Integer productNumber;
    
}
