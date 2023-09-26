# 📂 CEOS WEEK 2: DB 모델링과 JPA 🥕
<br>

## 🥕 2주차 목표
### 1️⃣ 당근의 DB를 모델링해요
### 2️⃣ Repository 단위 테스트를 진행해요
<br>

## 🥕2주차 미션

### 1️⃣ 당근의 DB를 모델링해요

###  **💭 DB 설계 과정 💭**
**기획서는 당근의 UI로 가정하였습니다.
당근의 '중고거래'를 메인 서비스로, 선정하였습니다.
기획서를 바탕으로 요구사항 및 기능을 분석하여 DB를 설계하였습니다.  
화면별 요구사항에 초점을 맞추어 진행해보았습니다!**

### **(1) USER**
- 회원 정보를 담고 있는 테이블입니다.
- 요구사항 분석:
    - 회원 가입 시 닉네임, 전화번호, 비밀번호를 입력한다.
    - 회원 정보에는 이메일, 프로필 이미지, 동네, 매너온도가 있다.
- 모델링:

**📦 Table 📦**
<br>
<img width="334" alt="스크린샷 2023-09-26 오후 8 24 20" src="https://github.com/CAPSTON-EIGHT/EIGHT_SERVER/assets/77966605/69ad2bc9-a312-4579-918f-4bd8baaa04c1">

**🔑 Keys 🔑**
주요 Key를 소개합니다.
- **userId**:
    -   PK(기본키)로, 유저를 고유하게 식별하기 위해 사용됩니다.
    -    'AUTO_INCREMENT' 속성을 가지며, 새로운 사용자가 추가될 때마다 자동으로 1씩 증가합니다.
    -  NULL일 수 없으며 각 유저는 유일한 'userId' 값을 가져야 합니다.
- **email**:
    -   NULL 값을 허용하도록 설정하였으므로, 추후 등록할 수 있습니다.
- ****temperature****:
    -   36.5로 기본값을 설정해놓았습니다.

**✴️  제외한 옵션✴️**
- 국가
- 당근 약관 및 동의사항 동의여부, 최소 연령 확인 => 미선택 시 가입을 하지 못하므로 DB에는 포함하지 않았습니다.


### **(2) POST**
- 게시물 정보를 담고 있는 테이블입니다.
- 요구사항 분석:
    - 회원은 '내 물건 팔기'를 통해 판매 상품을 등록할 때, 상품 이미지, 제목, 카테고리, 거래방식, 가격, 가격 제안받기 여부, 상품 설명, 거래 희망 장소를 작성합니다.

**📦 Table 📦**
<br>
<img width="454" alt="스크린샷 2023-09-26 오후 10 30 02" src="https://github.com/CAPSTON-EIGHT/EIGHT_SERVER/assets/77966605/e57415d4-213c-4d8e-ace6-37e9e24daa94">

**🔑 Keys 🔑**
주요 Key를 소개합니다.
- **postId**:
    -   PK(기본키)로, 게시물을 고유하게 식별하기 위해 사용됩니다.
- **userId**:
    -   FK(외래키)로, user 테이블의 userId 열과 관련이 있으며, 게시물과 사용자 간의 관계를 나타냅니다.
- **categoryId**:
    -   FK(외래키)로, Category 테이블의 categoryId 열과 관련이 있으며, 게시물의 카테고리를 나타냅니다.
- **dealMethod:**
    -  거래 방식을 나타냅니다.
    - '판매하기'와 '나눔하기' 중 하나의 값을 가질 수 있으므로 enum형으로 정의하였습니다.

**✴️ 제외한 옵션 ✴️**
- 게시물 종류- 알바, 과외/클래스, 농수산물, 부동산, 중고차
- 게시물 작성 옵션- 선호하는 거래 장소

**(3) POSTIMG**
- 게시물의 이미지에 대한 정보를 담고 있는 테이블입니다.
- 하나의 게시물에 1개 이상의 사진이 존재하므로 테이블을 생성하였습니다.

**📦 Table 📦**
<br>
<img width="275" alt="스크린샷 2023-09-26 오후 10 30 24" src="https://github.com/CAPSTON-EIGHT/EIGHT_SERVER/assets/77966605/637cee9f-28cb-4f84-8235-29bf351ad238">

**🔑 Keys 🔑**
주요 Key를 소개합니다.
- **postImgId**:
    -   PK로, 게시물 이미지를 고유하게 식별하기 위해 사용됩니다.
