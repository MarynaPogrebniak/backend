insert into account(email, state, role)
values ('test@mail.com', 'NOT_CONFIRMED', 'USER');
insert into account(email, state, role)
values ('test1@mail.com', 'CONFIRMED', 'ADMIN');

insert into task(title, description, START_DATE, FINISH_DATE, EXECUTOR_ID)
values ('1', 'задача 1', '2024-02-02', '2025-03-03', 1);
insert into task(title, description, START_DATE, FINISH_DATE, EXECUTOR_ID)
values ('2', 'задача 2', '2024-02-02', '2025-03-03', 1);