<template>
  <div>
    <!-- 윗공간 -->
    <v-responsive class="mx-auto mb-12" width="56">
      <v-divider class="mb-1"></v-divider>
      <v-divider></v-divider>
    </v-responsive>
    <!-- 제목 -->
    <h2
      class="display-2 font-weight-bold mb-3 text-uppercase text-center"
      style="color:white"
    >
      작가 신청 페이지
    </h2>
    <!-- 아랫공간 -->
    <v-responsive class="mx-auto mb-12" width="56">
      <v-divider class="mb-1"></v-divider>
      <v-divider></v-divider>
    </v-responsive>

    <v-row
      cols="12"
      align="center"
      justify="center"
      style="margin : 1px 20%; background-color: white;"
    >
      <v-col class="align-self-center">
        <div class="request">
          <v-form>
            <!-- 이메일, 닉네임 자동 입력되고 수정 불가 -->
            <v-text-field
              class="mb-3"
              v-model="userEmail"
              readonly
              solo
              prepend-icon="mdi-email"
            ></v-text-field>
            <v-text-field
              class="mb-3"
              v-model="userNickname"
              readonly
              solo
              prepend-icon="mdi-email"
              label="닉네임 값"
            ></v-text-field>
            <v-text-field
              class="mb-3"
              v-model="introduce"
              solo
              label="작가 소개"
              prepend-icon="mdi-message-text"
            ></v-text-field>
            <v-file-input
              v-model="files"
              show-size
              solo
              label="포트폴리오나 작품을 올려주세요"
              prepend-icon="mdi-camera"
            ></v-file-input>
            <v-textarea
              class="mb-3"
              v-model="decription"
              solo
              label="상세 설명"
              prepend-icon="mdi-message-text"
<<<<<<< HEAD
            ></v-text-field>
            <v-btn color="rgb(137,120,104)" width="100%" dark large>
              신청
              <!-- @click 이벤트로 파일 업로드 및 DB에 전송 -->
            </v-btn>
=======
            ></v-textarea>
            <div align="center" justify="center">
              <v-btn color="rgb(137,120,104)" width="50%" dark large>
                신청
                <!-- @click 이벤트로 파일 업로드 및 DB에 전송 -->
                <!-- Django admin 페이지 자동 생성 -> 여기서 업로드된 파일을 확인 할 수 있게... -->
              </v-btn>
            </div>
>>>>>>> f0e0708109fc65cf2466e1959d582038341494b5
          </v-form>
        </div>
      </v-col>
    </v-row>
  </div>
</template>

<script lang="ts">
<<<<<<< HEAD
import { Component, Vue } from "vue-property-decorator";

@Component({})
export default class RequestAmateur extends Vue {
  userMail = "";
  userNickname = "";
  introduce = "";
  files = "";
  description = "";
=======
import { Component, Vue, Watch } from "vue-property-decorator";

import { namespace } from "vuex-class";
import { User } from "../../store/Accounts.interface";

const AccountsModule = namespace("AccountsModule");

@Component({})
export default class RequestAmateur extends Vue {
  @AccountsModule.State user!: User;

  userEmail: string | null = "";
  userNickname: string | null = "";
  introduce = "";
  files = [];
  decription = "";

  @Watch("$route", { immediate: true })
  setUserInfo() {
    this.userEmail = this.$route.query.userEmail;
    this.userNickname = this.$route.query.userNickname;
  }
>>>>>>> f0e0708109fc65cf2466e1959d582038341494b5
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
