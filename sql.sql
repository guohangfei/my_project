prompt PL/SQL Developer import file
prompt Created on 2018年5月10日 by ghf
set feedback off
set define off
prompt Disabling triggers for T_RIGHT...
alter table T_RIGHT disable all triggers;
prompt Disabling triggers for T_ROLE...
alter table T_ROLE disable all triggers;
prompt Disabling triggers for T_ROLE_RIGHT...
alter table T_ROLE_RIGHT disable all triggers;
prompt Disabling triggers for T_SYS_LOG...
alter table T_SYS_LOG disable all triggers;
prompt Disabling triggers for T_USER...
alter table T_USER disable all triggers;
prompt Disabling triggers for T_USER_ROLE...
alter table T_USER_ROLE disable all triggers;
prompt Deleting T_USER_ROLE...
delete from T_USER_ROLE;
commit;
prompt Deleting T_USER...
delete from T_USER;
commit;
prompt Deleting T_SYS_LOG...
delete from T_SYS_LOG;
commit;
prompt Deleting T_ROLE_RIGHT...
delete from T_ROLE_RIGHT;
commit;
prompt Deleting T_ROLE...
delete from T_ROLE;
commit;
prompt Deleting T_RIGHT...
delete from T_RIGHT;
commit;
prompt Loading T_RIGHT...
insert into T_RIGHT (right_id, right_name, right_description)
values ('1', 'add', '新增');
insert into T_RIGHT (right_id, right_name, right_description)
values ('2', 'delete', '删除');
insert into T_RIGHT (right_id, right_name, right_description)
values ('3', 'edit', '修改');
insert into T_RIGHT (right_id, right_name, right_description)
values ('4', 'query', '查询');
commit;
prompt 4 records loaded
prompt Loading T_ROLE...
insert into T_ROLE (role_id, role_name, role_description, first_page, flag)
values ('1', 'admin', '最高管理员', null, null);
insert into T_ROLE (role_id, role_name, role_description, first_page, flag)
values ('2', 'user', '用户', null, null);
commit;
prompt 2 records loaded
prompt Loading T_ROLE_RIGHT...
insert into T_ROLE_RIGHT (role_id, right_id)
values ('1', '1');
insert into T_ROLE_RIGHT (role_id, right_id)
values ('1', '2');
insert into T_ROLE_RIGHT (role_id, right_id)
values ('1', '3');
insert into T_ROLE_RIGHT (role_id, right_id)
values ('1', '4');
insert into T_ROLE_RIGHT (role_id, right_id)
values ('2', '4');
commit;
prompt 5 records loaded
prompt Loading T_SYS_LOG...
prompt Table is empty
prompt Loading T_USER...
insert into T_USER (user_id, user_name, real_name, password, gender, bod, status, email, phone_number)
values ('2', 'test', '测试', 'fc1709d0a95a6be30bc5926fdb7f22f4', '无', null, null, null, null);
insert into T_USER (user_id, user_name, real_name, password, gender, bod, status, email, phone_number)
values ('1', 'ghf', '郭航飞', 'fc1709d0a95a6be30bc5926fdb7f22f4', '男', null, null, null, null);
commit;
prompt 2 records loaded
prompt Loading T_USER_ROLE...
insert into T_USER_ROLE (user_id, role_id)
values ('1', '1');
insert into T_USER_ROLE (user_id, role_id)
values ('2', '2');
commit;
prompt 2 records loaded
prompt Enabling triggers for T_RIGHT...
alter table T_RIGHT enable all triggers;
prompt Enabling triggers for T_ROLE...
alter table T_ROLE enable all triggers;
prompt Enabling triggers for T_ROLE_RIGHT...
alter table T_ROLE_RIGHT enable all triggers;
prompt Enabling triggers for T_SYS_LOG...
alter table T_SYS_LOG enable all triggers;
prompt Enabling triggers for T_USER...
alter table T_USER enable all triggers;
prompt Enabling triggers for T_USER_ROLE...
alter table T_USER_ROLE enable all triggers;
set feedback on
set define on
prompt Done.
