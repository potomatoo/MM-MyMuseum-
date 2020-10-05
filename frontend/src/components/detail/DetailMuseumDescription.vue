<template>
  <v-col class="artist-description">
    <v-row style="height: 100%">
      <v-col class="align-self-center text-center">
        <div
          v-if="museum.museumName"
          class="mb-5 museum-title"
          style="font-size: 40px; font-family: Playfair Display, serif;"
        >
          {{ museum.museumName }}
        </div>

        <div
          class="mb-5 museum-country"
          v-if="museum.museumCountry"
          style="font-family: Playfair Display, serif;"
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

.artist-description .museum-country:before {
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
