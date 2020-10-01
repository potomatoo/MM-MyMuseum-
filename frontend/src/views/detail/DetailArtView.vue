<template>
  <div id="fade" class="art-image" v-if="this.art">
    <div class="container">
      <img :src="this.art.artUrl" />

      <div
        style="min-width: 320px; color: white; font-family: Playfair Display, serif; align-self: center"
      >
        <div
          v-if="this.art.artTitle"
          class="art-title"
          style="font-size: 25px; margin-top: 5px"
        >
          {{ this.art.artTitle }}
        </div>

        <div
          v-if="this.art.artArtist"
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
                artist: this.art.artArtist
              }
            }"
          >
            <span>
              {{ this.art.artArtist }}
            </span>
          </router-link>
        </div>

        <div
          v-if="this.art.artAge"
          class="art-Age"
          style="font-size: 18px; margin-top: 10px"
        >
          <span style="font-family: 'Do Hyeon', sans-serif;">
            제작연도:
          </span>
          <span>
            {{ this.art.artAge }}
          </span>
        </div>

        <div
          v-if="this.art.artSize"
          class="art-Age"
          style="font-size: 18px; margin-top: 10px"
        >
          <span style="font-family: 'Do Hyeon', sans-serif;">
            크기:
          </span>
          <span>
            {{ this.art.artSize }}
          </span>
        </div>

        <div
          v-if="this.art.artType"
          class="art-Age"
          style="font-size: 18px; margin-top: 10px"
        >
          <span style="font-family: 'Do Hyeon', sans-serif;">
            작품유형:
          </span>
          <span>
            {{ this.art.artType }}
          </span>
        </div>

        <div
          v-if="this.art.artMehtod"
          class="art-Age"
          style="font-size: 18px; margin-top: 10px"
        >
          <span style="font-family: 'Do Hyeon', sans-serif;">
            작품방식:
          </span>
          <span>
            {{ this.art.artMehtod }}
          </span>
        </div>

        <div
          v-if="this.art.artGenre"
          class="art-Age"
          style="font-size: 18px; margin-top: 10px"
        >
          <span style="font-family: 'Do Hyeon', sans-serif;">
            장르:
          </span>
          <span>
            {{ this.art.artGenre }}
          </span>
        </div>

        <div
          v-if="this.art.artGenre"
          class="art-Age"
          style="font-size: 18px; margin-top: 10px"
        >
          <span
            v-if="this.art.artDescription"
            style="font-family: 'Do Hyeon', sans-serif;"
          >
            상세설명:
          </span>
          <span
            v-if="this.art.artDescription"
            style="font-family: 'Do Hyeon', sans-serif;"
          >
            <v-dialog v-model="dialog" width="500">
              <template v-slot:activator="{ on, attrs }">
                <span color="red lighten-2" dark v-bind="attrs" v-on="on">
                  더 보기
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
                  {{ this.art.artDescription }}
                </v-card-text>
              </v-card>
            </v-dialog>
          </span>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator";
import { namespace } from "vuex-class";
import router from "../../router";
import { Art } from "../../store/Detail.interface";

const DetailModule = namespace("DetailModule");

@Component
export default class DetailArtView extends Vue {
  @DetailModule.State art!: Art;
  @DetailModule.Action FETCH_ART: any;
  dialog = false;

  @Watch("$route", { immediate: true })
  fetchArt() {
    this.FETCH_ART({ artNo: Number(this.$route.params.artNo) });
  }
}
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

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
