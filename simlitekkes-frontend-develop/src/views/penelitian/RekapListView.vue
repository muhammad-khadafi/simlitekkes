<template>
  <v-container fluid>
    <div v-if="validated == true">
      <v-data-table
        :headers="headers"
        :items="listRekap"
        class="elevation-1"
        :loading="isLoading"
        loading-text="Loading... Please wait"
        :search="search"
      >
        <template v-slot:top>
          <v-toolbar>
            <v-toolbar-title>Rekap Usulan</v-toolbar-title>
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
            class="pl-0"
            small
            color="blue darken-1"
            text
            @click="showItem(item)"
          >
            Lihat Usulan
          </v-btn>
        </template>
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
    <div v-if="validated == false">
      <v-card>
        <v-toolbar>
          <v-toolbar-title>Rekap Usulan</v-toolbar-title>
        </v-toolbar>
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
  import { apiServer } from '../../setup-axios'

  export default {
    components: {
      Page403: () => import('@/views/403.vue')
    },
    data: () => ({
      search: '',
      snackbar: false,
      snackbarText: '',
      snackbarColor: '',
      isLoading: true,
      dialog: false,
      headers: [
        { text: 'Skema', value: 'namaSkema' },
        { text: 'Tahun Usulan', value: 'tahunUsulan' },
        { text: 'Tahun Pelaksanaan', value: 'tahunPelaksanaan' },
        { text: 'Jumlah Usulan', value: 'jumlahUsulan' },
        { text: '', value: 'action', sortable: false }
      ],
      listRekap: [],
      ketuaTimPeneliti: [],
      validated: null,
    }),

    watch: {
      dialog (val) {
        val || this.close()
      }
    },

    created () {
      this.getData()
    },

    methods: {
      getData () {
        this.isLoading = true
        console.log(this.isLoading)
        var idOrganisasi = this.$store.state.user.role.roleCode !== 'PENGELOLA_PUSAT' ? this.$store.state.user.organizationId : 1
        console.log('idOrganisasi,', idOrganisasi)
        var roleCode = this.$store.state.user.role.roleCode
        apiServer
          .get('/validasi/' + roleCode + '/' + idOrganisasi)
          .then(validateResponse => {
            this.validated = validateResponse.data
            if (this.validated) {
              apiServer
                .get('/penelitian/getRekap/' + idOrganisasi)
                .then(response => {
                  this.listRekap = response.data
                  console.log('response:', response.data)
                  this.isLoading = false
                })
                .catch(error => {
                  console.log('There was an error:', error.response) // Logs out the error
                  this.isLoading = false
                })
            }
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
            this.isLoading = false
          })
      },
      showItem (item) {
        console.log('item:', item)
        this.$store.dispatch('setRekapPenelitian', item)
        console.log('rekapPenelitian', this.$store.state.rekapPenelitian)
        this.$router.replace('/penelitian/usulan/' + item.idSkemaPenelitian + '/' + item.tahunUsulan)
      },
      showSnackbar (color, message) {
        console.log('snack ' + color + ' - ' + message)
        this.snackbar = true
        this.snackbarText = message
        this.snackbarColor = color
      }
    }
  }
</script>
