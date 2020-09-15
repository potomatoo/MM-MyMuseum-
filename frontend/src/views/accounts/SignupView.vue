<template>
  <div style="width: 50%" class="mx-auto mt-15">
    <h1 class="my-3">회원가입</h1>
    <v-form>
      <v-text-field
        v-model="userEmail"
        :rules="emailRules"
        solo
        label="이메일"
        required
      ></v-text-field>
      <v-text-field
        v-model="userNickname"
        :rules="nicknameRules"
        solo
        label="닉네임"
        required
      ></v-text-field>
      <v-text-field
        v-model="userPassword"
        :rules="passwordRules"
        solo
        label="비밀번호"
        required
      ></v-text-field>
      <v-text-field
        v-model="userPasswordCheck"
        :rules="passwordCheckRules"
        solo
        label="비밀번호 확인"
        required
      ></v-text-field>
      <v-btn color="indigo" width="100%" dark large>회원가입</v-btn>
    </v-form>
  </div>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";

@Component
export default class SignupView extends Vue {
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
}
</script>

<style></style>
