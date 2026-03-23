package com.example.movie.service;

import com.example.movie.domain.Bookmark;
import com.example.movie.domain.Member;
import com.example.movie.repository.BookmarkRepository;
import com.example.movie.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final MemberRepository memberRepository;

    public boolean isBookmarked(String username, Long itemId) {
        if (username == null) return false;
        Optional<Member> memberOpt = memberRepository.findByUsername(username);
        if (memberOpt.isPresent()) {
            return bookmarkRepository.countByMemberIdAndItemId(memberOpt.get().getId(), itemId) > 0;
        }
        return false;
    }

    public void toggleBookmark(String username, Long itemId) {
        if (username == null) return;
        Optional<Member> memberOpt = memberRepository.findByUsername(username);
        if (memberOpt.isPresent()) {
            Long memberId = memberOpt.get().getId();
            long count = bookmarkRepository.countByMemberIdAndItemId(memberId, itemId);
            if (count > 0) {
                // 이미 찜한 상태면 해제
                bookmarkRepository.deleteByMemberIdAndItemId(memberId, itemId);
            } else {
                // 찜하기
                Bookmark bookmark = new Bookmark();
                bookmark.setMemberId(memberId);
                bookmark.setItemId(itemId);
                bookmarkRepository.save(bookmark);
            }
        }
    }
}
