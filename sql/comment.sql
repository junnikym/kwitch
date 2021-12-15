CREATE TABLE comment (
    comment_id        UUID        PRIMARY KEY DEFAULT gen_random_uuid(),
    writer_id         UUID        NOT NULL,
    
    target_id         UUID        NOT NULL,
    usage             VARCHAR(32) NOT NULL,
    
    comment_text      TEXT        NOT NULL,
    created_at        TIMESTAMP   NOT NULL DEFAULT now(),
    updated_at        TIMESTAMP   NULL,
    
    CONSTRAINT member_comment__writer_id_fk 
    FOREIGN KEY (writer_id) REFERENCES member( member_id )
    ON DELETE CASCADE
);