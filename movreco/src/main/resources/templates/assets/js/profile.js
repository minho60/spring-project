  var titles = { info: '내 정보', reviews: '내가 작성한 리뷰', posts: '내가 작성한 글', bookmarks: '찜한 목록' };
        document.querySelectorAll('.profile-tab[data-tab]').forEach(function(btn) {
            btn.addEventListener('click', function() {
                document.querySelectorAll('.profile-tab').forEach(function(b) { b.classList.remove('active'); });
                btn.classList.add('active');
                document.querySelectorAll('.tab-panel').forEach(function(p) { p.classList.remove('active'); });
                document.getElementById('tab-' + btn.dataset.tab).classList.add('active');
                document.getElementById('page-title').textContent = titles[btn.dataset.tab] || '내 정보';
            });
        });

        // 탭 스크롤 그라데이션
        (function() {
            var sidebar = document.querySelector('.profile-sidebar');
            var wrapper = document.querySelector('.profile-tab-wrapper');
            if (!sidebar || !wrapper) return;

            function updateGradients() {
                var scrollLeft = sidebar.scrollLeft;
                var maxScroll = sidebar.scrollWidth - sidebar.clientWidth;
                wrapper.classList.toggle('scroll-left', scrollLeft > 2);
                wrapper.classList.toggle('scroll-right', scrollLeft < maxScroll - 2);
            }
            sidebar.addEventListener('scroll', updateGradients);
            window.addEventListener('resize', updateGradients);
            updateGradients();
        })();