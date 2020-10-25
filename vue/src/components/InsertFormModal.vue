<template>
  <div>
    <v-row
      justify="center"
    >
      <v-btn
        color="primary"
        class="ma-2"
        dark
        @click="dialog = true"
      >
        추가하기
      </v-btn>
      <v-dialog
        v-model="dialog"
        hide-overlay
        transition="dialog-bottom-transition"
      >
        <v-card tile>
          <v-toolbar
            flat
            dark
            color="primary"
          >
            <v-toolbar-title>추가하기</v-toolbar-title>
            <v-spacer></v-spacer>
            <v-toolbar-items>
              <v-btn
                dark
                text
                @click="dialog = false"
              >
                Cancel
              </v-btn>
              <v-btn
                dark
                text
                @click="dialog = false"
              >
                Save
              </v-btn>
            </v-toolbar-items>
          </v-toolbar>
          <v-card-text>
            <InsertFormModalContent/>
          </v-card-text>

        </v-card>
      </v-dialog>

      
    </v-row>
  </div>
</template>

<script>
import InsertFormModalContent from './InsertFormModalContent';

  export default {
    name: 'InsertFormModal',
    
    components: {
    InsertFormModalContent,
  	},

    data () {
      return {
        dialog: false,
      }
    },
  }
</script>
