<template>
  <div style="height: 100%" v-if="artList">
    <v-row style="height: 100vh">
      <detail-artist-description :show="show" v-on:isArtsFlag="isArtsFlag" />
      <detail-artist-img :show="show" />
    </v-row>

    <div>
      <detail-artist-arts :scrollHeight="scrollHeight" />
    </div>
  </div>
</template>

<script lang="ts">
import { Vue, Component, Watch } from "vue-property-decorator";
import { namespace } from "vuex-class";
import DetailArtistImg from "@/components/detail/DetailArtistImg.vue";
import DetailArtistDescription from "@/components/detail/DetailArtistDescription.vue";
import DetailArtistArts from "@/components/detail/DetailArtistArts.vue";

import { Art } from "../../store/Detail.interface";

const DetailModule = namespace("DetailModule");

@Component({
  components: {
    DetailArtistImg,
    DetailArtistDescription,
    DetailArtistArts
  }
})
export default class DetailArtistView extends Vue {
  @DetailModule.State artList!: Art[] | null;
  @DetailModule.Action FETCH_ART_LIST: any;

  artsFlag = false;
  show = false;
  scrollHeight = 0;

  async isArtsFlag() {
    this.artsFlag = true;
    await window.scrollTo({ top: this.scrollHeight, behavior: "smooth" });
  }

  mounted() {
    this.show = !this.show;
    this.scrollHeight = window.innerHeight;
  }

  created() {
    this.FETCH_ART_LIST({
      artist: this.$route.params.artist,
      start: 0
    });
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
