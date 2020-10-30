/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     29/10/2020 7:12:23 p. m.                     */
/*==============================================================*/


drop table if exists CITA;

drop table if exists COMENTARIO_MEDICO;

drop table if exists COMENTARIO_VETERINARIA;

drop table if exists COSTO;

drop table if exists DUENO;

drop table if exists HORA_ATENCION;

drop table if exists MASCOTA;

drop table if exists MEDICO;

drop table if exists PREGUNTA;

drop table if exists RESPUESTA;

drop table if exists ROL;

drop table if exists TIPO_ATENCION;

drop table if exists USUARIO;

drop table if exists VACUNACION;

drop table if exists VETERINARIA;

/*==============================================================*/
/* Table: CITA                                                  */
/*==============================================================*/
create table CITA
(
   ID_CITA              int not null auto_increment,
   ID_MEDICO            int not null,
   ID_MASCOTA           int not null,
   ID_ATENCION          int not null,
   FECHA_CITA           date not null,
   HORA_CITA            time not null,
   MODALIDAD_CITA       varchar(50) not null,
   primary key (ID_CITA)
);

/*==============================================================*/
/* Table: COMENTARIO_MEDICO                                     */
/*==============================================================*/
create table COMENTARIO_MEDICO
(
   ID_COMENTARIO_M      int not null auto_increment,
   ID_DUENO             int not null,
   ID_MEDICO            int not null,
   COMENTARIO_M         varchar(1024),
   PUNTUACION_M         float(8) not null,
   primary key (ID_COMENTARIO_M)
);

/*==============================================================*/
/* Table: COMENTARIO_VETERINARIA                                */
/*==============================================================*/
create table COMENTARIO_VETERINARIA
(
   ID_COMENTARIO_V      int not null auto_increment,
   ID_VETERINARIA       int not null,
   ID_DUENO             int not null,
   COMENTARIO_V         varchar(1024),
   PUNTUACION_V         float(8) not null,
   primary key (ID_COMENTARIO_V)
);

/*==============================================================*/
/* Table: COSTO                                                 */
/*==============================================================*/
create table COSTO
(
   ID_COSTO             int not null auto_increment,
   ID_ATENCION          int not null,
   ID_MEDICO            int not null,
   COSTO                int not null,
   primary key (ID_COSTO)
);

/*==============================================================*/
/* Table: DUENO                                                 */
/*==============================================================*/
create table DUENO
(
   ID_DUENO             int not null auto_increment,
   ID_USUARIO           int not null,
   CEDULA_DUENO         varchar(20) not null,
   NOMBRE_DUENO         varchar(150) not null,
   APELLIDO_DUENO       varchar(150) not null,
   TELEFONO_DUENO       bigint not null,
   DIRECCION_DUENO      varchar(150) not null,
   primary key (ID_DUENO)
);

/*==============================================================*/
/* Table: HORA_ATENCION                                         */
/*==============================================================*/
create table HORA_ATENCION
(
   ID_HORA              int not null auto_increment,
   ID_MEDICO            int not null,
   HORA_TEXTO           varchar(50) not null,
   HORA_TIEMPO          time not null,
   primary key (ID_HORA)
);

/*==============================================================*/
/* Table: MASCOTA                                               */
/*==============================================================*/
create table MASCOTA
(
   ID_MASCOTA           int not null auto_increment,
   ID_DUENO             int not null,
   NOMBRE_MASCOTA       varchar(1024) not null,
   ESPECIE              varchar(1024) not null,
   RAZA                 varchar(1024) not null,
   primary key (ID_MASCOTA)
);

/*==============================================================*/
/* Table: MEDICO                                                */
/*==============================================================*/
create table MEDICO
(
   ID_MEDICO            int not null auto_increment,
   ID_VETERINARIA       int,
   ID_USUARIO           int not null,
   CEDULA_MEDICO        varchar(50) not null,
   NOMBRE_MEDICO        varchar(50) not null,
   APELLIDO_MEDICO      varchar(50) not null,
   DIRECCION_MEDICO     varchar(50) not null,
   TELEFONO_MEDICO      bigint not null,
   MATRICULA_PROFESIONAL varchar(50) not null,
   LINK_MEDICO          varchar(100),
   primary key (ID_MEDICO)
);

/*==============================================================*/
/* Table: PREGUNTA                                              */
/*==============================================================*/
create table PREGUNTA
(
   ID_PREGUNTA          int not null auto_increment,
   ID_DUENO             int not null,
   PREGUNTA             longtext not null,
   primary key (ID_PREGUNTA)
);

/*==============================================================*/
/* Table: RESPUESTA                                             */
/*==============================================================*/
create table RESPUESTA
(
   ID_RESPUESTA         int not null auto_increment,
   ID_PREGUNTA          int not null,
   ID_MEDICO            int not null,
   RESPUESTA            varchar(1024) not null,
   primary key (ID_RESPUESTA)
);

/*==============================================================*/
/* Table: ROL                                                   */
/*==============================================================*/
create table ROL
(
   ID_ROL               int not null auto_increment,
   NOMBRE_ROL           varchar(50) not null,
   primary key (ID_ROL)
);

