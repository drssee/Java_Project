drop database if exists movie_db;
create database movie_db;
use movie_db;
set SQL_SAFE_UPDATES = 0;

create table movie
(
    tno      int auto_increment
        primary key,
    title    varchar(30) not null,
    story    text not null,
    director varchar(30) not null,
    runtime  int         not null,
    opendate date        not null,
    schedule timestamp   not null,
    regDate  date        not null,
    price    int         not null,
    actor    varchar(30) not null
);

create table user
(
    id            varchar(30)                        not null
        primary key,
    pwd           varchar(30)                        not null,
    name          varchar(30)                        not null,
    phone         varchar(30)                        not null,
    email         varchar(30)                        not null,
    regDate       datetime default CURRENT_TIMESTAMP null,
    total_payment int      default 0                 null,
    modDate       timestamp                          null,
    gender        int                                not null,
    age           int                                not null
);

create table reservation
(
    rno      int auto_increment
        primary key,
    title    varchar(30)                         not null,
    schedule timestamp                           not null,
    seatnum  int                                 not null,
    tno      int                                 not null,
    id       varchar(30)                         not null,
    price    int                                 not null,
    regDate  timestamp default CURRENT_TIMESTAMP null,
    modDate  timestamp                           null,
    constraint id
        foreign key (id) references user (id),
    constraint tno
        foreign key (tno) references movie (tno)
);

insert into movie_db.movie (title, story, director, runtime
                         , opendate, schedule, regDate ,price, actor)
values ('인생은아름다워','1','최국희',122,'2022-09-28','2022-10-04 21:30','2022-10-04',13000,'류승룡,염정아,박세완,옹성우');

insert into movie_db.movie (title, story, director, runtime
                         , opendate, schedule, regDate ,price, actor)
values ('인생은아름다워','2','최국희',122,'2022-09-28','2022-10-07 12:50','2022-10-04',11000,'류승룡,염정아,박세완,옹성우');

insert into movie_db.movie (title, story, director, runtime
                         , opendate, schedule, regDate ,price, actor)
values ('인생은아름다워','3','최국희',122,'2022-09-28','2022-10-09 15:30','2022-10-04',13000,'류승룡,염정아,박세완,옹성우');

insert into movie_db.movie (title, story, director, runtime
                         , opendate, schedule, regDate ,price, actor)
values ('인생은아름다워','1','최국희',122,'2022-09-28','2022-11-04 21:30','2022-10-04',13000,'류승룡,염정아,박세완,옹성우');

insert into movie_db.movie (title, story, director, runtime
                         , opendate, schedule, regDate ,price, actor)
values ('인생은아름다워','2','최국희',122,'2022-09-28','2022-11-07 12:50','2022-10-04',11000,'류승룡,염정아,박세완,옹성우');

insert into movie_db.movie (title, story, director, runtime
                         , opendate, schedule, regDate ,price, actor)
values ('인생은아름다워','3','최국희',122,'2022-09-28','2022-11-09 15:30','2022-10-04',13000,'류승룡,염정아,박세완,옹성우');

update movie_db.movie
set story = '생애 가장 빛나는 선물
모든 순간이 노래가 된다!

무뚝뚝한 남편 ‘진봉’과 무심한 아들 딸을 위해 헌신하며 살아온 ‘세연’은
어느 날 자신에게 시간이 얼마 남지 않았다는 것을 알게 된다.
한 치 앞도 알 수 없는 인생에 서글퍼진 ‘세연’은 마지막 생일선물로
문득 떠오른 자신의 첫사랑을 찾아 달라는 황당한 요구를 한다.
막무가내로 우기는 아내의 고집에 어쩔 수 없이 여행길에 따라나선 ‘진봉’은
아무런 단서도 없이 이름 석 자만 가지고 무작정 전국 방방곡곡을 누빈다.
시도 때도 없이 티격태격 다투던 두 사람은 가는 곳곳마다
자신들의 찬란했던 지난날 소중한 기억을 하나 둘 떠올리는데...
과연 ‘세연’의 첫사랑은 어디에 있으며 그들의 여행은 무사히 마칠 수 있을까?'
where title = '인생은아름다워';



insert into movie_db.movie (title, story, director, runtime
                         , opendate, schedule, regDate ,price, actor)
values ('정직한후보2','1','장유정',107,'2022-09-28','2022-10-05 18:50','2022-09-25',13000,
        '라미란,김무열,윤경호,서현우,박진주,윤두준');

