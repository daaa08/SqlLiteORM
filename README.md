## SQLite
- 데이터 베이스 관리 시스템이나 서버가 아니라 응용프로그램에 넣어 사용
- 안드로이드는 SQLite 데이터 베이스를 기본으로 제공
- 데이터 베이스 구성 : 테이블, 레코더, 필드
테이블 : 데이터가 저장되어있는 공간
필드 : 각 항목의 분류 ex) 전화번호, 이름...
레코더 : 하나의 항목과 관련된 필드의 값 ex) 이름,전화번호,이메일주소 -> 한 사람이 가지고있는 데이터

---
—DDL (Data Definition Language)
// 테이블 생성 쿼리
**create table 테이블이름 (컬럼이름1 컬럼속성, 컬럼이름2, 속성....);**
create table memo(memoid int,  title varchar(250), content text);
// 테이블 자동 증가 (실행을 시켜줘야함 -insert에서 id 안써야 함)
**create table memo(memoid integer primary key,  title varchar(250), content text); -> sqlite
create table memo(memoid auto_increment,  title varchar(250), content text); -> mysql**
—DML
// 데이터 입력 쿼리
**insert into 테이블이름 (컬럼이름1, 컬럼이름2…) values(숫자값, ‘문자값’…);
insert into memo(memoid, title, content) values(1, ‘제목’, ‘내용임’);**
// 데이터 조회 쿼리
**select 컬럼이름1, 컬럼이름2…from 테이블이름 where 조건절;**
select memoir, title from memo;  // 조건절이 없으면 전체 데이터를 가져온다
select * from memo; // 컬럼 이름 대신에 * 을 사용하면 전체 컬럼을 가져온다
select memoid, title, content from memo where memoid=1;
select * from memo where content like ‘%내용&%’;// 문자열 중간 검색
// 데이터 수정쿼리
**update 테이블이름 set 컬럼이름 = 숫자값, 컬럼이름 = ‘문자값’ where 조건절 ;**
update memo set content=‘수정됨’ , title=‘제목이 수정됨’ where memoid=3;
// 데이터 삭제쿼리
**delete from 테이블이름 where 조건절**
delete from memo where memoid=2;   // 조건이 없으면 다 지워짐
--
char(250)
varchar(250)  // 내가 사용할 수 있는 최대 공간이 250(byte)
null은없음
text  // 댜용량의 데이터를 넣을 수 있음
---

## ORMLite
- SQLite데이터를 객체화처럼 쓸수있게 도와줌
- 쿼리를 날리는 형태 (create, read, search, update, delete)
```java
public class DBhelper extends OrmLiteSqliteOpenHelper{// 데이터에 접근할 수 있도록 해 줌

// Create - 데이터를 입력해주는 역할
public void create(Memo memo){
    try {
        // 1 테이블에 연결
        Dao<Memo, Integer> dao = getDao(Memo.class);  // data access object = dao
        // 2 데이터를 입력
        dao.create(memo);
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
// Read - 전체 데이터를 읽음
public List<Memo> readAll(){

    List<Memo> datas = null;
    try {
        // 1 테이블에 연결
        Dao<Memo, Integer> dao = getDao(Memo.class);  // data access object = dao
        // 2.1 데이터를 입력
        datas = dao.queryForAll(); // 전체 데이터 받음
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return datas;
}
// 하나만 읽어야할 경우
public Memo read(int memoid){
    Memo memo = null;
    try {
        // 1 테이블에 연결
        Dao<Memo, Integer> dao = getDao(Memo.class);  // data access object = dao
        // 2.1 데이터를 입력
        memo = dao.queryForId(memoid);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return memo;
}
// Search - 데이터 검색하기
public List<Memo> search(String word){
    List<Memo> datas = null;
    try {
        // 1 테이블에 연결
        Dao<Memo, Integer> dao = getDao(Memo.class);  // data access object = dao
        // 2.1 데이터를 검색하기
        String query = "select * from memo where content like '%"+word+"%'";
        GenericRawResults<Memo> temps = dao.queryRaw(query, dao.getRawRowMapper());
        datas = temps.getResults();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return datas;
}
// Update
public void Update(Memo memo){

    try {
        // 1 테이블에 연결
        Dao<Memo, Integer> dao = getDao(Memo.class);
        // 2 데이터를 수정
        dao.update(memo);
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
// Delete Object
public void delete(Memo memo){
    try {
        // 1 테이블에 연결
        Dao<Memo, Integer> dao = getDao(Memo.class);
        // 2 데이터를 삭제
        dao.delete(memo);
        // *참고 : 아이디 삭제
//            dao.deleteById(3);
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
// Delete By Id
public void delete(int id){
    try {
        // 1 테이블에 연결
        Dao<Memo, Integer> dao = getDao(Memo.class);
        // 2 데이터를 삭제
        dao.deleteById(id);
        // *참고 : 아이디 삭제
//            dao.deleteById(3);
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}
```

- *라이브러리 추가 방법* : Project Structure > Dependencies > +버튼으로 Library Dependency 추가
![스크린샷 2017-06-10 오후 10.42.36](http://i.imgur.com/YKDKOvi.png)


> *DataBase* : 데이터를 빠르게 읽고 쓸 수 있도록 만들어진 구조
> *RestAPI* : 웹 어플리케이션개발에서 다른 서비스에 요청을 보내고 응답을 받기튀해 정의된 명세서 - 주소체계
