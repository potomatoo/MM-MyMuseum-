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
      <v-col class="artist-img" xs="6" sm="6" md="6" lg="6" xl="6">
        <v-row style="height: 100%">
          <v-col class="align-self-center text-center">
            <img
              src="//lh3.ggpht.com/1wo8kpGuE45XUHjxLCgnVWnGVpxyasc4ENyvMmuou9IoI2DTJc_pztjmqXFW"
              alt="artistImage"
            />
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
.artist-description {
  background-color: #e6e1de;
  /* background-image: url("../../assets/detailBackgroundColor01.png");
  background-size: cover; */

  font-family: "Playfair Display", serif;
}

.artist-img {
}
</style>
