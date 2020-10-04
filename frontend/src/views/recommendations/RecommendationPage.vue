<template>
  <div class="recommendation-page">
    <div
      class="rec-section"
      @mouseover="onWrapHover('user')"
      @mouseleave="outWrapHover('user')"
    >
      <h1 class="rec-header">
        <div
          class="rec-title"
          @mouseover="onTitleHover('user')"
          @mouseleave="outTitleHover('user')"
        >
          <div class="rec-header-title">
            니가 좋아할만한 작품
          </div>
          <div class="rec-header-aro">
            <v-fade-transition mode="out-in">
              <div
                v-if="recTitle.user.titleHover"
                class="see-all"
                @click="toAllUserRec"
              >
                모두보기
              </div>
            </v-fade-transition>
            <v-icon class="rec-icon" v-if="isRecHover" dark>
              mdi-36px mdi-chevron-right
            </v-icon>
          </div>
        </div>
      </h1>
      <vue-slick-carousel v-if="userRecArts" class="slick" v-bind="settings">
        <div v-for="art in userRecArts" :key="art.art_no">
          <router-link
            class="router-link"
            :to="{ name: 'DetailArtView', params: { artNo: art.art_no } }"
          >
            <img
              class="recommendation-img"
              :src="art.art_url"
              :alt="art.art_title"
            />
          </router-link>
        </div>
      </vue-slick-carousel>
    </div>
    <div
      class="rec-section"
      @mouseover="onWrapHover('author')"
      @mouseleave="outWrapHover('author')"
    >
      <h1 class="rec-header">
        <div
          class="rec-title"
          @mouseover="onTitleHover('author')"
          @mouseleave="outTitleHover('author')"
        >
          <div class="rec-header-title">
            작가 추천
          </div>
          <div class="rec-header-aro">
            <v-fade-transition mode="out-in">
              <div v-if="recTitle.author.titleHover" class="see-all">
                모두보기
              </div>
            </v-fade-transition>
            <v-icon class="rec-icon" v-if="isAuthorHover" dark>
              mdi-36px mdi-chevron-right
            </v-icon>
          </div>
        </div>
      </h1>
      <vue-slick-carousel v-if="userRecArts" class="slick" v-bind="settingsrtl">
        <div v-for="art in userRecArts" :key="art.art_no">
          <img
            class="recommendation-img"
            :src="art.art_url"
            :alt="art.art_title"
          />
        </div>
      </vue-slick-carousel>
    </div>
  </div>
</template>

<script>
import { Vue, Component, Watch } from "vue-property-decorator";
import { namespace } from "vuex-class";

import VueSlickCarousel from "vue-slick-carousel";
import "vue-slick-carousel/dist/vue-slick-carousel.css";
import "vue-slick-carousel/dist/vue-slick-carousel-theme.css";

const articleModule = namespace("articleModule");
const RecommendationModule = namespace("RecommendationModule");

@Component({
  components: {
    VueSlickCarousel
  }
})
export default class RecommendationPage extends Vue {
  @RecommendationModule.Getter userRecArts;
  @RecommendationModule.Action FETCH_ART_LIST;

  get isRecHover() {
    return this.recTitle.user.wrapHover || this.recTitle.user.titleHover;
  }

  get isAuthorHover() {
    return this.recTitle.author.wrapHover || this.recTitle.author.titleHover;
  }

  window = {
    width: 0,
    height: 0
  };

  recTitle = {
    user: {
      wrapHover: false,
      titleHover: false,
      allseeHover: false
    },
    author: {
      wrapHover: false,
      titleHover: false,
      allseeHover: false
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

  onWrapHover(cate) {
    if (cate == "user") {
      this.recTitle.user.wrapHover = true;
    } else if (cate == "author") {
      this.recTitle.author.wrapHover = true;
    }
  }

  outWrapHover(cate) {
    setTimeout(() => {
      if (cate == "user") {
        this.recTitle.user.wrapHover = false;
      } else if (cate == "author") {
        this.recTitle.author.wrapHover = false;
      }
    }, 500);
  }

  onTitleHover(cate) {
    if (cate == "user") {
      this.recTitle.user.titleHover = true;
    } else if (cate == "author") {
      this.recTitle.author.titleHover = true;
    }
    // const icon = document.querySelector(".rec-icon");
    // if (icon) {
    // icon.style.transform = "translateX(30px)";
    // icon.style.transition = "transform 5000ms";
    // setTimeout(() => {
    // }, 500);
    // }
  }

  outTitleHover(cate) {
    if (cate == "user") {
      this.recTitle.user.titleHover = false;
    } else if (cate == "author") {
      this.recTitle.author.titleHover = false;
    }
  }

  @Watch("window", { deep: true })
  async setDot() {
    const dots = await document.querySelectorAll(".slick-dots");
    dots.forEach(el => {
      el.style.bottom = "-40px";
    });
  }

  handleResize() {
    this.window.width = window.innerWidth;
    this.window.height = window.innerHeight;
  }

  created() {
    this.FETCH_ART_LIST("test");
    window.addEventListener("resize", this.handleResize);
    this.handleResize();
  }

  updated() {
    this.setDot();
  }

  beforeDestroy() {
    window.removeEventListener("resize", this.handleResize);
  }

  toAllUserRec() {
    this.$router.push({ name: "RecArtList" });
  }
}
</script>

<style scoped>
.router-link {
  text-decoration: none;
  color: inherit;
  border: 0;
  outline: none;
}
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
  cursor: pointer;
}

.rec-title {
  display: inline-block;
}

.see-all {
  display: inline-block;
  font-size: 20px;
  margin-left: 20px;
}

.rec-header-title {
  display: table-cell;
}

.rec-header-aro {
  display: table-cell;
}
</style>