/*==============================================================*/
/* Table: TIPO_ATENCION                                         */
/*==============================================================*/
create table TIPO_ATENCION
(
   ID_ATENCION          int not null auto_increment,
   DESCRIPCION_ATENCION varchar(50) not null,
   primary key (ID_ATENCION)
);

/*==============================================================*/
/* Table: USUARIO                                               */
/*==============================================================*/
create table USUARIO
(
   ID_USUARIO           int not null auto_increment,
   ID_ROL               int not null,
   USERNAME             varchar(50) not null,
   PASSWORD             varchar(50) not null,
   CORREO_ELECTRONICO   varchar(150) not null,
   primary key (ID_USUARIO)
);

/*==============================================================*/
/* Table: VACUNACION                                            */
/*==============================================================*/
create table VACUNACION
(
   ID_VACUNACION        int not null auto_increment,
   ID_MASCOTA           int not null,
   ID_MEDICO            int not null,
   DESCRIPCION_VACUNA   varchar(250) not null,
   FECHA_VACUNA         date not null,
   primary key (ID_VACUNACION)
);

/*==============================================================*/
/* Table: VETERINARIA                                           */
/*==============================================================*/
create table VETERINARIA
(
   ID_VETERINARIA       int not null auto_increment,
   NOMBRE_VETERINARIA   varchar(1024) not null,
   DIRECCION_VETERINARIA varchar(1024) not null,
   TELEFONO_VETERINARIA bigint not null,
   TIPO_VETERINARIA     varchar(1024) not null,
   primary key (ID_VETERINARIA)
);

alter table CITA add constraint FK_ATENCION foreign key (ID_MEDICO)
      references MEDICO (ID_MEDICO) on delete restrict on update restrict;

alter table CITA add constraint FK_ATENCION2 foreign key (ID_MASCOTA)
      references MASCOTA (ID_MASCOTA) on delete restrict on update restrict;

alter table CITA add constraint FK_TIPO_CITA foreign key (ID_ATENCION)
      references TIPO_ATENCION (ID_ATENCION) on delete restrict on update restrict;

alter table COMENTARIO_MEDICO add constraint FK_CALIFICACION_M foreign key (ID_MEDICO)
      references MEDICO (ID_MEDICO) on delete restrict on update restrict;

alter table COMENTARIO_MEDICO add constraint FK_CALIFICA_M foreign key (ID_DUENO)
      references DUENO (ID_DUENO) on delete restrict on update restrict;

alter table COMENTARIO_VETERINARIA add constraint FK_CALIFICACION_V foreign key (ID_VETERINARIA)
      references VETERINARIA (ID_VETERINARIA) on delete restrict on update restrict;

alter table COMENTARIO_VETERINARIA add constraint FK_CALIFICA_V foreign key (ID_DUENO)
      references DUENO (ID_DUENO) on delete restrict on update restrict;

alter table COSTO add constraint FK_COSTOS foreign key (ID_ATENCION)
      references TIPO_ATENCION (ID_ATENCION) on delete restrict on update restrict;

alter table COSTO add constraint FK_COSTOS2 foreign key (ID_MEDICO)
      references MEDICO (ID_MEDICO) on delete restrict on update restrict;

alter table DUENO add constraint FK_USER_DUENO foreign key (ID_USUARIO)
      references USUARIO (ID_USUARIO) on delete restrict on update restrict;

alter table HORA_ATENCION add constraint FK_HORARIO foreign key (ID_MEDICO)
      references MEDICO (ID_MEDICO) on delete restrict on update restrict;

alter table MASCOTA add constraint FK_DUENO_DE foreign key (ID_DUENO)
      references DUENO (ID_DUENO) on delete restrict on update restrict;

alter table MEDICO add constraint FK_ASOCIADO foreign key (ID_VETERINARIA)
      references VETERINARIA (ID_VETERINARIA) on delete restrict on update restrict;

alter table MEDICO add constraint FK_USER_MEDICO foreign key (ID_USUARIO)
      references USUARIO (ID_USUARIO) on delete restrict on update restrict;

alter table PREGUNTA add constraint FK_PREGUNTA foreign key (ID_DUENO)
      references DUENO (ID_DUENO) on delete restrict on update restrict;

alter table RESPUESTA add constraint FK_RESPONDE foreign key (ID_MEDICO)
      references MEDICO (ID_MEDICO) on delete restrict on update restrict;

alter table RESPUESTA add constraint FK_RESPUESTA foreign key (ID_PREGUNTA)
      references PREGUNTA (ID_PREGUNTA) on delete restrict on update restrict;

alter table USUARIO add constraint FK_USER_ROLE foreign key (ID_ROL)
      references ROL (ID_ROL) on delete restrict on update restrict;

alter table VACUNACION add constraint FK_VACUNACION foreign key (ID_MASCOTA)
      references MASCOTA (ID_MASCOTA) on delete restrict on update restrict;

alter table VACUNACION add constraint FK_VACUNACION2 foreign key (ID_MEDICO)
      references MEDICO (ID_MEDICO) on delete restrict on update restrict;

INSERT INTO `rol`(`NOMBRE_ROL`) VALUES ('DUENO');
INSERT INTO `rol`(`NOMBRE_ROL`) VALUES ('MEDICO');