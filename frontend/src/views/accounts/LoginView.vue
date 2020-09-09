<template>
  <div style="width: 50%" class="mx-auto mt-15">
    <h1 class="my-3">Sign in</h1>
    <v-form>
      <v-text-field
        v-model="userEmail"
        :rules="emailRules"
        solo
        label="User Email"
        autofocus
      ></v-text-field>
      <v-text-field
        v-model="userPassword"
        :rules="passwordRules"
        solo
        label="Password"
        type="password"
      ></v-text-field>
      <v-btn color="indigo" width="100%" dark large>로그인</v-btn>
    </v-form>
    <v-divider class="my-4" />
    <v-spacer />
    <p>계정이 없으신가요?</p>
  </div>
</template>

<script>
import { Vue, Component } from "vue-property-decorator";

@Component
export default class LoginView extends Vue {
  userEmail = "";
  userPassword = "";
  emailRules = [
    v => !!v || "이메일을 입력하세요",
    v => /.+@.+\..+/.test(v) || "올바른 이메일 형식으로 입력하세요"
  ];
  passwordRules = [
    v => !!v || "비밀번호를 입력하세요",
    v => v.length > 8 || "비밀번호는 8자 이상 입력해주세요"
  ];
}
</script>

<style></style>
