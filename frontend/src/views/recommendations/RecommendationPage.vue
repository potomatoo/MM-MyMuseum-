<template>
  <div class="recommendation-page">
    <div class="rec-section">
      <h1 class="rec-title">니가 좋아할만한 작품</h1>
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
    <div class="rec-section">
      <h1 class="rec-title">작가 추천</h1>
      <vue-slick-carousel class="slick" v-bind="settingsrtl">
        <div v-for="(article, i) in articles" :key="i">
          <img
            class="recommendation-img"
            :src="require(`@/assets/dummydata/articles/${article.hero}`)"
            :alt="article.author"
          />
        </div>
      </vue-slick-carousel>
    </div>
  </div>
</template>

<script>
import { Vue, Component, Watch } from "vue-property-decorator";
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

  window = {
    width: 0,
    height: 0
  };

  settings = {
    arrows: false,
    dots: true,
    infinite: true,
    slidesToShow: 5,
    slidesToScroll: 1,
    autoplay: true,
    speed: 2000,
    autoplaySpeed: 3000,
    responsive: [
      {
        breakpoint: 1399,
        settings: {
          slidesToShow: 4
        }
      },
      {
        breakpoint: 1099,
        settings: {
          slidesToShow: 3
        }
      },
      {
        breakpoint: 799,
        settings: {
          slidesToShow: 2
        }
      },
      {
        breakpoint: 499,
        settings: {
          slidesToShow: 2
        }
      }
    ]
  };

  settingsrtl = {
    arrows: false,
    dots: true,
    infinite: true,
    slidesToShow: 5,
    slidesToScroll: 1,
    autoplay: true,
    speed: 2000,
    autoplaySpeed: 3000,
    rtl: true,
    responsive: [
      {
        breakpoint: 1399,
        settings: {
          slidesToShow: 4
        }
      },
      {
        breakpoint: 1099,
        settings: {
          slidesToShow: 3
        }
      },
      {
        breakpoint: 799,
        settings: {
          slidesToShow: 2
        }
      },
      {
        breakpoint: 499,
        settings: {
          slidesToShow: 2
        }
      }
    ]
  };
  // onHover() {}
  @Watch("window", { deep: true })
  setDot() {
    const dots = document.querySelectorAll(".slick-dots");
    dots.forEach(el => {
      el.style.bottom = "-40px";
    });
  }

  handleResize() {
    this.window.width = window.innerWidth;
    this.window.height = window.innerHeight;
  }

  created() {
    this.SET_ARTICLE();
    window.addEventListener("resize", this.handleResize);
    this.handleResize();
  }

  mounted() {
    this.setDot();
  }

  beforeDestroy() {
    window.removeEventListener("resize", this.handleResize);
  }
}
</script>

<style scoped>
.recommendation-page {
  margin: 5%;
}

.recommendation-img {
  margin: 5px;
  width: 98%;
  height: 165px;
  transition: all ease 1ms;
}

.recommendation-img:hover {
  cursor: pointer;
  box-shadow: 0px 0px 10px 5px;
  transform: scale(1.1);
}

.slick {
  width: 100%;
  height: 165px;
}

.rec-section {
  margin-bottom: 50px;
}

.rec-title {
  margin-bottom: 10px;
  color: white;
}
</style>
