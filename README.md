그룹웨어prj를 진행하였고 일정관리자 역할을 맡은 이수인입니다
저의 담당 기능은 사원 계정, 관리자 기능, 달력을 이용한 일정관리입니다 

## 저는 시스템 개발 과정에서 단순한 기능 구현을 넘어서, 문제 해결과 성능 향상을 위해 심층적인 고민을 하면서 프로젝트를 완수했습니다. 프로젝트를 진행하며, 기능의 실용성을 넘어 시스템의 전체적인 품질과 효율성을 높이기 위한 다양한 접근 방식을 고민하고 적용했습니다. 한 층 나아지는 과정 봐주실래요😄❤
* * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * *  
![image](https://github.com/user-attachments/assets/8b89793f-75d2-49b0-8f83-3d883913fdff)
![image](https://github.com/user-attachments/assets/d5dbc596-e15d-46ff-a726-dba0473be27a)
![image](https://github.com/user-attachments/assets/67c331fa-e654-4827-813c-c4c875a2b138)
![image](https://github.com/user-attachments/assets/9772aeb0-e225-4b5c-8e10-096e47b7946b)
* * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * *  
![image](https://github.com/user-attachments/assets/112dc0d0-1d1f-4443-a39d-a4370d006651)
* * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * *  
![image](https://github.com/user-attachments/assets/668f62e8-f3fa-4e41-a7a7-ba53cbd5f6c8)
![image](https://github.com/user-attachments/assets/df73ede1-7527-4085-a14e-ad86b61cc0de)
![image](https://github.com/user-attachments/assets/b1d9d580-d3a7-47bd-97c9-017f90e2d157)
![image](https://github.com/user-attachments/assets/2bffb1c4-d323-4737-ae08-c12284891e87)
* * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * *  
![image](https://github.com/user-attachments/assets/733e498e-04ce-40bc-95b8-ae99f63276d3)
![image](https://github.com/user-attachments/assets/a79e56aa-8284-461e-9186-65e8131823c9)
![image](https://github.com/user-attachments/assets/4c973d8a-96c9-4eea-b240-523f1173a844)
![image](https://github.com/user-attachments/assets/7102bdf4-bc28-4b75-9bfa-deff980aebc7)
* * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * *  
![image](https://github.com/user-attachments/assets/3fd93efc-d9bd-43c3-a437-79f42efbe26d)
![image](https://github.com/user-attachments/assets/eff990ca-11f4-4fc9-90c8-a89b8171734e)
![image](https://github.com/user-attachments/assets/006f1c02-d7e6-40a2-88c9-12a4df01a54f)
![image](https://github.com/user-attachments/assets/24f5c597-813f-412f-b840-4b008d5d8081)
![image](https://github.com/user-attachments/assets/6aca743f-969c-4ebf-9b43-d91d6eb9dfb4)


* * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * *  
![image](https://github.com/user-attachments/assets/5395c512-3526-47a6-86a8-fc2b61cc00ff)

* * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * *  
저의 담당 기능입니다.
======================
### 1.사원 회원가입 
#### 가입 절차
보안을 강화하기 위해, 관리자가 신규 사원에게 시간 제한이 있는 가입 양식 링크를 이메일로 전송합니다. 사용자는 링크를 통해 가입을 진행할 수 있습니다.
#### 프로필 사진 업로드
프로필 사진은 AWS S3를 이용하여 클라우드에 저장됩니다. 사용자 프로필에서 실시간으로 업데이트된 사진을 확인할 수 있도록 구현하였습니다.
### 2.사원 로그인 및 로그아웃 
#### 로그인 처리
서블릿 컨테이너에 도달하기 전에 필터가 로그인 요청을 처리합니다. 로그인 시도 3회 실패 시 계정이 잠기며, 잠금 해제 기능은 관리자만 수행할 수 있습니다.
#### 보안
필터를 통해 로그인 요청을 선제적으로 처리하고, 사용자 인증을 강화하였습니다.
### 3.사원의 아이디 / 비밀번호 찾기 
#### 아이디 찾기
사용자가 자신의 정보를 입력하면, 일부 가려진 아이디를 조회할 수 있습니다.
#### 비밀번호 찾기
임시 비밀번호를 생성하여 가입한 이메일로 전송합니다. 이를 통해 비밀번호 찾기 기능을 안전하게 구현하였습니다.
### 4.최고 관리자의 부관리자 권한 관리
#### 권한 부여
상위 관리자가 하위 관리자에게 CRUD 권한을 부여합니다.
#### AOP 적용
AOP를 통해 부관리자일 때 프록시 객체가 target 메서드를 가로채어 권한 인증을 수행합니다. 이를 통해 권한 관리의 일관성과 보안을 강화하였습니다. 
### 5.신규 사원 등록 및 메일 전송
#### 신규 등록
신규 사원 등록 시 사원 정보를 데이터베이스에 저장하고, 이메일로 관련 정보를 발송합니다.
### 6.사원 조회 및 검색
#### 동적 쿼리
동적 쿼리를 사용하여 사원을 유연하게 검색하고 조회할 수 있도록 구현하였습니다. 사용자 요구에 맞춰 다양한 검색 조건을 지원합니다.
### 7.사원 정보 수정 및 퇴사 처리
#### 정보 수정
사원의 정보를 수정할 수 있으며, 비밀번호 잠금 해제 기능도 포함되어 있습니다.
#### 퇴사 처리
퇴사 처리 기능을 통해 사원의 상태를 관리할 수 있습니다.
### 8.FULLCALENDAR를 이용해 관리자의 일정 관리
#### 스케줄 조회, 등록, 수정, 삭제
관리자가 일정을 효과적으로 관리할 수 있는 기능을 제공합니다.
#### 공개 범위 설정
스케줄 등록 및 수정 시 공개 범위를 설정할 수 있습니다.
#### 중복 참여자 설정 기능
스케줄 등록 및 수정 시 스케줄을 같이 수행할 중복 참여자를 설정하는 기능을 구현하였습니다.
### 9.관리자 로그인, 로그아웃 : 필터 사용
#### 필터 사용
HTTP 요청이 들어올 때마다 세션에서 사용자 정보를 조회하고, 세션 유효성을 확인합니다. 세션이 없거나 사용자 정보가 없는 경우 인증되지 않은 상태로 간주하고 리디렉션 처리합니다.
   
* * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * *  
 나의 ERD
====================
 ![image](https://github.com/user-attachments/assets/388d0c02-ce08-4d89-97fb-da135c2dcb96)

* * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * *
         
 baby works 개발환경
====================
![image](https://github.com/user-attachments/assets/c7527abd-6731-466c-a969-152b55a1cbb1)

* * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * *


 baby works그룹웨어팀 프로젝트 팀원들 개발일정
=============================================
![image](https://github.com/user-attachments/assets/3e0677d3-97b6-4147-a1b9-8a75f1c270a0)
![image](https://github.com/user-attachments/assets/e56055c2-21bd-416f-8c75-6d7e2a595130)
![image](https://github.com/user-attachments/assets/1692049f-09f1-4be6-874d-a18ba4622399)
![image](https://github.com/user-attachments/assets/70a6afc9-0f99-4655-aa1c-5dd9378a474f)



 baby works그룹웨어팀 기능
=======================================
![image](https://github.com/user-attachments/assets/2c728d24-1861-43c4-b5f3-55612522a8c4)
