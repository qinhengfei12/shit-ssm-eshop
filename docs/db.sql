CREATE TABLE IF NOT EXISTS `sys_users` (
                                           username varchar(64) not null unique,
                                           uid varchar(40) not null unique default uuid(),
                                           id int(8) unsigned not null auto_increment primary key ,
                                           phone bigint(11) not null unique,
                                           password varchar(100) not null,
                                           role varchar(24) not null,
                                           avatar varchar(80) null,
                                           preferPayment tinyint(1) unsigned not null,
                                           preferDelivery tinyint(1) unsigned not null,
                                           addr varchar(120) not null
);

CREATE INDEX `usercheck` on `sys_users` (
                                         username
    );

CREATE TABLE IF NOT EXISTS `sys_user_cart` (
                                               id int(8) unsigned not null auto_increment primary key,
                                               uid varchar(40) not null unique,
                                               foreign key (uid) references sys_users(uid) on update cascade on delete cascade,
                                               items varchar(5000) null
);

CREATE INDEX `uididx` on `sys_user_cart` (
                                          uid
    );

CREATE TABLE IF NOT EXISTS `sys_item_cates` (
                                                name varchar(40) not null unique,
                                                cid varchar(40) not null unique default uuid(),
                                                id int(8) unsigned not null primary key auto_increment
);

CREATE TABLE IF NOT EXISTS `sys_items` (
                                           cid varchar(40) not null unique,
                                           foreign key (cid) references sys_item_cates(cid) on update cascade on delete cascade,
                                           id int unsigned not null primary key auto_increment,
                                           itemid varchar(40) not null unique default uuid(),
                                           name varchar(100) not null,
                                           price decimal(14,2) not null,
                                           status tinyint(1) unsigned not null,
                                           image varchar(255) null,
                                           descr varchar(5000) null,
                                           shopown varchar(40) not null,
                                           foreign key (shopown) references sys_users(uid) on update cascade on delete cascade
);

CREATE INDEX `itemidx` on `sys_items` (itemid, name);

CREATE TABLE IF NOT EXISTS `sys_orders` (
                                            oid varchar(40) not null unique default uuid(),
                                            id int(8) unsigned not null primary key auto_increment,
                                            status tinyint(1) unsigned not null,
                                            items varchar(5000) not null,
                                            uid varchar(40) not null,
                                            foreign key (uid) references sys_users(uid) on update cascade on delete cascade,
                                            finalPrice decimal(14,2) not null,
                                            genTime timestamp not null default unix_timestamp(),
                                            paidTime timestamp null,
                                            doneTime timestamp null,
                                            refundTime timestamp null,
                                            paymentId tinyint(1) unsigned not null,
                                            deliveryId tinyint(1) unsigned not null
);

CREATE INDEX `orders_idx` on `sys_orders` (
                                           `oid`,`uid`
    );