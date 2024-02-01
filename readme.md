## Spring-boot Store API     

### ✅ 프로젝트 설명
- 스프링부트를 이용하여 만든 Store API입니다.

### ✅ 개발 환경
- 운영체제 : MacOS
- 통합개발환경(IDE) : IntelliJ
- JAVA Version : JDK 11
- SpringBoot Version : 2.7.5
- 데이터 베이스 : MYSQL
- 빌드 툴 : Gradle
- 관리 툴 : GitHub

## 🔌 Dependencies
- Spring Data JPA
- Mysql connector
- h2
- Spring Security
- jwt
- swagger
- Lombok
- Spring Web


## 💻 기술 스택
- 백엔드
    - SpringBoot, Spring Data JPA
- 데이터베이스
    - MySQL, MySQL Workbench

## 🛠 ERD

![Image](https://github.com/casealot/casealot-backend/assets/101981639/9018361c-05d3-40d5-b95b-c9686234409f)

✅ API요구사항 1
- 공통 인증 구현
- 매장의 점장은 예약 서비스 앱에 상점을 등록(매장 명, 상점위치, 상점 설명)
- 매장을 등록하기 위해서는 파트너 회원 가입해야 가능(따로, 승인 조건은 없으며 가입 후 바로 이용 가능)

✅ API요구사항 2
- 매장 이용자는 앱을 통해서 매장을 검색하고 상세 정보를 확인
- 매장의 상세 정보를 보고, 예약을 진행 (예약을 진행하기 위해서는 회원 가입이 필수적으로 이루어 져야 한다.)

✅ API요구사항 3
- 서비스를 통해서 예약한 이후에, 예약 10분전에 도착하여 키오스크를 통해서 방문 확인을 진행
- 예약 및 사용 이후에 리뷰를 작성
- 서비스 이용 중 애로사항 발생 - 점장은 승인/예약 거절을 가능

✅ 추가 기능
- Controller 마다 Swagger 적용
- Custom Exception 처리

👉 PostMan

![Image](https://github.com/casealot/casealot-backend/assets/101981639/4e7031b4-a701-4095-828f-2263c90f6657)

👉 Swagger
- 권한이 있어야 crud 구현이 가능하기에 swagger 공개

![Image](https://github.com/casealot/casealot-backend/assets/101981639/bd2dae15-c85a-49eb-bc33-23df2a06938f)



[//]: # (🍏 API 활용법 &#40;임시~!&#41;)

[//]: # ()
[//]: # (회원가입)

[//]: # ()
[//]: # (![Image]&#40;https://github.com/IamAnjaehyun/fastcampus-project-board/assets/101981639/cad8e557-3e73-42ab-b9a7-4f273c4bc8e8&#41;)

[//]: # ()
[//]: # (로그인)

[//]: # ()
[//]: # (![Image]&#40;https://github.com/IamAnjaehyun/fastcampus-project-board/assets/101981639/95a82189-255c-4ef4-981b-98d8c95168ef&#41;)

[//]: # ()
[//]: # (권한 부여)

[//]: # ()
[//]: # (![Image]&#40;https://github.com/IamAnjaehyun/fastcampus-project-board/assets/101981639/8616bb5a-e5eb-446a-bffc-34451b387c5d&#41;)

[//]: # ()
[//]: # (식당 등록 &#40;권한이 있을 시에만 가능&#41;)

[//]: # ()
[//]: # (![Image]&#40;https://github.com/IamAnjaehyun/fastcampus-project-board/assets/101981639/6296b66f-4c33-4603-a2c9-a31479c57c4c&#41;)

[//]: # ()
[//]: # (식당 조회)

[//]: # ()
[//]: # (![Image]&#40;https://github.com/IamAnjaehyun/fastcampus-project-board/assets/101981639/aa669d88-684a-4974-b24a-d0c06870da66&#41;)

[//]: # ()
[//]: # (식당 예약 &#40;식당 조회 후 식당 번호를 통해 예약&#41;)

[//]: # ()
[//]: # (![Image]&#40;https://github.com/IamAnjaehyun/fastcampus-project-board/assets/101981639/6f2c652b-a87b-4df0-b9e4-908b38eb7f8b&#41;)

[//]: # ()
[//]: # (예약 정보 확인 &#40;사장 번호를 통해 사장이 보유한 식당의 예약 정보를 확인&#41;)

[//]: # ()
[//]: # (![Image]&#40;https://github.com/IamAnjaehyun/fastcampus-project-board/assets/101981639/b773145b-2619-4e94-a468-69b9c0a4e66f&#41;)

[//]: # ()
[//]: # (예약 승인 &#40;사장 토큰을 통해 phoneNum 비교하여 파트너인지 확인&#41;)

[//]: # ()
[//]: # (![Image]&#40;https://github.com/IamAnjaehyun/fastcampus-project-board/assets/101981639/5bb9893c-484b-4d25-94d6-af1d2850305b&#41;)

[//]: # ()
[//]: # (예약 승인, 거절 후 예약 정보 확인)

[//]: # ()
[//]: # (![Image]&#40;https://github.com/IamAnjaehyun/fastcampus-project-board/assets/101981639/4640c8bb-c048-4243-9bbc-a24ee01c1df4&#41;)

[//]: # ()
[//]: # (![Image]&#40;https://github.com/IamAnjaehyun/fastcampus-project-board/assets/101981639/f3eceaef-9993-4673-b437-72f21c126a36&#41;)

[//]: # ()
[//]: # (휴대폰 번호 입력을 통해 10분전 매장도착 확인)

[//]: # ()
[//]: # (![Image]&#40;https://github.com/IamAnjaehyun/fastcampus-project-board/assets/101981639/5976b8bb-4420-444b-937d-1bfe5eb77ce1&#41;)

[//]: # ()
[//]: # ()
[//]: # (상점 이용 후 리뷰 작성)

[//]: # ()
[//]: # (![Image]&#40;https://github.com/IamAnjaehyun/fastcampus-project-board/assets/101981639/cc4b5ef6-ce0c-41b2-a31f-dcfefeebe939&#41;)

👉 느낀점
- 강의를 통해 강사님께서 작성해주신대로만 토큰을 통해 여러가지 인증을 구현했던 것이 도움이 되었지만,</br>
이번 강의를 통해 혼자 처음으로 토큰을 구현하고, 사용 해 본 프로젝트였기에 어려움이 많았다. 특히, </br>
토큰을 파싱하여 권한을 확인하거나, 토큰 소유주의 정보를 가져와 이곳 저곳에 사용하는게 어려웠는데, </br>
하루하루 검색을 통해 해결방법을 찾고 해결해 나가는 과정이 재미있었던 프로젝트였다.💪</br>
요구사항을 제대로 확인하지 못해 마지막에 기능(조건을 갖고있는 조회 기능들..)을 추가했다.. 요구사항을 먼저 꼭 확인할것..</br>
미흡한 부분을 수정하다 custom exception 처리를 하였는데 이 부분이 가장 재미있었다..!</br>
내가 예상한 exception 발생 상황을 예측하고 발생시켜 내가 제대로 했다는 것을 확인하니 뿌듯했기때문.!



