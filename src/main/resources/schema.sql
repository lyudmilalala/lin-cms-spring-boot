SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- 用户表
-- ----------------------------

create table user
(
    id                int(11) AUTO_INCREMENT COMMENT '用户Id' PRIMARY KEY,
    username          varchar(26)                            NOT NULL COMMENT '用户名，活跃用户名唯一',
    isAdmin           char(1)                                DEFAULT 'N' NOT NULL COMMENT '用户是否为管理员（Y/N）',
    password          varchar(20)                            DEFAULT NULL COMMENT '用户密码',
    phone             varchar(20)                            DEFAULT NULL COMMENT '用户手机',
    email             varchar(50)                            DEFAULT NULL COMMENT '用户邮箱',
    wechat_credential varchar(50)                            DEFAULT NULL COMMENT '用户微信账号凭据',
    google_credential varchar(50)                            DEFAULT NULL COMMENT '用户谷歌账号凭据',
    create_time       datetime(3) DEFAULT CURRENT_TIMESTAMP(3) NOT NULL COMMENT '用户创建时间',
    update_time       datetime(3) DEFAULT CURRENT_TIMESTAMP(3) NOT NULL ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '用户信息最近更新时间',
    delete_time       datetime(3)                            DEFAULT NULL COMMENT '用户删除时间',
    UNIQUE (username, delete_time)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT '用户表';

-- ----------------------------
-- 项目表
-- ----------------------------

DROP TABLE IF EXISTS project;
CREATE TABLE project
(
    id          int(11) AUTO_INCREMENT COMMENT '项目Id' PRIMARY KEY,
    title       varchar(255) DEFAULT 'Untitled'           NOT NULL COMMENT '项目标题',
    author      int(11)                                   NOT NULL COMMENT '项目作者Id',
    content     longtext                                  DEFAULT NULL COMMENT '项目内容',
    category    varchar(30)                               DEFAULT NULL COMMENT'项目分类',
    create_time datetime(3) DEFAULT CURRENT_TIMESTAMP(3) NOT NULL COMMENT '项目创建时间',
    update_time datetime(3) DEFAULT CURRENT_TIMESTAMP(3) NOT NULL ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '项目最近更新时间',
    delete_time datetime(3)                               DEFAULT NULL COMMENT '项目删除时间'
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT '项目表';

-- ----------------------------
-- 资源表
-- ----------------------------
DROP TABLE IF EXISTS resource;
CREATE TABLE resource
(
    id          int(11) AUTO_INCREMENT COMMENT '资源Id' PRIMARY KEY,
    description text                                     DEFAULT NULL COMMENT '资源叙述',
    type        varchar(20)                              NOT NULL COMMENT '资源类型，e.g. 下载/链接',
    path        varchar(500)                             DEFAULT NULL COMMENT '资源地址',
    name        varchar(30)                              DEFAULT NULL COMMENT '资源名称',
    project_id  int(11)                                  NOT NULL COMMENT '资源所属的项目',
    md5         varchar(40)                              DEFAULT NULL COMMENT '资源md5, 防重复（仅下载资源）',
    create_time datetime(3) DEFAULT CURRENT_TIMESTAMP(3) NOT NULL COMMENT '资源创建时间',
    update_time datetime(3) DEFAULT CURRENT_TIMESTAMP(3) NOT NULL ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '资源最近更新时间',
    delete_time datetime(3)                              DEFAULT NULL COMMENT '资源删除时间'
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT '资源表';

-- ----------------------------
-- 评论表
-- ----------------------------

DROP TABLE IF EXISTS comment;
CREATE TABLE comment
(
    id          int(11) AUTO_INCREMENT COMMENT '评论Id' PRIMARY KEY,
    content     longtext                                 DEFAULT NULL COMMENT '评论内容',
    author      int(11)                                  NOT NULL COMMENT '评论作者Id',
    project_id  int(11)                                  NOT NULL COMMENT '被评论的项目',
    comment_id  int(11)                                  DEFAULT NULL COMMENT '被回复的评论，若为null则说明直接评论的项目',
    create_time datetime(3) DEFAULT CURRENT_TIMESTAMP(3) NOT NULL COMMENT '评论创建时间',
    update_time datetime(3) DEFAULT CURRENT_TIMESTAMP(3) NOT NULL ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '评论最近更新时间',
    delete_time datetime(3)                              DEFAULT NULL COMMENT '评论删除时间'
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT '评论表';

-- ----------------------------
-- 博客表
-- ----------------------------

DROP TABLE IF EXISTS blog;
CREATE TABLE blog
(
    id          int(11) AUTO_INCREMENT COMMENT '博客Id' PRIMARY KEY,
    title       varchar(255) DEFAULT 'Untitled'           NOT NULL COMMENT '博客标题',
    author      int(11)                                   NOT NULL COMMENT '博客作者Id',
    content     longtext                                  DEFAULT NULL COMMENT '博客内容',
    thumbnail   varchar(500)                              DEFAULT NULL COMMENT '博客头图资源路径',
    create_time datetime(3) DEFAULT CURRENT_TIMESTAMP(3) NOT NULL COMMENT '博客创建时间',
    update_time datetime(3) DEFAULT CURRENT_TIMESTAMP(3) NOT NULL ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '博客最近更新时间',
    delete_time datetime(3)                               DEFAULT NULL COMMENT '博客删除时间'
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT '博客表';

-- ----------------------------
-- 活动表
-- ----------------------------

DROP TABLE IF EXISTS event;
CREATE TABLE event
(
    id          int(11) AUTO_INCREMENT COMMENT '活动Id' PRIMARY KEY,
    title       varchar(255) DEFAULT 'Untitled'           NOT NULL COMMENT '活动标题',
    author      int(11)                                       NOT NULL COMMENT '活动作者Id',
    content     longtext                                  DEFAULT NULL COMMENT '活动内容',
    thumbnail   varchar(500)                              DEFAULT NULL COMMENT '活动头图资源路径',
    create_time datetime(3)  DEFAULT CURRENT_TIMESTAMP(3) NOT NULL COMMENT '活动创建时间',
    start_time  datetime(3)                               DEFAULT NULL COMMENT '活动开始时间',
    end_time    datetime(3)                               DEFAULT NULL COMMENT '活动结束时间',
    update_time datetime(3)  DEFAULT CURRENT_TIMESTAMP(3) NOT NULL ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '活动最近更新时间',
    delete_time datetime(3)                               DEFAULT NULL COMMENT '活动删除时间'
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT '活动表';

-- ----------------------------
-- 插入超级管理员
-- ----------------------------
BEGIN;
INSERT INTO user(id, username, isAdmin, password) VALUES (1, 'admin1', 'Y', 'SpinQ0827');
COMMIT;