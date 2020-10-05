<template>
  <section id="all">
    <div id="clicked1">
      <v-row>
        <span
          class="mdi mdi-chevron-left"
          @click="model++"
          style="margin-right: 10px; align-self: center; cursor: pointer"
        ></span>

        <v-carousel
          hide-delimiters
          id="box"
          width="100%"
          height="200px"
          :show-arrows="false"
          v-model="model"
        >
          <v-carousel-item
            v-for="(item, i) in items"
            :key="i"
            :src="item.src"
          ></v-carousel-item>
        </v-carousel>

        <span
          class="mdi mdi-chevron-right"
          @click="model++"
          style="margin-left: 10px; align-self: center; cursor: pointer"
        ></span>
      </v-row>
    </div>
  </section>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";

@Component
export default class MygalleryView extends Vue {
  model = 0;
  items = [
    {
      src:
        "//lh5.ggpht.com/LI90lhru6dCFuKx4C5S7kDDF0kcxaq98H8ETfWs3UjzjzFylWC14XDJDw_0K"
    },
    {
      src:
        "https://lh5.ggpht.com/4YrrPBu5qd9wG3xIdP9loPW5dyEJsw9G4RCWP8a2HUn_pVYjJTVtrT4YwfY"
    },
    {
      src:
        "//lh5.ggpht.com/sGFNYnsvcc0L5hH_h3bcFo7pcQSqsYRZninoM_YpT_zudbjOVQAxeA0DZgM"
    }
  ];
}
</script>

<style scoped>
#all {
  width: 100%;
  height: 100%;
  position: relative;
  background: url("../../assets/mygallery.png") no-repeat;
  background-size: 100% 100%;
}
#clicked1 {
  position: absolute;
  left: 47%;
  top: 40%;
  z-index: 100;
  width: 500px;
}
#clicked1 #box {
  width: 20vw;
  max-width: 500px;
  max-height: 200px;
  box-shadow: 0px 0px 15px 5px;
}
</style>
