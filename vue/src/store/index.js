import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    insertDialogVisible : false,
    selectedDate : [],
    selectedEvent: ''
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
    } 
  },
  actions: {
  },
  modules: {
  }
})
