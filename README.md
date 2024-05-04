## 기획


- 프로젝트 기획 내용
    
    미래 개발자를 꿈꾸는 사람들이 스터디 팀원을 쉽게 구할 수 있도록 스터디 게시판을 만들고자 했습니다.
    저는 먼저 게시판을 프론트, 백엔드 혹은 알고리즘 공부 등 큰 카테고리로 나누고 해당 게시판 안에서 개발 언어 등 세부적으로 원하는 스터디를 찾을 수 있도록 기획하였습니다.
    

## ERD

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/d85fd78d-de88-4966-9b27-62e3e2e8c2a0/1ada5106-0872-4fe7-938f-41b8f6b940fd/Untitled.png)

## 와이어프레임

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/d85fd78d-de88-4966-9b27-62e3e2e8c2a0/22c92157-1feb-4f60-806c-ebd71cfb8ced/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/d85fd78d-de88-4966-9b27-62e3e2e8c2a0/88382bee-e1ec-4b37-802d-be29d1e4be17/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/d85fd78d-de88-4966-9b27-62e3e2e8c2a0/a30fa44d-52ba-4c0f-a502-70561a10e84f/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/d85fd78d-de88-4966-9b27-62e3e2e8c2a0/97d179f0-6b57-408f-931c-6e6ed26d7236/Untitled.png)

## 트러블슈팅