- **postId**:
    -   FK로, 'post' 테이블의 'postId' 열과 관련이 있으며, 이미지와 게시물 간의 관계를 나타냅니다.


**(4) CATEGORY**
- 게시물의 카테고리 목록 정보를 담고 있는 테이블입니다.
- 요구사항 분석:
    - 회원은 카테고리를 선택해 상품을 볼 수 있습니다.

**📦 Table 📦**
<br>
<img width="317" alt="스크린샷 2023-09-26 오후 10 31 05" src="https://github.com/CAPSTON-EIGHT/EIGHT_SERVER/assets/77966605/65010f4d-2ab7-4f7c-a4e0-defbd8a53b75">

**🔑 Keys 🔑**
-  간단해서 생략하도록 하겠습니다!

**(5) CHATROOM**
- 구매자와 판매자의 채팅방에 대한 정보를 담고 있는 테이블입니다.
- 요구사항 분석:
    - 회원 간의 채팅을 통해 거래를 할 수 있습니다.

**📦 Table 📦**
<br>
<img width="343" alt="스크린샷 2023-09-26 오후 10 31 33" src="https://github.com/CAPSTON-EIGHT/EIGHT_SERVER/assets/77966605/40c2b96d-5a60-479e-88fc-721c5fad697d">



**🔑 Keys 🔑**
- **chatroomId**:
    -   PK(기본키)로, 채팅방을 고유하게 식별하기 위해 사용됩니다.
-  **sellerUserId:**
   -  FK로, 채팅방의 판매자 유저를 나타냅니다.
-  **buyerUserId:**
   -  FK로, 채팅방의 구매자 유저를 나타냅니다.

**(6) CHATCONTENT**
- 구매자와 판매자의 채팅방 내용에 대한 정보를 담고 있는 테이블입니다.

**📦 Table 📦**
<br>
<img width="350" alt="스크린샷 2023-09-26 오후 10 32 00" src="https://github.com/CAPSTON-EIGHT/EIGHT_SERVER/assets/77966605/61e4f155-e79a-4080-8084-6de507a446f8">

**🔑 Keys 🔑**
- **chatId**:
    -   PK(기본키)로, 채팅 내용을 고유하게 식별하기 위해 사용됩니다.
-  **chatroomId:**
    -   FK로, 채팅 메시지가 어떤 채팅방에 속한 것인지를 식별하는 데 사용됩니다.

**(7) AREA**
- 지역 정보를 담고 있는 테이블입니다.

**📦 Table 📦**
<br>
<img width="346" alt="스크린샷 2023-09-26 오후 10 32 32" src="https://github.com/CAPSTON-EIGHT/EIGHT_SERVER/assets/77966605/9752abb9-0298-4cc4-9b36-0c9a44773643">

**🔑 Keys 🔑**
- **areaId**:
    -   PK(기본키)로, 지역을 고유하게 식별하기 위해 사용됩니다.
- **latitude**:
    -   decimal 형으로 위도 정보를 저장합니다.
- **longitude**:
    -   decimal 형으로 경도 정보를 저장합니다.


**(8) USER_AREA**
- 회원이 설정한 동네 정보를 담고 있는 테이블입니다.
- 예) 여의동, 여의도동

**📦 Table 📦**
<br>
<img width="302" alt="스크린샷 2023-09-26 오후 10 33 02" src="https://github.com/CAPSTON-EIGHT/EIGHT_SERVER/assets/77966605/fbe853dd-ca4a-4c4a-8611-c018aceecc5f">

**🔑 Keys 🔑**
- **areaId**:
    -   FK로, 지역 정보를 나타냅니다.
-  **userId:**
    -   FK로, 사용자와 지역 간의 관계를 나타냅니다.


**(9) WISHLIST**
- 관심 목록에 추가한 게시물 정보를 담고 있는 테이블입니다.

**📦 Table 📦**
<br>
<img width="330" alt="스크린샷 2023-09-26 오후 10 33 25" src="https://github.com/CAPSTON-EIGHT/EIGHT_SERVER/assets/77966605/8653f7a2-9a1f-4edf-b9d0-22ae15366314">

**🔑 Keys 🔑**
- **wishlistId**:
    -   PK로, 관심 게시물을 고유하게 식별하기 위해 사용됩니다.
-  **userId:**
    -   FK로, 유저 정보를 식별합니다.
-  **postId:**
    -   FK로, 찜 목록 항목과 게시물 간의 관계를 나타냅니다.

**(10) REVIEW**
- 리뷰 게시물 테이블입니다.

