/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/3/27 15:05:18                           */
/*==============================================================*/

use contact_db;
drop table if exists contact;

drop table if exists user;

/*==============================================================*/
/* Table: contact                                               */
/*==============================================================*/
create table contact
(
   contactId            int not null auto_increment,
   userID               int not null,
   contactName          varchar(50) not null,
   contactGender        varchar(5) not null,
   contactBirthday      date,
   contactNumber        char(11),
   contactQQ            varchar(12),
   contactAddress       varchar(256),
   contactAddDate       date,
   primary key (contactId)
);

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   userID               int not null auto_increment,
   userNickName         varchar(50) not null,
   userRealName         varchar(50) not null,
   userGender           varchar(5) not null,
   userKey              varchar(100) not null,
   userEmail            varchar(50) not null,
   isAdmin              int not null default 0,
   userBirthday         date,
   userNumber           char(11),
   userAddress          varchar(500),
   registerDate         date not null,
   primary key (userID),
   key AK_userNickName (userNickName),
   key AK_userEmail (userEmail),
   key AK_userNumber (userNumber)
);

alter table contact add constraint FK_contacts foreign key (userID)
      references user (userID) on delete cascade on update restrict;

