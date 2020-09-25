<template>
  <v-col class="artist-img align-self-center">
    <v-row style="height: 100%">
      <v-col>
        <transition name="fade">
          <div
            v-if="show"
            class="flip-container"
            ontouchstart="this.classList.toggle('hover');"
          >
            <div class="flipper">
              <img
                src="https://lh3.ggpht.com/1wo8kpGuE45XUHjxLCgnVWnGVpxyasc4ENyvMmuou9IoI2DTJc_pztjmqXFW"
                alt=""
                class="artist-img-front"
              />
              <img
                src="https://lh3.ggpht.com/-6Qkc8AsUa8D7TE8uX-5urH4ehAh5BjjCA4fDgQcV_2YxASHiUMkQ3ZamZir"
                alt=""
                class="artist-img-back"
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

@Component
export default class DetailArtistImg extends Vue {
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
}

.artist-img-back {
  transform: rotateY(180deg);
  background-size: 100% 100%;
}

.flip-container:hover .flipper,
.flip-container.hover .flipper {
  transform: rotateY(180deg);
}

.flip-container,
.artist-img-front,
.artist-img-back {
  width: 400px;
  height: 500px;
}

.flipper {
  transition: 0.6s;
  transform-style: preserve-3d;
  position: relative;
}

.artist-img-front,
.artist-img-back {
  position: absolute;
  backface-visibility: hidden;
}
</style>
