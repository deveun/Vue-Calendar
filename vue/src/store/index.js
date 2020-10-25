import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    calType : 'month',
    insertFormModalState : true
  },
  mutations: {
    setCalType(state, payload) {
      state.calType = payload
    },
    setTrue(state) {
      state.insertFormModalState = true
    }
  },
  actions: {
  },
  modules: {
  }
})
