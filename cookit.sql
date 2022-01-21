-- 동의 페이지
/*
	agreepk - 동의 pk
	userpk - 회원 pk	
	termsOfUse - 이용약관(필수 ) 0-  동의 x, 1- 동의 
	userInformation - 개인정보 수집 및 이용(필수 ) 0-  동의 x, 1- 동의 
	marketing - 마케팅등 정보 수신 등 활용(선택)
	age - 14세 이상 확인(필수)
	notRealpage - 실제 페이지 아니라는 것( 필수)
	rdt - 첫 동의 일
	mdt - 수정 일

*/
CREATE TABLE cookit_agree(
	agreepk INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	userpk INT UNSIGNED NOT NULL,
	termsOfUse TINYINT UNSIGNED NOT NULL CHECK(termsOfUse IN(0,1)),
	userInformation TINYINT UNSIGNED NOT NULL CHECK(userInformation IN(0,1)),
	marketing TINYINT UNSIGNED NOT NULL DEFAULT 0 CHECK(marketing IN(0,1)),
	age TINYINT UNSIGNED NOT NULL CHECK(age IN(0,1)),
	notRealpage TINYINT UNSIGNED NOT NULL CHECK(notRealpage IN(0,1)),
	rdt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	mdt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP 
);
-- 사용자 테이블 
/*
   회원정보 Db
   userpk - 회원 pk
   email - 이메일 (id 기능 겸용)
   pw - 비밀번호
   nm - 이름
   gender - 성별 1- 남, 2- 여, 3- 선택안함
   birthdaymm - 생일 (월) 00 ~ 09 값 중 입력가능, 00 은 선택 안한값
   birthdaydd - 생일 (일) 00 ~ 31 값 중 입력가능, 00 은 선택 안한값
   rdt - 가입일
   ldt - 마지막 로그인 일
   joinpath - 회원가입 경로 0- 관리자 but 0 설정 막기,  1- 홈페이지, 2- 네이버, etc
              지정 경로 이외의 값 ex) 0 이 넘어오면 가입 거절
   ukey - uuid 저장값 refreshtoken 키에 사용.
   deluser - 삭제여부 정상유지- 0, 삭제- 1 
*/
CREATE TABLE cookit_user(
   userpk INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
   email VARCHAR(50) UNIQUE NOT NULL,
   pw VARCHAR(500) NOT NULL,
   nm VARCHAR(5) NOT NULL,
   gender TINYINT UNSIGNED NOT NULL CHECK(gender IN (1, 2, 3)),
   birthdaymm VARCHAR(2) NOT NULL CHECK(birthdaymm >= 00 AND birthdaymm <= 09),
   birthdaydd VARCHAR(2) NOT NULL CHECK(birthdaydd >= 00 AND birthdaydd <= 31),
   rdt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),
   ldt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),
   joinpath TINYINT UNSIGNED NOT NULL CHECK(joinpath >= 0),
   ukey VARCHAR(2) NOT NULL DEFAULT '',
   deluser TINYINT UNSIGNED NOT NULL DEFAULT 0 CHECK(deluser >= 0 AND deluser <= 1)
);

-- 배송지 테이블
/*
	shipping address Db
	addresspk - 배송지 pk
	a - 받는사람
	b - 주소
	c - 연락처
	d - 기본배송지 설정
	userpk - 회원 pk
	
*/
CREATE TABLE cookit_shipping_address(
	addresspk INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
);

-- 상품 테이블
/*
	상품 Db
	goodspk - 상품 pk ( select 시 문자가 있다면 속도차이 발생(양이 많은경우 해당)
	gnum - 상품코드(카테고리pk + 숫자(0000001))
	categorypk - 카테고리 pk
	gnm - 상품명
	price - 가격
	quantity	- 보유 수량
	rdt - 등록일
	isdel - 삭제
*/
CREATE TABLE cookit_goods(
	goodspk INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	gnum VARCHAR(8) UNIQUE NOT NULL,
	categorypk INT UNSIGNED,
	gnm VARCHAR(50) NOT NULL,
	price INT UNSIGNED NOT NULL DEFAULT 0,
	quantity TINYINT UNSIGNED NOT NULL DEFAULT 0,
	rdt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),
	isdel TINYINT UNSIGNED DEFAULT 0
);

-- 상품 카테고리 테이블
/*
	상품 category Db
	gcategorypk - 카테고리 pk
	categorynm - 카테고리 이름
*/
CREATE TABLE cookit_goods_category(
	categorypk INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	categorynm VARCHAR(10) NOT NULL,
);

-- 상품 이미지 테이블
/*
	상품 image Db
	imgpk - 이미지 pk
	goodspk - 해당상품
	img - 이미지 명
	defaultimage - 메인되는 기본 이미지 설정
*/
CREATE TABLE cookit_goods_image (
	imgpk INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	goodspk INT UNSIGNED NOT NULL,
	img VARCHAR(50),
	defaultimage TINYINT UNSIGNED
);

-- 상품 리뷰 테이블

/*
	reviewpk - 리뷰 pk
	goodspk - 상품정보 pk
	userpk - 회원정보 pk
	revscore - 별점 (0~5)
	revctnt - 리뷰 내용
	revimg - 이미지
	rdt - 작성일
	mdt - 수정일
	isdel - 삭제처리
*/

CREATE TABLE cookit_review(
	reviewpk INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	goodspk INT UNSIGNED NOT NULL, 
	userpk INT UNSIGNED NOT NULL, 
	revscore TINYINT UNSIGNED NOT NULL DEFAULT 0 CHECK(revscore >=0 AND revscore<=5),
	revctnt VARCHAR(1000) NOT NULL DEFAULT '',
	revimg VARCHAR(10) NOT NULL DEFAULT '',
	rdt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	mdt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	isdel TINYINT UNSIGNED NOT NULL DEFAULT 0
);