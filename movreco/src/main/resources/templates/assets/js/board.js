document.addEventListener('DOMContentLoaded', function() {
    const btnNewPostGuest = document.getElementById('btn-new-post-guest');
    if (btnNewPostGuest) {
        btnNewPostGuest.addEventListener('click', function(e) {
            e.preventDefault();
            if (confirm('글을 작성하려면 로그인이 필요합니다. 로그인 페이지로 이동하시겠습니까?')) {
                window.location.href = '/login?login_required=true';
            }
        });
    }
});

