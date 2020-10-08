<template>
  <div class="slide-wrap">
    <div class="slide" @click="toArtsByColor('white')">
      <div class="content" style="color: #bdbdbd">
        <h2>WHITE</h2>
      </div>
    </div>
    <div class="slide" @click="toArtsByColor('gray')">
      <div class="content">
        <h2>GRAY</h2>
      </div>
    </div>
    <div class="slide" @click="toArtsByColor('pink')">
      <div class="content">
        <h2>PINK</h2>
      </div>
    </div>
    <div class="slide" @click="toArtsByColor('blue')">
      <div class="content">
        <h2>BLUE</h2>
      </div>
    </div>
    <div class="slide" @click="toArtsByColor('teal')">
      <div class="content">
        <h2>TEAL</h2>
      </div>
    </div>
    <div class="slide" @click="toArtsByColor('green')">
      <div class="content">
        <h2>GREEN</h2>
      </div>
    </div>
    <div class="slide" @click="toArtsByColor('yellow')">
      <div class="content">
        <h2>YELLOW</h2>
      </div>
    </div>
    <div class="slide" @click="toArtsByColor('orange')">
      <div class="content">
        <h2>ORANGE</h2>
      </div>
    </div>
    <div class="slide" @click="toArtsByColor('red')">
      <div class="content">
        <h2>RED</h2>
      </div>
    </div>
    <div class="slide" @click="toArtsByColor('brown')">
      <div class="content">
        <h2>BROWN</h2>
      </div>
    </div>
    <div class="slide" @click="toArtsByColor('black')">
      <div class="content">
        <h2>BLACK</h2>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";
import { namespace } from "vuex-class";

const RecommendationModule = namespace("RecommendationModule");

@Component
export default class ColorSlider extends Vue {
  @RecommendationModule.Action FETCH_ART_LIST_BY_COLOR: any;

  toArtsByColor(color: string) {
    this.$router.push({ name: "ArtListByColor", params: { color } });
  }
}
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: "Poppins", sans-serif;
}

.slide-wrap {
  width: 100%;
  height: 400px;
  display: flex;
}

.slide-wrap .slide {
  margin: 0 5px;
  position: relative;
  height: 100%;
  flex: 1;
  overflow: hidden;
  transition: 0.5s ease-in-out;
  cursor: pointer;
  border-radius: 8px;
}

.slide-wrap .slide:hover {
  flex-grow: 5;
}

.slide-wrap .slide:nth-child(1) {
  background-color: #ffffff;
  background-position: center center;
}

.slide-wrap .slide:nth-child(2) {
  background-color: #bdbdbd;
  background-position: center center;
}

.slide-wrap .slide:nth-child(3) {
  background-color: #f48fb1;
  background-position: center center;
}

.slide-wrap .slide:nth-child(4) {
  background-color: #2a56c6;
  background-position: center center;
}

.slide-wrap .slide:nth-child(5) {
  background-color: #2979ff;
  background-position: center center;
}

.slide-wrap .slide:nth-child(6) {
  background-color: #0d904f;
  background-position: center center;
}

.slide-wrap .slide:nth-child(7) {
  background-color: #fdd835;
  background-position: center center;
}

.slide-wrap .slide:nth-child(8) {
  background-color: #ff9800;
  background-position: center center;
}

.slide-wrap .slide:nth-child(9) {
  background-color: #d50000;
  background-position: center center;
}

.slide-wrap .slide:nth-child(10) {
  background-color: #795548;
  background-position: center center;
}

.slide-wrap .slide:nth-child(11) {
  background-color: #37474f;
  background-position: center center;
}

.slide-wrap .slide .content {
  position: absolute;
  bottom: 0;
  margin: 40px;
  padding: 30px;
  color: #fff;
  visibility: hidden;
  opacity: 0;
  transition: 0s;
  transform: translateY(100px);
}

.slide-wrap .slide:hover .content {
  visibility: visible;
  opacity: 1;
  transition: 0.4s;
  transition-delay: 0.4s;
  transform: translateY(0);
}
</style>
