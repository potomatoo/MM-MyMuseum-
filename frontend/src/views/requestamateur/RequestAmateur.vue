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
      style="margin : 1px 20%; background-color:white;"
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
              label="이메일 값"
            ></v-text-field>
            <v-text-field
              class="mb-3"
              v-model="userNickname"
              solo
              readonly
              prepend-icon="mdi-account"
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
            <v-text-field
              class="mb-3"
              v-model="decription"
              solo
              label="상세 설명"
              prepend-icon="mdi-message-text"
            ></v-text-field>
            <v-btn color="rgb(137,120,104)" width="100%" dark large>
              신청
              <!-- @click 이벤트로 파일 업로드 및 DB에 전송 -->
              <!-- Django admin 페이지 자동 생성 -> 여기서 업로드된 파일을 확인 할 수 있게... -->
            </v-btn>
          </v-form>
        </div>
      </v-col>
    </v-row>
  </div>
</template>

<script lang="ts">
/*
user - 작가 신청 페이지
자동 입력 정보 : 아이디, 이름 등 개인 구분 정보
입력 해야할 정보 : 작가 소개 , 포트폴리오나 작품 상세 소개 및 정보

--- 이하 쟝고 어드민 페이지 자동 생성 -> 여기서 테이블 직접 수정  ---
admin - 신청 글 관리 페이지
user table에 작가 상태 0-> 일반 유저// 1-> 작가// 2-> 반려

신청 글에 승인 반려 버튼
승인 -> user table 작가 상태 1
반려 -> user table 작가 상태 2 -> 반려 버튼 누르면 메세지창 -> 반려 사유

반려 테이블 생성 사유 번호 + 번호당 사유 ex) 1 -> 포트폴리오나 작품 파일 미 제출

관리자만 볼 수 있는 신청 게시판 <R, U +D -> message>

유저는 작가 신청 글만 작성<C>

나중에 자기 정보 확인하기에서 사유만 확인가능하게 위의 메시지 받음

-필요한 페이지 자기정보 확인하기
-----------------------------------------------------------------------------------------------

- 메인 버튼에서 아마추어 관으로 이동하는 버튼
 */

import { Component, Vue } from "vue-property-decorator";

@Component({})
export default class RequestAmateur extends Vue {
  /*
  userEmail = "";
  userNickname = "";
  introduce = "";
  files = [];
  decription = "";
  */
  //user table에서 이메일하고 닉네임 가져오기
  //파일하고 상세 설명 전송하기
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
