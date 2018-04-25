# PilotSpeaker

## 개요
Blockchain & Digital Me 연구 실험 파일럿 스터디에서 사용될 프로토타입 어플리케이션


## 기능
- 실험 컨셉 설명 페이지
- 녹음할 내용을 음성 파일로 재생
- 녹음 기능


### 실험 컨셉 설명 페이지
- BeforeStart1.java ~ BeforeStart4.java 
- 단순 액티비티 이동으로 구현
- 4개의 설명 페이지가 끝나면 실험 주의사항 설명을 위한 StartApp.java로 이동
- StartApp.java에서 실험 시작 버튼을 누르면 SharedPreference로 first라는 항목을 만들고 "1"을 넣어줌
- MainActivity.java는 앱이 켜질때마다 first가 1인지 확인하고, 1이면 Mainpage.java로 이동 / 아니면 BeforeStart1.java로 이동


### 녹음할 내용을 음성 파일로 재생
- RecordDiary.java, AskKeyword.java, AskEmotion.java
- Amazon Polly(https://aws.amazon.com/ko/polly/)로 mp3 파일을 다운받아 사용 
- 3개의 요구사항이 Seyeon 음성으로 재생됨
- 각 음성 파일 시간에 맞춰서, 음성 재생이 끝나면 자동으로 녹음 시작


### 녹음 기능
- RecordDiary.java, AskKeyword.java, AskEmotion.java
- '녹음 종료' 버튼을 누르면 녹음이 종료되고 내장 메모리에 PilotSpeaker 폴더에 하위 폴더가 생성되어 녹음 파일이 저장됨 


