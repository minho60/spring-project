document.addEventListener('DOMContentLoaded', function() {
    // Mobile menu toggle
    const btn = document.getElementById('menu-toggle');
    const nav = document.getElementById('main-nav');
    if (btn && nav) {
        btn.addEventListener('click', function() {
            nav.classList.toggle('nav-open');
            btn.classList.toggle('active');
        });
        window.addEventListener('resize', function() {
            if (window.innerWidth > 768) {
                nav.classList.remove('nav-open');
                btn.classList.remove('active');
            }
        });
    }

    // Profile dropdown toggle
    const profileBtn = document.getElementById('profile-btn');
    const profileMenu = document.getElementById('profile-menu');
    if (profileBtn && profileMenu) {
        profileBtn.addEventListener('click', function(e) {
            e.stopPropagation();
            profileMenu.classList.toggle('show');
        });
        document.addEventListener('click', function() {
            profileMenu.classList.remove('show');
        });
    }

    // 뒤로가기 시 새로고침 (캐시된 데이터 방지)
    window.addEventListener('pageshow', function(event) {
        if (event.persisted || (window.performance && window.performance.navigation.type === 2)) {
            window.location.reload();
        }
    });
});