insert into movie_db.movie (title, story, director, runtime
                         , opendate, schedule, regDate ,price, actor)
values ('정직한후보2','2','장유정',107,'2022-09-28','2022-10-09 19:40','2022-09-25',13000,
        '라미란,김무열,윤경호,서현우,박진주,윤두준');

insert into movie_db.movie (title, story, director, runtime
                         , opendate, schedule, regDate ,price, actor)
values ('정직한후보2','3','장유정',107,'2022-09-28','2022-10-10 10:20','2022-09-25',9000,
        '라미란,김무열,윤경호,서현우,박진주,윤두준');

insert into movie_db.movie (title, story, director, runtime
                         , opendate, schedule, regDate ,price, actor)
values ('정직한후보2','1','장유정',107,'2022-09-28','2022-11-05 18:50','2022-09-25',13000,
        '라미란,김무열,윤경호,서현우,박진주,윤두준');

insert into movie_db.movie (title, story, director, runtime
                         , opendate, schedule, regDate ,price, actor)
values ('정직한후보2','2','장유정',107,'2022-09-28','2022-11-09 19:40','2022-09-25',13000,
        '라미란,김무열,윤경호,서현우,박진주,윤두준');

insert into movie_db.movie (title, story, director, runtime
                         , opendate, schedule, regDate ,price, actor)
values ('정직한후보2','3','장유정',107,'2022-09-28','2022-11-10 10:20','2022-09-25',9000,
        '라미란,김무열,윤경호,서현우,박진주,윤두준');

update movie_db.movie
set story = '거짓말 못하는 ‘진실의 주둥이’ 컴백! 이번엔 2명?!

서울시장 선거에서 떨어지며 쫄딱 망한 백수가 된 ‘주상숙’은
우연히 바다에 빠진 한 청년을 구한 일이 뉴스를 타며
고향에서 화려한 복귀의 기회를 잡는다.

하지만 정직하면 할수록 바닥으로 곤두박질치는 지지율 앞에
다시 뻥쟁이로 돌아간 그 순간,
‘주상숙’에게 운명처럼 찾아온 ‘진실의 주둥이’!

이번엔 ‘주상숙’의 비서실장 ‘박희철’까지 주둥이가 쌍으로 털리게 되는데...

재미도 2배! 웃음도 2배!
주둥이 대폭발 코미디가 돌아왔다!'
where title = '정직한후보2';

insert into movie_db.movie (title, story, director, runtime
                         , opendate, schedule, regDate ,price, actor)
values ('듄','1','드니 빌뢰브',155,'2022-10-05','2022-10-07 21:30','2022-10-01',13000,'티모시 샬라메,레베카 퍼거슨,오스카 아이삭');

insert into movie_db.movie (title, story, director, runtime
                         , opendate, schedule, regDate ,price, actor)
values ('듄','2','드니 빌뢰브',155,'2022-10-05','2022-10-08 10:30','2022-10-01',9000,'티모시 샬라메,레베카 퍼거슨,오스카 아이삭');

insert into movie_db.movie (title, story, director, runtime
                         , opendate, schedule, regDate ,price, actor)
values ('듄','3','드니 빌뢰브',155,'2022-10-05','2022-10-09 12:20','2022-10-01',11000,'티모시 샬라메,레베카 퍼거슨,오스카 아이삭');

insert into movie_db.movie (title, story, director, runtime
                         , opendate, schedule, regDate ,price, actor)
values ('듄','1','드니 빌뢰브',155,'2022-10-05','2022-11-07 21:30','2022-10-01',13000,'티모시 샬라메,레베카 퍼거슨,오스카 아이삭');

insert into movie_db.movie (title, story, director, runtime
                         , opendate, schedule, regDate ,price, actor)
values ('듄','2','드니 빌뢰브',155,'2022-10-05','2022-11-08 10:30','2022-10-01',9000,'티모시 샬라메,레베카 퍼거슨,오스카 아이삭');

insert into movie_db.movie (title, story, director, runtime
                         , opendate, schedule, regDate ,price, actor)
values ('듄','3','드니 빌뢰브',155,'2022-10-05','2022-11-09 12:20','2022-10-01',11000,'티모시 샬라메,레베카 퍼거슨,오스카 아이삭');

update movie_db.movie
set story = '“듄을 지배하는 자가 우주를 지배한다!”

