<template>
  <div style="height: 100%">
    <v-row style="height: 100vh">
      <detail-artist-description :show="show" v-on:isArtsFlag="isArtsFlag" />
      <detail-artist-img :show="show" />
    </v-row>

    <div v-if="artsFlag">
      <detail-artist-arts />
    </div>
  </div>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";
import DetailArtistImg from "@/components/detail/DetailArtistImg.vue";
import DetailArtistDescription from "@/components/detail/DetailArtistDescription.vue";
import DetailArtistArts from "@/components/detail/DetailArtistArts.vue";

@Component({
  components: {
    DetailArtistImg,
    DetailArtistDescription,
    DetailArtistArts
  }
})
export default class DetailArtistView extends Vue {
  artsFlag = false;
  show = false;

  isArtsFlag() {
    this.artsFlag = true;
  }

  mounted() {
    this.show = !this.show;
  }
}
</script>

<style>
.fade-enter-active,
.fade-leave-active {
  opacity: 0;
  transition: opacity 5s;
}

.fade-enter-to,
.fade-leave-to {
  opacity: 1;
}
</style>
