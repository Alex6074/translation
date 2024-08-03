CREATE TABLE translation_record (
    id BIGSERIAL PRIMARY KEY,
    ip VARCHAR(255),
    input_text VARCHAR(255),
    translated_text VARCHAR(255)
);