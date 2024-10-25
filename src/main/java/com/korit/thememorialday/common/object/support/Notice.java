package com.korit.thememorialday.common.object.support;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.korit.thememorialday.entity.support.NoticeEntity;

import lombok.Getter;

@Getter
public class Notice {
    private Integer noticeNumber;
    private String noticeTitle;
    private String noticeContents;
    private Date noticeDay;

    private Notice(NoticeEntity noticeEntity) {
        this.noticeNumber = noticeEntity.getNoticeNumber();
        this.noticeTitle = noticeEntity.getNoticeTitle();
        this.noticeContents = noticeEntity.getNoticeContents();
        this.noticeDay = noticeEntity.getNoticeDay();
    }

    public static List<Notice> getList(List<NoticeEntity> noticeEntities) {
        List<Notice> notices = new ArrayList<>();
        for (NoticeEntity noticeEntity : noticeEntities) {
            Notice notice = new Notice(noticeEntity);
            notices.add(notice);
        }
        return notices;
    }
}
