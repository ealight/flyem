create table `email_tokens`
(
    `user_id` bigint      not null,
    `token`   varchar(40) not null,
    foreign key (`user_id`) references `user` (`id`)
)