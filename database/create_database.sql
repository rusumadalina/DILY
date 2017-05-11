drop table Tag_Memory;
drop table Tag;
drop table Comment_table;
drop table Media;
drop table Notification;
drop table Relationship;
drop table Timeline;
drop table Tagged;
drop table Memory;
drop table User_table;
create table User_table
( 
  user_id number(38) not null,
  name varchar2(100) not null,
  username varchar2(100) not null unique,
  password varchar2(100) not null,
  email varchar2(100) not null unique,
  date_of_birth date not null,
  country varchar2(100),
  city varchar2(100),
  profile_picture varchar2(100) ,
  gender varchar2(100),
  CONSTRAINT User_pk PRIMARY KEY (user_id)
);
  
create table Memory
  (
    memoryId number(38) not null,
    title varchar2(50) not null,
    description varchar2(1000) not null,
    memoryLocation varchar2(50) ,
    dateM date not null,
    privacy varchar2(20),
    mainPicture varchar2(100),
    CONSTRAINT Memory_pk PRIMARY KEY (memoryId)
    );
  
create table Tagged 
  (
    memoryId number(38) not null,
    user_id number(38) not null,
     CONSTRAINT Memory_fk FOREIGN KEY (memoryId)
         REFERENCES Memory(memoryId),
      CONSTRAINT User_fk FOREIGN KEY (user_id)
         REFERENCES User_table(user_id),
      CONSTRAINT Tag_pair unique (memoryId,user_id)
    
  );
  
create table Timeline
  ( 
    memoryId number(38) not null,
    user_id number(38) not null,
     CONSTRAINT Memory_timeline_fk FOREIGN KEY (memoryId)
         REFERENCES Memory(memoryId),
      CONSTRAINT User_timeline_fk FOREIGN KEY (user_id)
         REFERENCES User_table(user_id),
      CONSTRAINT Timeline_pair unique (memoryId,user_id)
  );
  
create table Relationship(
  
    user1 number(38) not null,
    user2 number(38) not null,
    dateFriends date not null,
     CONSTRAINT User1_fk FOREIGN KEY (user1)
         REFERENCES User_table(user_id),
      CONSTRAINT User2_fk FOREIGN KEY (user2)
         REFERENCES User_table(user_id),
      CONSTRAINT friend_pair unique (user1,user2)
  );

create table Notification
(
  notification_id number not null,
  user_id number(38) not null,
  from_user  number(38) not null,
  dateN date not null,
  notificationText varchar2(100) not null,
  CONSTRAINT User3_fk FOREIGN KEY (user_id)
         REFERENCES User_table(user_id),
  CONSTRAINT User5_fk FOREIGN KEY (from_user)
         REFERENCES User_table(user_id),
  CONSTRAINT notification_pk PRIMARY KEY (notification_id)
);

create table Media(
  mediaId number(38) not null,
  mediaType varchar2(30) not null,
  memoryId number(38) not null,
  mediaPath varchar2(500) not null,
  CONSTRAINT Media_pk PRIMARY KEY (mediaId),
  CONSTRAINT Memory2_fk FOREIGN KEY (memoryId)
         REFERENCES Memory(memoryId)
);

create table Comment_table(
  comment_id number(38) not null,
  user_id number(38) not null,
  from_user number(38) not null,
  memoryId number(38) not null,
  comment_text varchar(100) not null,
  comment_date date,
  CONSTRAINT Comment_pk PRIMARY KEY (comment_id),
  CONSTRAINT Memory4_fk FOREIGN KEY (memoryId)
         REFERENCES Memory(memoryId),
   CONSTRAINT user4_fk FOREIGN KEY (user_id)
         REFERENCES User_table(user_id),   
  CONSTRAINT User6_fk FOREIGN KEY (from_user)
         REFERENCES User_table(user_id)
);

create table Tag(
  tag_name varchar2(30) not null unique,
  tag_id number(38) not null,
  CONSTRAINT Tag_pk PRIMARY KEY (tag_id)
);

create table Tag_Memory(
  tag_id number(38) not null,
  memoryId number(38) not null,
  CONSTRAINT Memory3_fk FOREIGN KEY (memoryId)
         REFERENCES Memory(memoryId),
   CONSTRAINT Tag_fk FOREIGN KEY (tag_id)
         REFERENCES Tag(tag_id),
    CONSTRAINT Tag_memory_pair unique (tag_id,memoryId)
);

