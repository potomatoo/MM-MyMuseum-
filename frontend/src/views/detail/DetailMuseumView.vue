<template>
  <div id="fade" style="height: 100%" v-if="artList">
    <v-row style="height: 100vh">
      <detail-museum-description
        v-on:isArtsFlag="isArtsFlag"
        :museum="museum"
      />
      <detail-museum-img :museum="museum" />
    </v-row>

    <div>
      <detail-museum-arts :scrollHeight="scrollHeight" />
    </div>
  </div>
</template>

<script lang="ts">
import { Vue, Component, Watch } from "vue-property-decorator";
import { namespace } from "vuex-class";
import DetailMuseumImg from "@/components/detail/DetailMuseumImg.vue";
import DetailMuseumDescription from "@/components/detail/DetailMuseumDescription.vue";
import DetailMuseumArts from "@/components/detail/DetailMuseumArts.vue";

import { Art, Museum } from "../../store/Detail.interface";

const DetailModule = namespace("DetailModule");

@Component({
  components: {
    DetailMuseumImg,
    DetailMuseumDescription,
    DetailMuseumArts
  }
})
export default class DetailArtistView extends Vue {
  @DetailModule.State artList!: Art[] | null;
  @DetailModule.State museum!: Museum | null;
  @DetailModule.State scrollEnd!: boolean;
  @DetailModule.Mutation SET_ART_LIST_ZERO!: any;
  @DetailModule.Action FETCH_MUSEUM_ART_LIST: any;
  @DetailModule.Action ADD_ART_LIST: any;

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
        this.FETCH_MUSEUM_ART_LIST({
          museum: this.$route.params.museum,
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
  fetchArtList() {
    this.start = 0;
    this.FETCH_MUSEUM_ART_LIST({
      museum: this.$route.params.museum,
      start: this.start
    });
  }

  destroyed() {
    this.SET_ART_LIST_ZERO();
  }
}
</script>

<style></style>
