create table user (
  id int not null auto_increment primary key,
  name varchar(50) not null,
  email varchar(50) not null,
  password varchar(255) not null,

  constraint user_uk_email unique key (email)
);

insert into user (id, name, email, password) values
(1, 'Carlos Alves', 'carlos.alves@gmail.com', '$2a$10$RZM.5.LvWpP2kUYLFYB45.wIQ3mSxNG.g8yCE0Wup.Ta7tUqw4iPu'),
(2, 'Tiago Santos', 'tiago.santos@gmail.com', '$2a$10$F7uqplE2EId7UdQ5w4XrpOxrzv1Ppi2DCqsN.K299w5Nbs7D9/tbi'),
(3, 'Rafael Gomes', 'rafael.gomes@gmail.com', '$2a$10$SY3YuuoE04Qh9.3MThEBjO60aXF2YcfxDpm1c74oFnkzRFlTEBjZa'),
(4, 'João Andrade', 'joao.andrade@gmail.com', '$2a$10$sP9eVlRBVLvmqlkPGq6wt.2U2HFqJWm2V3HzCeC/id.RFv/c2Oc06'),
(5, 'Igor Neves', 'igor.neves@gmail.com', '$2a$10$5NsPlFZ5PPmZi4eEBLoJg.rwhb1.G0wb2F86xgUw1Yd.bD6upDWo2');
