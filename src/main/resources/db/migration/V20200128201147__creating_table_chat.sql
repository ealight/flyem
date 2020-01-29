create table `chat` (
                        `id` bigint auto_increment not null,
                        `token` varchar(20) not null,
                        `title` varchar(100) not null,
                        `creation_date` DATETIME DEFAULT CURRENT_TIMESTAMP,
                        primary key (`id`),
                        unique index `unique_token` (`token` asc)
)