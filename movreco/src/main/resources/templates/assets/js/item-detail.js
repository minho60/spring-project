document.querySelectorAll('.review-content').forEach(function(el) {
    var full = el.getAttribute('data-full');
    if (full && full.length > 150) {
        var truncated = full.substring(0, 150) + '...';
        el.textContent = truncated;
        var btn = document.createElement('button');
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
