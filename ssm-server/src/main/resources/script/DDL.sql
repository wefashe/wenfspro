-- 数据定义语言DDL 表、视图、索引
/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2019-02-21 18:56:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS sys_dept;
CREATE TABLE sys_dept (
  dept_id varchar(50) NOT NULL COMMENT '主键',
  dept_name varchar(40) NOT NULL COMMENT '部门名称',
  par_id varchar(50) DEFAULT NULL COMMENT '上级部门id',
  sequence bigint(20) DEFAULT NULL COMMENT '部门在当前层级下的顺序',
  create_time varchar(30) NOT NULL COMMENT '创建时间',
  update_time varchar(30) NOT NULL COMMENT '最后修改时间',
  operator varchar(30) NOT NULL COMMENT '操作人',
  update_ip varchar(20) NOT NULL COMMENT '最后修改者的ip',
  remark varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (dept_id)
);
comment on table SYS_DEPT is '部门表';

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS sys_log;
CREATE TABLE sys_log (
  log_id varchar(50) NOT NULL COMMENT '日志id',
  target_type bigint(20) NOT NULL COMMENT '权限更新的类型，1：部门，2：用户，3：权限，4：角色，5：角色用户关系，6：角色权限关系',
  target_id varchar(50) NOT NULL COMMENT '对象id，比如用户、权限、角色等',
  old_value text COMMENT '旧值',
  new_value text COMMENT '新值',
  log_status tinyint(2) NOT NULL COMMENT '当前是否复原过，0：没有，1：复原过',
  operator varchar(30) NOT NULL COMMENT '操作人',
  update_time varchar(30) NOT NULL COMMENT '最后修改时间',
  update_ip varchar(20) NOT NULL COMMENT '最后修改者的ip',
  PRIMARY KEY (log_id)
);
comment on table SYS_LOG is '日志表';

-- ----------------------------
-- Table structure for sys_perm
-- ----------------------------
DROP TABLE IF EXISTS sys_perm;
CREATE TABLE sys_perm (
  perm_id varchar(50) NOT NULL COMMENT '主键',
  perm_name varchar(50) NOT NULL COMMENT '权限名称',
  perm_url varchar(100) NOT NULL COMMENT '权限url或其他',
  perm_icon varchar(50) DEFAULT NULL COMMENT '权限图标 一般菜单权限才有',
  perm_status tinyint(2) unsigned DEFAULT '1' COMMENT '权限状态 1-启用 0-停用',
  par_id varchar(50) DEFAULT NULL COMMENT '上级权限id',
  sequence bigint(20) DEFAULT '0' COMMENT '权限在当前模块下的顺序',
  perm_type tinyint(2) unsigned DEFAULT '1' COMMENT '权限类型 1-菜单  2-按钮  3-其他',
  operator varchar(30) NOT NULL COMMENT '操作人',
  create_time varchar(30) NOT NULL COMMENT '创建时间',
  update_time varchar(30) NOT NULL COMMENT '最后修改时间',
  update_ip varchar(20) NOT NULL COMMENT '最后修改者的ip',
  remark varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (perm_id)
);
comment on table SYS_PERM is '权限表';

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS sys_role;
CREATE TABLE sys_role (
  role_id varchar(50) NOT NULL COMMENT '主键',
  role_name varchar(40) NOT NULL COMMENT '角色名称',
  role_status tinyint(2) unsigned DEFAULT '1' COMMENT '角色状态 1-启用 0-停用',
  create_time varchar(30) NOT NULL COMMENT '创建时间',
  update_time varchar(30) NOT NULL COMMENT '最后修改时间',
  operator varchar(30) NOT NULL COMMENT '操作人',
  update_ip varchar(20) NOT NULL COMMENT '最后修改者的ip',
  remark varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (role_id)
);
comment on table SYS_ROLE is '角色表';

-- ----------------------------
-- Table structure for sys_role_perm
-- ----------------------------
DROP TABLE IF EXISTS sys_role_perm;
CREATE TABLE sys_role_perm (
  id varchar(50) NOT NULL COMMENT '主键',
  role_id varchar(50) NOT NULL COMMENT '角色id',
  perm_id varchar(50) NOT NULL COMMENT '权限id',
  operator varchar(30) NOT NULL COMMENT '操作人',
  update_time varchar(30) NOT NULL COMMENT '最后修改时间',
  update_ip varchar(20) NOT NULL COMMENT '最后修改者的ip',
  PRIMARY KEY (id),
  CONSTRAINT fk_sys_role_perm_perm_id FOREIGN KEY (perm_id) REFERENCES sys_perm (perm_id),
  CONSTRAINT fk_sys_role_perm_role_id FOREIGN KEY (role_id) REFERENCES sys_role (role_id)
);
comment on table SYS_ROLE_PERM is '角色权限表';

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS sys_user;
CREATE TABLE sys_user (
  user_id varchar(50) NOT NULL COMMENT '主键',
  user_name varchar(20) DEFAULT NULL COMMENT '登录账户',
  user_pass varchar(50) NOT NULL COMMENT '登录密码',
  nick_name varchar(50) DEFAULT NULL COMMENT '用户昵称',
  pass_salt varchar(50) DEFAULT NULL COMMENT '密码加密盐',
  user_status tinyint(2) unsigned DEFAULT '1' COMMENT '用户状态 1-启用 0-停用',
  user_sex tinyint(2) unsigned DEFAULT NULL COMMENT '性别 0-女，1-男',
  head_portrait varchar(100) DEFAULT NULL COMMENT '头像地址',
  user_phone varchar(20) DEFAULT NULL COMMENT '手机',
  user_email varchar(30) DEFAULT NULL COMMENT '邮箱',
  user_birthday varchar(30) DEFAULT NULL COMMENT '生日',
  create_time varchar(30) NOT NULL COMMENT '注册时间',
  update_time varchar(30) NOT NULL COMMENT '最后修改时间',
  last_login_time varchar(30) DEFAULT NULL COMMENT '最后登录时间',
  dept_id varchar(50) DEFAULT NULL COMMENT '部门id',
  operator varchar(30) NOT NULL COMMENT '操作人',
  update_ip varchar(20) NOT NULL COMMENT '最后修改者的ip',
  remark varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (user_id),
  UNIQUE KEY uq_sys_user_user_name (user_name),
  CONSTRAINT fk_sys_user_dept_id FOREIGN KEY (dept_id) REFERENCES sys_dept (dept_id)
);
comment on table SYS_USER is '用户表';

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS sys_user_role;
CREATE TABLE sys_user_role (
  id varchar(50) NOT NULL COMMENT '主键',
  user_id varchar(50) NOT NULL COMMENT '用户id',
  role_id varchar(50) NOT NULL COMMENT '角色id',
  operator varchar(30) NOT NULL COMMENT '操作人',
  update_time varchar(30) NOT NULL COMMENT '最后修改时间',
  update_ip varchar(20) NOT NULL COMMENT '最后修改者的ip',
  PRIMARY KEY (id),
  CONSTRAINT fk_sys_role_perm_user_id FOREIGN KEY (user_id) REFERENCES sys_user (user_id),
  CONSTRAINT fk_sys_user_role_role_id FOREIGN KEY (role_id) REFERENCES sys_role (role_id)
);
comment on table SYS_USER_ROLE is '用户角色表';
