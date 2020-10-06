<template>
  <div>
    <div class="mygallery">
      <div class="box">
        <span
          v-for="(art, index) in arts"
          :key="index"
          :style="`--i:${index + 1};`"
        >
          <img :src="art.art_url" :alt="art.art_title" />
        </span>
        <!-- <span style="--i:1;"
          ><img
            src="https://lh4.ggpht.com/UuYCUnqvo2EIZhyFHYFVLbkmma_cubVk7SwxOF3lklT6aor5647BXVhEaFB7jg"
        /></span>
        <span style="--i:2;"
          ><img
            src="https://lh6.ggpht.com/HlgucZ0ylJAfZgusynnUwxNIgIp5htNhShF559x3dRXiuy_UdP3UQVLYW6c"
        /></span>
        <span style="--i:3;"
          ><img
            src="https://lh4.ggpht.com/zwvR_x7jq9u9Oo1vQ2QrIF5KPNcxM9jWu20F1n_AgX1QvCqLRWgXYTqt5H0"
        /></span>
        <span style="--i:4;"
          ><img
            src="https://lh6.ggpht.com/rCrxkaW6mPFJ6V2cWCGpmW5SkGo2SrEpB71oX8vWRxOVoiEDdT3BOBiHyJQ"
        /></span>
        <span style="--i:5;"
          ><img
            src="https://lh3.ggpht.com/MA_gR9Zh_v3ew1n91JoAhjIyt_CprhrLP-qX2J92hKfQaYmMh80fMIjZ8po"
        /></span>
        <span style="--i:6;"
          ><img
            src="https://lh5.ggpht.com/BukMxmYxe18AYLp-yRd64pXDDIDRWS2YiF8q95zTRRosNUqrXVQllxv4Vwk"
        /></span>
        <span style="--i:7;"
          ><img
            src="https://lh5.ggpht.com/BukMxmYxe18AYLp-yRd64pXDDIDRWS2YiF8q95zTRRosNUqrXVQllxv4Vwk"
        /></span>
        <span style="--i:8;"
          ><img
            src="https://lh5.ggpht.com/BukMxmYxe18AYLp-yRd64pXDDIDRWS2YiF8q95zTRRosNUqrXVQllxv4Vwk"
        /></span> -->
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Vue, Component, Prop } from "vue-property-decorator";
import { Art } from "../../store/Recommendation.interface";

@Component
export default class DetailArtRotate extends Vue {
  @Prop() private arts!: Art[];
}
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

.mygallery {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 70vh;
  background: #000;
}

.box {
  position: relative;
  width: 200px;
  height: 250px;
  transform-style: preserve-3d;
  animation: animate 30s linear infinite;
}

@keyframes animate {
  0% {
    transform: perspective(1000px) rotateY(0deg);
  }
  100% {
    transform: perspective(1000px) rotateY(360deg);
  }
}
.box span {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  transform-origin: center;
  transform-style: preserve-3d;
  transform: rotateY(calc(var(--i) * 45deg)) translateZ(400px);
  -webkit-box-reflect: below 0px
    linear-gradient(transparent, transparent, #0004);
}

.box span img {
  position: relative;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
}
</style>
