<template>
  <div>
    <detail-art-rotate :arts="userRecArts" />
    <div class="recommendation-page">
      <!-- 유저별 추천 작품 -->
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
              {{ userName }} 님을 위한 추천 작품
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
        <vue-slick-carousel
          v-if="userRecArts"
          class="slick"
          v-bind="settingsrtl"
        >
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

      <!-- 색상 별 작품 -->
      <div class="color-slider">
        <color-slider />
      </div>

      <!-- 날씨 별 추천 -->
      <div
        class="rec-section"
        @mouseover="onWrapHover('weather')"
        @mouseleave="outWrapHover('weather')"
      >
        <h1 class="rec-header">
          <div
            class="rec-title"
            @mouseover="onTitleHover('weather')"
            @mouseleave="outTitleHover('weather')"
          >
            <div class="rec-header-title">
              {{ weatherTitle }}
            </div>
            <div class="rec-header-aro">
              <v-fade-transition mode="out-in">
                <div
                  v-if="recTitle.weather.titleHover"
                  class="see-all"
                  @click="toAllWeatherArts"
                >
                  모두보기
                </div>
              </v-fade-transition>
              <v-icon class="rec-icon" v-if="isWeatherHover" dark>
                mdi-36px mdi-chevron-right
              </v-icon>
            </div>
          </div>
        </h1>
        <vue-slick-carousel
          v-if="weatherRecArts"
          class="slick"
          v-bind="settings"
        >
          <div v-for="art in weatherRecArts" :key="art.art_no">
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

      <!-- 시간 별 추천 -->
      <div
        class="rec-section"
        @mouseover="onWrapHover('time')"
        @mouseleave="outWrapHover('time')"
      >
        <h1 class="rec-header">
          <div
            class="rec-title"
            @mouseover="onTitleHover('time')"
            @mouseleave="outTitleHover('time')"
          >
            <div class="rec-header-title">
              {{ timeTitle }}
            </div>
            <div class="rec-header-aro">
              <v-fade-transition mode="out-in">
                <div
                  v-if="recTitle.time.titleHover"
                  class="see-all"
                  @click="toAllTimeArts"
                >
                  모두보기
                </div>
              </v-fade-transition>
              <v-icon class="rec-icon" v-if="isTimeHover" dark>
                mdi-36px mdi-chevron-right
              </v-icon>
            </div>
          </div>
        </h1>
        <vue-slick-carousel
          v-if="timeRecArts"
          class="slick"
          v-bind="settingsrtl"
        >
          <div v-for="art in timeRecArts" :key="art.art_no">
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
    </div>
  </div>
</template>

<script>
import { Vue, Component, Watch } from "vue-property-decorator";
import { namespace } from "vuex-class";

import VueSlickCarousel from "vue-slick-carousel";
import "vue-slick-carousel/dist/vue-slick-carousel.css";
import "vue-slick-carousel/dist/vue-slick-carousel-theme.css";
import DetailArtRotate from "@/components/detail/DetailArtRotate.vue";
import ColorSlider from "@/components/recommendations/ColorSlider.vue";

const articleModule = namespace("articleModule");
const RecommendationModule = namespace("RecommendationModule");
const AccountsModule = namespace("AccountsModule");

@Component({
  components: {
    VueSlickCarousel,
    DetailArtRotate,
    ColorSlider
  }
})
export default class RecommendationPage extends Vue {
  @AccountsModule.Getter userName;
  @RecommendationModule.Getter userRecArts;
  @RecommendationModule.Getter weatherRecArts;
  @RecommendationModule.Getter timeRecArts;
  @RecommendationModule.Getter weatherTitle;
  @RecommendationModule.Getter timeTitle;
  @RecommendationModule.Action FETCH_ART_LIST;
  @RecommendationModule.Action FETCH_ART_LIST_BY_WEATHER;

  get isRecHover() {
    return this.recTitle.user.wrapHover || this.recTitle.user.titleHover;
  }

  get isWeatherHover() {
    return this.recTitle.weather.wrapHover || this.recTitle.weather.titleHover;
  }

  get isTimeHover() {
    return this.recTitle.time.wrapHover || this.recTitle.time.titleHover;
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
    weather: {
      wrapHover: false,
      titleHover: false,
      allseeHover: false
    },
    time: {
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
    } else if (cate == "weather") {
      this.recTitle.weather.wrapHover = true;
    } else if (cate == "time") {
      this.recTitle.time.wrapHover = true;
    }
  }

  outWrapHover(cate) {
    setTimeout(() => {
      if (cate == "user") {
        this.recTitle.user.wrapHover = false;
      } else if (cate == "weather") {
        this.recTitle.weather.wrapHover = false;
      } else if (cate == "time") {
        this.recTitle.time.wrapHover = false;
      }
    }, 500);
  }

  onTitleHover(cate) {
    if (cate == "user") {
      this.recTitle.user.titleHover = true;
    } else if (cate == "weather") {
      this.recTitle.weather.titleHover = true;
    } else if (cate == "time") {
      this.recTitle.time.titleHover = true;
    }
  }

  outTitleHover(cate) {
    if (cate == "user") {
      this.recTitle.user.titleHover = false;
    } else if (cate == "weather") {
      this.recTitle.weather.titleHover = false;
    } else if (cate == "time") {
      this.recTitle.time.titleHover = false;
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
    this.FETCH_ART_LIST();
    this.FETCH_ART_LIST_BY_WEATHER();
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

  toAllWeatherArts() {
    this.$router.push({ name: "WeatherArtsList" });
  }

  toAllTimeArts() {
    this.$router.push({ name: "TimeArtsList" });
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
  margin: 20px 60px;
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

.recommendation-page .rec-section .slick .slick-dots li button::before {
  color: white;
}

.rec-section {
  margin-bottom: 100px;
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

.color-slider {
  height: 400px;
  margin: 150px 0px 100px;
}
</style>
