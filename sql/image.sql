CREATE TABLE image (
	image_id		       UUID          PRIMARY KEY DEFAULT gen_random_uuid(),
    uploader_id            UUID          NOT NULL,
    video_path             VARCHAR(255)  NOT NULL,
    video_extension        VARCHAR(8)    NOT NULL,
    video_usage            VARCHAR(64)   NOT NULL,
    created_at             DATE          NOT NULL DEFAULT now(),
    updated_at             DATE          NULL,
    
    CONSTRAINT member_image__uploader_id_fk 
    FOREIGN KEY (uploader_id) REFERENCES member( member_id )
    ON DELETE CASCADE
);

ALTER TABLE image DROP COLUMN image_path; 

ALTER TABLE image ADD COLUMN image_path VARCHAR(255) NOT NULL;





# -------------------------
# Community Post Image
# -------------------------

CREATE TABLE community_post_image (
    image_id    UUID NOT NULL,
    post_id     UUID NOT NULL,
    
    CONSTRAINT community_post_image_image__image_id_fk 
    FOREIGN KEY (image_id) REFERENCES image( image_id )
    ON DELETE CASCADE,
    
    CONSTRAINT community_post_image_community_post__image_id_fk 
    FOREIGN KEY (post_id) REFERENCES community_post( post_id )
    ON DELETE CASCADE
);