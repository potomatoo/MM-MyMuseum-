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
      작품 등록
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
              >{{ userEmail }}</v-text-field
            >
            <v-text-field
              class="mb-3"
              v-model="userNickname"
              readonly
              solo
              prepend-icon="mdi-email"
              label="닉네임 값"
              >{{ userNickname }}</v-text-field
            >
            <v-text-field
              class="mb-3"
              v-model="title"
              solo
              label="작품 제목"
              prepend-icon="mdi-message-text"
            ></v-text-field>
            <v-file-input
              v-model="file"
              show-size
              solo
              label="포트폴리오나 작품을 올려주세요"
              prepend-icon="mdi-camera"
            ></v-file-input>
            <v-text-field
              class="mb-3"
              v-model="type"
              solo
              label="작품 장르"
              prepend-icon="mdi-message-text"
            ></v-text-field>
            <v-textarea
              class="mb-3"
              v-model="description"
              solo
              label="상세 설명"
              prepend-icon="mdi-message-text"
            ></v-textarea>
            <div align="center" justify="center">
              <v-btn
                color="rgb(137,120,104)"
                width="50%"
                dark
                large
                @click="submitForm"
              >
                등록
              </v-btn>
            </div>
          </v-form>
        </div>
      </v-col>
    </v-row>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator";

import { namespace } from "vuex-class";
import { User } from "../../../store/Accounts.interface";
const AccountsModule = namespace("AccountsModule");

import { UploadData } from "../../../store/AmateurInterface";
const amateurModule = namespace("amateurModule");

@Component({})
export default class AmateurArtUpload extends Vue {
  @AccountsModule.State user!: User;
  @AccountsModule.Action FETCH_USER_INFO: any;
  @amateurModule.Action UPLOAD_AMATEUR_ART: any;

  userEmail = "";
  userNickname = "";

  description = "";
  file = null;
  title = "";
  type = "";

  created() {
    if (!this.user) {
      this.FETCH_USER_INFO();
    }
  }

  @Watch("user", { immediate: true, deep: true })
  setUserInfo() {
    if (this.user) {
      this.userEmail = this.user.userId!;
      this.userNickname = this.user.userName!;
    }
  }

  submitForm() {
    this.UPLOAD_AMATEUR_ART({
      token: sessionStorage.getItem("jwt-token"),
      description: this.description,
      file: this.file,
      title: this.title,
      type: this.type
    });
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
