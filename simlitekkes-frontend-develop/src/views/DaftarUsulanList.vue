<template>
  <v-container fluid>
    <div v-if="hasAccess == true">
      <v-data-table
        :headers="headers"
        :items="listUsulan"
        sort-by="namaKategori"
        class="elevation-1"
        :loading="isLoading"
        loading-text="Loading... Please wait"
        :search="search"
      >
        <template v-slot:top>
          <v-toolbar>
            <v-toolbar-title>Daftar Usulan</v-toolbar-title>
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

        <template
          v-slot:item.action="{ item }"
          style="width: 10%"
        >
          <v-btn
            v-if="item.isEdit"
            class="pl-0"
            small
            color="blue darken-1"
            text
            @click="editItem(item)"
          >
            Ubah
          </v-btn>
          <v-btn
            v-if="!item.isEdit"
            class="pl-0"
            small
            color="blue darken-1"
            text
            @click="showItem(item)"
          >
            Detail
          </v-btn>
        </template>
      </v-data-table>

      <v-snackbar
        v-model="snackbar"
        :color="snackbarColor"
        :timeout="9000"
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
    <div v-if="hasAccess == false">
      <v-card>
        <v-row>
          <v-col class="align">
            <page403 />
          </v-col>
        </v-row>
      </v-card>
    </div>
  </v-container>
</template>

<script>
  import { apiServer } from '../setup-axios'
  import moment from 'moment'

  export default {
    components: {
      Page403: () => import('@/views/403.vue'),
    },
    data: () => ({
      search: '',
      snackbar: false,
      snackbarText: '',
      snackbarColor: '',
      isLoading: true,
      menu: false,
      dialog: false,
      listUsulan: [],
      headers: [
        {
          text: 'Judul Usulan',
          align: 'left',
          sortable: false,
          value: 'judul',
        },
        { text: 'Tahun Usulan', value: 'tahunUsulan' },
        { text: 'Skema', value: 'namaSkema' },
        { text: 'Status', value: 'status' },
        { text: 'Tanggal Terima Status', value: 'tanggalTerimaF' },
        { text: 'Tanggal Deadline Status', value: 'tanggalDeadlineF' },
        { text: 'Aksi', value: 'action' },
      ],
      hasAccess: null,
    }),

    created () {
      if (this.$store.state.status.snackbarText != null) {
        this.showMessage()
      }
      this.getDataUsulan()
    },

    methods: {
      getDataUsulan () {
        this.isLoading = true
        console.log(this.isLoading)
        apiServer
          .get('/validasi/isPoltekkes/' + this.$store.state.user.organizationId)
          .then(response => {
            var isPoltekkes = response.data
            var isDosen = this.$store.state.user.role.roleCode === 'DOSEN'
            this.hasAccess = !!(isPoltekkes && isDosen)
            if (this.hasAccess) {
              apiServer
                .get('/usulanList/' + this.$store.state.user.userId)
                .then(response1 => {
                  this.listUsulan = response1.data
                  this.listUsulan.forEach(this.completeData)
                  console.log('list:', this.listUsulan)
                  this.isLoading = false
                })
                .catch(error => {
                  console.log('There was an error:', error.response) // Logs out the error
                  this.isLoading = false
                })
            } else {
              this.isLoading = false
            }
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
            this.isLoading = false
          })
      },
      completeData (item) {
        item.tanggalTerimaF = item.tanggalTerima
          ? moment(item.tanggalTerima).format('DD-MM-YYYY')
          : ''
        item.tanggalDeadlineF = item.tanggalDeadline
          ? moment(item.tanggalDeadline).format('DD-MM-YYYY')
          : ''
        item.isEdit = item.kodeStatus === 'DRAFT' || item.kodeStatus === 'DRAFT_REVISION'
      },
      showItem (item) {
        this.$router.replace('/penelitian/' + item.id + '/view-usulan')
      },
      editItem (item) {
        this.$router.replace('/penelitian/' + item.id + '/identitas-usulan')
      },
      showSnackbar (color, message) {
        console.log('snack ' + color + ' - ' + message)
        this.snackbar = true
        this.snackbarText = message
        this.snackbarColor = color
      },
      showMessage () {
        this.showSnackbar(
          this.$store.state.status.snackbarColor,
          this.$store.state.status.snackbarText
        )
        this.$store.dispatch('setStatus', {
          snackbarColor: null,
          snackbarText: null,
        })
      },
    },
  }
</script>
