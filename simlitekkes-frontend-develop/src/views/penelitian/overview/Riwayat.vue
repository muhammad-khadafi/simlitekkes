<template>
  <v-container fluid>
    <div>
      <v-data-table
        :headers="headers"
        :items="listWorkflow"
        class="elevation-1"
        :loading="isLoading"
        loading-text="Loading... Please wait"
      >
      </v-data-table>

      <v-snackbar
        v-model="snackbar"
        :color="snackbarColor"
        :timeout="2000"
        :top="true"
      >
        {{ snackbarText }}
        <v-btn
          dark
          text
          @click="snackbar = false"
        >
          Close
        </v-btn>
      </v-snackbar>
    </div>
  </v-container>
</template>

<script>
  import { apiServer } from '../../../setup-axios'
  import moment from 'moment'

  export default {
    data: () => ({
      snackbar: false,
      snackbarText: '',
      snackbarColor: '',
      isLoading: true,
      listWorkflow: [],
      headers: [
        { text: 'Status', value: 'status', sortable: false },
        { text: 'Tanggal Pembaruan', value: 'timestampUpdate', sortable: false }
      ],
      hasAccess: false
    }),

    created () {
      this.getData()
    },

    methods: {
      getData () {
        this.isLoading = true
        apiServer
          .get('/workflow-penelitian/' + this.$route.params.id)
          .then(response => {
            this.listWorkflow = response.data
            this.listWorkflow.forEach(this.formattingDate)
            this.isLoading = false
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
            this.isLoading = false
          })
      },
      showSnackbar (color, message) {
        console.log('snack ' + color + ' - ' + message)
        this.snackbar = true
        this.snackbarText = message
        this.snackbarColor = color
      },
      formattingDate (value) {
        value.timestampUpdate = value.timestampUpdate
          ? moment(value.timestampUpdate).format('DD-MM-YYYY HH:mm:ss')
          : ''
      },
    },
  }
</script>
