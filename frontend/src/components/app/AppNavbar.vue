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
          width="40"
        />
      </router-link>

      <v-img
        alt="Vuetify Name"
        class="shrink mt-1 hidden-sm-and-down"
        contain
        min-width="150"
        src="../../assets/text_logo_test2.png"
        width="100"
      />
    </div>

    <v-spacer></v-spacer>
    <span v-show="!isHome">
      <v-btn class="navbar-btn" text>
        <span>추천 작품</span>
      </v-btn>
      <v-btn class="navbar-btn" :to="{ name: 'CategoryList' }" text>
        <span>컬렉션</span>
      </v-btn>
      <v-btn class="navbar-btn" text>
        <span>내 전시회</span>
      </v-btn>
    </span>
    <!-- v-btn--active 삭제 시키기 -->
    <v-btn class="navbar-btn" :to="{ name: 'Login' }" text>
      <span>로그인</span>
    </v-btn>
    <v-btn class="navbar-btn" :to="{ name: 'Signup' }" text>
      <span>회원가입</span>
    </v-btn>
  </v-app-bar>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator";

@Component
export default class AppNavbar extends Vue {
  isHome = false;

  @Watch("$route", { immediate: true })
  checkRoute() {
    if (this.$route.name === "Home") {
      this.isHome = true;
    } else {
      this.isHome = false;
    }
  }
}
</script>

<style scoped>
.navbar-btn {
  margin: 5px 10px 0px 10px;
}
</style>
