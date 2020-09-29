<template>
  <div class="art-image">
    <div class="container">
      <img src="@/assets/dummydata/articles/garden.jpg" alt="" />
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { namespace } from "vuex-class";
import { Art } from "../../store/Detail.interface";

const DetailModule = namespace("DetailModule");

@Component
export default class DetailArtImage extends Vue {}
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
.art-image {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background: #000;
}
.container {
  width: 1100px;
  display: flex;
  justify-content: center;
}
.container img {
  max-width: 700px;
  transform-origin: center;
  transform: perspective(800px) rotateY(0deg);
  transition: 0.5s;
  -webkit-box-reflect: below 1px
    linear-gradient(transparent, transparent, #0004);
}
</style>
