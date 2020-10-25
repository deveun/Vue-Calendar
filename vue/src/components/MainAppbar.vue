<template>
    <v-app-bar app color="blue-grey" dark hide-on-scroll>
      <div class="d-flex align-center">
        <v-img
          alt="Vuetify Logo"
          class="shrink mr-2"
          contain
          src="https://cdn.vuetifyjs.com/images/logos/vuetify-logo-dark.png"
          transition="scale-transition"
          width="40"
        />
		<h1 class="font-weight-bold">
          kt ds
        </h1>
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
