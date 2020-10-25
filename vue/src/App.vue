<template>
  <v-app>
    <v-main>
    	<MainAppbar/>
      <MainHeader/>
    	<InsertFormModal/>
		  <Calendar/>
    </v-main>
  </v-app>
</template>

<script>
import MainAppbar from './components/MainAppbar';
import MainHeader from './components/MainHeader';
import InsertFormModal from './components/InsertFormModal';
import Calendar from './components/Calendar';

export default {
  name: 'App',

  components: {
    MainAppbar,
    MainHeader,
    InsertFormModal,
    Calendar
  },

  data: () => ({
  })
};
</script>
