<template>
  <div class="recommendation-page">
    <div
      class="rec-section"
      @mouseover="onWrapHover('user', true)"
      @mouseleave="onWrapHover('user', false)"
    >
      <h1 class="rec-header">
        <div
          class="rec-title"
          @mouseover="onTitleHover('user', true)"
          @mouseleave="onTitleHover('user', false)"
        >
          니가 좋아할만한 작품
        </div>
        <v-fade-transition mode="out-in">
          <div v-if="recTitle.user.titleHover" class="see-all">모두보기</div>
        </v-fade-transition>
        <v-icon class="rec-icon" v-if="recTitle.user.wrapHover" dark>
          mdi-36px mdi-chevron-right
        </v-icon>
      </h1>
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
    <div
      class="rec-section"
      @mouseover="onWrapHover('author', true)"
      @mouseleave="onWrapHover('author', false)"
    >
      <h1 class="rec-header">
        <div
          class="rec-title"
          @mouseover="onTitleHover('author', true)"
          @mouseleave="onTitleHover('author', false)"
        >
          작가 추천
        </div>
        <v-fade-transition mode="out-in">
          <div v-if="recTitle.author.titleHover" class="see-all">모두보기</div>
        </v-fade-transition>
        <v-icon class="rec-icon" v-if="recTitle.author.wrapHover" dark>
          mdi-36px mdi-chevron-right
        </v-icon>
      </h1>
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

  recTitle = {
    user: {
      titleHover: false,
      wrapHover: false
    },
    author: {
      titleHover: false,
      wrapHover: false
    }
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

  onWrapHover(cate, bool) {
    console.log(cate);
    if (cate == "user") {
      this.recTitle.user.wrapHover = bool;
    } else if (cate == "author") {
      this.recTitle.author.wrapHover = bool;
    }
  }

  onTitleHover(cate, bool) {
    console.log(cate);
    if (cate == "user") {
      this.recTitle.user.titleHover = bool;
    } else if (cate == "author") {
      this.recTitle.author.titleHover = bool;
    }
  }

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

.rec-header {
  margin-bottom: 10px;
  color: white;
}

.rec-title {
  display: inline-block;
}

/* .rec-icon {
} */
.rec-icon:hover {
  transform: translate(30px);
}

.see-all {
  display: inline;
  font-size: 20px;
}
</style>
