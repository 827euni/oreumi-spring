document.addEventListener("DOMContentLoaded", function() {
    const articleId = document.getElementById("article-id").value;

    fetch(`/comments/article/${articleId}`) // 댓글 조회 API 호출
        .then(response => response.json())
        .then(comments => {
            const commentsSection = document.querySelector(".comments-section");
            if (comments.length > 0) {
                const commentsHtml = comments.map(comment => `
                    <div class="comment mb-3">
                        <div class="comment-body">
                            <p>${comment.content}</p>
                        </div>
                    </div>
                `).join('');
                commentsSection.innerHTML += commentsHtml; // HTML에 댓글 추가
            }
            else {
                // 댓글이 없을 경우, 메시지 표시
                commentsSection.innerHTML += `<p>댓글이 없습니다.</p>`;
            }
        });
});
