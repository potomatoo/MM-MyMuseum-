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

@Component({
  components: {
    AppNavbar,
    AppMain,
    GoTop
  }
})
export default class App extends Vue {}
</script>

<style>
#app {
  background-image: url("assets/backgoundColorImg.png");
}

img {
  animation: fadein 3s linear forwards;
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
