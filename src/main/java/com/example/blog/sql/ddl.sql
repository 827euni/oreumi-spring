create table article (
                         id BIGINT AUTO_INCREMENT primary key,
                         title varchar(255) not null,
                         content varchar(255) not null
);

INSERT INTO article (title, content) VALUES ('블로그 제목', '블로그 내용');
INSERT INTO article (title, content) VALUES ('블로그 제목2', '블로그 내용2');
INSERT INTO article (title, content) VALUES ('블로그 제목3', '블로그 내용3');