<template>
  <v-btn
    class="go-top-btn"
    v-show="fab"
    v-scroll="onScroll"
    fab
    dark
    @click="toTop"
    color="rgb(174, 164, 152)"
  >
    <v-icon>mdi-chevron-up</v-icon>
  </v-btn>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";

@Component
export default class GoTop extends Vue {
  fab = false;
  $vuetify: any;

  onScroll(e: MouseEvent) {
    const target: HTMLElement = e.currentTarget as HTMLElement;
    if (typeof window === "undefined") return;
    const top = window.pageYOffset || target.scrollTop || 0;
    this.fab = top > 300;
  }

  toTop() {
    this.$vuetify.goTo(0);
  }
}
</script>

<style scoped>
.go-top-btn {
  position: fixed;
  right: 5vw;
  bottom: 5vh;
  cursor: pointer;
}
</style>
