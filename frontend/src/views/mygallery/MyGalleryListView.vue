<template>
  <div style="color:white">
    <div v-if="favoriteArts && favoriteArts.length">
      <detail-art-rotate :arts="favoriteArts" />
      <art-list-in-gallery :arts="favoriteArts" />
    </div>
    <div v-else class="rec-title">
      <h2 class="display-2 my-10 text-center ">
        추가된 작품이 없습니다.
      </h2>
      <h2 class="display-2 my-10 text-center">
        작품을 추가해 주세요.
      </h2>
    </div>
  </div>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";
import { namespace } from "vuex-class";
import { FavoriteArt } from "../../store/Recommendation.interface";
import DetailArtRotate from "@/components/detail/DetailArtRotate.vue";
import ArtListInGallery from "@/components/mygallery/ArtListInGallery.vue";

const RecommendationModule = namespace("RecommendationModule");

@Component({
  components: {
    DetailArtRotate,
    ArtListInGallery
  }
})
export default class MyGalleryListView extends Vue {
  @RecommendationModule.State favoriteArts!: FavoriteArt[] | null;
  @RecommendationModule.State scrollEnd!: boolean;
  @RecommendationModule.Mutation REMOVE_FAVORITE_ART_LIST: any;
  @RecommendationModule.Action FETCH_FAVORITE_ART_LIST: any;

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
        this.$route.name === "MyGalleryListView"
      ) {
        this.FETCH_FAVORITE_ART_LIST(this.start++);
      }
    };
  }

  created() {
    this.FETCH_FAVORITE_ART_LIST(this.start++);
  }

  mounted() {
    this.scroll();
    this.scrollHeight = window.innerHeight;
  }

  beforeDestroy() {
    this.REMOVE_FAVORITE_ART_LIST();
  }
}
</script>

<style scoped>
.rec-title {
  margin-top: 20% !important;
}
</style>
