
<aside>
💡 article 테이블만 사용했던 기존 프로젝트에서 comment 테이블도 추가하여 REST API를 구현 합니다.
데이터 접근 기술은 JPA, MyBatis 중 편한 기술로 선택해주세요.

- 댓글 정보 comment 테이블 추가 (comment 테이블 데이터도 자동 생성 되도록 INSERT INTO구문 추가)
- 댓글 정보를 조회/생성 하는 REST API 개발

</aside>

## 1. 댓글 정보 comment 테이블 추가 (기본Ver.)

해당 테이블의 데이터도 자동 생성되도록 data.sql에 INSERT INTO 구문을 추가합니다.

### comment 테이블 추가

- article 테이블과 comment 테이블은 `article.id = comment.article_id` 로 조인할 수 있는 관계(1:1)에 있습니다.

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/e8f11927-b70c-4524-9227-a3efac08e7aa/7093cb4c-4da4-481d-b546-8b5c0bfe4b21/Untitled.png)

## 2. 댓글 정보를 조회/생성 하는 REST API 개발

- POST `/comments/{articleId}` : articleId 게시글의 댓글 생성

```json
{
"body": "댓글 내용1"
}
```

- GET `/comments/{articleId}/{commentId}` :  commentId 댓글 정보 조회

```json
{
"id": 1,
"body": "댓글 내용1",
"createdAt": "2024-03-13 12:00:00"
}
```

## 심화 Ver.

### article 테이블과 comment 테이블과의 관계 (1:N)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/e8f11927-b70c-4524-9227-a3efac08e7aa/25e9a322-638c-4743-ae1a-851d0195ee13/Untitled.png)

- article 테이블과 comment 테이블은 `article.id = comment.article_id` 로 조인할 수 있는 연관 관계(1:N)에 있습니다.

- GET `/comments/{articleId}` : 하나의 게시글의 댓글(리스트) 조회

```json
// HTTP Status Code 200 (성공)응답

{
"articleId": 12345,
"title": "게시글 제목",
"content": "게시글 내용",
"createdAt": "2024-03-13 12:00:00",
"updatedAt": "2024-03-13 12:00:00",
"comments": [
{
"id": 1,
"body": "댓글 내용1",
"createdAt": "2024-03-13 12:00:00"
},
{
"id": 2,
"body": "댓글 내용2",
"createdAt": "2024-03-13 12:30:00"
},
{
"id": 3,
"body": "댓글 내용3",
"createdAt": "2024-03-13 13:00:00"
}
]
}
```