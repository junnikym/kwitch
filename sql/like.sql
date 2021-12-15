CREATE TABLE liked (
    usage       VARCHAR(32)    NOT NULL,
    is_unliked  BOOLEAN        NOT NULL DEFAULT FALSE,
    giver_id    UUID           NOT NULL,
    target_id   UUID           NOT NULL,
    
    CONSTRAINT member_liked__giver_id_fk 
    FOREIGN KEY (giver_id) REFERENCES member( member_id )
    ON DELETE CASCADE
);