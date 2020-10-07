<template>
  <div id="fade" class="art-image" v-if="detail">
    <div class="container">
      <!-- dd-->
      <img :src="detail.myartUrl" />
      <v-row> </v-row>
      <div
        style="min-width: 320px; color: white; font-family: Playfair Display, serif; align-self: center"
      >
        <div class="description-text">
          <div class="art-title" style="font-size: 23px; margin-top: 5px">
            {{ detail.myartTitle }}
          </div>
        </div>
        <div class="art-detail-description">
          <div class="art-artist" style="font-size: 18px; margin-top: 30px">
            <span style="font-family: 'Do Hyeon', sans-serif;">
              아티스트:
            </span>

            <span> {{ detail.userId }} </span>
          </div>

          <div class="art-age" style="font-size: 18px; margin-top: 10px">
            <span style="font-family: 'Do Hyeon', sans-serif;">
              등록날짜:
            </span>
            <span>
              {{ detail.myartAge.substring(0, 10) }}
            </span>
          </div>

          <div class="art-type" style="font-size: 18px; margin-top: 10px">
            <span style="font-family: 'Do Hyeon', sans-serif;">
              작품유형:
            </span>
            <span>
              {{ detail.myartType }}
            </span>
          </div>

          <div
            class="d-flex row"
            style="justify-content: flex-end; margin-top: 50px"
          >
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
                  <div id="fade" class="art-image">
                    <div class="container">
                      <img :src="detail.myartUrl" />
                    </div>
                  </div>
                </v-card>
              </v-dialog>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator";
import { namespace } from "vuex-class";
import { AmateurArt } from "../../../store/AmateurInterface";

const amateurModule = namespace("amateurModule");
@Component
export default class DetailArtDescription extends Vue {
  @amateurModule.State detail!: AmateurArt | null;
  @amateurModule.Action FETCH_DETAIL_AMATEUR_ART: any;
  @amateurModule.Mutation SET_DETAIL_AMATEUR_ART_ZERO: any;
  showDialog = false;

  @Watch("$route", { immediate: true })
  fetchDetailAmateurArt() {
    this.FETCH_DETAIL_AMATEUR_ART(Number(this.$route.params.myartNo));
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
