<template>
  <div style="height: 100%">
    <v-row style="height: 100%">
      <v-col class="empty-space"></v-col>
      <v-col class="align-self-center text-center">
        <img
          class="login-picture"
          src="https://lh3.googleusercontent.com/vENTojlUPt9bsdozdur91HQz3VeA3-LPgA7Oq7K8iU9ftNYAuCGWBWm8nR3z33x87g"
          alt="account-basic-picture"
        />
      </v-col>
      <v-col class="empty-space"></v-col>
      <v-col class="align-self-center">
        <div class="login">
          <h1 class="login-text mb-5">로그인</h1>
          <v-form class="mb-9">
            <v-text-field
              class="mb-3"
              v-model="userEmail"
              :rules="emailRules"
              label="이메일"
              solo
              autocomplete
              required
            ></v-text-field>
            <v-text-field
              class="mb-3"
              v-model="userPassword"
              :rules="passwordRules"
              label="비밀번호"
              type="password"
              solo
              autocomplete
              required
            ></v-text-field>
            <v-btn
              color="rgb(137,120,104)"
              width="100%"
              dark
              large
              @click="login"
            >
              로그인
            </v-btn>
            <p class="text-center my-3 login-text">or</p>
            <v-btn
              color="rgb(137,120,104)"
              width="100%"
              dark
              large
              @click="toGoogleLogin"
            >
              <img
                class="google-icon"
                src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/Pg0KPCEtLSBHZW5lcmF0b3I6IEFkb2JlIElsbHVzdHJhdG9yIDE5LjAuMCwgU1ZHIEV4cG9ydCBQbHVnLUluIC4gU1ZHIFZlcnNpb246IDYuMDAgQnVpbGQgMCkgIC0tPg0KPHN2ZyB2ZXJzaW9uPSIxLjEiIGlkPSJMYXllcl8xIiB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHhtbG5zOnhsaW5rPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5L3hsaW5rIiB4PSIwcHgiIHk9IjBweCINCgkgdmlld0JveD0iMCAwIDUxMiA1MTIiIHN0eWxlPSJlbmFibGUtYmFja2dyb3VuZDpuZXcgMCAwIDUxMiA1MTI7IiB4bWw6c3BhY2U9InByZXNlcnZlIj4NCjxwYXRoIHN0eWxlPSJmaWxsOiNGQkJCMDA7IiBkPSJNMTEzLjQ3LDMwOS40MDhMOTUuNjQ4LDM3NS45NGwtNjUuMTM5LDEuMzc4QzExLjA0MiwzNDEuMjExLDAsMjk5LjksMCwyNTYNCgljMC00Mi40NTEsMTAuMzI0LTgyLjQ4MywyOC42MjQtMTE3LjczMmgwLjAxNGw1Ny45OTIsMTAuNjMybDI1LjQwNCw1Ny42NDRjLTUuMzE3LDE1LjUwMS04LjIxNSwzMi4xNDEtOC4yMTUsNDkuNDU2DQoJQzEwMy44MjEsMjc0Ljc5MiwxMDcuMjI1LDI5Mi43OTcsMTEzLjQ3LDMwOS40MDh6Ii8+DQo8cGF0aCBzdHlsZT0iZmlsbDojNTE4RUY4OyIgZD0iTTUwNy41MjcsMjA4LjE3NkM1MTAuNDY3LDIyMy42NjIsNTEyLDIzOS42NTUsNTEyLDI1NmMwLDE4LjMyOC0xLjkyNywzNi4yMDYtNS41OTgsNTMuNDUxDQoJYy0xMi40NjIsNTguNjgzLTQ1LjAyNSwxMDkuOTI1LTkwLjEzNCwxNDYuMTg3bC0wLjAxNC0wLjAxNGwtNzMuMDQ0LTMuNzI3bC0xMC4zMzgtNjQuNTM1DQoJYzI5LjkzMi0xNy41NTQsNTMuMzI0LTQ1LjAyNSw2NS42NDYtNzcuOTExaC0xMzYuODlWMjA4LjE3NmgxMzguODg3TDUwNy41MjcsMjA4LjE3Nkw1MDcuNTI3LDIwOC4xNzZ6Ii8+DQo8cGF0aCBzdHlsZT0iZmlsbDojMjhCNDQ2OyIgZD0iTTQxNi4yNTMsNDU1LjYyNGwwLjAxNCwwLjAxNEMzNzIuMzk2LDQ5MC45MDEsMzE2LjY2Niw1MTIsMjU2LDUxMg0KCWMtOTcuNDkxLDAtMTgyLjI1Mi01NC40OTEtMjI1LjQ5MS0xMzQuNjgxbDgyLjk2MS02Ny45MWMyMS42MTksNTcuNjk4LDc3LjI3OCw5OC43NzEsMTQyLjUzLDk4Ljc3MQ0KCWMyOC4wNDcsMCw1NC4zMjMtNy41ODIsNzYuODctMjAuODE4TDQxNi4yNTMsNDU1LjYyNHoiLz4NCjxwYXRoIHN0eWxlPSJmaWxsOiNGMTQzMzY7IiBkPSJNNDE5LjQwNCw1OC45MzZsLTgyLjkzMyw2Ny44OTZjLTIzLjMzNS0xNC41ODYtNTAuOTE5LTIzLjAxMi04MC40NzEtMjMuMDEyDQoJYy02Ni43MjksMC0xMjMuNDI5LDQyLjk1Ny0xNDMuOTY1LDEwMi43MjRsLTgzLjM5Ny02OC4yNzZoLTAuMDE0QzcxLjIzLDU2LjEyMywxNTcuMDYsMCwyNTYsMA0KCUMzMTguMTE1LDAsMzc1LjA2OCwyMi4xMjYsNDE5LjQwNCw1OC45MzZ6Ii8+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8L3N2Zz4NCg=="
              />
              구글로그인
            </v-btn>
          </v-form>
          <hr />
          <p class="login-text">
            계정이 없으신가요?
            <v-btn class="mb-1" text dark @click="toSignup">회원가입</v-btn>
          </p>
        </div>
      </v-col>
      <v-col class="empty-space"></v-col>
    </v-row>
  </div>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";