**📦 Table 📦**
<br>
<img width="427" alt="스크린샷 2023-09-26 오후 10 33 56" src="https://github.com/CAPSTON-EIGHT/EIGHT_SERVER/assets/77966605/0c2fddc7-120a-452f-8eb9-dc08be3cc65b">



**🔑 Keys 🔑**
- **reviewId**:
    -   PK로, 리뷰 게시물을 고유하게 식별하기 위해 사용됩니다.
-  **reviewerId:**
    -   FK로, 리뷰를 작성한 사용자를 식별합니다.
-  **revieweeId:**
    -   FK로, 리뷰를 받은 사용자를 식별합니다.
-  **preference:**
    -   리뷰의 평가를 나타내는 ENUM 열로, '별로에요', '좋아요!', '최고에요!' 중 하나의 값을 가집니다.

**✴️변경한 사항✴️**
- 기존 당근 서비스와 달리 유저에 대한 리뷰/ 상품에 대한 리뷰가 나뉘는 것이 아닌 유저에만 리뷰가 저장되도록 하였습니다.
- 리뷰 중 '어떤 점이 좋았나요?' 부분은 생략하였습니다.
---
### 2️⃣ Repository 단위 테스트를 진행해요
모델링 제작을 완료하였다면 해당 모델이 제대로 되었는지 확인하기 위해서 `Repository` 계층의 단위 테스트를 작성해봅시다!

-   **ForeignKey 필드를 포함하는 Entity**을 하나 선택하여 테스트를 진행해주세요 ➡️ **🙋‍♀️ `User` 를 선택하였습니다!**

#### 0)  main application 실행 확인
<img width="1351" alt="스크린샷 2023-09-27 오전 12 13 43" src="https://github.com/CAPSTON-EIGHT/EIGHT_SERVER/assets/77966605/59f0f913-1d9a-40f1-926b-95cb5ba6b417">

👩🏻‍🔧 *자잘한 트러블슈팅*은 맨 아래 섹션에 있습니다..

#### 1)  given when then 에 따라서 테스트를 작성하기

**1️⃣ given**
```java
User myMelody = User.builder()  
.nickname("마이멜로디")  
.email("mymelody@gmail.com")  
.phone("010-1234-5678")  
.password("mamerie0v0")  
.created(Timestamp.valueOf(LocalDateTime.now()))  
.temperature(BigDecimal.valueOf(36.7))  
.updated(Timestamp.valueOf(LocalDateTime.now()))  
.build();  
  
User kuromi = User.builder()  
.nickname("쿠로미")  
.email("kurooooo@gmail.com")  
.phone("010-3434-3434")  
.password("kuromzzang!")  
.created(Timestamp.valueOf(LocalDateTime.now()))  
.temperature(BigDecimal.valueOf(40))  
.updated(Timestamp.valueOf(LocalDateTime.now()))  
.build();  
  
User kitty = User.builder()  
.nickname("키티")  
.email("kitty@gmail.com")  
.phone("010-1111-3434")  
.password("kitty!0!")  
.created(Timestamp.valueOf(LocalDateTime.now()))  
.temperature(BigDecimal.valueOf(40))  
.updated(Timestamp.valueOf(LocalDateTime.now()))  
.build();
```

> 팁 아닌 팁..? created, updated도 test시 넣어줘야 에러가 나지 않습니다..

**2️⃣ when**
```java
userRepository.save(myMelody);  
userRepository.save(kuromi);  
userRepository.save(kitty);
```

**3️⃣ then**
```java
List<User> users = userRepository.findAll();  
assertThat(users.size()).isEqualTo(3);  
  
// 닉네임이 "쿠로미"인 사용자 찾기  
Optional<User> foundUser1 = userRepository.findByNickname("쿠로미");  
assertTrue(foundUser1.isPresent());  
assertEquals("쿠로미", foundUser1.get().getNickname());  
  
// 이메일이 "mymelody@gmail.com"인 사용자 찾기  
Optional<User> foundUser2 = userRepository.findByEmail("mymelody@gmail.com");  
assertTrue(foundUser2.isPresent());  
assertEquals("mymelody@gmail.com", foundUser2.get().getEmail());  
  
// 이메일 "kitty@gmail.com" 존재 여부 확인  
boolean emailExists = userRepository.existsByEmail("kitty@gmail.com");  
assertTrue(emailExists);  
  
// 닉네임 "쿠로미" 존재 여부 확인  
boolean nicknameExists = userRepository.existsByNickname("키티");  
assertTrue(nicknameExists);
```
<br> 같은 파일 내 코드지만 가독성을 위해 로그 출력 부분은 다른 box에 적어보았습니다~ 

