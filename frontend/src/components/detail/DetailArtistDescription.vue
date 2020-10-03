<template>
  <v-col class="artist-description">
    <v-row style="height: 100%">
      <v-col class="align-self-center text-center">
        <div
          class="mb-5"
          style="font-size: 40px; font-family: Playfair Display, serif;"
        >
          {{ this.$route.params.artist }}
        </div>

        <div class="mb-5" style="font-family: Playfair Display, serif;">
          Mar 30, 1853 - Jul 29, 1890
        </div>

        <button
          class="allArtBtn"
          @click="isArtsFlag"
          style="font-size: 25px; font-family: 'Do Hyeon', sans-serif;"
        >
          작품 보기
        </button>
      </v-col>
    </v-row>
  </v-col>
</template>

<script lang="ts">
import { Vue, Component, Prop } from "vue-property-decorator";

@Component
export default class DetailArtistDescription extends Vue {
  isArtsFlag() {
    this.$emit("isArtsFlag");
  }
}
</script>

<style>
.artist-description {
  background-color: #e6e1de;
}

.allArtBtn {
  outline: none;
}
</style>
