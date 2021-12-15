CREATE TABLE channel (
	channel_id	        UUID	        NOT NULL 	PRIMARY KEY DEFAULT gen_random_uuid(),
	owner_id	        UUID	        NOT NULL    UNIQUE,
	channel_title	    VARCHAR(64)	    NOT NULL,
	channel_message     VARCHAR(3000)	NULL    	DEFAULT '',
	created_at	        TIMESTAMP       NOT NULL 	DEFAULT now(),
	updated_at	        TIMESTAMP       NULL,
    
    CONSTRAINT member_channel__channel_owner_id_fk 
    FOREIGN KEY (owner_id) REFERENCES member( member_id )
    ON DELETE CASCADE
);

alter table channel alter COLUMN channel_owner_id UUID NOT NULL UNIQUE


# -------------------------
# Member Channel Role
# -------------------------

CREATE TABLE channel_role (
    role_id        UUID          PRIMARY KEY DEFAULT gen_random_uuid(),
    channel_id     UUID          NOT NULL,
    authority      VARCHAR(64)   NOT NULL    DEFAULT '',
    role_flag      BIT(16)       NOT NULL    DEFAULT B'0000000000000000',
    is_default     BOOLEAN       NOT NULL    DEFAULT FALSE,
    
    CONSTRAINT channel_channel_role__channel_id_fk 
    FOREIGN KEY (channel_id) REFERENCES channel( channel_id )
    ON DELETE CASCADE
);

CREATE TABLE member_channel_role (
    member_id          UUID   NOT NULL,
    channel_id         UUID   NOT NULL,
    role_id            UUID   NOT NULL,
    
    CONSTRAINT member_member_channel_role__member_id_fk 
    FOREIGN KEY (member_id) REFERENCES member( member_id )
    ON DELETE CASCADE,
    
    CONSTRAINT channel_member_channel_role__channel_id_fk 
    FOREIGN KEY (channel_id) REFERENCES channel( channel_id )
    ON DELETE CASCADE,
    
    CONSTRAINT channel_role_member_channel_role__role_id_fk 
    FOREIGN KEY (role_id) REFERENCES channel_role( role_id )
    ON DELETE CASCADE
);

CREATE TABLE channel_streaming (
    streaming_id       UUID        NOT NULL PRIMARY KEY DEFAULT gen_random_uuid(),
    streaming_password VARCHAR(32) NULL,
    is_private         BOOLEAN     NOT NULL DEFAULT FALSE,
    start_at           TIMESTAMP   NULL     DEFAULT NULL,
    end_at             TIMESTAMP   NULL     DEFAULT NULL,
    
    channel_id         UUID   NOT NULL,
    channel_owner_id   UUID   NOT NULL,
    
    CONSTRAINT channel_channel_streaming__channel_id_fk 
    FOREIGN KEY (channel_id) REFERENCES channel( channel_id )
    ON DELETE CASCADE,
    
    CONSTRAINT channel_channel_streaming__channel_owner_id_fk 
    FOREIGN KEY (channel_owner_id) REFERENCES channel( owner_id )
    ON DELETE CASCADE
);

CREATE TABLE channel_subscribe (
    subscriber_id      UUID   NOT NULL,
    channel_id         UUID   NOT NULL,
    
    CONSTRAINT member_channel_subscribe__subscriber_id_fk 
    FOREIGN KEY (subscriber_id) REFERENCES member( member_id ),
    
    CONSTRAINT channel_channel_subscribe__channel_id_fk 
    FOREIGN KEY (channel_id) REFERENCES channel( channel_id )
);

CREATE TABLE channel_chat (
    chat_id      UUID      NOT NULL PRIMARY KEY DEFAULT gen_random_uuid(),
    
    writer_id    UUID      NOT NULL,
    channel_id   UUID      NOT NULL,
    
    chat_text    TEXT      NOT NULL,
    
    created_at   TIMESTAMP NOT NULL DEFAULT now(),
    is_deleted   BOOLEAN   NOT NULL DEFAULT FALSE,
    
    CONSTRAINT member_channel_chat__writer_id_fk 
    FOREIGN KEY (writer_id) REFERENCES member( member_id ),
    
    CONSTRAINT channel_channel_chat__channel_id_fk 
    FOREIGN KEY (channel_id) REFERENCES channel( channel_id )
);
