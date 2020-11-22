<template>
    <v-app-bar app color="teal lighten-3" dark hide-on-scroll>
      <div class="d-flex align-center">
        <v-img
          alt="Vuetify Logo"
          class="shrink mr-2"
          contain
          src="../assets/logo.png"
          transition="scale-transition"
          width="70"
        />
		    <h2 class="font-weight-bold pt-2">
          운영캘린더
        </h2>
      </div>
    </v-app-bar>
</template>

<script>
  export default {
    name: 'MainAppbar',

    data: () => ({
      
    }),
  }
</script>
