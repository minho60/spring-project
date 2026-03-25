document.addEventListener('DOMContentLoaded', function() {
    document.querySelectorAll('.review-content').forEach(function(el) {
        const full = el.getAttribute('data-full');
        if (full && full.length > 150) {
            const truncated = full.substring(0, 150) + '...';
            el.textContent = truncated;
            const btn = document.createElement('button');
            btn.textContent = '더보기';
            btn.className = 'btn-toggle-review';
            btn.addEventListener('click', function() {
                if (btn.textContent === '더보기') {
                    el.textContent = full;
                    btn.textContent = '접기';
                } else {
                    el.textContent = truncated;
                    btn.textContent = '더보기';
                }
                el.parentNode.insertBefore(btn, el.nextSibling);
            });
            el.parentNode.insertBefore(btn, el.nextSibling);
        }
    });

    // 찜하기 버튼 비로그인 처리
    const btnBookmarkGuest = document.getElementById('btn-bookmark-guest');
    if (btnBookmarkGuest) {
        btnBookmarkGuest.addEventListener('click', function() {
            if (confirm('찜하기 기능은 로그인이 필요합니다. 로그인 페이지로 이동하시겠습니까?')) {
                window.location.href = '/login?login_required=true';
            }
        });
    }
});
