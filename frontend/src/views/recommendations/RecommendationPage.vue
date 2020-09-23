<template>
  <div class="recommendation-page">
    <h1>니가 좋아할만한 작품</h1>
    <vue-slick-carousel class="slick" v-bind="settings">
      <div v-for="(article, i) in articles" :key="i">
        <img
          class="recommendation-img"
          :src="require(`@/assets/dummydata/articles/${article.hero}`)"
          :alt="article.author"
        />
      </div>
    </vue-slick-carousel>
  </div>
</template>

<script>
import { Vue, Component } from "vue-property-decorator";
import { namespace } from "vuex-class";
import { Article } from "../../store/ArticleInterface";

import VueSlickCarousel from "vue-slick-carousel";
import "vue-slick-carousel/dist/vue-slick-carousel.css";
import "vue-slick-carousel/dist/vue-slick-carousel-theme.css";

const articleModule = namespace("articleModule");

@Component({
  components: {
    VueSlickCarousel
  }
})
export default class RecommendationPage extends Vue {
  @articleModule.State articles;
  @articleModule.Mutation SET_ARTICLE;

  settings = {
    arrows: false,
    dots: true,
    infinite: true,
    slidesToShow: 6,
    slidesToScroll: 1,
    autoplay: true,
    speed: 3000,
    autoplaySpeed: 2000,
    cssEase: "linear"
  };

  created() {
    this.SET_ARTICLE();
  }
}
</script>

<style scoped>
.recommendation-page {
  margin: 5%;
}

.recommendation-img {
  height: 300px;
  width: 100%;
}

.slick {
  width: 100%;
  height: 300px;
}
</style>
