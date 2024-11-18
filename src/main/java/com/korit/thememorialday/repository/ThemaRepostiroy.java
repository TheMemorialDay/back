package com.korit.thememorialday.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.korit.thememorialday.entity.ThemaEntity;
import com.korit.thememorialday.entity.pk.ThemaPk;
import com.korit.thememorialday.repository.resultSet.GetThemeResultSet;

import jakarta.transaction.Transactional;

public interface ThemaRepostiroy extends JpaRepository<ThemaEntity, ThemaPk> {

    List<ThemaEntity> findByProductNumber(Integer productNubmer);

    @Transactional
    void deleteByProductNumber(Integer productNumber);

    //* 인기 테마 조회 */
    @Query(value = 
		"SELECT thema, count(*) AS cnt " +
		"FROM thema " +
		"GROUP BY thema " +
		"ORDER BY cnt DESC " +
		"LIMIT 5 ",
		nativeQuery = true
	)
	List<GetThemeResultSet> getTheme();

}
