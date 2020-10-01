<template>
  <div
    v-if="this.art"
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
      class="art-age"
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
      class="art-size"
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
      class="art-type"
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
      class="art-method"
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
      class="art-genre"
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
      v-if="this.art.artDescription"
      class="art-description"
      style="font-size: 18px; margin-top: 10px"
    >
      <span
        v-if="this.art.artDescription"
        style="font-family: 'Do Hyeon', sans-serif;"
      >
        상세설명:
      </span>
      <span style="font-family: 'Do Hyeon', sans-serif;">
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
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { namespace } from "vuex-class";
import { Art } from "../../store/Detail.interface";

const DetailModule = namespace("DetailModule");
@Component
export default class DetailArtDescription extends Vue {
  @DetailModule.State art!: Art;

  dialog = false;
}
</script>

<style scoped>
.router-link {
  text-decoration: none;
  color: inherit;
  border: 0;
  outline: none;
}
</style>
