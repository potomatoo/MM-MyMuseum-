<template>
  <v-col class="artist-img align-self-center" v-if="artList">
    <v-row style="height: 100%">
      <v-col>
        <transition name="fade">
          <div class="flip-container">
            <div class="flipper">
              <img
                v-if="genre.genreUrl"
                :src="genre.genreUrl"
                class="artist-img-front"
              />
            </div>
          </div>
        </transition>
      </v-col>
    </v-row>
  </v-col>
</template>

<script lang="ts">
import { Vue, Component, Prop } from "vue-property-decorator";

import { namespace } from "vuex-class";

import { Art, Genre } from "../../store/Detail.interface";

const DetailModule = namespace("DetailModule");

@Component
export default class DetailArtistImg extends Vue {
  @DetailModule.State artList!: Art[] | null;
  @Prop({ type: Object }) readonly genre!: Genre;
  @Prop({ type: Boolean }) readonly show!: boolean;
}
</script>

<style scoped>
.flip-container {
  perspective: 1000px;
  border: 2px solid rgba(0, 0, 0, 0) !important;
  margin: auto;
}

.artist-img-front {
  z-index: 2;
  transform: rotateY(0deg);
  background-size: 100% 100%;
  border-radius: 5px !important;
  box-shadow: 0px 0px 10px 5px;
}

.flip-container,
.artist-img-front {
  width: 400px;
  height: 500px;
}
</style>
