<template>
  <v-col v-if="museum && museum.museumName" class="artist-description">
    <v-row style="height: 100%">
      <v-col class="align-self-center text-center">
        <div
          class="mb-5 museum-title"
          style="font-size: 40px; font-family: Playfair Display, serif;"
        >
          {{ museum.museumName }}
        </div>

        <div
          class="mb-5 museum-country"
          v-if="museum && museum.museumLogo"
          style="margin-top: 50px;"
        >
          <img
            :src="museum.museumLogo"
            style="max-width: 100px; max-height: 100px; cursor: pointer"
            @click="dialog = true"
          />
        </div>

        <v-dialog v-model="dialog" width="500">
          <v-card>
            <v-card-title
              class="headline grey lighten-2"
              style="font-family: 'Do Hyeon', sans-serif !important;"
            >
              {{ museum.museumName }}
            </v-card-title>
            <v-card-text
              style="margin-top: 15px; font-family: 'Do Hyeon', sans-serif;"
            >
              {{ museum.museumDescription }}
            </v-card-text>
          </v-card>
        </v-dialog>

        <div
          class="mb-5 museum-country"
          v-if="museum && museum.museumCountry"
          style="font-size: 15px;font-family: 'Do Hyeon', sans-serif;"
        >
          {{ museum.museumCountry }}
        </div>

        <button
          class="allArtBtn art-show"
          @click="isArtsFlag"
          style="font-size: 25px; font-family: 'Do Hyeon', sans-serif;"
        >
          작품 보기
        </button>
      </v-col>
    </v-row>
  </v-col>
</template>

<script lang="ts">
import { Vue, Component, Prop } from "vue-property-decorator";
import { Museum } from "../../store/Detail.interface";

@Component
export default class DetailArtistDescription extends Vue {
  @Prop({ type: Object }) readonly museum!: Museum;

  dialog = false;
  isArtsFlag() {
    this.$emit("isArtsFlag");
  }
}
</script>

<style scoped>
.artist-description .museum-title:before,
.artist-description .museum-country:before {
  content: "";
  position: absolute;
  background: #e6e1de;
  top: 0;
  left: 0;
  width: 50%;
  height: 100vh;
  transform-origin: right;
  animation: revealText 1s ease-in-out forwards;
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
  animation-delay: 1s;
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
