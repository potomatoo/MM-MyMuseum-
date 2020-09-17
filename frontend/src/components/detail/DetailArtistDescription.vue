<template>
  <v-col class="artist-description" xs="6" sm="6" md="6" lg="6" xl="6">
    <v-row style="height: 100%">
      <v-col class="align-self-center text-center">
        <transition name="fade">
          <div
            class="mb-5"
            v-if="show"
            style="font-size: 40px; font-family: Playfair Display, serif;"
          >
            Vincent van Gogh
          </div>
        </transition>

        <transition name="fade">
          <div
            class="mb-5"
            v-if="show"
            style="font-family: Playfair Display, serif;"
          >
            Mar 30, 1853 - Jul 29, 1890
          </div>
        </transition>

        <transition name="fade">
          <button
            class="allArtBtn"
            v-if="show"
            @click="isArtsFlag"
            style="font-size: 25px; font-family: 'Do Hyeon', sans-serif;"
          >
            작품 보기
          </button>
        </transition>
      </v-col>
    </v-row>
  </v-col>
</template>

<script lang="ts">
import { Vue, Component, Prop } from "vue-property-decorator";

@Component
export default class DetailArtistDescription extends Vue {
  @Prop({ type: Boolean }) readonly show!: boolean;

  isArtsFlag() {
    this.$emit("isArtsFlag");
  }
}
</script>

<style scoped>
.artist-description {
  background-color: #e6e1de;
}

.allArtBtn {
  outline: none;
}
</style>
