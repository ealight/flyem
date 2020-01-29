create table `user_to_chat` (
                             `user_id` bigint not null,
                             `chat_id` bigint not null,
                             primary key (`user_id`, `chat_id`),
                             foreign key (`chat_id`) references `chat` (`id`),
                             foreign key (`user_id`) references `user` (`id`)
)