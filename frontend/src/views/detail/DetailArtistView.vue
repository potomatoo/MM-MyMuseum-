<template>
  <div>
    <v-row>
      <v-col class="artist-description" xs="12" sm="12" md="6" lg="6" xl="6">
        description
      </v-col>
      <v-col class="artist-img" xs="12" sm="12" md="6" lg="6" xl="6">
        artist-img
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
  background-color: rgb(137, 120, 104);
  height: calc(100vh - 64px);
  font-family: "Playfair Display", serif;
}

.artist-img {
}
</style>
