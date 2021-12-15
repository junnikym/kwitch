CREATE TABLE notification (
    notification_id	        UUID          PRIMARY KEY DEFAULT gen_random_uuid(),
    notification_href       VARCHAR(128)  NOT NULL,
    notification_text       VARCHAR(128)  NOT NULL,
    sender_id               UUID          NOT NULL,
    receiver_id             UUID          NOT NULL,
    notification_is_checked BOOLEAN       NOT NULL DEFAULT FALSE,
    created_at              TIMESTAMP     DEFAULT now(),
    
    CONSTRAINT member_notification__sender_id_fk 
    FOREIGN KEY (sender_id) REFERENCES member( member_id )
    ON DELETE CASCADE,
    
    CONSTRAINT member_notification__receiver_id_fk 
    FOREIGN KEY (receiver_id) REFERENCES member( member_id )
    ON DELETE CASCADE
);
