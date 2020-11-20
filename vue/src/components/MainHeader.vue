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
  </v-container>
</template>

<script>
  export default {
    name: 'MainHeader',

    data: () => ({
      calType : 'month'
    }),

    methods: {

    }
  }
</script>
