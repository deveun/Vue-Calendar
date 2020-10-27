<template>
    <v-dialog
      v-model=$store.state.insertDialogVisible
      width="500"
    >
      <v-card>
        <v-card-title>
          <span class="headline">일정 추가하기</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12">
                <v-text-field label="일정 이름*" required/>
              </v-col>
              
              <v-col cols="12" sm="6">
                <v-select label = "소속 팀*" required
                  :items="teams"/>
              </v-col>
              
              <v-col cols="12" sm="6">
                <v-text-field label="작성자  *" required/>
              </v-col>

              <v-col cols="12" sm="6">
                <v-menu ref="menu" v-model="menu"
                :close-on-content-click="false"
                :return-value.sync="dates"
                transition="scale-transition"
                offset-y
                min-width="290px">
                  <template v-slot:activator="{ on, attrs }">
                    <v-text-field v-model="dateRangeText" label="시작/종료날짜"
                    v-bind="attrs" v-on="on" readonly/>
                  </template>
                  <v-date-picker v-model="dates" range/>
                  <v-spacer></v-spacer>
                  <v-btn text color="primary" @click="menu=false">Cancel</v-btn>
                  <v-btn text color="primary" @click="$refs.menu.save(dates)">OK</v-btn>
                </v-menu>
              </v-col>

              <v-col cols="12">
                <v-textarea label="상세내용" outlined rows="2" row-height="15"/>
              </v-col>
              
              <v-col
                cols="12"
                sm="6"
              >
                <v-autocomplete
                  :items="['Skiing', 'Ice hockey', 'Soccer', 'Basketball', 'Hockey', 'Reading', 'Writing', 'Coding', 'Basejump']"
                  label="Interests"
                  multiple
                ></v-autocomplete>
              </v-col>
            </v-row>
          </v-container>
          <small>*indicates required field</small>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="blue darken-1"
            text
            @click="dialog = false"
          >
            Close
          </v-btn>
          <v-btn
            color="blue darken-1"
            text
            @click="dialog = false"
          >
            Save
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
</template>

<script>
  export default {
    name: 'InsertDialog',

    data () {
      return {
        dialog: false,
        teams: ["결제플랫폼", "인증플랫폼", "서비스플랫폼", "Smart-X", "기가지니플랫폼", "미디어플랫폼"],
        dates: ['2019-09-10', '2019-09-20'],
        menu: false,
      }
    },
    computed: {
      dateRangeText () {
        if(this.dates.length == 2 && this.dates[0] > this.dates[1]) {
          let tmp = this.dates[0]
          this.dates[0] = this.dates[1]
          this.dates[1] = tmp;
        }
        return this.dates.join(' ~ ')
      },
    },
  }
</script>
