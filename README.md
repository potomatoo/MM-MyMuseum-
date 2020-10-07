# :classical_building: MM - My Museum

## :bulb: IN THE MM

코로나로 인해 막힌 박물관, 미술관, 전시회 관람을 온라인을 통해 개인에 맞는 전시회를 제공합니다.

작가, 화풍, 박물관 별로 작품을 감상할 수 있습니다.

개인 취향에 맞는 작품을 추천받을 수 있습니다.

좋아하는 작품을 저장하고 저장된 작품을 기반으로 나만의 전시회를 만들 수 있습니다.



## :mag_right: 추천 시스템

### 유저 행동 분석을 통한 추천

1. 작품 상세 페이지의 클릭 이벤트를 4가지로 분기
   * 직접 검색해서 들어오는 경우
   * 추천 페이지에서 상위 그림 중 선택한 경우
   * 추천 페이지에서 중위 그림 중 선택한 경우
   * 추천 페이지에서 랜덤으로 선택한 경우
2. 일정 시간 주기로 쌓여있는 클릭 정보 기반으로 작품 비교 후 위의 4가지 경우에 따라 가중치에 맞게 점수 갱신
3. 추천 페이지에서 상위의 작품들이 선택되지 않는 경우 점수 감점

### NLP 통한 작품 추천

1. Kaggle 에서 수집한 데이터를 통하여 문장에 대한 감정을 학습시킴
2. 학습으로 도출된 모델에 작펌의 제목, 세부 설명의 데이터를 넣어 해당 작품이 가지고 있는 감정에 대한 label을 추출
3. 생성된 감정 label을 작품의 세부 사항 항목에 추가하여 작품 별 감정을 생성
4. 추천 페이지에서 유저의 현재 감정에 따른 작품 추천이나 지금 보고 싶은 작품의 분위기를 선정하여 추천

# :tada: Getting Started

## Front end
```
cd ./frontend
npm install
npm run serve or npm run build
```
## Back end(Spring)
```
cd ./backend/Spring
mvn package
java -jar target/Teletubbies-0.0.1-SNAPSHOT.jar
```

## Back end(Django)
```
cd backend/Django
pip install -r requirements.txt
python manage.py runserver
```

# :wrench: Configuration

## require
- [Mavne](https://maven.apache.org/)
- [Node.js](https://nodejs.org/en/)
- [Mysql](https://www.mysql.com/)
- [Redis](https://redis.io/)
- [Google login](https://developers.google.com/identity/sign-in/web)
- [JWT](https://jwt.io/)
- [Python](https://www.python.org/)
- [Django](https://www.djangoproject.com/)

## Frontend Config
### BaseUrl Config ( ./frontend/src/service/axios.service.ts )
```
...
export class Axios {
  static readonly instance: AxiosInstance = axios.create({
    baseURL: `Your_base_url`,
    timeout: 10000
  });
}
...
```

### Google Login Config ( ./frontend/src/main.ts )
```
...
Vue.use(GAuth, {
  clientId: `Google_Client_ID`,
  scope: "profile email https://www.googleapis.com/auth/plus.login",
});
...
```
## Backend Config

### DB Config ( ./backend/Spring/src/main/resources/application.properties )
```
# MySql
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.url=  `mysql_url`
spring.datasource.username= `mysql_user_name`
spring.datasource.password= `mysql_user_password`

#Redis
spring.cache.type=redis
spring.redis.host= `redis_url`
spring.redis.port= `redis_port`

```


### Google Login Config( ./backend/Spring/src/main/java/com/ssafy/config/GoogleLoginConfig.java )
```
public static final String GOOGLE_CLIENT_ID = `Google_Client_ID`;
public static final String GOOGLE_SECRIT_ID = `Google_Secrit_ID`;
...
	@Bean
OAuth2Parameters oAuth2Parameters() {
	OAuth2Parameters oAuth2Parameters = new OAuth2Parameters();
	oAuth2Parameters.setRedirectUri( `Redirect_url` );
	oAuth2Parameters.setScope( `Google_Login_scope` );
	return oAuth2Parameters;
}

```
### JWT Config ( ./backend/Springsrc/main/java/com/ssafy/config/JwtProperties.java )
```
public class JwtProperties {
    ...
    public static final String SECRET = `Your_JWT_Secrit_Key`;
    ...
}
```

## :pencil: Tech Stack

### Front end
- Vue
- Vuex
- Vue-Router
- TypeScript
- Eslint
- Prettier
- vue-google-oauth2
- axios

### Back end

- Spring Boot
- Spring Security
- Django
- Django REST framework
- Pandas
- TensorFlow
- JWT
- Swagger

### DB

- Mysql
- Redis

### CI/CD

- Jenkins
- Docker
- nginx



## :busts_in_silhouette: Author

- 이연재 - FE
- 김동욱1 - FE
- 김동욱2 - FE
- 윤동현 - BE
- 김준호 - DA



