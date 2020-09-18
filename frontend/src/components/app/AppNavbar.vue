<template>
  <v-app-bar app dark src="../../assets/backgoundColorImg.png" flat>
    <div class="d-flex align-center">
      <router-link :to="{ name: 'Home' }">
        <v-img
          alt="Vuetify Logo"
          class="shrink mr-2"
          contain
          src="https://cdn.vuetifyjs.com/images/logos/vuetify-logo-dark.png"
          transition="scale-transition"
          width="40"
        />
      </router-link>

      <v-img
        alt="Vuetify Name"
        class="shrink mt-1 hidden-sm-and-down"
        contain
        min-width="100"
        src="https://cdn.vuetifyjs.com/images/logos/vuetify-name-dark.png"
        width="100"
      />
    </div>

    <v-spacer></v-spacer>

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
import { Component, Vue } from "vue-property-decorator";

@Component
export default class AppNavbar extends Vue {}
</script>

<style scoped>
.navbar-btn {
  margin: 5px 10px 0px 10px;
}
</style>
