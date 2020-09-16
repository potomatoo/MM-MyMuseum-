<template>
  <div style="height: 100%">
    <v-row style="height: 100%">
      <v-col class="artist-description" xs="6" sm="6" md="6" lg="6" xl="6">
        <v-row style="height: 100%">
          <v-col class="align-self-center text-center">
            <div class="mb-5" style="font-size: 40px">
              Vincent van Gogh
            </div>
            <div>
              Mar 30, 1853 - Jul 29, 1890
            </div>
          </v-col>
        </v-row>
      </v-col>
      <v-col
        class="artist-img align-self-center"
        xs="6"
        sm="6"
        md="6"
        lg="6"
        xl="6"
      >
        <v-row style="height: 100%">
          <v-col>
            <div
              class="flip-container"
              ontouchstart="this.classList.toggle('hover');"
            >
              <div class="flipper">
                <div class="front"></div>
                <div class="back"></div>
              </div>
            </div>
          </v-col>
        </v-row>
      </v-col>
    </v-row>
  </div>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";

@Component
export default class DetailArtistView extends Vue {}
</script>

<style>
.flip-container {
  perspective: 1000px;
  border: 2px solid rgba(0, 0, 0, 0) !important;

  margin: auto;
}

.front {
  z-index: 2;
  transform: rotateY(0deg);
  background: url("//lh3.ggpht.com/1wo8kpGuE45XUHjxLCgnVWnGVpxyasc4ENyvMmuou9IoI2DTJc_pztjmqXFW")
    no-repeat;
  background-size: 100% 100%;
}

.back {
  transform: rotateY(180deg);
  background: url("//lh3.ggpht.com/-6Qkc8AsUa8D7TE8uX-5urH4ehAh5BjjCA4fDgQcV_2YxASHiUMkQ3ZamZir")
    no-repeat;
  background-size: 100% 100%;
}

.flip-container:hover .flipper,
.flip-container.hover .flipper {
  transform: rotateY(180deg);
}

.flip-container,
.front,
.back {
  width: 400px;
  height: 500px;
}

.flipper {
  transition: 0.6s;
  transform-style: preserve-3d;
  position: relative;
}

.front,
.back {
  position: absolute;
  backface-visibility: hidden;
}

.artist-description {
  background-color: #e6e1de;
  font-family: "Playfair Display", serif;
}
</style>
