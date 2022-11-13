DROP TABLE IF EXISTS code_info;

CREATE TABLE code_info
(
    id       BIGINT NOT NULL,
    code     CHAR(36) NULL DEFAULT NULL,
    file_path VARCHAR(20) NULL DEFAULT NULL,
    PRIMARY KEY (id)
);
