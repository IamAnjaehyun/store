## Spring-boot Store

### ✅ 프로젝트 설명
- 스프링부트를 이용하여 만든 Store API입니다.

### ✅ 개발 환경
- 운영체제 : MacOS
- 통합개발환경(IDE) : IntelliJ
- JDK 버전 : JDK 11
- 데이터 베이스 : MYSQL
- 빌드 툴 : Gradle
- 관리 툴 : GitHub

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


🍏 API 활용법 (임시~!)

회원가입

![Image](https://github.com/IamAnjaehyun/fastcampus-project-board/assets/101981639/cad8e557-3e73-42ab-b9a7-4f273c4bc8e8)

로그인

![Image](https://github.com/IamAnjaehyun/fastcampus-project-board/assets/101981639/95a82189-255c-4ef4-981b-98d8c95168ef)

권한 부여

![Image](https://github.com/IamAnjaehyun/fastcampus-project-board/assets/101981639/8616bb5a-e5eb-446a-bffc-34451b387c5d)

식당 등록 (권한이 있을 시에만 가능)

![Image](https://github.com/IamAnjaehyun/fastcampus-project-board/assets/101981639/6296b66f-4c33-4603-a2c9-a31479c57c4c)

식당 조회

![Image](https://github.com/IamAnjaehyun/fastcampus-project-board/assets/101981639/aa669d88-684a-4974-b24a-d0c06870da66)

식당 예약 (식당 조회 후 식당 번호를 통해 예약)

![Image](https://github.com/IamAnjaehyun/fastcampus-project-board/assets/101981639/6f2c652b-a87b-4df0-b9e4-908b38eb7f8b)

예약 정보 확인 (사장 번호를 통해 사장이 보유한 식당의 예약 정보를 확인)

![Image](https://github.com/IamAnjaehyun/fastcampus-project-board/assets/101981639/b773145b-2619-4e94-a468-69b9c0a4e66f)

예약 승인 (사장 토큰을 통해 phoneNum 비교하여 파트너인지 확인)

![Image](https://github.com/IamAnjaehyun/fastcampus-project-board/assets/101981639/5bb9893c-484b-4d25-94d6-af1d2850305b)

예약 승인, 거절 후 예약 정보 확인

![Image](https://github.com/IamAnjaehyun/fastcampus-project-board/assets/101981639/4640c8bb-c048-4243-9bbc-a24ee01c1df4)

![Image](https://github.com/IamAnjaehyun/fastcampus-project-board/assets/101981639/f3eceaef-9993-4673-b437-72f21c126a36)

휴대폰 번호 입력을 통해 10분전 매장도착 확인

![Image](https://github.com/IamAnjaehyun/fastcampus-project-board/assets/101981639/5976b8bb-4420-444b-937d-1bfe5eb77ce1)


상점 이용 후 리뷰 작성

![Image](https://github.com/IamAnjaehyun/fastcampus-project-board/assets/101981639/cc4b5ef6-ce0c-41b2-a31f-dcfefeebe939)

✅ ERD

![Image](https://github.com/casealot/casealot-backend/assets/101981639/8aa07234-8e2e-4549-bbc9-55787e29621f)
