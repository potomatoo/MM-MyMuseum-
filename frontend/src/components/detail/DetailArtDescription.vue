<template>
  <div
    v-if="art"
    style="min-width: 320px; color: white; font-family: Playfair Display, serif; align-self: center"
  >
    <div class="description-text">
      <div
        v-if="art.artTitle"
        class="art-title"
        style="font-size: 23px; margin-top: 5px"
      >
        {{ art.artTitle }}
      </div>

      <div class="art-detail-description">
        <div
          v-if="art.artArtist"
          class="art-artist"
          style="font-size: 18px; margin-top: 30px"
        >
          <span style="font-family: 'Do Hyeon', sans-serif;">
            아티스트:
          </span>

          <router-link
            class="router-link"
            :to="{
              name: 'DetailArtistView',
              params: {
                artist: art.artArtist
              }
            }"
          >
            <span> {{ art.artArtist }} </span>
          </router-link>
        </div>

        <div
          v-if="art.artAge"
          class="art-age"
          style="font-size: 18px; margin-top: 10px"
        >
          <span style="font-family: 'Do Hyeon', sans-serif;">
            제작연도:
          </span>
          <span>
            {{ art.artAge }}
          </span>
        </div>

        <div
          v-if="art.artSize"
          class="art-size"
          style="font-size: 18px; margin-top: 10px"
        >
          <span style="font-family: 'Do Hyeon', sans-serif;">
            크기:
          </span>
          <span>
            {{ art.artSize }}
          </span>
        </div>

        <div
          v-if="art.artType"
          class="art-type"
          style="font-size: 18px; margin-top: 10px"
        >
          <span style="font-family: 'Do Hyeon', sans-serif;">
            작품유형:
          </span>
          <span>
            {{ art.artType }}
          </span>
        </div>

        <div
          v-if="art.artMehtod"
          class="art-method"
          style="font-size: 18px; margin-top: 10px"
        >
          <span style="font-family: 'Do Hyeon', sans-serif;">
            작품방식:
          </span>
          <span>
            {{ art.artMehtod }}
          </span>
        </div>

        <div
          v-if="art.artGenre"
          class="art-genre"
          style="font-size: 18px; margin-top: 10px"
        >
          <span style="font-family: 'Do Hyeon', sans-serif;">
            장르:
          </span>
          <span>
            {{ art.artGenre }}
          </span>
        </div>
      </div>
    </div>

    <div class="d-flex row" style="justify-content: flex-end; margin-top: 50px">
      <div
        v-if="art.artDescription"
        class="art-show"
        style="font-size: 18px; margin-top: 10px;"
      >
        <span
          v-if="art.artDescription"
          style="font-family: 'Do Hyeon', sans-serif;"
        >
        </span>
        <span style="font-family: 'Do Hyeon', sans-serif;">
          <v-dialog v-model="dialog" width="500">
            <template v-slot:activator="{ on, attrs }">
              <span color="red lighten-2" dark v-bind="attrs" v-on="on">
                <button
                  style="border: 1px solid white; border-radius: 5px; padding: 5px; margin-right: 15px"
                >
                  상세설명
                </button>
              </span>
            </template>

            <v-card>
              <v-card-title
                class="headline grey lighten-2"
                style="font-family: 'Do Hyeon', sans-serif !important;"
              >
                상세설명
              </v-card-title>

              <v-card-text
                style="margin-top: 15px; font-family: 'Do Hyeon', sans-serif;"
              >
                {{ art.artDescription }}
              </v-card-text>
            </v-card>
          </v-dialog>
        </span>
      </div>

      <div
        class="art-show"
        style="font-size: 18px; margin-top: 10px; margin-right: 15px"
      >
        <v-dialog
          v-model="showDialog"
          fullscreen
          hide-overlay
          transition="dialog-bottom-transition"
        >
          <template v-slot:activator="{ on, attrs }">
            <span
              style="font-family: 'Do Hyeon', sans-serif !important;"
              v-bind="attrs"
              v-on="on"
            >
              <button
                style="border: 1px solid white; border-radius: 5px; padding: 5px"
              >
                작품감상
              </button>
            </span>
          </template>
          <v-card style="background: black">
            <v-btn icon dark @click="showDialog = false">
              <v-icon>mdi-close</v-icon>
            </v-btn>
            <div id="fade" class="art-image" v-if="art">
              <div class="container">
                <img :src="art.artUrl" />
              </div>
            </div>
          </v-card>
        </v-dialog>
      </div>

      <div
        v-if="!isFavoriteArt"
        class="art-show"
        style="font-size: 18px; margin-top: 10px;"
      >
        <span style="font-family: 'Do Hyeon', sans-serif;">
          <button
            style="border: 1px solid white; border-radius: 5px; padding: 5px"
          >
            전시회 추가
          </button>
        </span>
      </div>

      <div
        v-if="isFavoriteArt"
        class="art-show"
        style="font-size: 18px; margin-top: 10px;"
      >
        <span style="font-family: 'Do Hyeon', sans-serif;">
          <button
            style="border: 1px solid white; border-radius: 5px; padding: 5px"
          >
            전시회 삭제
          </button>
        </span>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator";
import { namespace } from "vuex-class";
import { Art } from "../../store/Detail.interface";

const DetailModule = namespace("DetailModule");
@Component
export default class DetailArtDescription extends Vue {
  @DetailModule.State art!: Art;
  @DetailModule.State isFavoriteArt!: boolean;

  dialog = false;
  showDialog = false;
}
</script>

<style scoped>
.router-link {
  text-decoration: none;
  color: inherit;
  border: 0;
  outline: none;
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
    transform: translateY(20px);
    opacity: 0;
  }
  100% {
    transform: translateY();
    opacity: 1;
  }
}
</style>
