<template>
  <div id="fade" style="height: 100%" v-if="artList">
    <v-row style="height: 100vh">
      <detail-artist-description
        v-on:isArtsFlag="isArtsFlag"
        :artist="artist"
      />
      <detail-artist-img />
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

import { Art, Artist } from "../../store/Detail.interface";

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
  @DetailModule.State scrollEnd!: boolean;
  @DetailModule.State artist!: Artist;
  @DetailModule.Mutation SET_ART_LIST_ZERO!: any;
  @DetailModule.Action FETCH_ARTIST_ART_LIST: any;
  @DetailModule.Action FETCH_ARTIST: any;

  artsFlag = false;
  scrollHeight = 0;
  start = 0;

  scroll() {
    window.onscroll = () => {
      const ceilBottomOfWindow =
        Math.ceil(window.pageYOffset) + window.innerHeight ===
        document.documentElement.offsetHeight;

      const plusBottomOfWindow =
        Math.ceil(window.pageYOffset) + window.innerHeight + 1 ===
        document.documentElement.offsetHeight;

      if (
        (ceilBottomOfWindow || plusBottomOfWindow) &&
        !this.scrollEnd &&
        this.$route.name === "DetailArtistView"
      ) {
        console.log(this.$route.name);
        ++this.start;
        this.FETCH_ARTIST_ART_LIST({
          artist: this.$route.params.artist,
          start: this.start
        });
      }
    };
  }

  async isArtsFlag() {
    this.artsFlag = true;
    await window.scrollTo({ top: this.scrollHeight, behavior: "smooth" });
  }

  mounted() {
    this.scroll();
    this.scrollHeight = window.innerHeight;
  }

  @Watch("$route", { immediate: true })
  fetchArtist() {
    this.FETCH_ARTIST({ artistName: this.$route.params.artist });
  }

  @Watch("$route", { immediate: true })
  fetchArtList() {
    this.start = 0;
    this.FETCH_ARTIST_ART_LIST({
      artist: this.$route.params.artist,
      start: this.start
    });
  }

  destroyed() {
    this.SET_ART_LIST_ZERO();
  }
}
</script>

<style></style>
