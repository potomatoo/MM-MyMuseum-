<template>
  <div id="fade" style="height: 100%" v-if="artList">
    <v-row style="height: 100vh">
      <detail-genre-description v-on:isArtsFlag="isArtsFlag" :genre="genre" />
      <detail-genre-img :genre="genre" />
    </v-row>

    <div>
      <detail-genre-arts :scrollHeight="scrollHeight" />
    </div>
  </div>
</template>

<script lang="ts">
import { Vue, Component, Watch } from "vue-property-decorator";
import { namespace } from "vuex-class";
import DetailGenreImg from "@/components/detail/DetailGenreImg.vue";
import DetailGenreDescription from "@/components/detail/DetailGenreDescription.vue";
import DetailGenreArts from "@/components/detail/DetailGenreArts.vue";

import { Art, Genre } from "../../store/Detail.interface";

const DetailModule = namespace("DetailModule");

@Component({
  components: {
    DetailGenreImg,
    DetailGenreDescription,
    DetailGenreArts
  }
})
export default class DetailArtistView extends Vue {
  @DetailModule.State artList!: Art[] | null;
  @DetailModule.State genre!: Genre | null;
  @DetailModule.State scrollEnd!: boolean;
  @DetailModule.Mutation SET_ART_LIST_ZERO!: any;
  @DetailModule.Action FETCH_GENRE_ART_LIST: any;
  @DetailModule.Action FETCH_GENRE: any;

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
        ++this.start;
        this.FETCH_GENRE_ART_LIST({
          genre: this.$route.params.genre,
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
  fetchGenre() {
    this.FETCH_GENRE({ genreName: this.$route.params.genre });
  }

  @Watch("$route", { immediate: true })
  fetchGenreList() {
    this.start = 0;
    this.FETCH_GENRE_ART_LIST({
      genre: this.$route.params.genre,
      start: this.start
    });
  }

  destroyed() {
    this.SET_ART_LIST_ZERO();
  }
}
</script>

<style></style>