import { namespace } from "vuex-class";

const AccountsModule = namespace("AccountsModule");

@Component
export default class LoginView extends Vue {
  @AccountsModule.Action LOGIN: any;
  @AccountsModule.Action GOOGLE_LOGIN: any;

  userEmail = "";
  userPassword = "";
  emailRules = [
    (v: string) => !!v || "이메일을 입력하세요",
    (v: string) => /.+@.+\..+/.test(v) || "이메일 형식으로 입력하세요"
  ];
  passwordRules = [
    (v: string) => !!v || "비밀번호를 입력하세요",
    (v: string) => v.length >= 8 || "비밀번호는 8자 이상 입력해주세요"
  ];
  $gAuth: any;

  login() {
    const userInfo = {
      userId: this.userEmail,
      userPassword: this.userPassword
    };
    this.LOGIN(userInfo);
  }

  toSignup() {
    this.$router.push({ name: "Signup" });
  }

  toGoogleLogin() {
    this.$gAuth
      .getAuthCode()
      .then((authToken: string) => this.GOOGLE_LOGIN(authToken));
  }
}
</script>

<style scoped>
.google-icon {
  position: absolute;
  left: 0.5vw;
  height: 20px;
}

.login-picture {
  width: 100%;
  min-width: 400px;
  max-width: 600px;
  box-shadow: 0px 0px 50px 20px;
}

.login {
  width: 100%;
  min-width: 400px;
  max-width: 600px;
}

.login-text {
  color: rgb(181, 178, 177);
}

.empty-space {
  max-width: 200px;
}

hr {
  border: solid 1px rgb(181, 178, 177);
}
</style>
