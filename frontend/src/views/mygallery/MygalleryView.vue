<template>
  <section id="all">
    <div id="clicked1">
      <img
        class="box"
        src="https://lh6.ggpht.com/HlgucZ0ylJAfZgusynnUwxNIgIp5htNhShF559x3dRXiuy_UdP3UQVLYW6c"
        width="100%"
        height="auto"
      />
    </div>
  </section>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";

@Component
export default class MygalleryView extends Vue {}
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
  left: 50%;
  top: 40%;
  z-index: 100;
  width: 500px;
}
#clicked1 .box {
  width: 20vw;
  max-width: 500px;
  max-height: 200px;

  box-shadow: 0px 0px 15px 5px;
}
</style>
