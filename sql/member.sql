CREATE TABLE member (
	member_id		        UUID          PRIMARY KEY DEFAULT gen_random_uuid(),
	member_email            VARCHAR(64)   NOT NULL,
	member_pw 		        VARCHAR(64)   NOT NULL,
	member_name 	        VARCHAR(32)	  NOT NULL,
    member_alias            VARCHAR(32)   NULL,
    member_message          VARCHAR(1000) NULL,
    member_phone            VARCHAR(20)   NOT NULL,
    member_birth            DATE          NULL,
    profile_image           UUID          NULL,    -- UNIQUE    
    background_image        UUID          NULL,    -- UNIQUE    
	created_at 		        TIMESTAMP	  NOT NULL DEFAULT now(),
    updated_at              TIMESTAMP     NULL,
    
    UNIQUE(profile_image, background_image)
);

ALTER TABLE member DROP COLUMN member_profile_image; 
ALTER TABLE member ADD COLUMN member_background_image BOOLEAN NULL

# -------------------------
# Member System Role
# -------------------------

# authority 가 낮을 수록 많은 권한을 가짐

CREATE TABLE system_role (
    role_id     UUID          PRIMARY KEY DEFAULT gen_random_uuid(),
    authority   VARCHAR(64)   NOT NULL    DEFAULT '',
    level       INTEGER        NOT NULL    DEFAULT 0 
);

CREATE TABLE member_system_role (
    memeber_id  UUID          NOT NULL,
    role_id     UUID          NOT NULL,
    
    CONSTRAINT member_system_role__memeber_id_fk 
    FOREIGN KEY (memeber_id) REFERENCES member( member_id )
    ON DELETE CASCADE,
    
    CONSTRAINT member_system_role__role_id_fk 
    FOREIGN KEY (role_id) REFERENCES system_role( role_id )
    ON DELETE CASCADE
);