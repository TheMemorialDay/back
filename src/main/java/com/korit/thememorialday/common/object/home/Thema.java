package com.korit.thememorialday.common.object.home;

import java.util.ArrayList;
import java.util.List;

import com.korit.thememorialday.repository.resultSet.GetThemeResultSet;

import lombok.Getter;

//* 가져올 테마 객체 (키워드, 개수) */

@Getter
public class Thema {

	private String thema;
	private Integer cnt;

	private Thema(GetThemeResultSet resultSet) {
		this.thema = resultSet.getThema();
		this.cnt = resultSet.getCnt();
	};

	public static List<Thema> getList(List<GetThemeResultSet> resultSets) {
		List<Thema> themas = new ArrayList<>();

		for (GetThemeResultSet resultSet: resultSets) {
			Thema thema = new Thema(resultSet);
			themas.add(thema);
		}

		return themas;
	};
	
}