10191년, 아트레이데스 가문의 후계자인 폴(티모시 샬라메)은 시공을 초월한 존재이자
전 우주를 구원할 예지된 자의 운명을 타고났다.
그리고 어떤 계시처럼 매일 꿈에서 아라키스 행성에 있는 한 여인을 만난다.
모래언덕을 뜻하는 ''듄''이라 불리는 아라키스는 물 한 방울 없는 사막이지만
우주에서 가장 비싼 물질인 신성한 환각제 스파이스의 유일한 생산지로 이를 차지하기 위한 전쟁이 치열하다.
황제의 명령으로 폴과 아트레이데스 가문은 죽음이 기다리는 아라키스로 향하는데…
위대한 자는 부름에 응답한다, 두려움에 맞서라, 이것은 위대한 시작이다!'
where title = '듄';

insert into movie_db.movie (title, story, director, runtime
                         , opendate, schedule, regDate ,price, actor)
values ('인터스텔라','1','크리스토퍼 놀란',169,'2022-10-05','2022-10-07 09:50','2022-10-03',9000,'앤 해서웨이,제시카 차스테인,매튜 맥커너히');

insert into movie_db.movie (title, story, director, runtime
                         , opendate, schedule, regDate ,price, actor)
values ('인터스텔라','2','크리스토퍼 놀란',169,'2022-10-05','2022-10-09 15:40','2022-10-03',11000,'앤 해서웨이,제시카 차스테인,매튜 맥커너히');

insert into movie_db.movie (title, story, director, runtime
                         , opendate, schedule, regDate ,price, actor)
values ('인터스텔라','3','크리스토퍼 놀란',169,'2022-10-05','2022-10-11 19:20','2022-10-03',13000,'앤 해서웨이,제시카 차스테인,매튜 맥커너히');

insert into movie_db.movie (title, story, director, runtime
                         , opendate, schedule, regDate ,price, actor)
values ('인터스텔라','1','크리스토퍼 놀란',169,'2022-10-05','2022-11-07 09:50','2022-10-03',9000,'앤 해서웨이,제시카 차스테인,매튜 맥커너히');

insert into movie_db.movie (title, story, director, runtime
                         , opendate, schedule, regDate ,price, actor)
values ('인터스텔라','2','크리스토퍼 놀란',169,'2022-10-05','2022-11-09 15:40','2022-10-03',11000,'앤 해서웨이,제시카 차스테인,매튜 맥커너히');

insert into movie_db.movie (title, story, director, runtime
                         , opendate, schedule, regDate ,price, actor)
values ('인터스텔라','3','크리스토퍼 놀란',169,'2022-10-05','2022-11-11 19:20','2022-10-03',13000,'앤 해서웨이,제시카 차스테인,매튜 맥커너히');

update movie_db.movie
set story = '“우린 답을 찾을 거야, 늘 그랬듯이”
세계 각국의 정부와 경제가 완전히 붕괴된 미래가 다가온다.
지난 20세기에 범한 잘못이 전 세계적인 식량 부족을 불러왔고, NASA도 해체되었다.
이때 시공간에 불가사의한 틈이 열리고, 남은 자들에게는 이 곳을 탐험해 인류를 구해야 하는 임무가 지워진다.
사랑하는 가족들을 뒤로 한 채 인류라는 더 큰 가족을 위해, 그들은 이제 희망을 찾아 우주로 간다.
그리고 우린 답을 찾을 것이다. 늘 그랬듯이…'
where title = '인터스텔라';


insert into movie_db.movie (title, story, director, runtime
                         , opendate, schedule, regDate ,price, actor)
values ('스파이더맨-노 웨이 홈, 펀버전','1','존 왓츠',157,'2022-10-05','2022-10-08 19:20','2022-10-04',13000,'톰 홀랜드,젠데이아 콜먼,베네딕트 컴버배치');

insert into movie_db.movie (title, story, director, runtime
                         , opendate, schedule, regDate ,price, actor)
values ('스파이더맨-노 웨이 홈, 펀버전','2','존 왓츠',157,'2022-10-05','2022-10-10 13:20','2022-10-04',11000,'톰 홀랜드,젠데이아 콜먼,베네딕트 컴버배치');

insert into movie_db.movie (title, story, director, runtime
                         , opendate, schedule, regDate ,price, actor)
values ('스파이더맨-노 웨이 홈, 펀버전','3','존 왓츠',157,'2022-10-05','2022-10-12 10:20','2022-10-04',9000,'톰 홀랜드,젠데이아 콜먼,베네딕트 컴버배치');

