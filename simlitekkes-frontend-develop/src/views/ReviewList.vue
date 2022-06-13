<template>
  <v-container>
    <material-card v-if="hasAccess == false">
      <page403 />
    </material-card>
    <div v-if="hasAccess">
      <v-data-table
        :headers="headers"
        :items="listReview"
        sort-by="judul"
        class="elevation-1"
        :loading="isLoading"
        loading-text="Loading... Please wait"
        :search="search"
      >
        <template v-slot:item.alert="{ item }">
          <v-chip
            :color="getColor(item)"
            width="2000"
            dark
          >
            <span style="display:inline-block; width: 30px;"></span>
          </v-chip>
        </template>
        <template v-slot:top>
          <v-toolbar>
            <v-toolbar-title>Daftar Review</v-toolbar-title>
          </v-toolbar>
          <v-divider />
          <v-toolbar
            flat
            class="mt-1"
          >
            <v-text-field
              v-model="search"
              append-icon="mdi-magnify"
              label="Search"
              single-line
              hide-details
              class="search"
            />
            <div class="flex-grow-1" />
          </v-toolbar>
        </template>
        <template v-slot:item.action="{ item }">
          <v-btn
            small
            color="blue"
            text
            @click="detailUsulan(item)"
          >
            Detail Usulan
          </v-btn>
        </template>
        <template v-slot:no-data />
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

  import { apiServer } from '../setup-axios'
  import moment from 'moment'

  export default {
    data: () => ({
      search: '',
      snackbar: false,
      snackbarText: '',
      snackbarColor: '',
      isLoading: true,
      menu: false,
      dialog: false,
      listReview: [],
      currentTime: new Date(),
      hasAccess: null,
      headers: [
        { text: 'Judul Usulan', value: 'judul' },
        { text: 'Institusi', value: 'institusi' },
        { text: 'Tahun Usulan', value: 'tahunUsulan' },
        { text: 'Tanggal Terima Status', value: 'tanggalTerimaF' },
        { text: 'Tanggal Selesai Status', value: 'tanggalSelesaiF' },
        { text: 'Status', value: 'status' },
        { text: '', value: 'alert' },
        { text: 'Aksi', align: 'center', value: 'action', sortable: false },
      ],
    }),

    created () {
      apiServer
        .get('/validasi/checkUserIsReviewer/' + this.$store.state.user.userId + '/' + this.$store.state.user.role.roleCode)
        .then(response => {
          console.log('isReviewer/has penugasan: ', response.data)
          if (response.data) {
            this.hasAccess = true
            this.getData()
          } else {
            this.hasAccess = false
          }
        })
        .catch(error => {
          console.log('There was an error:', error.response) // Logs out the error
          this.isLoading = false
        })
    },

    components: {
      Page403: () => import('@/views/403.vue'),
    },

    methods: {
      getData () {
        apiServer
          .get('/review/' + this.$store.state.user.userId + '/' + (new Date()).getFullYear())
          .then(response => {
            this.listReview = response.data
            this.listReview.forEach(this.formattingDate)
            console.log('list review:', response.data) // For now, logs out the response
            this.isLoading = false
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
            this.isLoading = false
          })
      },
      detailUsulan (item) {
        this.$router.push('/penelitian/' + item.id + '/view-usulan')
      },
      formattingDate (item) {
        item.tanggalTerimaF = item.tanggalTerima
          ? moment(item.tanggalTerima).format('DD-MM-YYYY')
          : ''
        item.tanggalSelesaiF = item.tanggalSelesai
          ? moment(item.tanggalSelesai).format('DD-MM-YYYY')
          : ''
      },
      showSnackbar (color, message) {
        console.log('snack ' + color + ' - ' + message)
        this.snackbar = true
        this.snackbarText = message
        this.snackbarColor = color
      },
      getColor (item) {
        var terima2 = new Date(item.tanggalTerima)
        terima2.setDate(terima2.getDate() + 2)
        var terima5 = new Date(item.tanggalTerima)
        terima5.setDate(terima5.getDate() + 5)
        var terima7 = new Date(item.tanggalTerima)
        terima7.setDate(terima7.getDate() + 7)
        if (this.currentTime < terima2) {
          return 'green'
        } else if (this.currentTime < terima5) {
          return 'yellow'
        } else if (this.currentTime < terima7) {
          return 'red'
        } else {
          return 'black'
        }
      },
    },
  }
</script>

<style lang="scss">
  // .search{
  //   margin-left: 20%;
  // }
</style>
