package com.korit.thememorialday.common.object.home;

import java.util.ArrayList;
import java.util.List;

import com.korit.thememorialday.repository.resultSet.GetKeywordResultSet;

import lombok.Getter;

//* 가져올 키워드 객체 (키워드, 개수) */

@Getter
public class Keyword {

	private String keyword;
	private Integer cnt;

	private Keyword(GetKeywordResultSet resultSet) {
		this.keyword = resultSet.getKeyword();
		this.cnt = resultSet.getCnt();
	};

	public static List<Keyword> getList(List<GetKeywordResultSet> resultSets) {
		List<Keyword> keywords = new ArrayList<>();

		for (GetKeywordResultSet resultSet: resultSets) {
			Keyword keyword = new Keyword(resultSet);
			keywords.add(keyword);
		};

		return keywords;
	};
	
}
