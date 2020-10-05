<template>
  <v-col class="artist-description">
    <v-row style="height: 100%">
      <v-col class="align-self-center text-center">
        <div
          v-if="museum.museumName"
          class="mb-5"
          style="font-size: 40px; font-family: Playfair Display, serif;"
        >
          {{ museum.museumName }}
        </div>

        <div
          class="mb-5"
          v-if="museum.museumCountry"
          style="font-family: Playfair Display, serif;"
        >
          {{ museum.museumCountry }}
        </div>

        <button
          class="allArtBtn"
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

<style>
.artist-description {
  background-color: #e6e1de;
}

.allArtBtn {
  outline: none;
}
</style>
