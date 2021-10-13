
select * from bms_branch;

update  bms_branch set ifsc_code ='IDIB0000105' where branch_id =5

drop table public.bms_branch;

delete from public.bms_branch where branch_id =47;

insert into public.bms_branch values(1,'2021-10-05','Chrompet',4);

into bms_employee values(1,'chennai','2021-10-05','meenaovi@gmal.com','953454350','meena','ks99asd',9000,'2021-10-05',41);

delete from drop table bms_branch;

insert public.bms_employee where employeeid=2;

select * from bms_employee;

drop table bms_employee;

select * from bms_customer

delete from public.bms_customer where customer_id =8

drop table public.bms_customer 

insert into public.bms_customer values(1,'meenaasdsa@gmail.com','195007315131','Meena',43,'2021-09-19','2021-09-19')

select * from bms_accounttype

drop table public.bms_accounttype

insert into public.bms_accounttype values(19,602268754951,'No','Savings',16)

select * from public.bms_account 

insert into public.bms_account values(9,1000,1)

select * from bms_account;

delete from public.bms_accounttype where type_id=5;

delete from public.bms_account where account_id>=2 and account_id<=10;

drop table public.bms_account 

select * from bms_transaction;

truncate table public.bms_transaction 

delete from public.bms_transaction  where transaction_id >20;

drop table public.bms_transaction ;

