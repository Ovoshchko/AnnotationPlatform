ALTER TABLE task
    ADD COLUMN created_at timestamp not null default now();

ALTER TABLE task
    DROP COLUMN filled_count;