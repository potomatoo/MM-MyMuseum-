<template>
  <div class="home full-height">
    <v-row class="full-height">
      <v-col class="full-height col-6 col-sm-6 col-md-5">
        <div class="text-scope">
          <p
            class="text-h4 main-text"
            @mouseover="hovered.recommendation = true"
            @mouseleave="hovered.recommendation = false"
            @click="routerToRecommendation"
          >
            <span v-if="hovered.recommendation" class="arrow">&rarr;</span>
            추천 작품
          </p>
          <p
            class="text-h4 main-text"
            @mouseover="hovered.collection = true"
            @mouseleave="hovered.collection = false"
            @click="routerToCollection"
          >
            <span v-if="hovered.collection" class="arrow">&rarr;</span>
            컬렉션
          </p>
          <p
            class="text-h4 main-text"
            @mouseover="hovered.exhibition = true"
            @mouseleave="hovered.exhibition = false"
            @click="routerToMyGallery"
          >
            <span v-if="hovered.exhibition" class="arrow">&rarr;</span>
            내 전시회
          </p>
        </div>
      </v-col>
      <v-col class="full-height col-5 col-sm-5 col-md-4">
        <img
          class="main-img"
          alt="Vue logo"
          src="https://lh3.googleusercontent.com/J0sw0IiqP2F4gavYnI-vUa5IBgHiHy42lohgm-qq1vuygUX0HQgylVSV1ZdDTV5XIg=w294-c-h425-fcrop64=1,1c270000ec70ffff-rw-v1"
        />
      </v-col>
      <v-col class="full-height col-1 col-sm-1 col-md-3"> </v-col>
    </v-row>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { namespace } from "vuex-class";

const AccountsModule = namespace("AccountsModule");

@Component
export default class Home extends Vue {
  @AccountsModule.Getter isLoggedIn!: boolean;
  hovered = {
    recommendation: false,
    collection: false,
    exhibition: false
  };

  routerToCollection() {
    this.$router.push({ name: "CategoryList" });
  }

  routerToRecommendation() {
    this.$router.push({ name: "RecommendView" });
  }

  routerToMyGallery() {
    if (this.isLoggedIn) {
      this.$router.push({ name: "MyGalleryListView" });
    } else {
      if (
        confirm("로그인이 필요합니다.\n로그인 화면으로 이동하시겠습니까?") ===
        true
      ) {
        this.$router.push({ name: "Login" });
      }
    }
  }
}
</script>

<style>
.full-height {
  height: 100%;
  margin: 0px;
  padding: 0px;
}

.main-img {
  height: 100%;
  box-shadow: 0px 0px 50px 20px;
}

.text-scope {
  position: absolute;
  margin-left: 2vw;
  bottom: 15vh;
}

.main-text {
  color: rgb(115, 106, 98);
  transition: all ease 1s;
}

.main-text:hover {
  cursor: pointer;
  color: rgb(174, 164, 152);
  transform: translateX(20px) scaleX(1.2) scaleY(1.3);
}

.arrow {
  color: rgb(149, 46, 23);
}
</style>
