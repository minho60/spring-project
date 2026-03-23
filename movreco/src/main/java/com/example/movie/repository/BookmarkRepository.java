package com.example.movie.repository;

import com.example.movie.domain.Bookmark;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkRepository extends CrudRepository<Bookmark, Long> {

    @Query("SELECT COUNT(*) FROM bookmark WHERE member_id = :memberId AND item_id = :itemId")
    long countByMemberIdAndItemId(@Param("memberId") Long memberId, @Param("itemId") Long itemId);

    @Modifying
    @Query("DELETE FROM bookmark WHERE member_id = :memberId AND item_id = :itemId")
    void deleteByMemberIdAndItemId(@Param("memberId") Long memberId, @Param("itemId") Long itemId);
}
