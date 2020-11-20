<template>
  <div>
    <v-sheet tile height="60" class="d-flex align-content-start">
      <v-btn outlined class="mx-4" color="grey darken-2" @click="setToday">
        Today
      </v-btn>
      <v-btn text color="grey darken-2" @click="prev">
        <v-icon>mdi-chevron-left</v-icon>
      </v-btn>
      <span class ="headline" v-if="$refs.calendar">
        {{ $refs.calendar.title }}
      </span>
      <v-btn text color="grey darken-2" @click="next">
        <v-icon>mdi-chevron-right</v-icon>
      </v-btn>
      <v-menu bottom right>
        <template v-slot:activator="{ on, attrs }">
          <v-btn class="ml-auto mx-2" outlined color="grey darken-2" v-bind="attrs" v-on="on">
            <span>{{ type }}</span>
            <v-icon right> mdi-menu-down </v-icon>
          </v-btn>
        </template>
        <v-list>
          <v-list-item @click="type = 'day'">
            <v-list-item-title>Day</v-list-item-title>
          </v-list-item>
          <v-list-item @click="type = 'week'">
            <v-list-item-title>Week</v-list-item-title>
          </v-list-item>
          <v-list-item @click="type = 'month'">
            <v-list-item-title>Month</v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>
    </v-sheet>
    <v-sheet height="600">
      <v-calendar
      ref="calendar"
      v-model="value"
      :type="type"
      :weekdays="weekday"
      :events="events"
      :event-overlap-mode="mode"
      :event-overlap-threshold="50"
      :event-color="getEventColor"
      @click:event="showEvent"
      @click:date="insertCal"
      @change="getEvents">
      </v-calendar>
      <!-- selectOpen Dialog -->
      <v-menu offset-x v-model="selectedOpen"
      :close-on-content-click="false"
      :activator="selectedElement">
        <v-card color="grey lighten-4" min-width="350px" flat>
          <v-toolbar :color="selectedEvent.color" dark>
            <v-toolbar-title v-html="selectedEvent.team"></v-toolbar-title>
            <v-spacer></v-spacer>
            <!-- modify -->
            <v-btn icon @click="updateCalen">
              <v-icon>mdi-pencil</v-icon>
            </v-btn>
            <!-- delete -->
            <v-btn icon @click="deleteCalen">
              <v-icon>mdi-trash-can</v-icon>
            </v-btn>
          </v-toolbar>
          <v-card-text v-model="selectedContentModify">
            <div>({{selectedEvent.startDt}} ~ {{selectedEvent.endDt}})</div>
            <div class="mt-1 subtitle-1 text--primary">
              일정이름: {{selectedEvent.title}}
            </div>
            <div class="mt-3 subtitle-2 text--primary">
              작성자: {{selectedEvent.creator}}
            </div>
            <div class="subtitle-2 text--primary">
              상세내용: {{selectedEvent.content}}
            </div>
          </v-card-text>
          <v-card-actions>
            <v-btn class="ml-auto" text color="secondary" @click="selectedOpen = false">
              Cancel
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-menu>
      
    </v-sheet>
  </div>
</template>

<script>

  import axios from 'axios';

  export default {

    data: () => ({
      mode: 'stack',
      type: 'month',
      types: ['month', 'week', 'day'],
      modes: ['stack', 'column'],
      weekday: [0, 1, 2, 3, 4, 5, 6],
      value: '',
      events: [],
      colors: ['blue', 'indigo', 'deep-purple', 'cyan', 'green', 'orange', 'grey darken-1'],
      names: ['Meeting', 'Holiday', 'PTO', 'Travel', 'Event', 'Birthday', 'Conference', 'Party'],
      selectedEvent: {},
      selectedElement: null,
      selectedContentModify: false,
      selectedOpen: false,
    }),
    methods: {
      // 월별 | 주별 | 일별로 한 화면에 보이는 기간 (start , end)
      // 기간으로 조회한 일정 내용을 events에 담기
      getEvents ({ start, end }) {
        const events = []
        const colors = this.colors
        const startDt =start.date.replaceAll("-","") + '000000'
        const endDt = end.date.replaceAll("-","") + '235959'

        //axios 
        //: minDt, maxDt 를 post로 API 호출
        //일정 name, startDt, endDt, color
        axios.post('/selectCalen', {startDt: startDt, endDt: endDt})
        .then(function(response) {
          console.log(response);
          //alert(startDt + " " + endDt + " " + response.data.length);
          for(let i = 0; i< response.data.length; i++) {
            events.push({
              name: response.data[i].team + " " + response.data[i].title,
              start: new Date(response.data[i].startDt),
              end: new Date(response.data[i].endDt),
              startDt: response.data[i].startDt,
              endDt: response.data[i].endDt,
              no: response.data[i].seq_no,
              content: response.data[i].content,
              creator: response.data[i].creator,
              title: response.data[i].title,
              team: response.data[i].team,
              color: colors[Math.floor((colors.length - 2) * Math.random())],
              //false이면 시간은 표시 x
              timed: true
            })
          }
        })
        .catch(function(error) {
          console.log(error);
        })
        this.events = events
      },
      insertCal ({ date }) {
        //alert(date);
        this.$store.commit('setInsertDialogVisible', [date, date])
      },
      setToday () {
        this.value = ''
      },
      prev () {
        this.$refs.calendar.prev()
      },
      next () {
        this.$refs.calendar.next()
      },
      getEventColor (event) {
        return event.color
      },
      showEvent ({ nativeEvent, event }) {
        //alert(nativeEvent.target)
        const open = () => {
          this.selectedEvent = event
          this.selectedElement = nativeEvent.target
          setTimeout(() => {
            this.selectedOpen = true
          }, 10)
        }

        if (this.selectedOpen) {
          this.selectedOpen = false
          setTimeout(open, 10)
        } else {
          open()
        }

        nativeEvent.stopPropagation()
      },
      deleteCalen() {
        
        //axios 
        //: delete calendar data
        //alert(this.selectedEvent.no)
        console.log(this.selectedEvent)
        axios.delete('/deleteCalen/' + this.selectedEvent.no)
        .then(function(response) {
          console.log(response);
          //alert('삭제');
        })
        .catch(function(error) {
          console.log(error);
        })

        //after axios --> close dialog / refresh (getEvents)
        console.log(this.$refs.calendar)
        this.selectedOpen = false
        const start = this.$refs.calendar.lastStart
        const end = this.$refs.calendar.lastEnd
        this.getEvents({start, end})
      },
      updateCalen() {
        //alert("update")
        this.selectedOpen = false
        this.$store.commit('setInsertDialogVisible', [this.selectedEvent.startDt.substring(0, 10), this.selectedEvent.endDt.substring(0,10)])
        this.$store.commit('setSelectedEvent', this.selectedEvent)
        console.log(this.selectedEvent)
      }
    },
  }
</script>
