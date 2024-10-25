package com.korit.thememorialday.entity.support;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="notice")
@Table(name="notice")
public class NoticeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer noticeNumber;

    @Column(name = "notice_title", length = 50, nullable = false)
    private String noticeTitle;

    @Column(name = "notice_contents", nullable = false, columnDefinition = "TEXT")
    private String noticeContents;

    @Column(name = "notice_day", nullable = false)
    private Date noticeDay;
}
