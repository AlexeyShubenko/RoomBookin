# RoomBooking
Servlets, JDBC, JavaScript, jQuery, Ajax

Room:

create table room(
id bigserial not null,
num character varying(255),
size int,
constraint room_pkey primary key(id)
);

Employee:

create table employee(
id bigserial not null,
name character varying(255),
login character varying(255),
password character varying(255),
role_id bigserial,
constraint empl_pkey primary key(id),
constraint role_id_fkey foreign key(role_id) references role(id)
);

Role:

create table role(
id bigserial not null,
role character varying(255),
constraint role_pkey primary key(id)
);

RoomBooking:

create table roombooking(
id bigserial not null,
room_id bigserial not null,
empl_id bigserial not null,
fromtime timestamp without time zone,
totime timestamp without time zone,
constraint roombooking_pkey primary key(id),
CONSTRAINT fromtime FOREIGN KEY (room_id)
      REFERENCES room (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
CONSTRAINT ftotime FOREIGN KEY (empl_id)
      REFERENCES employee(id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
