<template>
  <div>
    <!-- 윗공간 -->
    <v-responsive class="mx-auto mb-12" width="56">
      <v-divider class="mb-1"></v-divider>
      <v-divider></v-divider>
    </v-responsive>
    <!-- 제목 -->
    <h2
      class="display-2 font-weight-bold mb-3 text-uppercase text-center"
      style="color:white"
    >
      {{ userName }}님의 작품들
    </h2>

    <v-row
      cols="12"
      sm="6"
      offset-sm="3"
      align="center"
      justify="center"
      style="margin : 1px 20%"
    >
      <div v-if="amateurArts && !amateurArts.length" style="margin: 20px">
        <h1 style="color: white;">등록된 작품이 없습니다.</h1>
      </div>
    </v-row>

    <v-row style="margin: 10px 10%" cols="12" sm="6" offset-sm="3">
      <v-container fluid cols="12">
        <v-row>
          <v-col
            v-for="(value, n) in amateurArts"
            :key="n"
            class="d-flex child-flex"
            cols="3"
          >
            <v-hover v-slot:default="{ hover }">
              <v-card
                flat
                tile
                class="d-flex"
                :elevation="hover ? 12 : 2"
                :class="{ 'on-hover': hover }"
                @click="moveDetail(value.myartNo)"
              >
                <v-img
                  :src="value.myartUrl"
                  aspect-ratio="1"
                  class="grey lighten-2 artist-card"
                >
                  <template v-slot:placeholder>
                    <v-row
                      class="fill-height ma-0"
                      align="center"
                      justify="center"
                    >
                      <v-progress-circular
                        indeterminate
                        color="grey lighten-5"
                      ></v-progress-circular>
                    </v-row>
                  </template>

                  <v-expand-transition>
                    <div
                      v-if="hover"
                      class="d-flex transition-fast-in-fast-out darken-2 v-card--reveal display-1 white--text black text-center"
                      style="width: 100%; height: 100%;"
                    >
                      {{ value.myartTitle }}
                    </div>
                  </v-expand-transition>
                </v-img>
              </v-card>
            </v-hover>
          </v-col>
        </v-row>
      </v-container>
    </v-row>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator";

import { namespace } from "vuex-class";
import { AmateurArt } from "../../../store/AmateurInterface";
const amateurModule = namespace("amateurModule");

@Component
export default class AmateurArtView extends Vue {
  @amateurModule.State amateurArts!: AmateurArt[] | null;
  @amateurModule.Action FETCH_AMATEUR_ART: any;
  @amateurModule.Mutation SET_AMATEUR_ART_ZERO: any;
  @amateurModule.State userId: any;
  @amateurModule.State userName: any;
  @amateurModule.Mutation SET_USERINFO: any;
  @amateurModule.Mutation SET_USERINFO_ZERO: any;

  start = 0;

  created() {
    if (this.$route.params.userId != null) {
      this.SET_USERINFO({
        userId: this.$route.params.userId.toString(),
        userName: this.$route.params.userName.toString()
      });
    }
    this.FETCH_AMATEUR_ART({
      start: this.start,
      userId: this.userId
    });
    console.log("@@" + this.userId + "!!! " + this.userName);
  }

  moveDetail(myartNo: string) {
    this.$router.push({
      name: "DetailAmateurArt",
      params: { myartNo: myartNo }
    });
  }

  destroyed() {
    this.SET_AMATEUR_ART_ZERO();
  }
}
</script>

<style scoped>
.v-card {
  transition: opacity 0.4s ease-in-out;
}

.v-card:not(.on-hover) {
  opacity: 0.9;
}

.v-card--reveal {
  align-items: center;
  bottom: 0;
  justify-content: center;
  opacity: 0.5;
  position: relative;
  width: 100%;
}

.artist-card {
  box-shadow: 0px 0px 10px 5px;
}
</style>
