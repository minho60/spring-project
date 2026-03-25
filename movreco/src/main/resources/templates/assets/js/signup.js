document.addEventListener('DOMContentLoaded', function() {
    const btnCheck = document.getElementById('btn-check-username');
    const inputUsername = document.getElementById('username');
    const msgDiv = document.getElementById('username-msg');
    const validFlag = document.getElementById('is-username-valid');
    const form = document.querySelector('form');

    if (btnCheck) {
        btnCheck.addEventListener('click', async () => {
            const username = inputUsername.value.trim();
            if(!username) {
                msgDiv.textContent = '아이디를 입력해주세요.';
                msgDiv.className = 'feedback-msg text-danger';
                return;
            }
            
            try {
                const response = await fetch(`/api/check-username?username=${encodeURIComponent(username)}`);
                const isTaken = await response.json();
                
                if (isTaken) {
                    msgDiv.textContent = '❌ 이미 사용 중인 아이디입니다.';
                    msgDiv.className = 'feedback-msg text-danger';
                    validFlag.value = 'false';
                } else {
                    msgDiv.textContent = '✅ 멋진 아이디네요! 사용 가능합니다.';
                    msgDiv.className = 'feedback-msg text-success';
                    validFlag.value = 'true';
                }
            } catch (err) {
                msgDiv.textContent = '서버 통신 오류가 발생했습니다.';
                msgDiv.className = 'feedback-msg text-danger';
            }
        });
    }

    if (inputUsername) {
        inputUsername.addEventListener('input', () => {
            validFlag.value = 'false';
            msgDiv.textContent = '';
        });
    }

    if (form) {
        form.addEventListener('submit', (e) => {
            if (validFlag.value !== 'true') {
                e.preventDefault();
                alert('회원가입 전 반드시 아이디 중복 확인을 통과해야 합니다.');
                inputUsername.focus();
            }
        });
    }
});
