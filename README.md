담당 기능 : 사원 계정, 관리자 기능, 달력을 이용한 일정관리
=============
### 1.사원 회원가입 
 :보안을 위해 모든 사람이 회원가입을 할 수 있는게 아니라 관리자가 미리 신규사원 메일로 시간 제한이 있는 가입 양식 링크를 전달하게 함. 프로필 사진 업로드는 AWS S3를 이용하여 사진을 클라우드에 저장하고, 실시간으로 업데이트하여 사용자 프로필에서 확인할 수 있도록 구현 )
### 2.사원 로그인 및 로그아웃 
:	로그인 시 3회 실패하면 계정이 잠기며, 잠금 해제 기능은 관리자에게만 부여함
### 3.사원의 아이디 / 비밀번호 찾기 
: 자신의 일부 정보를 입력하면 일부 가려서 조회 할 수 있고 비밀 번호는 임시 비밀 번호를 생성해 가입한 이메일로 전송함
### 4.최고 관리자의 부관리자 권한 관리
:상위 관리자가 하위 관리자에게 CRUD 권한을 부여
### 5.신규 사원 등록 및 메일 전송
### 6.사원 조회 및 검색
:동적 쿼리를 사용하여 유연하게 사원을 검색하고 조회할 수 있도록 함
### 7.사원 정보 수정 및 퇴사 처리
:사원의 정보를 수정하거나( 비밀번호 잠금 해제 포함) 퇴사 처리를 할 수 있는 기능을 구현
### 8.FULLCALENDAR를 이용해 관리자의 일정 관리
:스케줄 조회, 등록, 수정, 삭제   
:공개 범위 설정 기능: 스케줄 등록 및 수정 시 공개 범위를 설정할 수 있도록 함   
:중복 참여자 설정 기능: 스케줄 등록 및 수정 시 중복 참여자를 설정할 수 있는 기능을 구현

저는 시스템 개발 과정에서 단순한 기능 구현을 넘어서, 다음과 같은 심층적인 고민과 기술적 접근을 통해 파이널 프로젝트를 마쳤습니다
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
![image](https://github.com/user-attachments/assets/caa80c63-4575-4bff-a04d-930f92b87dc7)
![image](https://github.com/user-attachments/assets/7000a783-e6f9-4c1e-9d26-cf25a36a6735)
![image](https://github.com/user-attachments/assets/4131f61d-156b-4e73-944d-1891e4b0e156)
![image](https://github.com/user-attachments/assets/5bcf35e8-4c5b-4b15-8773-83d6195ae2b8)
![image](https://github.com/user-attachments/assets/13febec5-1bb9-4996-8ab5-19c7ddcc6f40)

![image](https://github.com/user-attachments/assets/3273d4eb-2a5c-480c-a38b-1d312318b98e)
![image](https://github.com/user-attachments/assets/c9029890-a49b-4ace-b313-bf9ff5ef9159)
![image](https://github.com/user-attachments/assets/63d1a473-fa91-4789-ba3e-759ceb012dad)

* * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * *
![image](https://github.com/user-attachments/assets/1acdc02b-c056-48ce-942a-7ee073a63f30)
![image](https://github.com/user-attachments/assets/b6f10013-8677-4350-acf2-e9a5d890353c)
![image](https://github.com/user-attachments/assets/1357fcca-570b-47a7-99e7-c09d7894f0ed)
![image](https://github.com/user-attachments/assets/3ecf11b2-fb85-43d3-8f76-67c2f38cf5e7)

* * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * *
![image](https://github.com/user-attachments/assets/56fdb698-2feb-4918-b38a-0af4c2ae1595)
* * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * *
![image](https://github.com/user-attachments/assets/6b26c89c-6188-4091-a241-0f500a7db9e3)
![image](https://github.com/user-attachments/assets/cf4864c2-f9c9-4c07-ad0c-9a1ae9dfc6ef)
![image](https://github.com/user-attachments/assets/65333a08-ba85-4818-919a-14fd0576887c)








* * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * *

 baby works 개발환경
====================
![image](https://github.com/user-attachments/assets/c7527abd-6731-466c-a969-152b55a1cbb1)


* * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * ** * *

 baby works그룹웨어조 프로젝트 개발일정
=======================================


![image](https://github.com/user-attachments/assets/3e0677d3-97b6-4147-a1b9-8a75f1c270a0)
![image](https://github.com/user-attachments/assets/e56055c2-21bd-416f-8c75-6d7e2a595130)
