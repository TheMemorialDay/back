package com.korit.thememorialday.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.korit.thememorialday.entity.PopularKeywordEntity;
import com.korit.thememorialday.repository.resultSet.GetKeywordResultSet;

//* 인기 키워드 리포지토리 */

@Repository
public interface PopularKeywordRepository extends JpaRepository<PopularKeywordEntity, Integer> {

	// 핫한 키워드 조회
	@Query(value = 
		"SELECT keyword, count(*) AS cnt " +
		"FROM popular_keyword " +
		"GROUP BY keyword " +
		"ORDER BY cnt DESC " +
		"LIMIT 10 ",
		nativeQuery = true
	)
	List<GetKeywordResultSet> getKeyword();
	
}
