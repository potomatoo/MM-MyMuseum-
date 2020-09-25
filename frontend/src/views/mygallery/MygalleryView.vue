<template>
  <div class="mygallery">
    <img
      src="https://lh3.ggpht.com/rjXpJnIc60qlIcB_Kgk_gwauMt8fju9J8EiZ1_BDtK3zYT0g4QOBePQFe3M"
      alt="myimg"
    />
  </div>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";

@Component
export default class MygalleryView extends Vue {}
</script>

<style scoped>
.mygallery {
  background: url("../../assets/mygallery.png");
  background-size: cover;
  height: 100vh;
}

img {
  position: fixed;
  left: 600px;
  top: 380px;

  width: 550px;
  height: 280px;
  box-shadow: 0px 0px 10px 5px;
}
</style>
