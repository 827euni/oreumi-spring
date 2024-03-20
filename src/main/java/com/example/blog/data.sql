CREATE TABLE article (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         title VARCHAR(255) NOT NULL,
                         content VARCHAR(255) NOT NULL,
                         created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                         updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO article (title, content, created_at, updated_at) VALUES ('제목1', '내용1', NOW(), NOW());
INSERT INTO article (title, content, created_at, updated_at) VALUES ('제목2', '내용2', NOW(), NOW());
INSERT INTO article (title, content, created_at, updated_at) VALUES ('제목3', '내용3', NOW(), NOW());



