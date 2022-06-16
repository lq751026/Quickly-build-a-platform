package com.object.type;

public enum LiFieldType {
    INT,
    CHAR,//	0-255 bytes	定长字符串
    VARCHAR,//	0-65535 bytes	变长字符串
    TINYINT,
    SMALLINT,//	2 Bytes	(-32 768，32 767)	(0，65 535)	大整数值
    MEDIUMINT,//	3 Bytes	(-8 388 608，8 388 607)	(0，16 777 215)	大整数值
    INTEGER,//	4 Bytes	(-2 147 483 648，2 147 483 647)	(0，4 294 967 295)	大整数值
    BIGINT,//	8 Bytes	(-9,223,372,036,854,775,808，9 223 372 036 854 775 807)	(0，18 446 744 073 709 551 615)	极大整数值
    FLOAT,//4 Bytes	(-3.402 823 466 E+38，-1.175 494 351 E-38)，0，(1.175 494 351 E-38，3.402 823 466 351 E+38)	0，(1.175 494 351 E-38，3.402 823 466 E+38)	单精度浮点数值
    DOUBLE,//	8 Bytes	(-1.797 693 134 862 315 7 E+308，-2.225 073 858 507 201 4 E-308)，0，(2.225 073 858 507 201 4 E-308，1.797 693 134 862 315 7 E+308)	0，(2.225 073 858 507 201 4 E-308，1.797 693 134 862 315 7 E+308)	双精度

    DECIMAL,//	对DECIMAL(M,D) ，如果M>D，为M+2否则为D+2	依赖于M和D的值	依赖于M和D的值	小数值日期和时间类型


    DATE,//	3	1000-01-01/9999-12-31	YYYY-MM-DD	日期值
    TIME,//	3	'-838:59:59'/'838:59:59'	HH:MM:SS	时间值或持续时间
    YEAR,//	1	1901/2155	YYYY	年份值
    DATETIME,//	8	1000-01-01 00:00:00/9999-12-31 23:59:59	YYYY-MM-DD HH:MM:SS	混合日期和时间值
    TIMESTAMP,//	4

    TINYBLOB,//	0-255 bytes	不超过 255 个字符的二进制字符串
    TINYTEXT,//	0-255 bytes	短文本字符串
    BLOB,//	0-65 535 bytes	二进制形式的长文本数据
    TEXT,//	0-65 535 bytes	长文本数据
    MEDIUMBLOB,//	0-16 777 215 bytes	二进制形式的中等长度文本数据
    MEDIUMTEXT,//	0-16 777 215 bytes	中等长度文本数据
    LONGBLOB,//	0-4 294 967 295 bytes	二进制形式的极大文本数据
    LONGTEXT,//	0-4 294 967 295 bytes	极大文本数

}