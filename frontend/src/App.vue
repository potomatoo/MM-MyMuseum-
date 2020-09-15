<template>
  <v-app>
    <v-app-bar app color="primary" dark>
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

      <v-btn :to="{ name: 'Login' }" text>
        <span class="mr-2">로그인</span>
      </v-btn>
      <v-btn :to="{ name: 'Signup' }" text>
        <span class="mr-2">회원가입</span>
      </v-btn>
    </v-app-bar>

    <v-main>
      <route-view />
    </v-main>
  </v-app>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";

import Home from "@/views/Home.vue";
import RouteView from "@/components/RouteView.vue";

@Component({
  components: {
    Home,
    RouteView
  }
})
export default class App extends Vue {}
</script>

<style scoped></style>
