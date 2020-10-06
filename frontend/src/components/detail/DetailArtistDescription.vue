<template>
  <v-col class="artist-description">
    <v-row style="height: 100%">
      <v-col class="align-self-center text-center">
        <div
          v-if="artist.artistName"
          class="artist-title"
          style="font-size: 40px; font-family: Playfair Display, serif; margin-bottom: 10px"
        >
          {{ artist.artistName }}
        </div>

        <div
          v-if="artist.artistAge"
          class="mb-5 artist-age"
          style="font-family: Playfair Display, serif; font-size: 25px; font-family: 'Do Hyeon', sans-serif;"
        >
          {{ artist.artistAge }}
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
import { Artist } from "../../store/Detail.interface";

@Component
export default class DetailArtistDescription extends Vue {
  @Prop({ type: Object }) readonly artist!: Artist;
  isArtsFlag() {
    this.$emit("isArtsFlag");
  }
}
</script>

<style>
.artist-description {
  background-color: #e6e1de;
}

.allArtBtn {
  outline: none;
}
</style>

<style scoped>
.artist-description .artist-title:before,
.artist-description .artist-age:before {
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
