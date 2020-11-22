<template>
  <v-app>
    <v-main>
    	<MainAppbar/>
      <MainHeader/>
      <v-card elevation="11" outlined max-width="95%" class="mx-auto pa-4 mb-10">
		    <Calendar/>
      </v-card>
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
