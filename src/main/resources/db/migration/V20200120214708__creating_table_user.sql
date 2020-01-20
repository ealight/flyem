create table `user` (
     `id` bigint auto_increment not null,
     `username` varchar(50) not null,
     `email` varchar(100) not null,
     `password` varchar(50) not null,
     primary key (`id`),
     unique index `unique_username` (`username` asc),
     unique index `unique_email` (`username` desc)
)