create table `chat_data` (
                        `id` bigint auto_increment not null,
                        `chat_id` bigint not null,
                        `text` varchar(1024) not null,
                        `author` varchar(30) not null,
                        `send_date` DATETIME DEFAULT CURRENT_TIMESTAMP,
                        primary key (`id`),
                        foreign key (`chat_id`) references `chat` (`id`)
)