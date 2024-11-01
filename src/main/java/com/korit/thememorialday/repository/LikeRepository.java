package com.korit.thememorialday.repository;

import com.korit.thememorialday.entity.LikeEntity;
import com.korit.thememorialday.entity.pk.LikePk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, LikePk> {

  LikeEntity findByUserIdAndStoreNumber(String userId, Integer storeNumber);

}
