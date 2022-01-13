-- 사용자 테이블 

CREATE TABLE cookit_user(
   userpk INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
   email VARCHAR(50) UNIQUE NOT NULL,
   pw VARCHAR(500) NOT NULL,
   nm VARCHAR(5) NOT NULL,
   gender TINYINT UNSIGNED NOT NULL CHECK(gender IN (1, 2, 3)),
   birthdaymm VARCHAR(2) NOT NULL CHECK(birthdaymm >= 01 AND birthdaymm <= 09),
   birthdaydd VARCHAR(2) NOT NULL CHECK(birthdaydd >= 01 AND birthdaydd <= 31),
   rdt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),
   ldt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),
   joinpath INT UNSIGNED NOT NULL CHECK(joinpath >= 1),
   deluser TINYINT NOT NULL DEFAULT 0 CHECK(deluser >= 0 AND deluser <= 1) 
);

-- 상품 테이블
/*
	상품 Db
	goodspk - 상품 pk ( select 시 문자가 있다면 속도차이 발생(양이 많은경우 해당)
	gnum - 상품코드(문자4자리+숫자3 ~ 4자리)
	gnm - 상품명
	price - 가격
	quantity	- 보유 수량
	rdt - 등록일
	isdel - 삭제zetc
*/
CREATE TABLE goods(
	goodspk INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	gnum VARCHAR(8) NOT NULL,
	gnm VARCHAR(50) NOT NULL,
	price INT UNSIGNED DEFAULT 0,
	quantity TINYINT UNSIGNED DEFAULT 0,
	rdt DATETIME,
	isdel TINYINT UNSIGNED DEFAULT 0
);
