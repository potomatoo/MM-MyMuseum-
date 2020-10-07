<template>
  <div v-if="user" style="height: 100%">
    <v-row style="height: 100%">
      <v-col class="empty-space"></v-col>
      <v-col class="align-self-center text-center">
        <img
          class="mypage-picture"
          src="https://lh3.googleusercontent.com/otydKBtLgKIk87IrpLhK6XjHInIvB_T9SZq-lES5mVTedeqwmKQpUjf0uCRqBhSA"
          alt="account-basic-picture"
        />
      </v-col>
      <v-col class="empty-space"></v-col>
      <v-col class="align-self-center">
        <div class="mypage">
          <h1 class="mypage-text mb-5">마이페이지</h1>
          <v-form>
            <v-text-field
              class="mb-3"
              v-model="userEmail"
              label="이메일"
              solo
              disabled
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
            <!-- <v-text-field
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
            ></v-text-field> -->
            <v-row>
              <v-col cols="4">
                <v-btn
                  class="pa-2"
                  width="100%"
                  color="rgb(137,120,104)"
                  dark
                  large
                >
                  수정
                </v-btn>
              </v-col>
              <v-col cols="4">
                <v-btn
                  class="pa-2"
                  width="100%"
                  color="rgb(137,120,104)"
                  dark
                  large
                >
                  취소
                </v-btn>
              </v-col>
              <v-col cols="4">
                <v-btn
                  v-if="userArtist != 3"
                  class="pa-2"
                  width="100%"
                  color="rgb(137,120,104)"
                  dark
                  large
                  @click="toRequestAmateur()"
                >
                  작가 신청
                </v-btn>
                <v-btn
                  v-if="userArtist == 3"
                  class="pa-2"
                  width="100%"
                  color="rgb(137,120,104)"
                  dark
                  large
                  @click="moveAmateurart(userId, userName)"
                >
                  작품 등록
                </v-btn>
              </v-col>
            </v-row>
          </v-form>
        </div>
      </v-col>
      <v-col class="empty-space"></v-col>
    </v-row>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator";
import { namespace } from "vuex-class";
import { User } from "../../store/Accounts.interface";

const AccountsModule = namespace("AccountsModule");

@Component
export default class MyPageView extends Vue {
  @AccountsModule.State user!: User;
  @AccountsModule.Action FETCH_USER_INFO: any;
  @AccountsModule.Action REQUEST_AMATEURARTIST: any;

  userEmail: string | null = "";
  userNickname: string | null = "";
  userArtist: number | null = 0;
  userPassword = "";
  userPasswordCheck = "";

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

  @Watch("user", { immediate: true, deep: true })
  setUserInfo() {
    if (this.user) {
      this.userEmail = this.user.userId;
      this.userNickname = this.user.userName;
      this.userArtist = this.user.userArtist;
    }
  }

  created() {
    this.FETCH_USER_INFO();
  }

  //이동
  toRequestAmateur() {
    this.REQUEST_AMATEURARTIST();
  }

  moveAmateurArtUpload() {
    this.$router.push({
      name: "AmateurArtUpload"
    });
  }
}
</script>

<style>
.mypage-picture {
  width: 100%;
  min-width: 400px;
  max-width: 600px;
  box-shadow: 0px 0px 50px 20px;
}

.mypage {
  width: 100%;
  min-width: 400px;
  max-width: 600px;
}

.mypage-text {
  color: rgb(181, 178, 177);
}

.empty-space {
  max-width: 200px;
}
</style>
