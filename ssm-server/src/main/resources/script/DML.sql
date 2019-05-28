-- 数据操纵语言DML 插入、修改、删除

INSERT INTO sys_dept VALUES ('d8007a2f-3353-11e8-99df-0c8bfd3cb549', '人力资源部', null, '1', '1522329585821', '1522329585821', 'admin', '127.0.0.1', '');


INSERT INTO sys_perm VALUES ('1', '角色管理', 'role/', 'fi-database icon-yellow', '1', '', '0', '1', 'admin', '1522822971651', '1522822971651', '127.0.0.1', null);
INSERT INTO sys_perm VALUES ('2', '用户管理', 'user/', 'fi-torsos-all', '1', '5c883cf64e2aaa37821c40015ecc9899', '0', '1', 'admin', '1522823064197', '1522823064197', '127.0.0.1', null);
INSERT INTO sys_perm VALUES ('3', '部门管理', 'dept/', 'fi-results-demographics', '1', '', '0', '1', 'admin', '1522823102421', '1522823102421', '127.0.0.1', null);
INSERT INTO sys_perm VALUES ('4', '权限管理', 'perm/', 'fi-folder icon-black', '1', '', '0', '1', 'admin', '1522822892938', '1522822892938', '127.0.0.1', null);


INSERT INTO sys_role VALUES ('1', '测试账户', '1', '1111111111111', '222222222', '新建', '1111111', '22222');



INSERT INTO sys_role_perm VALUES ('1', '1', '1', '2313', '32323', '43434');
INSERT INTO sys_role_perm VALUES ('2', '1', '2', '2313', '32323', '43434');
INSERT INTO sys_role_perm VALUES ('3', '1', '3', '2313', '32323', '43434');
INSERT INTO sys_role_perm VALUES ('4', '1', '4', '2313', '32323', '43434');


INSERT INTO sys_user VALUES ('1', 'admin', '9a862d15408f362f1517aad11c9e99fc', 'admin', 'green', '1', '1', 'default.jpg', '18702515261', 'wefashe@qq.com', '7879798787', '8979798779', '7897978797', '1550501157339', null, '默认创建', '127.0.0.1', '还原');
INSERT INTO sys_user VALUES ('3febaf2ab7c6401a849babf5ee66fcff', 'admin741', '1111111111', 'admin', 'test', '1', '1', null, '18702515261', 'wefashe@qq.com', null, '1522424099343', '1522424099343', null, null, 'admin', '127.0.0.1', '还原');
INSERT INTO sys_user VALUES ('8b39f8730e45461f86c2d70cccc3b817', 'admin852', '1111111111', 'admin', 'test', '1', '1', null, '18702515261', 'wefashe@qq.com', null, '1522424058755', '1522424058755', null, null, 'admin', '127.0.0.1', '还原');
INSERT INTO sys_user VALUES ('979710143944589314', 'test123', '1111111111', 'admin', 'test', '1', '1', null, '18702515261', 'wefashe@qq.com', null, '1522416074891', '1522416074891', null, null, 'admin', '127.0.0.1', '还原');
INSERT INTO sys_user VALUES ('b106dece57324d8cb227c880e1a86944', 'test', 'test', 'admin', 'test', '1', '1', null, '18702515261', 'wefashe@qq.com', null, '1522415877360', '1522415877360', '1544691786676', null, 'admin', '127.0.0.1', '还原');
INSERT INTO sys_user VALUES ('df1cee87f6864772aafa2c7ff3c69601', 'admin123', '1111111111', 'admin', 'test', '1', '1', null, '18702515261', 'wefashe@qq.com', null, '1522417783168', '1522417783168', null, null, 'admin', '127.0.0.1', '还原');
INSERT INTO sys_user VALUES ('e68a675215d34e7684da87c6ad444ace', 'wenfs', '1111111111', 'admin', 'test', '1', '1', null, '18702515261', 'wefashe@qq.com', null, '1522417169818', '1522417169818', '1545657606685', null, 'admin', '127.0.0.1', '还原');
INSERT INTO sys_user VALUES ('e6ace6d852304664862af5fc2edc87d4', 'admin456', '1111111111', 'admin', 'test', '1', '1', null, '18702515261', 'wefashe@qq.com', null, '1522417842849', '1522417842849', null, null, 'admin', '127.0.0.1', '还原');


INSERT INTO sys_user_role VALUES ('1', '1', '1', '1', '1', '1');