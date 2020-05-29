create database `lostAndfound` default character set utf8 collate utf8_general_ci;
use lostAndfound;

#用户表
create table laf_user
(
    `id`          int(11)     not null auto_increment comment '用户id',
    `username`    varchar(50) not null comment '用户名',
    `password`    varchar(50) not null comment '用户密码,MD5加密',
    `contact`     varchar(50) comment '联系方式',
    `profile_url` varchar(500) default null comment '用户头像,url相对地址',
    `profile`     text comment '图片地址',
    `role`        int(4)      not null comment '0:管理员,1-用户',
    primary key (`id`),
    unique `username_unique` (`username`) using btree
) engine = innodb
  default charset = utf8;

#类别表
create table laf_category
(
    `id`         int(11) not null auto_increment comment '类别id',
    `parent_id`  int(11)     default null comment '父类id,parent_id=0时为根节点,一级类别',
    `name`       varchar(50) default null comment '类别名称',
    `status`     tinyint(1)  default '1' comment '类别状态,1:正常,2:弃用',
    `sort_order` int(4)      default null comment '排序编号,同类展示顺序,数值相等自然展开',
    primary key (`id`),
    key `laf_category` (`name`) using btree
) engine = innodb
  default charset = utf8;

#发布表
create table laf_post
(
    `id`          int(11)     not null auto_increment comment '发布id',
    `user_id`    int(11) not null comment '用户ID',
    `LoF`         int         not null comment '0:寻物启事;1:失物招领',
    `name`        varchar(50) not null comment '物品名称',
    `category_id` int(11)      default null comment '物品分类',
    `time`        datetime    not null comment '丢失/拾取时间',
    `address`     varchar(200) default null comment '丢失/拾取地址',
    `detail`      varchar(200) default null comment '描述说明',
    `picture_url` varchar(500) default null comment '物品图片,url相对地址',
    `picture`     text comment '图片地址,json格式',
    primary key (`id`),
    key `laf_name` (`name`) using btree,
    key `laf_time` (`time`) using btree
) engine = innodb
  default charset = utf8;

#轮播表
create table laf_carousel
(
    `id`      int(11)  not null auto_increment comment '轮播id',
    `post_id` int(11)  not null comment '发布id',
    `playOrder`   int(11) default 0 comment '轮播顺序',
    `time`    datetime not null comment '设置时间',
    primary key (`id`)
) engine = innodb
  default charset = utf8;

#支付表
create table laf_payinfo
(
    `id`              int(11)  not null auto_increment,
    `user_id`         int(11)      not null,
    `post_id`         int(11)  not null,
    `order_no`        bigint(20)   DEFAULT null comment '订单号',
#     `pay_platform`    int(10)      DEFAULT null comment '支付平台 1-支付宝 2-微信',
#     `platform_number` VARCHAR(200) DEFAULT null comment '支付宝支付流水号',
    `platform_status` int  not null comment '支付状态 50:已取消 10:未付款 20:已付款 30:交易成功 40:交易关闭',
    `create_time`     datetime not null comment '创建时间',
    `payment_time`     datetime not null comment '支付时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;