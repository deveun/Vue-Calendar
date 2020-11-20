<template>
    <v-dialog v-model="setInsertDialog" persistent width="500">
      <v-form ref = "form" model="valid">  
      <v-card>
        <v-card-title>
          <span class="headline">일정 추가하기</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12">
                <v-text-field label="일정 이름*" counter
                v-model="title"
                :rules="[rules.required]"
                minlength="2"/>
              </v-col>
              
              <v-col cols="12" sm="6">
                <v-select label = "소속 팀*"
                v-model="team"
                :rules="[rules.required]"
                :items="teams"/>
              </v-col>
              
              <v-col cols="12" sm="6">
                <v-text-field label="작성자*"
                v-model = "creator"
                :rules="[rules.required]"/>
              </v-col>

              <v-col cols="8" sm="8">
                <v-menu ref="dateMenu" v-model="dateMenu"
                :close-on-content-click="false"
                :return-value.sync="dates"
                transition="scale-transition"
                offset-y
                min-width="290px">
                  <template v-slot:activator="{ on, attrs }">
                    <v-text-field v-model="dateRangeText" label="시작/종료날짜"
                    v-bind="attrs" v-on="on" prepend-icon="mdi-calendar" readonly/>
                  </template>
                  <v-date-picker v-model="dates" range/>
                  <v-spacer></v-spacer>
                  <v-btn text color="white" @click="dateMenu=false">Cancel</v-btn>
                  <v-btn text color="white" @click="$refs.dateMenu.save(dates)">OK</v-btn>
                </v-menu>
              </v-col>
              
              <v-col cols="4" sm="4">
                <v-checkbox v-model="dateMode" 
                label="하루종일">
                </v-checkbox>  
              </v-col>

              <v-col cols="6" sm="6">
                <v-menu ref="startTimeMenu" v-model="startTimeMenu"
                :disabled="dateMode"
                :close-on-content-click="false"
                :nudge-right="40"
                :return-value.sync="startTime"
                transition="scale-transition"
                offset-y
                max-width="290px"
                min-width="290px">
                  <template v-slot:activator="{ on, attrs }">
                    <v-text-field v-model="startTime" label="시작 시간"
                    prepend-icon="mdi-clock-time-four-outline"
                    readonly v-bind="attrs" v-on="on" :disabled="dateMode"/>
                  </template>
                    <v-time-picker v-if="startTimeMenu" v-model="startTime" full-width
                    @click:minute="$refs.startTimeMenu.save(startTime)"/>
                </v-menu>
              </v-col>

              <v-col cols="6" sm="6">
                <v-menu ref="endTimeMenu" v-model="endTimeMenu"
                :disabled="dateMode"
                :close-on-content-click="false"
                :nudge-right="40"
                :return-value.sync="endTime"
                transition="scale-transition"
                offset-y
                max-width="290px"
                min-width="290px">
                  <template v-slot:activator="{ on, attrs }">
                    <v-text-field v-model="endTime" label="종료 시간"
                    prepend-icon="mdi-clock-time-four-outline"
                    readonly v-bind="attrs" v-on="on" :disabled="dateMode"/>
                  </template>
                    <v-time-picker v-if="endTimeMenu" v-model="endTime" full-width
                    @click:minute="$refs.endTimeMenu.save(endTime)"/>
                </v-menu>
              </v-col>

              <v-col cols="12">
                <v-textarea v-model="content" label="상세내용" outlined rows="2" row-height="15"/>
              </v-col>
            <small>*indicates required field</small>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="close"> Close </v-btn>
          <v-btn color="blue darken-1" text @click="save"> Save </v-btn>
        </v-card-actions>
      </v-card>
      </v-form>
    </v-dialog>
</template>

<script>

  import axios from 'axios';

  export default {
    name: 'InsertDialog',

    data () {
      return {
        //form data(title, content, creator, team, startDt, endDt)
        title: null,
        content: null,
        creator: null,
        team: null,
        startDt: null,
        endDt: null,
        no : null,
        teams: ["결제플랫폼", "인증플랫폼", "서비스플랫폼", "Smart-X", "기가지니플랫폼", "미디어플랫폼"],
        dates: this.$store.state.selectedDate,
        dateMenu: false,
        startTimeMenu: false,
        endTimeMenu: false,
        dateMode: true,
        startTime: "00:00",
        endTime: "23:59",
        rules: {
          required: value => !!value || 'Required.',
        }
      }
    },
    methods: {
      //close event : clear form data and close dialog
      close: function(event) {
        this.title= null
        this.content= null
        this.creator= null
        this.team= null
        this.startTime= "00:00:00"
        this.endTime= "23:59:59"
        this.no=null
        //this.$validator.clear()
        //this.$store.state.insertDialogVisible = false
        this.$store.commit('setInsertDialogInvisible')
        this.$store.state.selectedEvent = ''
        this.$refs.form.resetValidation()
      },
      //save event : submit data using axios and close dialog
      save: function(event) {
        //alert(this.title + " " + this.content + " " + this.creator + " " + this.team + " ")
        //alert(this.dates[0] + " " + this.startTime + "/" + this.dates[1] + " " + this.endTime)
        
        this.$refs.form.validate()
        if(!this.$refs.form.validate())
          return
        //alert(this.$refs.form.validate())

        if(this.$store.state.selectedEvent == '') {
          //axios 
          //: insert calendar data
          axios.post('/insertCalen', {
            title: this.title,
            content: this.content,
            creator: this.creator,
            team: this.team,
            startDt: this.dates[0] + " " + this.startTime,
            endDt: this.dates[1] + " " + this.endTime
          })
          .then(function(response) {
            console.log(response);
            //alert('데이터삽입');
          })
          .catch(function(error) {
            console.log(error);
          })
        } else {
          //axios 
          //: update calendar data
          axios.put('/updateCalen', {
            seq_no: this.no,
            title: this.title,
            content: this.content,
            creator: this.creator,
            team: this.team,
            startDt: this.dates[0] + " " + this.startTime,
            endDt: this.dates[1] + " " + this.endTime
          })
          .then(function(response) {
            console.log(response);
            //alert('데이터수정');
          })
          .catch(function(error) {
            console.log(error);
          })
        }

        //this.$store.state.insertDialogVisible = false
        //this.$store.commit('setInsertDialogInvisible')
        //this.$store.state.selectedEvent = ''
        //this.close();
        //alert("REFRESH")
        location.reload()
      },
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
      setInsertDialog () {
        //alert(this.$store.state.insertDialogVisible)
        if(this.$store.state.insertDialogVisible) {
          this.dates = this.$store.state.selectedDate
          if(this.$store.state.selectedEvent != '') {
            console.log(this.$store.state.selectedEvent)
            this.title = this.$store.state.selectedEvent.title
            this.content = this.$store.state.selectedEvent.content
            this.creator = this.$store.state.selectedEvent.creator
            this.team = this.$store.state.selectedEvent.team
            this.startTime = this.$store.state.selectedEvent.startDt.substring(11, 19)
            this.endTime = this.$store.state.selectedEvent.endDt.substring(11,19)
            this.no = this.$store.state.selectedEvent.no
          }
        }
        return this.$store.state.insertDialogVisible
      }
    },
    watch: {
      dateMode: function(newVal, oldVal) {
        if(newVal == true) {
          this.startTime = "00:00"
          this.endTime = "23:59"
        }
      },
    },
  }
</script>