![The HTTP response header [Location] with value [[http://localhost:8080/error?message=비밀번호가](http://localhost:8080/error?message=%EB%B9%84%EB%B0%80%EB%B2%88%ED%98%B8%EA%B0%80) 일치하지 않습니다. 다시 확인해주세요.] has been removed from the response because it is invalid](https://prod-files-secure.s3.us-west-2.amazonaws.com/d85fd78d-de88-4966-9b27-62e3e2e8c2a0/d8278f68-2f18-452c-97dc-bc7752a1986c/Untitled.png)

The HTTP response header [Location] with value [[http://localhost:8080/error?message=비밀번호가](http://localhost:8080/error?message=%EB%B9%84%EB%B0%80%EB%B2%88%ED%98%B8%EA%B0%80) 일치하지 않습니다. 다시 확인해주세요.] has been removed from the response because it is invalid

- 문제상황 : 댓글을 수정하는 경우, 등록했을 당시 설정했던 비밀번호 일치 여부에 따라서 삭제 및 수정이 가능하도록 구현하는 코드를 작성했다. 데이터베이스 상에서는 정상적으로 동작을 하는데 클라이언트 쪽 화면에서 응답을 받았을 때 alert를 통해 알려주는 부분을 연결하지 못하고 있는 상황이었다.
- 원인 : 프론트 쪽 자바스크립트 코드를 수정해야 했다. 서버로 수정 요청을 보내고 난 후 성공적인 응답을 받았을 때의 처리, 오류 응답을 받았을 때의 처리를 제대로 하지 못해서 생긴 문제였다.
    
    ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/d85fd78d-de88-4966-9b27-62e3e2e8c2a0/dd6b951e-a997-43f3-9363-e55b7024b3de/Untitled.png)
    
- 해결방법 : fetch 함수를 사용해서 서버에 데이터를 요청하고, response.ok 응답을 받으면 댓글 수정 완료 알림창을, 인증 오류 코드인 401 응답 코드를 받으면 비밀번호 오류 알림창을 띄우는 식으로 작업을 수행해 해결했다.

## 회고

- 프로젝트를 하면서 어려웠던 부분
    
    MVC 패턴에 익숙해지는데 시간이 조금 걸렸던 것 같다. 처음에 프로젝트 기본 설정을 하고 구조를 짤 때에는 너무 막막했다. 아직 개념들이 확실하게 이해되지 않은 상황에서 혼자 처음부터 CRUD를 구현하려고 하니까 생각보다 쉽지 않았다. 
    
- 어려웠던 부분을 극복한 경험
    
    수업 때 봤던 코드들을 그대로 붙여 넣거나 어노테이션 등의 설정들을 그대로 쓰면 나중에 가서 문제가 생길 것 같았다. 그래서 초반에 많은 시간을 들이더라도 한 줄 한 줄 이해해가면서 코드를 작성했던 것 같다. 
    
    그렇게 많은 시간을 들여서 게시판 CRUD를 작성하고 나니 나머지 게시글과 댓글 CRUD는 앞에 썼던 시간보다는 훨씬 적은 시간을 투자하게 됐고 그 과정에서 뿌듯함을 느꼈다. 
    
    또 엘리스 수업 때 보여주신 실습 코드를 많이 참고하게 됐는데, 제 코드와 비교하면서 구현하니 어느 부분을 더 보완해야 할지도 보였다. 
    
    프론트 부분은 거의 신경쓰지 못하고 기능을 구현하는데 거의 시간을 쓴 점이 아쉽지만, 보완하지 못한 부분과 Junit 단위테스트 및 추가 기능들은 발표가 끝나고 나서 수정해 볼 예정이다.
    
- 공부할 때 도움이 되었던 노하우, 꿀팁
    
    가장 먼저 디버깅 모드가 굉장히 많은 도움이 된다. 한 줄 한 줄 씩 내가 생각한 대로 코드가 동작하는 지 보니까 어느 부분에서 문제가 발생하는 지 빠르게 찾을 수 있게 되었다.
    
    GPT과 구글링을 적절히 사용하는 것도 필요한 것 같다. 대신 코드를 따라치지 않고 모든 부분을 이해했을 때 사용하는 방식으로 쓴다면 괜찮다고 생각한다.
    
- (추가한 기능이 있다면) 기능 추가를 한 이유와 사용한 기술
    
    비밀번호 설정 기능을 추가했다.
    
- 전반적인 소감
    
    확실히 직접 프로젝트를 만들고 나니까 스프링부트에 대한 이해도가 높아진 것 같다.
    

## 시연

![게시판 조회](https://prod-files-secure.s3.us-west-2.amazonaws.com/d85fd78d-de88-4966-9b27-62e3e2e8c2a0/37a0bcc8-0de7-47bd-a21a-7a7adf01da1e/%E1%84%80%E1%85%A6%E1%84%89%E1%85%B5%E1%84%91%E1%85%A1%E1%86%AB_%E1%84%8C%E1%85%A9%E1%84%92%E1%85%AC.png)

게시판 조회

![게시판 추가](https://prod-files-secure.s3.us-west-2.amazonaws.com/d85fd78d-de88-4966-9b27-62e3e2e8c2a0/5fd1b8e3-561d-4114-b15b-89117453927a/%E1%84%80%E1%85%A6%E1%84%89%E1%85%B5%E1%84%91%E1%85%A1%E1%86%AB_%E1%84%8E%E1%85%AE%E1%84%80%E1%85%A1.png)

게시판 추가

![게시판 수정](https://prod-files-secure.s3.us-west-2.amazonaws.com/d85fd78d-de88-4966-9b27-62e3e2e8c2a0/cdb0dd7b-70c3-4ff3-8a92-9f502f23c9cb/%E1%84%80%E1%85%A6%E1%84%89%E1%85%B5%E1%84%91%E1%85%A1%E1%86%AB_%E1%84%89%E1%85%AE%E1%84%8C%E1%85%A5%E1%86%BC.png)

게시판 수정

![게시판 삭제](https://prod-files-secure.s3.us-west-2.amazonaws.com/d85fd78d-de88-4966-9b27-62e3e2e8c2a0/03a3bc2c-331e-454e-961c-aa27ebfcb644/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA_2024-05-03_%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE_6.00.35.png)

게시판 삭제

![게시글 전체 조회 (모집완료가 제일 아래에 위치 && 최신순 정렬)](https://prod-files-secure.s3.us-west-2.amazonaws.com/d85fd78d-de88-4966-9b27-62e3e2e8c2a0/ea03a215-8cac-47bf-9401-e89e1bf6a709/%E1%84%80%E1%85%A6%E1%84%89%E1%85%B5%E1%84%80%E1%85%B3%E1%86%AF_%E1%84%8C%E1%85%A5%E1%86%AB%E1%84%8E%E1%85%A6_%E1%84%8C%E1%85%A9%E1%84%92%E1%85%AC.png)

게시글 전체 조회 (모집완료가 제일 아래에 위치 && 최신순 정렬)

![게시글 검색](https://prod-files-secure.s3.us-west-2.amazonaws.com/d85fd78d-de88-4966-9b27-62e3e2e8c2a0/3b176dab-29a4-4a09-93d9-4229acbe0e45/%E1%84%80%E1%85%A6%E1%84%89%E1%85%B5%E1%84%80%E1%85%B3%E1%86%AF_%E1%84%80%E1%85%A5%E1%86%B7%E1%84%89%E1%85%A2%E1%86%A8.png)

게시글 검색

![게시글 추가](https://prod-files-secure.s3.us-west-2.amazonaws.com/d85fd78d-de88-4966-9b27-62e3e2e8c2a0/c10b94e9-a127-45e2-adc2-56e3568dbc10/Untitled.png)

게시글 추가

![게시글 수정](https://prod-files-secure.s3.us-west-2.amazonaws.com/d85fd78d-de88-4966-9b27-62e3e2e8c2a0/f5c74864-7d19-4c5e-8402-a822cc6c193a/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA_2024-05-03_%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE_6.03.30.png)

게시글 수정

![게시글 조회](https://prod-files-secure.s3.us-west-2.amazonaws.com/d85fd78d-de88-4966-9b27-62e3e2e8c2a0/53443efd-54b4-48ff-96d7-b3cafb1e8bb3/%E1%84%80%E1%85%A6%E1%84%89%E1%85%B5%E1%84%80%E1%85%B3%E1%86%AF.png)

게시글 조회

![댓글 조회](https://prod-files-secure.s3.us-west-2.amazonaws.com/d85fd78d-de88-4966-9b27-62e3e2e8c2a0/e6a77aec-21ad-4779-a6f3-42a4e3e09d44/%E1%84%83%E1%85%A2%E1%86%BA%E1%84%80%E1%85%B3%E1%86%AF.png)

댓글 조회

![댓글 수정](https://prod-files-secure.s3.us-west-2.amazonaws.com/d85fd78d-de88-4966-9b27-62e3e2e8c2a0/4fd30947-8613-4707-a80a-06f554eebe7e/%E1%84%83%E1%85%A2%E1%86%BA%E1%84%80%E1%85%B3%E1%86%AF_%E1%84%89%E1%85%AE%E1%84%8C%E1%85%A5%E1%86%BC.png)

댓글 수정

![댓글 삭제](https://prod-files-secure.s3.us-west-2.amazonaws.com/d85fd78d-de88-4966-9b27-62e3e2e8c2a0/97a5a6a8-6c28-4ae6-aa61-e481a1a2eb98/%E1%84%83%E1%85%A2%E1%86%BA%E1%84%80%E1%85%B3%E1%86%AF_%E1%84%89%E1%85%A1%E1%86%A8%E1%84%8C%E1%85%A6.png)

댓글 삭제
