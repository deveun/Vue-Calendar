import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    insertDialogVisible : false,
    selectedDate : [],
    selectedEvent: '',
    selectedTeams: ["결제플랫폼", "인증플랫폼", "서비스플랫폼", "Smart-X", "기가지니플랫폼", "미디어플랫폼"]
  },
  mutations: {
    setInsertDialogVisible(state, payload) {
      state.insertDialogVisible = true
      state.selectedDate = payload
    },
    setInsertDialogInvisible(state) {
      state.insertDialogVisible = false
    },
    setSelectedEvent(state, payload) {
      state.selectedEvent = payload
    },
    setSelectedTeams(state, payload) {
      state.selectedTeams = payload
    }
  },
  actions: {
  },
  modules: {
  }
})
