<template>
  <v-app id="app">
    <app-navbar app dark />
    <app-main />
  </v-app>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";

import AppNavbar from "@/components/app/AppNavbar.vue";
import AppMain from "@/components/app/AppMain.vue";

@Component({
  components: {
    AppNavbar,
    AppMain
  }
})
export default class App extends Vue {}
</script>

<style>
#app {
  background-image: url("assets/backgoundColorImg.png");
}
</style>
