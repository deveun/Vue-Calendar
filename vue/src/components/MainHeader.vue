<template>
  <v-container>
    <v-row class="text-center">
      <v-col class="my-2">
        <h1 class="display-2 font-weight-bold mb-5">
          플랫폼 본부 운영캘린더
        </h1>

        <!-- <p class="subheading font-weight-regular">
          각 팀의 서비스별 운영일정을 확인합니다.
        </p> -->
      </v-col>
    </v-row>
    <!-- <v-row class="text-center">
      <v-col>
        <h2 class="headline font-weight-bold mb-3">
          캘린더 타입 선택
        </h2>
        <v-btn-toggle v-model=$store.state.calType tile group mandatory>
          <v-btn value="month" >
            월별
          </v-btn>
          <v-btn value="week">
            주별
          </v-btn>
          <v-btn value="day" >
            일별
          </v-btn>
        </v-btn-toggle>
      </v-col>
    </v-row> -->
    <v-row class="text-center">
      <v-col>
        <h2 class="headline font-weight-bold mb-3">
          화면에 보여줄 팀을 선택
        </h2>
        <v-btn-toggle v-model="$store.state.selectedTeams" dense multiple backgroud-color="primary" class="white--text">
          <v-btn class="mx-1" value="결제플랫폼" elevation="2" :activeClass="color='pink lighten-2'">결제플랫폼</v-btn>
          <v-btn class="mx-1" value="인증플랫폼" elevation="2" :activeClass="color = 'deep-purple'">인증플랫폼</v-btn>
          <v-btn class="mx-1" value="서비스플랫폼" elevation="2" :activeClass="color = 'green'">서비스플랫폼</v-btn>
          <v-btn class="mx-1" value="Smart-X" elevation="2" :activeClass="color = 'orange'">Smart-X</v-btn>
          <v-btn class="mx-1" value="기가지니플랫폼" elevation="2" :activeClass="color = 'teal darken-1'">기가지니플랫폼</v-btn>
          <v-btn class="mx-1" value="미디어플랫폼" elevation="2" :activeClass="color = 'lime darken-2'">미디어플랫폼</v-btn>
        </v-btn-toggle>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
  export default {
    name: 'MainHeader',

    data: () => ({
    }),
    methods: {
    }
  }
</script>
