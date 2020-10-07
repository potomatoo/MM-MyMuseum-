<template>
  <section id="all">
    <div id="clicked1">
      <v-row>
        <span
          class="mdi mdi-chevron-left"
          @click="model++"
          style="margin-right: 10px; align-self: center; cursor: pointer"
        ></span>

        <v-carousel
          hide-delimiters
          id="box"
          width="100%"
          height="200px"
          :show-arrows="false"
          v-model="model"
        >
          <v-carousel-item
            :key="favoriteArt.artNo"
            :src="favoriteArt.artUrl"
            @click="showDialog = true"
            style="cursor: pointer"
          >
            <v-dialog
              v-model="showDialog"
              fullscreen
              hide-overlay
              transition="dialog-bottom-transition"
            >
              <v-card style="background: black">
                <v-btn icon dark @click="showDialog = false">
                  <v-icon>mdi-close</v-icon>
                </v-btn>
                <div id="fade" class="art-image">
                  <div class="container">
                    <img :src="favoriteArt.artUrl" />
                  </div>
                </div>
              </v-card>
            </v-dialog>
          </v-carousel-item>
        </v-carousel>

        <span
          class="mdi mdi-chevron-right"
          @click="model++"
          style="margin-left: 10px; align-self: center; cursor: pointer"
        ></span>
      </v-row>
    </div>
  </section>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { namespace } from "vuex-class";
import { FavoriteArt } from "../../store/Recommendation.interface";

const RecommendationModule = namespace("RecommendationModule");

@Component
export default class MygalleryView extends Vue {
  @RecommendationModule.State favoriteArt!: FavoriteArt | null;
  @RecommendationModule.Action FETCH_FAVORITE_ART: any;

  model = 0;
  showDialog = false;

  items = [
    {
      src:
        "//lh5.ggpht.com/LI90lhru6dCFuKx4C5S7kDDF0kcxaq98H8ETfWs3UjzjzFylWC14XDJDw_0K"
    },
    {
      src:
        "https://lh5.ggpht.com/4YrrPBu5qd9wG3xIdP9loPW5dyEJsw9G4RCWP8a2HUn_pVYjJTVtrT4YwfY"
    },
    {
      src:
        "//lh5.ggpht.com/sGFNYnsvcc0L5hH_h3bcFo7pcQSqsYRZninoM_YpT_zudbjOVQAxeA0DZgM"
    }
  ];

  created() {
    this.FETCH_FAVORITE_ART(this.$route.params.artNo);
  }
}
</script>

<style scoped>
#all {
  width: 100%;
  height: 100%;
  position: relative;
  background: url("../../assets/mygallery.png") no-repeat;
  background-size: 100% 100%;
}
#clicked1 {
  position: absolute;
  left: 47%;
  top: 40%;
  z-index: 100;
  width: 500px;
}
#clicked1 #box {
  width: 20vw;
  max-width: 500px;
  max-height: 200px;
  box-shadow: 0px 0px 15px 5px;
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
  justify-content: center;
}

.container img {
  max-width: 1100px;
  transform-origin: center;
  transform: perspective(800px) rotateY(0deg);
  transition: 0.5s;
  -webkit-box-reflect: below 1px
    linear-gradient(transparent, transparent, #0004);
}

.description-text .art-title:before,
.description-text .art-detail-description:before {
  content: "";
  position: absolute;
  background: black;
  bottom: 0;
  width: 100vh;
  height: 100vh;
  transform-origin: right;
  animation: revealText 1s ease-in-out forwards;
}

.description-text .art-detail-description:before {
  animation-delay: 0.5s;
}
@keyframes revealText {
  0% {
    transform: scaleX(1);
  }
  100% {
    transform: scaleX(0);
  }
}

.art-show {
  opacity: 0;
  animation: fadeInBottom 0.5s linear forwards;
  animation-delay: 1.5s;
}
@keyframes fadeInBottom {
  0% {
    transform: translateY(50px);
    opacity: 0;
  }
  100% {
    transform: translateY();
    opacity: 1;
  }
}
</style>
