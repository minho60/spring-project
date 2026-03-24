package com.example.movie.repository;

import com.example.movie.domain.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
    List<Review> findByItemIdOrderByCreatedAtDesc(Long itemId);
    List<Review> findByMemberIdOrderByCreatedAtDesc(Long memberId);
}
