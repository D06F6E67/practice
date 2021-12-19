-- 根据用户id查询用户信息
DROP PROCEDURE IF EXISTS `select_user_by_id`; -- 如果存在这个存储过程就删除
DELIMITER ;;
-- 定义换行符，当语句中有;; 时就运行语句，默认为;
CREATE PROCEDURE `select_user_by_id`(
    IN userId BIGINT,
    OUT userName VARCHAR (50),
    OUT userPassword VARCHAR (50),
    OUT userEmail VARCHAR (50),
    OUT userInfo TEXT,
    OUT headImg BLOB,
    OUT createTime DATETIME)
BEGIN
SELECT user_name, user_password, user_email, user_info, head_img, create_time
INTO userName, userPassword, userEmail, userInfo, headImg, createTime
FROM sys_user
WHERE id = userId;
END ;;
DELIMITER ;


-- 根据用户名和分页参数进行查询，返回总数和分页数据
DROP PROCEDURE IF EXISTS `select_user_page`;
DELIMITER ;;
CREATE PROCEDURE `select_user_page`(
    IN userName VARCHAR (50),
    IN _offset BIGINT,
    IN _limit BIGINT,
    OUT total BIGINT)
BEGIN
-- 查询总数
SELECT COUNT(*) INTO total
FROM sys_user
WHERE user_name LIKE CONCAT('%', userName, '%');
-- 分页数据 下方的语句会生成另外一个结果集，在mapper.xml中还需要定义resultMap来指定该结果集映射的类型
SELECT *
FROM sys_user
WHERE user_name LIKE CONCAT('%', userName, '%')
LIMIT _offset, _limit;
END ;;
DELIMITER ;


-- 保持用户信息和角色关联信息
DROP PROCEDURE IF EXISTS `insert_user_and_roles`;
DELIMITER ;;
-- 定义换行符，当语句中有;; 时就运行语句，默认为;
CREATE PROCEDURE `insert_user_and_roles`(
    OUT userId BIGINT,
    IN userName VARCHAR (50),
    IN userPassword VARCHAR (50),
    IN userEmail VARCHAR (50),
    IN userInfo TEXT,
    IN headImg BLOB,
    OUT createTime DATETIME,
    IN roleIds VARCHAR (200))
BEGIN
-- 设置当前时间
SET createTime = NOW();
-- 插入数据
INSERT INTO sys_user(user_name, user_password, user_email, user_info, head_img, create_time)
VALUES (userName, userPassword, userEmail, userInfo, headImg, createTime);
-- 获取自增主键
SELECT LAST_INSERT_ID() INTO userId;
-- 保存用户和角色关系数据
SET roleIds = CONCAT(',', roleIds, ',');
INSERT INTO sys_user_role(user_id, role_id)
-- 查询出角色表中id在roleIds中的id。例如roleIds:,1,2,3 role中只用id: 1 / 2的数据
-- userId通过上方查询出来的是一个固定值，最后结果为 userid 1 / userId 2
-- 然后将这两组数据插入到sys_user_role中
SELECT userId, id
FROM sys_role
WHERE INSTR(roleIds, CONCAT(',',id,',')) > 0;
END ;;
DELIMITER ;


-- 删除用户信息和角色关联信息
DROP PROCEDURE IF EXISTS `delete_user_by_id`;
DELIMITER ;;
CREATE PROCEDURE `delete_user_by_id`(
    IN userId BIGINT)
BEGIN
DELETE FROM sys_user_role WHERE user_id = userId;
DELETE FROM sys_user WHERE id = userId;
END ;;
DELIMITER ;