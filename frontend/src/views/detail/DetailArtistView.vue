<template>
  <div id="fade" style="height: 100%" v-if="artList">
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
  @DetailModule.State scrollEnd!: boolean;
  @DetailModule.Action FETCH_ART_LIST: any;
  @DetailModule.Action ADD_ART_LIST: any;

  artsFlag = false;
  show = false;
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

      if ((ceilBottomOfWindow || plusBottomOfWindow) && !this.scrollEnd) {
        ++this.start;
        this.FETCH_ART_LIST({
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
#fade {
  animation: fadein 2s;
  -moz-animation: fadein 2s; /* Firefox */
  -webkit-animation: fadein 2s; /* Safari and Chrome */
  -o-animation: fadein 2s; /* Opera */
}
@keyframes fadein {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}
@-moz-keyframes fadein {
  /* Firefox */
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}
@-webkit-keyframes fadein {
  /* Safari and Chrome */
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}
@-o-keyframes fadein {
  /* Opera */
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}
</style>