insert into movie_db.movie (title, story, director, runtime
                         , opendate, schedule, regDate ,price, actor)
values ('스파이더맨-노 웨이 홈, 펀버전','1','존 왓츠',157,'2022-10-05','2022-11-08 19:20','2022-10-04',13000,'톰 홀랜드,젠데이아 콜먼,베네딕트 컴버배치');

insert into movie_db.movie (title, story, director, runtime
                         , opendate, schedule, regDate ,price, actor)
values ('스파이더맨-노 웨이 홈, 펀버전','2','존 왓츠',157,'2022-10-05','2022-11-10 13:20','2022-10-04',11000,'톰 홀랜드,젠데이아 콜먼,베네딕트 컴버배치');

insert into movie_db.movie (title, story, director, runtime
                         , opendate, schedule, regDate ,price, actor)
values ('스파이더맨-노 웨이 홈, 펀버전','3','존 왓츠',157,'2022-10-05','2022-11-12 10:20','2022-10-04',9000,'톰 홀랜드,젠데이아 콜먼,베네딕트 컴버배치');


update movie_db.movie
set story = '‘미스테리오’의 계략으로 세상에 정체가 탄로난 스파이더맨 ‘피터 파커’는
하루 아침에 평범한 일상을 잃게 된다.
문제를 해결하기 위해 ‘닥터 스트레인지’를 찾아가 도움을 청하지만
뜻하지 않게 멀티버스가 열리면서 각기 다른 차원의 불청객들이 나타난다.
‘닥터 옥토퍼스’를 비롯해 스파이더맨에게 깊은 원한을 가진 숙적들의 강력한 공격에
‘피터 파커’는 사상 최악의 위기를 맞게 되는데…'
where title = '스파이더맨-노 웨이 홈, 펀버전';

insert into movie_db.movie (title, story, director, runtime
                         , opendate, schedule, regDate ,price, actor)
values ('공조2-인터내셔날','1','이석훈',129,'2022-09-07','2022-10-06 10:20','2022-09-05',9000,'현빈,유해진,임윤아,다니엘 헤니,진선규');

insert into movie_db.movie (title, story, director, runtime
                         , opendate, schedule, regDate ,price, actor)
values ('공조2-인터내셔날','2','이석훈',129,'2022-09-07','2022-10-08 13:20','2022-09-05',11000,'현빈,유해진,임윤아,다니엘 헤니,진선규');

insert into movie_db.movie (title, story, director, runtime
                         , opendate, schedule, regDate ,price, actor)
values ('공조2-인터내셔날','3','이석훈',129,'2022-09-07','2022-10-11 18:20','2022-09-05',13000,'현빈,유해진,임윤아,다니엘 헤니,진선규');

insert into movie_db.movie (title, story, director, runtime
                         , opendate, schedule, regDate ,price, actor)
values ('공조2-인터내셔날','1','이석훈',129,'2022-09-07','2022-11-06 10:20','2022-09-05',9000,'현빈,유해진,임윤아,다니엘 헤니,진선규');

insert into movie_db.movie (title, story, director, runtime
                         , opendate, schedule, regDate ,price, actor)
values ('공조2-인터내셔날','2','이석훈',129,'2022-09-07','2022-11-08 13:20','2022-09-05',11000,'현빈,유해진,임윤아,다니엘 헤니,진선규');

insert into movie_db.movie (title, story, director, runtime
                         , opendate, schedule, regDate ,price, actor)
values ('공조2-인터내셔날','3','이석훈',129,'2022-09-07','2022-11-11 18:20','2022-09-05',13000,'현빈,유해진,임윤아,다니엘 헤니,진선규');

update movie_db.movie
set story = '공조 이즈 백! 이번엔 삼각 공조다!

남한으로 숨어든 글로벌 범죄 조직을 잡기 위해
새로운 공조 수사에 투입된 북한 형사 ‘림철령’(현빈).
수사 중의 실수로 사이버수사대로 전출됐던 남한 형사 ‘강진태’(유해진)는
광수대 복귀를 위해 모두가 기피하는 ‘철령’의 파트너를 자청한다.

이렇게 다시 공조하게 된 ‘철령’과 ‘진태’!
‘철령’과 재회한 ‘민영’(임윤아)의 마음도 불타오르는 가운데,
‘철령’과 ‘진태’는 여전히 서로의 속내를 의심하면서도 나름 그럴싸한 공조 수사를 펼친다.
드디어 범죄 조직 리더인 ‘장명준’(진선규)의 은신처를 찾아내려는 찰나,
미국에서 날아온 FBI 소속 ‘잭’(다니엘 헤니)이 그들 앞에 나타나는데…!

