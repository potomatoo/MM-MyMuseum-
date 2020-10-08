<template>
  <div style="height: 100%">
    <v-row style="height: 100%">
      <v-col class="empty-space"></v-col>
      <v-col class="align-self-center text-center">
        <img
          class="signup-picture"
          src="https://lh3.googleusercontent.com/otydKBtLgKIk87IrpLhK6XjHInIvB_T9SZq-lES5mVTedeqwmKQpUjf0uCRqBhSA"
          alt="account-basic-picture"
        />
      </v-col>
      <v-col class="empty-space"></v-col>
      <v-col class="align-self-center">
        <div class="signup">
          <h1 class="signup-text mb-5">회원가입</h1>
          <v-form>
            <v-text-field
              class="mb-3"
              v-model="userEmail"
              :rules="emailRules"
              label="이메일"
              solo
              required
            ></v-text-field>
            <v-text-field
              class="mb-3"
              v-model="userNickname"
              :rules="nicknameRules"
              solo
              label="닉네임"
              required
            ></v-text-field>
            <v-text-field
              class="mb-3"
              v-model="userPassword"
              :rules="passwordRules"
              type="password"
              solo
              label="비밀번호"
              autocomplete
              required
            ></v-text-field>
            <v-text-field
              class="mb-3"
              v-model="userPasswordCheck"
              :rules="passwordCheckRules"
              type="password"
              solo
              label="비밀번호 확인"
              autocomplete
              required
            ></v-text-field>
            <v-btn
              color="rgb(137,120,104)"
              width="100%"
              dark
              large
              @click="signup"
            >
              회원가입
            </v-btn>
          </v-form>
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
export default class SignupView extends Vue {
  @AccountsModule.Action SIGNUP: any;
  @AccountsModule.Action CHECK_EMAIL: any;

  userEmail = "";
  userNickname = "";
  userPassword = "";
  userPasswordCheck = "";
  emailRules = [
    (v: string) => !!v || "이메일을 입력하세요",
    (v: string) => /.+@.+\..+/.test(v) || "이메일 형식으로 입력하세요"
  ];
  nicknameRules = [
    (v: string) => !!v || "닉네임을 입력하세요",
    (v: string) => v.length >= 2 || "닉네임은 2자 이상 입력해주세요"
  ];
  passwordRules = [
    (v: string) => !!v || "비밀번호를 입력하세요",
    (v: string) => v.length >= 8 || "비밀번호는 8자 이상 입력해주세요"
  ];
  passwordCheckRules = [
    (v: string) => !!v || "비밀번호 확인을 해주세요",
    (v: string) => this.checkPassword(v) || "비밀번호가 일치하지 않습니다"
  ];

  checkPassword(password: string) {
    return this.userPassword == password;
  }

  async checkEmail(email: string) {
    const isValid = await this.CHECK_EMAIL(email);
    return isValid;
  }

  async signup() {
    const userInfo = {
      userId: this.userEmail,
      userName: this.userNickname,
      userPassword: this.userPassword
    };
    const isValid = await this.CHECK_EMAIL(this.userEmail);
    if (isValid) {
      this.SIGNUP(userInfo);
    } else {
      alert("이미 사용중인 아이디입니다.");
    }
    // this.SIGNUP(userInfo);
  }
}
</script>

<style scoped>
.signup-picture {
  width: 100%;
  min-width: 400px;
  max-width: 600px;
  box-shadow: 0px 0px 50px 20px;
}

.signup {
  width: 100%;
  min-width: 400px;
  max-width: 600px;
}

.signup-text {
  color: rgb(181, 178, 177);
}

.empty-space {
  max-width: 200px;
}
</style>
