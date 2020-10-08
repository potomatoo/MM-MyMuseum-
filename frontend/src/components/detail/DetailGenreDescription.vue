<template>
  <v-col class="artist-description">
    <v-row style="height: 100%">
      <v-col class="align-self-center text-center">
        <div
          class="mb-5 genre-title"
          style="font-size: 40px; font-family: Playfair Display, serif;"
        >
          {{ this.$route.params.genre }}
        </div>

        <div class="d-flex row genre-title" style="justify-content: center">
          <div v-if="genre && genre.genreDescription" style="font-size: 10px;">
            <span style="font-size: 15px; font-family: 'Do Hyeon', sans-serif;">
              <v-dialog v-model="dialog" width="500">
                <template v-slot:activator="{ on, attrs }">
                  <span dark v-bind="attrs" v-on="on">
                    <v-btn color="black" icon dark>
                      <v-icon>mdi-magnify</v-icon>
                    </v-btn>
                  </span>
                </template>

                <v-card>
                  <v-card-title
                    class="headline grey lighten-2"
                    style="font-family: 'Do Hyeon', sans-serif !important;"
                  >
                    {{ genre.genreKtitle }} ( {{ genre.genreAge }} )
                  </v-card-title>

                  <v-card-text
                    style="margin-top: 15px; font-family: 'Do Hyeon', sans-serif;"
                  >
                    {{ genre.genreDescription }}
                  </v-card-text>
                </v-card>
              </v-dialog>
            </span>
          </div>

          <div
            v-if="genre && genre.genreKtitle"
            class="mb-5 genre-title"
            style="font-size: 25px; font-family: 'Do Hyeon', sans-serif;"
          >
            {{ genre.genreKtitle }}
          </div>
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
import { Genre } from "../../store/Detail.interface";

@Component
export default class DetailArtistDescription extends Vue {
  @Prop({ type: Object }) readonly genre!: Genre;

  dialog = false;
  isArtsFlag() {
    this.$emit("isArtsFlag");
  }
}
</script>

<style scoped>
.artist-description .genre-title:before,
.artist-description .genre-description:before {
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