아직도 짠내 나는 남한 형사,
여전한 엘리트 북한 형사,
그리고 FBI 소속 해외파 형사까지!
각자의 목적으로 뭉친 그들의 짜릿한 공조 수사가 시작된다!'
where title = '공조2-인터내셔날';

insert into movie_db.movie (title, story, director, runtime
                         , opendate, schedule, regDate ,price, actor)
values ('기생충','1','봉준호',131,'2022-09-11','2022-10-01 18:20','2022-09-06',13000,'송강호,이선균,조여정,최우식,박소담,장혜진');

insert into movie_db.movie (title, story, director, runtime
                         , opendate, schedule, regDate ,price, actor)
values ('기생충','2','봉준호',131,'2022-09-11','2022-09-27 12:20','2022-09-06',11000,'송강호,이선균,조여정,최우식,박소담,장혜진');

insert into movie_db.movie (title, story, director, runtime
                         , opendate, schedule, regDate ,price, actor)
values ('기생충','3','봉준호',131,'2022-09-11','2022-10-13 09:50','2022-09-06',9000,'송강호,이선균,조여정,최우식,박소담,장혜진');

insert into movie_db.movie (title, story, director, runtime
                         , opendate, schedule, regDate ,price, actor)
values ('기생충','1','봉준호',131,'2022-09-11','2022-11-01 18:20','2022-09-06',13000,'송강호,이선균,조여정,최우식,박소담,장혜진');

insert into movie_db.movie (title, story, director, runtime
                         , opendate, schedule, regDate ,price, actor)
values ('기생충','2','봉준호',131,'2022-09-11','2022-10-27 12:20','2022-09-06',11000,'송강호,이선균,조여정,최우식,박소담,장혜진');

insert into movie_db.movie (title, story, director, runtime
                         , opendate, schedule, regDate ,price, actor)
values ('기생충','3','봉준호',131,'2022-09-11','2022-11-13 09:50','2022-09-06',9000,'송강호,이선균,조여정,최우식,박소담,장혜진');


update movie_db.movie
set story = '“폐 끼치고 싶진 않았어요”
전원백수로 살 길 막막하지만 사이는 좋은 기택(송강호) 가족.
장남 기우(최우식)에게 명문대생 친구가 연결시켜 준 고액 과외 자리는
모처럼 싹튼 고정수입의 희망이다.
온 가족의 도움과 기대 속에 박사장(이선균) 집으로 향하는 기우.
글로벌 IT기업 CEO인 박사장의 저택에 도착하자
젊고 아름다운 사모님 연교(조여정)가 기우를 맞이한다.
그러나 이렇게 시작된 두 가족의 만남 뒤로, 걷잡을 수 없는 사건이 기다리고 있었으니…'
where title = '기생충';

insert into movie_db.movie (title, story, director, runtime
                         , opendate, schedule, regDate ,price, actor)
values ('범죄도시2','1','이상용',106,'2022-05-18','2022-05-20 19:50','2022-05-16',13000,'마동석,손석구,최귀화,박지환,허동원,하준,정재광');

insert into movie_db.movie (title, story, director, runtime
                         , opendate, schedule, regDate ,price, actor)
values ('범죄도시2','2','이상용',106,'2022-05-18','2022-07-22 18:30','2022-05-16',13000,'마동석,손석구,최귀화,박지환,허동원,하준,정재광');

insert into movie_db.movie (title, story, director, runtime
                         , opendate, schedule, regDate ,price, actor)
values ('범죄도시2','3','이상용',106,'2022-05-18','2022-10-15 09:50','2022-05-16',9000,'마동석,손석구,최귀화,박지환,허동원,하준,정재광');

insert into movie_db.movie (title, story, director, runtime
                         , opendate, schedule, regDate ,price, actor)
values ('범죄도시2','1','이상용',106,'2022-05-18','2022-06-20 19:50','2022-05-16',13000,'마동석,손석구,최귀화,박지환,허동원,하준,정재광');

insert into movie_db.movie (title, story, director, runtime
                         , opendate, schedule, regDate ,price, actor)
values ('범죄도시2','2','이상용',106,'2022-05-18','2022-07-22 18:30','2022-05-16',13000,'마동석,손석구,최귀화,박지환,허동원,하준,정재광');

