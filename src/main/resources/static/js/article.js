const deleteButton = document.getElementById('delete-btn');
const modifyButton = document.getElementById('modify-btn');
const createButton = document.getElementById('create-btn');

// 버튼을 선택해서 deleteButton 변수에 저장
if (deleteButton) {
    deleteButton.addEventListener('click', event => {
        let id = document.getElementById('article-id').value;
        fetch(`/api/articles/${id}`, {
            method: 'DELETE' // api/articles/ 경로에 id를 추가하여 특정 기사를 삭제하도록 서버에 요청합
        }).then(() => { // DELETE 요청을 처리한 후 실행될 코드
            alert('삭제가 완료되었습니다');
            location.replace('/articles');
            // 현재페이지를 기사목록 페이지로 변경함
        });
    });
}

if (modifyButton) {
    // 클릭 이벤트가 감지되면 수정 API 요청
    modifyButton.addEventListener('click', event => {
        let params = new URLSearchParams(location.search);
        let id = params.get('id');

        fetch(`/api/articles/${id}`, {
            method: 'PUT',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById('title').value,
                content: document.getElementById('content').value
            })
        }).then(() => {
            alert('수정이 완료되었습니다');
            location.replace(`/articles/${id}`);
        }).catch(error => {
            alert('수정 API 호출 오류:');
        })
    })
}

if (createButton) {
    createButton.addEventListener('click', event => {
        fetch(`/api/articles`, {
            method: 'POST',
            headers: {
                "Content-Type": "application/json"
            },
            body : JSON.stringify({
                title: document.getElementById('title').value,
                content: document.getElementById('content').value
            }),
        }).then(() => {
            alert('등록 완료되었습니다');
            location.replace("/articles");
        })
    })
}