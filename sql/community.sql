
CREATE TABLE community (
	community_id              UUID	    NOT NULL 	PRIMARY KEY DEFAULT gen_random_uuid(),
	channel_id                UUID	    NOT NULL    UNIQUE,   
	community_show_notice_n   INTEGER 	NOT NULL 	DEFAULT 3,
    
    CONSTRAINT channel_community__channel_id_fk 
    FOREIGN KEY (channel_id) REFERENCES channel( channel_id )
    ON DELETE CASCADE
);

CREATE TABLE community_menu (
    menu_id	            UUID	    NOT NULL    PRIMARY KEY DEFAULT gen_random_uuid(),
    community_id	    UUID	    NOT NULL,
	menu_title          VARCHAR(64)	NOT NULL,
    menu_read_allow     BOOLEAN     NOT NULL    DEFAULT TRUE,
    menu_write_allow    BOOLEAN     NOT NULL    DEFAULT TRUE,        
    
    CONSTRAINT community_community_menu__community_id_fk 
    FOREIGN KEY (community_id) REFERENCES community( community_id )
    ON DELETE CASCADE
);

CREATE TABLE community_menu_permission (
    menu_id        UUID     NOT NULL,
    role_id        UUID     NOT NULL,
    allow_to_write BOOLEAN  NOT NULL,
    allow_to_read  BOOLEAN  NOT NULL,
    
    CONSTRAINT community_menu_community_menu_permission__menu_id_fk 
    FOREIGN KEY (menu_id) REFERENCES community_menu( menu_id )
    ON DELETE CASCADE,
    
    CONSTRAINT channel_role_community_menu__role_id_fk 
    FOREIGN KEY (role_id) REFERENCES channel_role( role_id )
    ON DELETE CASCADE
);

CREATE TABLE community_post (
	post_id    	          UUID	        NOT NULL    PRIMARY KEY DEFAULT gen_random_uuid(),
	community_id	      UUID	        NOT NULL,
    menu_id               UUID          NOT NULL,
	writer_id	          UUID	        NOT NULL,
    post_title            VARCHAR(64)   NOT NULL,
	post_content	      TEXT	        NOT NULL,
	post_comment_block	  BOOLEAN 	    NOT NULL 	DEFAULT FALSE,
	created_at            TIMESTAMP	    NOT NULL 	DEFAULT now(),
	updated_at	          TIMESTAMP     NULL,
    
    CONSTRAINT community_community_post__community_id_fk 
    FOREIGN KEY (community_id) REFERENCES community( community_id )
    ON DELETE CASCADE,
    
    CONSTRAINT community_menu_community_post__community_id_fk 
    FOREIGN KEY (menu_id) REFERENCES community_menu( menu_id )
    ON DELETE CASCADE,
    
    CONSTRAINT member_community_post__writer_id_fk 
    FOREIGN KEY (writer_id) REFERENCES member( member_id )
);

CREATE TABLE community_home (
    menu_id        UUID     NOT NULL,
    community_id   UUID     NOT NULL,
    setting_flag   BIT(16)  NOT NULL DEFAULT B'0000000000000001',
    priority       INTEGER  NOT NULL,
    thumb_size     INTEGER  NOT NULL DEFAULT 5,
    
    CONSTRAINT community_menu_community_home__menu_id_fk 
    FOREIGN KEY (menu_id) REFERENCES community_menu( menu_id )
    ON DELETE CASCADE,
    
    CONSTRAINT community_community_home__community_id_fk 
    FOREIGN KEY (community_id) REFERENCES community( community_id )
    ON DELETE CASCADE
);

CREATE TABLE community_post_history (
    post_id         UUID,
    member_id       UUID,
    history_date    TIMESTAMP DEFAULT now(),
    
    CONSTRAINT community_post_community_post_history__post_id_fk 
    FOREIGN KEY (post_id) REFERENCES community_post( post_id )
    ON DELETE CASCADE,
    
    CONSTRAINT member_community_post_history__post_id_fk 
    FOREIGN KEY (member_id) REFERENCES member( member_id )
    ON DELETE CASCADE
);
