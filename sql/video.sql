CREATE TABLE video (
	video_id		       UUID          PRIMARY KEY DEFAULT gen_random_uuid(),
    video_title            VARCHAR(64)   NOT NULL,
    video_text             TEXT          NULL,
    uploader_id            UUID          NOT NULL,
    
    video_name             VARCHAR(255)  NOT NULL,
    video_md5              VARCHAR(32)   NOT NULL,
    video_path             VARCHAR(255)  NOT NULL,
    video_type             VARCHAR(16)   NOT NULL,
    video_size             INTEGER       NOT NULL,
    
    created_at             DATE          NOT NULL DEFAULT now(),
    updated_at             DATE          NULL,
    
    CONSTRAINT member_video__uploader_id_fk 
    FOREIGN KEY (uploader_id) REFERENCES member( member_id )
    ON DELETE CASCADE
);

ALTER TABLE image DROP COLUMN image_path; 

ALTER TABLE image ADD COLUMN image_path VARCHAR(255) NOT NULL;