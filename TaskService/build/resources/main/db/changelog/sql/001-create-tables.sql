CREATE TABLE IF NOT EXISTS task_type
(
    id                  uuid PRIMARY KEY,
    name                varchar(255) not null,
    annotation_metadata jsonb,
    description         text
);

CREATE TABLE IF NOT EXISTS batch
(
    id                  uuid PRIMARY KEY,
    name                varchar(255) not null,
    owner               varchar(255) not null,
    priority            integer      not null default 1,
    agreements          jsonb,
    overlap_coefficient int          not null default 1,
    created_at          timestamp    not null,
    task_type_id        uuid         not null,
    is_educational      boolean      not null default false,
    foreign key (task_type_id) references task_type (id)
);

CREATE TABLE IF NOT EXISTS task
(
    id           uuid PRIMARY KEY,
    name         varchar(255) not null,
    s3_path      text         not null,
    batch_id     uuid         not null,
    metadata     jsonb,
    is_honeypot  boolean      not null default false,
    status       varchar(255) not null DEFAULT 'NOT_STARTED',
    filled_count integer      not null default 0,
    final_answer jsonb,
    properties jsonb not null,
    foreign key (batch_id) references batch (id)
);

CREATE TABLE IF NOT EXISTS task_filled
(
    id         uuid PRIMARY KEY,
    task_id    uuid         not null,
    answer     jsonb,
    filled_by  varchar(255) not null,
    created_at timestamp    not null,
    foreign key (task_id) references task (id)
);