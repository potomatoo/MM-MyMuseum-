<template>
  <v-app id="app">
    <app-navbar />
    <app-main />
    <go-top />
  </v-app>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";

import AppNavbar from "@/components/app/AppNavbar.vue";
import AppMain from "@/components/app/AppMain.vue";
import GoTop from "@/components/commons/GoTop.vue";
import { namespace } from "vuex-class";
import { User } from "./store/Accounts.interface";

const AccountsModule = namespace("AccountsModule");

@Component({
  components: {
    AppNavbar,
    AppMain,
    GoTop
  }
})
export default class App extends Vue {
  @AccountsModule.State user!: User | null;
  @AccountsModule.Getter isLoggedIn!: boolean;
  @AccountsModule.Action FETCH_USER_INFO: any;
  created() {
    if (this.isLoggedIn && !this.user) {
      this.FETCH_USER_INFO();
    }
  }
}
</script>

<style>
#app {
  /* background-image: url("assets/backgoundColorImg.png"); */
  background-color: black;
  animation: fadein 1s;
  -webkit-animation: fadein 1s; /* Safari and Chrome */
}

img {
  animation: fadein 2s linear forwards;
}

@keyframes fadein {
  0% {
    opacity: 0;
  }
  100% {
    opacity: 1;
  }
}
</style>
