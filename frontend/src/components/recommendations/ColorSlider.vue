<template>
  <div class="slide-wrap">
    <div class="slide">
      <div class="content">
        <h2>Slide One</h2>
      </div>
    </div>
    <div class="slide">
      <div class="content">
        <h2>Slide Two</h2>
      </div>
    </div>
    <div class="slide">
      <div class="content">
        <h2>Slide Three</h2>
      </div>
    </div>
    <div class="slide">
      <div class="content">
        <h2>Slide Four</h2>
      </div>
    </div>
    <div class="slide">
      <div class="content">
        <h2>Slide Five</h2>
      </div>
    </div>
    <div class="slide">
      <div class="content">
        <h2>Slide Six</h2>
      </div>
    </div>
    <div class="slide">
      <div class="content">
        <h2>Slide Seven</h2>
      </div>
    </div>
    <div class="slide">
      <div class="content">
        <h2>Slide Eight</h2>
      </div>
    </div>
    <div class="slide">
      <div class="content">
        <h2>Slide Nine</h2>
      </div>
    </div>
    <div class="slide">
      <div class="content">
        <h2>Slide Ten</h2>
      </div>
    </div>
    <div class="slide">
      <div class="content">
        <h2>Slide Eleven</h2>
      </div>
    </div>
    <div class="slide">
      <div class="content">
        <h2>Slide Twelve</h2>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";

@Component
export default class ColorSlider extends Vue {}
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: "Poppins", sans-serif;
}

.slide-wrap {
  position: absolute;
  width: 70%;
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
}

.slide-wrap .slide:hover {
  flex-grow: 5;
}

.slide-wrap .slide:nth-child(1) {
  background-color: rgb(255, 0, 0);
  background-position: center center;
}

.slide-wrap .slide:nth-child(2) {
  background-color: rgb(157, 255, 0);
  background-position: center center;
}

.slide-wrap .slide:nth-child(3) {
  background-color: rgb(0, 38, 255);
  background-position: center center;
}

.slide-wrap .slide:nth-child(4) {
  background-color: rgb(247, 0, 255);
  background-position: center center;
}

.slide-wrap .slide:nth-child(5) {
  background-color: rgb(0, 255, 179);
  background-position: center center;
}

.slide-wrap .slide:nth-child(6) {
  background-color: rgb(187, 255, 0);
  background-position: center center;
}

.slide-wrap .slide:nth-child(7) {
  background-color: rgb(255, 153, 0);
  background-position: center center;
}

.slide-wrap .slide:nth-child(8) {
  background-color: rgb(67, 203, 207);
  background-position: center center;
}

.slide-wrap .slide:nth-child(9) {
  background-color: rgb(36, 38, 128);
  background-position: center center;
}

.slide-wrap .slide:nth-child(10) {
  background-color: rgb(38, 71, 61);
  background-position: center center;
}

.slide-wrap .slide:nth-child(11) {
  background-color: rgb(255, 0, 140);
  background-position: center center;
}

.slide-wrap .slide:nth-child(12) {
  background-color: rgb(153, 88, 94);
  background-position: center center;
}

.slide-wrap .slide .content {
  position: absolute;
  bottom: 0;
  margin: 40px;
  padding: 30px;
  background: #000;
  color: #fff;
  visibility: hidden;
  opacity: 0;
  transition: 0s;
  transform: translateY(100px);
}

.slide-wrap .slide:hover .content {
  visibility: visible;
  opacity: 1;
  transition: 0.5s;
  transition-delay: 0.5s;
  transform: translateY(0);
}
</style>
