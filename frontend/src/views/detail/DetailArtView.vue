<template>
  <div id="fade" class="art-image" v-if="art">
    <div class="container">
      <img :src="art.artUrl" />
      <detail-art-description />
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator";
import { namespace } from "vuex-class";
import router from "../../router";
import DetailArtDescription from "@/components/detail/DetailArtDescription.vue";
import { Art } from "../../store/Detail.interface";

const DetailModule = namespace("DetailModule");

@Component({
  components: {
    DetailArtDescription
  }
})
export default class DetailArtView extends Vue {
  @DetailModule.State art!: Art;
  @DetailModule.Action FETCH_ART: any;
  @DetailModule.State isFavoriteArt!: boolean;
  @DetailModule.Action IS_FAVORITE_ART!: any;

  @Watch("$route", { immediate: true })
  checkFavoriteArt() {
    this.IS_FAVORITE_ART({ artNo: Number(this.$route.params.artNo) });
  }

  @Watch("$route", { immediate: true })
  fetchArt() {
    const { artNo, type } = this.$route.params;
    this.FETCH_ART({ artNo, type });
  }
}
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

.art-image {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background: #000;
}
.container {
  width: 1100px;
  display: flex;
  justify-content: space-between;
}
.container img:hover {
  max-width: 600px;
  transform-origin: center;
  transform: perspective(800px) rotateY(0deg);
  transition: 0.5s;
  -webkit-box-reflect: below 1px
    linear-gradient(transparent, transparent, #0004);
}

.container img {
  max-width: 600px;
  transform-origin: center;
  transform: perspective(800px) rotateY(20deg);
  transition: 0.5s;
  -webkit-box-reflect: below 1px
    linear-gradient(transparent, transparent, #0004);
}
</style>
