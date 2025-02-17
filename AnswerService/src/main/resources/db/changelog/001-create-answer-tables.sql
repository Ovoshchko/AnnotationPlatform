CREATE TABLE IF NOT EXISTS task_filled
(
    id         uuid PRIMARY KEY,
    task_id    uuid         not null,
    answer     jsonb,
    filled_by  varchar(255) not null,
    created_at timestamp    not null
);