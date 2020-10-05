<template>
  <v-app-bar app dark src="../../assets/backgoundColorImg.png" flat>
    <div class="d-flex align-center">
      <router-link :to="{ name: 'Home' }">
        <v-img
          alt="Home logo"
          class="shrink mr-2"
          contain
          src="../../assets/logo_test2.png"
          transition="scale-transition"
          width="50"
        />
      </router-link>

      <v-img
        alt="Vuetify Name"
        class="shrink mt-1 hidden-sm-and-down"
        contain
        min-width="160"
        src="../../assets/text_logo_test.png"
        width="100"
      />
    </div>

    <v-spacer></v-spacer>
    <span v-show="!isHome">
      <v-btn class="navbar-btn" :to="{ name: 'RecommendView' }" text>
        <span>추천 작품</span>
      </v-btn>
      <v-btn class="navbar-btn" :to="{ name: 'CategoryList' }" text>
        <span>컬렉션</span>
      </v-btn>
      <v-btn class="navbar-btn" :to="{ name: 'MyGalleryView' }" text>
        <span>내 전시회</span>
      </v-btn>
    </span>
    <!-- v-btn--active 삭제 시키기 -->
    <v-btn v-if="!isLoggedIn" class="navbar-btn" :to="{ name: 'Login' }" text>
      <span>로그인</span>
    </v-btn>
    <v-btn v-if="!isLoggedIn" class="navbar-btn" :to="{ name: 'Signup' }" text>
      <span>회원가입</span>
    </v-btn>
    <v-btn v-if="isLoggedIn" class="navbar-btn" @click="logout" text>
      <span>로그아웃</span>
    </v-btn>
  </v-app-bar>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator";
import { namespace } from "vuex-class";

const AccountsModule = namespace("AccountsModule");

@Component
export default class AppNavbar extends Vue {
  @AccountsModule.Getter isLoggedIn!: boolean;
  @AccountsModule.Action LOGOUT: any;
  @AccountsModule.Mutation REMOVE_TOKEN: any;

  isHome = false;

  @Watch("$route", { immediate: true })
  checkRoute() {
    this.isHome = this.$route.name === "Home";
  }

  logout() {
    // this.LOGOUT();
    this.REMOVE_TOKEN();
  }
}
</script>

<style scoped>
.navbar-btn {
  margin: 5px 10px 0px 10px;
}
</style>
