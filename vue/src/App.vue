<template>
  <v-app>
    <v-main>
    	<MainAppbar/>
      <MainHeader/>
		  <Calendar/>
      <InsertDialog/>
    </v-main>
  </v-app>
</template>

<script>
import MainAppbar from './components/MainAppbar';
import MainHeader from './components/MainHeader';
import InsertDialog from './components/InsertDialog';
import Calendar from './components/Calendar';

export default {
  name: 'App',

  components: {
    MainAppbar,
    MainHeader,
    InsertDialog,
    Calendar
  },

  data: () => ({
  })
};
</script>
