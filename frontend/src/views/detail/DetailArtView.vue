<template>
  <div class="art-image">
    <div class="container">
      <img
        src="https://lh5.ggpht.com/Go9QucSIS0OASfQar5o3ziLZFJfpbzoEURXvSL2vpu9B8yvvarl7qhrZQKs"
      />

      <div
        style="min-width: 320px; color: white; font-family: Playfair Display, serif; align-self: center"
      >
        <div class="art-title" style="font-size: 25px; margin-top: 5px">
          Merahi metua no Tehamana
        </div>

        <div class="art-artist" style="font-size: 18px; margin-top: 30px">
          <span style="font-family: 'Do Hyeon', sans-serif;">
            아티스트:
          </span>
          <span>
            Vincent van Gogh
          </span>
        </div>

        <div class="art-Age" style="font-size: 18px; margin-top: 10px">
          <span style="font-family: 'Do Hyeon', sans-serif;">
            제작연도:
          </span>
          <span>
            1893
          </span>
        </div>

        <div class="art-Age" style="font-size: 18px; margin-top: 10px">
          <span style="font-family: 'Do Hyeon', sans-serif;">
            크기:
          </span>
          <span>
            w93 x h71.1 cm
          </span>
        </div>

        <div class="art-Age" style="font-size: 18px; margin-top: 10px">
          <span style="font-family: 'Do Hyeon', sans-serif;">
            작품유형:
          </span>
          <span>
            Painting
          </span>
        </div>

        <div class="art-Age" style="font-size: 18px; margin-top: 10px">
          <span style="font-family: 'Do Hyeon', sans-serif;">
            작품방식:
          </span>
          <span>
            Oil on canvas
          </span>
        </div>

        <div class="art-Age" style="font-size: 18px; margin-top: 10px">
          <span style="font-family: 'Do Hyeon', sans-serif;">
            장르:
          </span>
          <span>
            Modern art
          </span>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { namespace } from "vuex-class";
import { Art } from "../../store/Detail.interface";

const DetailModule = namespace("DetailModule");

@Component
export default class DetailArtView extends Vue {
  @DetailModule.State art!: Art;
}
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
  justify-content: space-between;
}
.container img:hover {
  max-width: 600px;
  transform-origin: center;
  transform: perspective(800px) rotateY(0deg);
  transition: 0.5s;
  -webkit-box-reflect: below 1px
    linear-gradient(transparent, transparent, #0004);
}

.container img {
  max-width: 600px;
  transform-origin: center;
  transform: perspective(800px) rotateY(20deg);
  transition: 0.5s;
  -webkit-box-reflect: below 1px
    linear-gradient(transparent, transparent, #0004);
}
</style>
