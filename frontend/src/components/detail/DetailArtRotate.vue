<template>
  <div>
    <div class="mygallery">
      <!-- 추천페이지 -->
      <div v-if="currentPage === 'RecommendView'" class="box">
        <span
          v-for="(art, index) in arts"
          :key="index"
          :style="`--i:${index + 1};`"
        >
          <img :src="art.art_url" :alt="art.art_title" />
        </span>
      </div>
      <!-- 내전시회 -->
      <div v-if="currentPage === 'MyGalleryListView'" class="box">
        <span
          v-for="(art, index) in arts"
          :key="index"
          :style="`--i:${index + 1};`"
        >
          <img :src="art.artUrl" :alt="art.artTitle" />
        </span>
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

  get currentPage() {
    return this.$route.name;
  }
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