insert into movie_db.movie (title, story, director, runtime
                         , opendate, schedule, regDate ,price, actor)
values ('범죄도시2','3','이상용',106,'2022-05-18','2022-11-15 09:50','2022-05-16',9000,'마동석,손석구,최귀화,박지환,허동원,하준,정재광');


update movie_db.movie
set story = '“느낌 오지? 이 놈 잡아야 하는 거”

가리봉동 소탕작전 후 4년 뒤,
금천서 강력반은 베트남으로 도주한 용의자를 인도받아 오라는 미션을 받는다.

괴물형사 ‘마석도’(마동석)와 ‘전일만’(최귀화) 반장은 현지 용의자에게서 수상함을 느끼고,
그의 뒤에 무자비한 악행을 벌이는 ‘강해상’(손석구)이 있음을 알게 된다.

‘마석도’와 금천서 강력반은 한국과 베트남을 오가며
 역대급 범죄를 저지르는 ‘강해상’을 본격적으로 쫓기 시작하는데...

나쁜 놈들 잡는 데 국경 없다!
통쾌하고 화끈한 범죄 소탕 작전이 다시 펼쳐진다! '
where title = '범죄도시2';

# 관리자
insert into movie_db.user (id, pwd, name, phone, email, regDate, gender, age)
values ('admin','admin','EZEN_Movie','031-111-2222','EZEN_Movie@movie.com',now(),0,0);

# 일반유저
insert into movie_db.user (id, pwd, name, phone, email, regDate, total_payment,gender, age)
values ('user1','user1','김영화','010-111-2222','movie1@movie.com',now(),22000,1,20);

insert into movie_db.user (id, pwd, name, phone, email, regDate, total_payment,gender, age)
values ('mintchoco','mintchoco','이영화','010-111-2222','movie2@movie.com',now(),9000,2,25);

insert into movie_db.user (id, pwd, name, phone, email, regDate, total_payment,gender, age)
values ('bochoja','bochoja','이수현','010-111-2222','movie3@movie.com',now(),11000,1,17);

insert into movie_db.user (id, pwd, name, phone, email, regDate, total_payment,gender, age)
values ('mwosigi','mwosigi','이다은','010-111-2222','movie4@movie.com',now(),9000,2,18);

insert into movie_db.user (id, pwd, name, phone, email, regDate, total_payment,gender, age)
values ('aether','aether','박수현','010-111-2222','movie5@movie.com',now(),9000,1,35);

insert into movie_db.user (id, pwd, name, phone, email, regDate, total_payment,gender, age)
values ('whalpha','whalpha','이서우','010-111-2222','movie6@movie.com',now(),9000,2,34);

insert into movie_db.user (id, pwd, name, phone, email, regDate, total_payment,gender, age)
values ('durian','durian','한시현','010-111-2222','movie7@movie.com',now(),9000,1,41);

insert into movie_db.user (id, pwd, name, phone, email, regDate, total_payment,gender, age)
values ('nene','nene','송지율','010-111-2222','movie8@movie.com',now(),11000,2,40);


# 초기예약
insert into reservation (title, schedule, seatnum, tno, id, price)
values ('범죄도시2','2022-11-15 09:50',10,48,'user1',9000);

insert into reservation (title, schedule, seatnum, tno, id, price,regDate)
values ('범죄도시2','2022-05-20 19:50',21,43,'user1',13000,'2022-05-18');

insert into reservation (title, schedule, seatnum, tno, id, price)
values ('범죄도시2','2022-11-15 09:50',31,48,'whalpha',9000);

insert into reservation (title, schedule, seatnum, tno, id, price)
values ('범죄도시2','2022-11-15 09:50',55,48,'durian',9000);

insert into reservation (title, schedule, seatnum, tno, id, price)
values ('스파이더맨-노 웨이 홈','2022-10-12 10:20',28,27,'mintchoco',9000);

insert into reservation (title, schedule, seatnum, tno, id, price)
values ('스파이더맨-노 웨이 홈','2022-10-12 10:20',1,27,'aether',9000);

insert into reservation (title, schedule, seatnum, tno, id, price)
values ('스파이더맨-노 웨이 홈','2022-10-12 10:20',100,27,'mwosigi',9000);

insert into reservation (title, schedule, seatnum, tno, id, price)
values ('듄','2022-10-12 10:20',99,18,'bochoja',11000);

insert into reservation (title, schedule, seatnum, tno, id, price)
values ('듄','2022-10-12 10:20',5,18,'nene',11000);
