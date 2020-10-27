<template>
  <div>
    <v-btn outlined class="ma-4" color="grey darken-2" @click="setToday">
      Today
    </v-btn>
    <v-btn text color="grey darken-2" @click="prev">
      <
    </v-btn>
    <span class ="headline" v-if="$refs.calendar">
      {{ $refs.calendar.title }}
    </span>
    <v-btn text color="grey darken-2" @click="next">
      >
    </v-btn>

    <v-calendar
    ref="calendar"
    v-model="value"
    :type=$store.state.calType
    :weekdays="weekday"
    :events="events"
    :event-overlap-mode="mode"
    :event-overlap-threshold="50"
    :event-color="getEventColor"
    @click:date="insertCal"
    @change="getEvents">
    </v-calendar>
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
    }),
    methods: {
      // 월별 | 주별 | 일별로 한 화면에 보이는 기간 (start , end)
      // 기간으로 조회한 일정 내용을 events에 담기
      getEvents ({ start, end }) {

        //alert(start.date + " " + end.date);
        const min = new Date(`${start.date}T00:00:00`)
        const max = new Date(`${end.date}T23:59:59`)
        const startDt =start.date.replaceAll("-","") + '000000'
        const endDt = end.date.replaceAll("-","") + '235959'

        const events = []
        const days = (max.getTime() - min.getTime()) / 86400000
        const eventCount = this.rnd(days, days + 20)

        const colors = this.colors
        //axios 
        //: minDt, maxDt 를 post로 API 호출
        //일정 name, startDt, endDt, color
        // 
        axios.post('/selectCalen', {startDt: startDt, endDt: endDt})
        .then(function(response) {
          console.log(response);
          alert(startDt + " " + endDt + " " + response.data.length);
          for(let i = 0; i< response.data.length; i++) {
            events.push({
              name: response.data[i].title,
              start: new Date(response.data[i].startDt),
              end: new Date(response.data[i].endDt),
              color: colors[Math.floor((colors.length - 2) * Math.random())],
              timed: true
            })
          }



        })
        .catch(function(error) {
          console.log(error);
        })

        // for (let i = 0; i < eventCount; i++) {
        //   const allDay = this.rnd(0, 3) === 0
        //   const firstTimestamp = this.rnd(min.getTime(), max.getTime())
        //   const first = new Date(firstTimestamp - (firstTimestamp % 900000))
        //   const secondTimestamp = this.rnd(2, allDay ? 288 : 8) * 900000
        //   const second = new Date(first.getTime() + secondTimestamp)

        //   events.push({
        //     name: this.names[this.rnd(0, this.names.length - 1)],
        //     start: first,
        //     end: second,
        //     color: this.colors[this.rnd(0, this.colors.length - 1)],
        //     timed: true,
        //   })
        // }

        this.events = events
      },
      getEventColor (event) {
        
        return event.color
      },
      rnd (a, b) {
        return Math.floor((b - a + 1) * Math.random()) + a
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
    },
  }
</script>
