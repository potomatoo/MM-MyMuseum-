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
            <v-row v-if="userArtist != 3">
              <v-col cols="4">
                <v-btn
                  class="pa-2"
                  width="100%"
                  color="rgb(137,120,104)"
                  dark
                  large
                  @click="changeName"
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
                  @click="cancleChange"
                >
                  취소
                </v-btn>
              </v-col>
              <v-col cols="4">
                <v-btn
                  class="pa-2"
                  width="100%"
                  color="rgb(137,120,104)"
                  dark
                  large
                  @click="toRequestAmateur()"
                >
                  작가 신청
                </v-btn>
              </v-col>
            </v-row>
            <div v-if="userArtist == 3">
              <v-row>
                <v-col cols="6">
                  <v-btn
                    class="pa-2"
                    width="100%"
                    color="rgb(137,120,104)"
                    dark
                    large
                    @click="changeName"
                  >
                    수정
                  </v-btn>
                </v-col>
                <v-col cols="6">
                  <v-btn
                    class="pa-2"
                    width="100%"
                    color="rgb(137,120,104)"
                    dark
                    large
                    @click="cancleChange"
                  >
                    취소
                  </v-btn>
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="6">
                  <v-dialog v-model="showDialog" width="500">
                    <template v-slot:activator="{ on, attrs }">
                      <v-btn
                        v-bind="attrs"
                        v-on="on"
                        class="pa-2"
                        width="100%"
                        color="rgb(137,120,104)"
                        dark
                        large
                      >
                        작가 사진 등록
                      </v-btn>
                    </template>

                    <v-card>
                      <v-card-title
                        class="headline grey lighten-2"
                        style="font-family: 'Do Hyeon', sans-serif !important;"
                      >
                        작가 사진 등록
                      </v-card-title>

                      <v-card-text
                        style="margin-top: 15px; font-family: 'Do Hyeon', sans-serif;"
                      >
                        <v-img
                          :src="
                            user.userProfile ||
                              require('@/assets/dummydata/category/amateurartist.jpg')
                          "
                          aspect-ratio="1"
                          class="grey lighten-2 artist-img mx-auto"
                        >
                        </v-img>
                        <v-file-input
                          v-model="imgFile"
                          show-size
                          solo
                          label="포트폴리오나 작품을 올려주세요"
                          prepend-icon="mdi-camera"
                        ></v-file-input>
                        <v-row>
                          <v-col cols="6">
                            <v-btn
                              class="pa-2"
                              width="100%"
                              color="rgb(137,120,104)"
                              dark
                              large
                              @click="updateAmateurImg"
                            >
                              등록
                            </v-btn>
                          </v-col>
                          <v-col cols="6">
                            <v-btn
                              class="pa-2"
                              width="100%"
                              color="rgb(137,120,104)"
                              dark
                              large
                              @click="cancleImg"
                            >
                              취소
                            </v-btn>
                          </v-col>
                        </v-row>
                      </v-card-text>
                    </v-card>
                  </v-dialog>
                </v-col>
                <v-col cols="6">
                  <v-btn
                    class="pa-2"
                    width="100%"
                    color="rgb(137,120,104)"
                    dark
                    large
                    @click="moveAmateurArtUpload()"
                  >
                    작품 등록
                  </v-btn>
                </v-col>
              </v-row>
            </div>
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
const AmateurModule = namespace("amateurModule");

@Component
export default class MyPageView extends Vue {
  @AccountsModule.State user!: User;
  @AccountsModule.Action FETCH_USER_INFO: any;
  @AccountsModule.Action CHANGE_USER_NAME: any;
  @AccountsModule.Action REQUEST_AMATEURARTIST: any;
  @AmateurModule.Action UPLOAD_AMATEUR_IMG: any;

  userEmail: string | null = "";
  userNickname: string | null = "";
  userArtist: number | null = 0;
  userPassword = "";
  userPasswordCheck = "";
  showDialog = false;
  imgFile: string | null = null;

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

  changeName() {
    if (this.userNickname !== this.user.userName) {
      this.CHANGE_USER_NAME(this.userNickname);
    } else {
      alert("변경사항이 없습니다.");
    }
  }

  cancleChange() {
    this.userNickname = this.user.userName;
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
  async updateAmateurImg() {
    if (this.imgFile) {
      const formData = new FormData();
      formData.append("file", this.imgFile);
      const status = await this.UPLOAD_AMATEUR_IMG(formData);
      if (status) {
        this.FETCH_USER_INFO();
        alert("사진 등록이 완료되었습니다.");
      } else {
        alert("사진 등록이 실패하였습니다. 다시 시도해주세요.");
      }
    } else {
      alert("사진을 등록해 주세요.");
    }
    this.imgFile = null;
  }
  cancleImg() {
    this.imgFile = null;
    this.showDialog = false;
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

.artist-img {
  box-shadow: 0px 0px 5px 2px;
  margin: 30px 0px 40px 0px;
  max-width: 300px;
}
</style>