``` java
// 저장된 유저들 로그에 출력  
logger.info("저장된 유저들:");  
for (User user : users) {  
logger.info("User ID: {}, Nickname: {}, Email: {}", user.getId(), user.getNickname(), user.getEmail());  
}  
  
// 유저 정보 로그에 출력  
logger.info("Found User1: ID={}, Nickname={}, Email={}",  
foundUser1.map(User::getId).orElse(null),  
foundUser1.map(User::getNickname).orElse(null),  
foundUser1.map(User::getEmail).orElse(null));  
  
logger.info("Found User2: ID={}, Nickname={}, Email={}",  
foundUser2.map(User::getId).orElse(null),  
foundUser2.map(User::getNickname).orElse(null),  
foundUser2.map(User::getEmail).orElse(null));  
  
logger.info("Email exists: {}", emailExists);  
  
logger.info("Nickname exists: {}", nicknameExists);  
}
```


####  2) 테스트에서 객체를 3개 이상 넣은 이후에 해당 객체가 출력되는지 확인하기
<center>⬇️ <br>
💡 결과 확인! 💡
<img width="1179" alt="스크린샷 2023-09-27 오전 1 06 27" src="https://github.com/CAPSTON-EIGHT/EIGHT_SERVER/assets/77966605/e68c25c0-2cdf-43ed-a73a-5d74855988d6">


**✅ 저장된 유저들 출력하기** <br>
**✅ 닉네임이 "쿠로미"인 사용자 찾기** <br>
**✅ 이메일이 "kitty@gmail.com"인 사용자 찾기** <br>
**✅ 이메일 "mymelody@gmail.com" 존재 여부 확인** <br>
**✅ 닉네임 "키티" 존재 여부 확인**

<br><br>
모두 체크 완료!

####  3) 테스트를 수행할 때 발생하는 JPA 쿼리를 조회해보기
**`UserRepository.java`**
``` java
@Repository  
public interface UserRepository extends JpaRepository<User, Integer> {  
  
	Optional<User> findByNickname(String nickname);  
	Optional<User> findByEmail(String email);  
	boolean existsByEmail(String email);  
	boolean existsByNickname(String nickname);  
  
}
```

**1) findByNickname**: 닉네임을 기준으로 사용자를 조회
**2) findByEmail:** 이메일을 기준으로 사용자를 조회
**3) existsByEmail:** 이메일이 데이터베이스에 존재하는지 확인
**4) existsByNickname:** 닉네임이 데이터베이스에 존재하는지 확인

---
###  **❓ 고민해볼 사항 ❓**
- 지역 정보를 담는 테이블을 고민하는 부분이 과제 수행 중 가장 어려웠던 것 같습니다. 스터디 이후 더 개선해봐야할 것 같습니다!

---
### 🤖 Troubleshooting 🤖
**1) entity has no identifier**
분명 entity 파일에 **@Id** 어노테이션을 갖는 필드가 존재함에도 위와 같은 에러가 발생하며 애플리케이션이 실행되지 않았습니다.
**👩🏻‍🔧 해결방법**: 잘못된 패키지에서 Id 클래스를 import 하고 있었습니다.
아래와 같이 변경하면 됩니다!
import org.springframework.data.annotation.Id; ➡️ import javax.persistence.Id;
<br><br>
**2) Caused by: java.sql.SQLSyntaxErrorException: Table 'karrot.post' doesn't exist**
DB 연동도 잘 되어있는데 위와 같은 문제가 발생했습니다. 원인은 엔티티 클래스의 관계 설정 부분이 잘못되어 있어서 발생했던 것..!
**👩🏻‍🔧 해결방법**:
**변경 전)**
``` java
@ManyToOne  
@JoinColumn(name = "postId", nullable = false)  private Post post;
```
**변경 후)**
```java
@ManyToOne  
@JoinColumn(name = "postId", referencedColumnName = "postId", nullable = false)  private Post post;
```
**`referencedColumnName`** 속성을 추가해 연관된 엔티티 ( `Post` 엔티티)의 PK 이름을 지정하니 해결됐습니다~

---
###  **❎ 읽어볼 만한 사이트들 ❎**
https://www.techm.kr/news/articleView.html?idxno=97322
https://aws.amazon.com/ko/solutions/case-studies/danggeun/

---