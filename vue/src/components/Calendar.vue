<template>
  <div>
    <v-btn outlined class="ma-4" color="grey darken-2" @click="setToday">
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
    <v-sheet height="600">
      <v-calendar
      ref="calendar"
      v-model="value"
      :type=$store.state.calType
      :weekdays="weekday"
      :events="events"
      :event-overlap-mode="mode"
      :event-overlap-threshold="50"
      :event-color="getEventColor"
      @click:event="showEvent"
      @click:date="insertCal"
      @change="getEvents">
      </v-calendar>
      <v-menu offset-x v-model="selectedOpen"
      :close-on-content-click="false"
      :activator="selectedElement">
        <v-card color="grey lighten-4" min-width="350px" flat>
          <v-toolbar :color="selectedEvent.color" dark>
            <v-toolbar-title v-html="selectedEvent.name"></v-toolbar-title>
            <v-spacer></v-spacer>
            <v-btn icon @click="deleteCalen">
              <v-icon>mdi-trash-can</v-icon>
            </v-btn>
          </v-toolbar>
          <v-card-text>
            <span v-html="selectedEvent.details"></span>
          </v-card-text>
          <v-card-actions>
            <v-btn text color="secondary" @click="selectedOpen = false">
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
      modes: ['stack', 'column'],
      weekday: [0, 1, 2, 3, 4, 5, 6],
      value: '',
      events: [],
      colors: ['blue', 'indigo', 'deep-purple', 'cyan', 'green', 'orange', 'grey darken-1'],
      names: ['Meeting', 'Holiday', 'PTO', 'Travel', 'Event', 'Birthday', 'Conference', 'Party'],
      selectedEvent: {},
      selectedElement: null,
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
          alert(startDt + " " + endDt + " " + response.data.length);
          for(let i = 0; i< response.data.length; i++) {
            events.push({
              name: response.data[i].team + " " + response.data[i].title,
              start: new Date(response.data[i].startDt),
              end: new Date(response.data[i].endDt),
              no: response.data[i].seqNo,
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
        alert(date);
        this.$store.commit('setTrue')
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
        alert("delete")
        this.selectedOpen = false
        const start = this.$refs.calendar.parsedStart
        const end = this.$refs.calendar.parsedEnd
        this.getEvents({start, end})
      }
    },
  }
</script>
