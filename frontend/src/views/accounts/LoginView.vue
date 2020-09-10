<template>
  <div style="width: 50%" class="mx-auto mt-15">
    <h1 class="my-3">로그인</h1>
    <v-form>
      <v-text-field
        v-model="userEmail"
        :rules="emailRules"
        solo
        label="이메일"
        required
      ></v-text-field>
      <v-text-field
        v-model="userPassword"
        :rules="passwordRules"
        solo
        label="비밀번호"
        type="password"
        required
      ></v-text-field>
      <v-btn color="indigo" width="100%" dark large>로그인</v-btn>
    </v-form>
    <v-divider class="my-4" />
    <v-spacer />
    <p>
      계정이 없으신가요?
      <v-btn class="mb-1" text color="indigo" @click="toSignup">회원가입</v-btn>
    </p>
  </div>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";

@Component
export default class LoginView extends Vue {
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

  toSignup() {
    this.$router.push({ name: "Signup" });
  }
}
</script>

<style></style>
